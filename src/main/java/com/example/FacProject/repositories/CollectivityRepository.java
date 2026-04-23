package com.example.FacProject.repositories;

import com.example.FacProject.config.DataSource;
import com.example.FacProject.dto.*;
import com.example.FacProject.entities.PaymentModeEnum;
import com.example.FacProject.exceptions.NotFoundException;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@Repository
public class CollectivityRepository {
    private final DataSource dataSource;;

    public CollectivityRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String create(CreateCollectivityDTO requests) {
        String sql = """
            INSERT INTO collectivities (id, location, federation_approval)
            VALUES (?, ?, ?)
        """;
        try (Connection connection = dataSource.getConnection()) {
            try(PreparedStatement ps = connection.prepareStatement(sql)){
                connection.setAutoCommit(false);
                String id = UUID.randomUUID().toString().substring(0, 9);
                ps.setString(1, id);
                ps.setString(2, requests.getLocation());
                ps.setBoolean(3, requests.getFederationApproval());
                ps.executeUpdate();
                connection.commit();
                return id;
            }catch(SQLException ex){
                connection.rollback();
                throw ex;
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void assignNamAndNumber(String id,CreateCollectivityInformationsDTO request){
        String sql = """
                UPDATE collectivities
                SET name = ?, number = ?
                WHERE id = ?;
                """;
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            try(PreparedStatement stmt = connection.prepareStatement(sql)){
                stmt.setString(1, request.getName());
                stmt.setInt(2, request.getNumber());
                stmt.setString(3, id);

                int rows = stmt.executeUpdate();

                if (rows == 0) {
                    throw new RuntimeException("Assignment failed (already assigned or not found)");
                }
                connection.commit();
            }catch (Exception e) {
                connection.rollback();
                throw e;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean isExist(String collectivityId) {
        String sql = """
                select id from collectivities where id = ?;
                """;
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, collectivityId);
            ResultSet rs = stmt.executeQuery();
            if(!rs.next()){
                return false;
            }
            return true;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }
    public Boolean isExistByNameAndNumber(String name, Integer number) {
        String sql = """
                select id from collectivities where name = ? and number = ?
                """;
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setInt(2, number);
            ResultSet rs = stmt.executeQuery();
            if(!rs.next()){
                return false;
            }
            return true;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    public List<CollectivityTransactionDTO> findTransactions(
            String collectivityId,
            LocalDate from,
            LocalDate to
    ) {

        String sql = """
        SELECT id, creation_date, amount, payment_mode, member_id
        FROM transactions
        WHERE account_id = ?
        AND creation_date BETWEEN ? AND ?
    """;

        List<CollectivityTransactionDTO> list = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, collectivityId);
            stmt.setDate(2, java.sql.Date.valueOf(from));
            stmt.setDate(3, java.sql.Date.valueOf(to));

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                CollectivityTransactionDTO dto = new CollectivityTransactionDTO();

                dto.setId(rs.getString("id"));
                dto.setCreationDate(rs.getDate("creation_date").toLocalDate());
                dto.setAmount(rs.getDouble("amount"));

                dto.setPaymentMode(
                        PaymentModeEnum.valueOf(rs.getString("payment_mode"))
                );

                dto.setMemberDebitedId(rs.getString("member_id"));

                list.add(dto);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
    }
    public void saveTransactionFromPayment(String memberId, int amount, PaymentModeEnum mode) {

        String sql = """
        INSERT INTO transactions (
            id, account_id, member_id, amount, payment_mode, creation_date
        )
        SELECT ?, m.collectivity_id, ?, ?, ?::payment_mode, CURRENT_DATE
        FROM members m
        WHERE m.id = ?
    """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, UUID.randomUUID().toString().substring(0, 9));
            stmt.setString(2, memberId);
            stmt.setInt(3, amount);
            stmt.setString(4, mode.name());
            stmt.setString(5, memberId);

            stmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<FinancialAccountDTO> getFinancialAccounts(String collectivityId, LocalDate at) {

        String sql = """
        SELECT id, account_type, holder_name, service, mobile_number,
               bank_name, bank_code, branch_code, account_number, account_key,
               amount
        FROM financial_accounts
        WHERE collectivity_id = ?
    """;

        List<FinancialAccountDTO> accounts = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, collectivityId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                String type = rs.getString("account_type");

                switch (type) {

                    case "CASH" -> {
                        CashAccountDTO dto = new CashAccountDTO();
                        dto.setId(rs.getString("id"));
                        dto.setAmount(rs.getDouble("amount"));
                        accounts.add(dto);
                    }

                    case "MOBILE" -> {
                        MobileBankingAccountDTO dto = new MobileBankingAccountDTO();
                        dto.setId(rs.getString("id"));
                        dto.setAmount(rs.getDouble("amount"));
                        dto.setHolderName(rs.getString("holder_name"));
                        dto.setMobileBankingService(rs.getString("service"));
                        dto.setMobileNumber(rs.getLong("mobile_number"));
                        accounts.add(dto);
                    }

                    case "BANK" -> {
                        BankAccountDTO dto = new BankAccountDTO();
                        dto.setId(rs.getString("id"));
                        dto.setAmount(rs.getDouble("amount"));
                        dto.setHolderName(rs.getString("holder_name"));
                        dto.setBankName(rs.getString("bank_name"));
                        dto.setBankCode(rs.getInt("bank_code"));
                        dto.setBankBranchCode(rs.getInt("branch_code"));
                        dto.setBankAccountNumber(rs.getInt("account_number"));
                        dto.setBankAccountKey(rs.getInt("account_key"));
                        accounts.add(dto);
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return accounts;
    }
    public Optional<GetCollectivityDTO> findById(String id) {
        String sql = """
                select id ,
                    name,
                    number,
                    location from collectivities where id = ?
                """;
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            GetCollectivityDTO getCollectivityDTO = null;
            if(rs.next()){
                getCollectivityDTO = new GetCollectivityDTO();
                CreateCollectivityInformationsDTO info = new CreateCollectivityInformationsDTO();
                info.setName(rs.getString("name"));
                info.setNumber(rs.getInt("number"));
                getCollectivityDTO.setInformation(info);
                getCollectivityDTO.setId(rs.getString("id"));
                getCollectivityDTO.setLocation(rs.getString("location"));
                getCollectivityDTO.setMembers(findAllMembersById(id));
                Optional<CollectivityStructureDTO> structureDTO = findStructureById(id);
                if(structureDTO.isPresent()){
                    getCollectivityDTO.setStructure(structureDTO.get());
                }
                else{
                    throw new NotFoundException("Collectivity structure not found");
                }
            }
            return Optional.ofNullable(getCollectivityDTO);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public List<MemberDTO> findAllMembersById(String id) {
        String memberSql = """ 
SELECT id, 
                                        first_name, 
                                        last_name,
                                        birth_date,
                                        gender,
                                        address,
                                        profession,
                                        phone_number,
                                        email,
                                        occupation
                                        FROM members WHERE collectivity_id = ? 
                                        """;
        String refereesSql = "SELECT referee_id FROM member_referees WHERE member_id = ?";

        try (Connection connection = dataSource.getConnection()) {
            List<MemberDTO> memberDTOList = new ArrayList<>();
            MemberDTO dto = null;
            String memberId = null;
            try (PreparedStatement pstmt = connection.prepareStatement(memberSql)) {
                pstmt.setString(1, id);
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        dto = new MemberDTO();
                        memberId = rs.getString("id");
                        dto.setId(memberId);
                        dto.setFirstName(rs.getString("first_name"));
                        dto.setLastName(rs.getString("last_name"));

                        Date bDate = rs.getDate("birth_date");
                        if (bDate != null) {
                            dto.setBirthDate(bDate.toLocalDate());
                        };
                        dto.setGender(rs.getString("gender"));
                        dto.setAddress(rs.getString("address"));
                        dto.setProfession(rs.getString("profession"));
                        dto.setPhoneNumber(rs.getLong("phone_number"));

                        dto.setEmail(rs.getString("email"));
                        dto.setOccupation(rs.getString("occupation"));
                        if (memberId != null) {
                            List<String> refereeIds = new ArrayList<>();
                            try (PreparedStatement rstmt = connection.prepareStatement(refereesSql)) {
                                rstmt.setString(1, memberId);
                                try (ResultSet rRs = rstmt.executeQuery()) {
                                    while (rRs.next()) {
                                        refereeIds.add(rRs.getString("referee_id"));
                                    }
                                }
                            }
                            dto.setReferees(refereeIds);
                            memberDTOList.add(dto);
                        }
                    }
                }
            }
            return memberDTOList;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Optional<CollectivityStructureDTO> findStructureById(String id) {
        String sql = """
                select president_id,
                       vice_president_id ,
                    treasurer_id,
                     secretary_id from collectivity_structure where collectivity_id = ? 
        """;
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            CollectivityStructureDTO collectivityStructureDTO = null;
            if(rs.next()){
                collectivityStructureDTO = new CollectivityStructureDTO();
                collectivityStructureDTO.setPresident(rs.getString("president_id"));
                collectivityStructureDTO.setVicePresident(rs.getString("vice_president_id"));
                collectivityStructureDTO.setTreasurer(rs.getString("treasurer_id"));
                collectivityStructureDTO.setSecretary(rs.getString("secretary_id"));
            }
            return Optional.ofNullable(collectivityStructureDTO);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


}
