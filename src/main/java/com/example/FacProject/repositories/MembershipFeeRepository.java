package com.example.FacProject.repositories;

import com.example.FacProject.config.DataSource;
import com.example.FacProject.dto.CreateMembershipFeeDTO;
import com.example.FacProject.dto.MembershipFeeDTO;
import com.example.FacProject.entities.ActivityStatusEnum;
import com.example.FacProject.entities.FrequencyEnum;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.sql.Date;
import java.util.*;

@Repository
public class MembershipFeeRepository {
    private DataSource dataSource;


    public MembershipFeeRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public MembershipFeeDTO save(String collectivityId, CreateMembershipFeeDTO dto) {

        String sql = """
        INSERT INTO membership_fees(id, collectivity_id, eligible_from, frequency, amount, label)
        VALUES (?, ?, ?, ?::frequency, ?, ?)
        RETURNING *
    """;
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement stmt = connection.prepareStatement(sql);) {
                String id = UUID.randomUUID().toString().substring(0, 9);
                stmt.setString(1, id);
                stmt.setString(2, collectivityId);
                stmt.setDate(3, Date.valueOf(dto.eligibleFrom));
                stmt.setString(4, dto.frequency.name());
                stmt.setBigDecimal(5, dto.amount);
                stmt.setString(6, dto.label);
                stmt.executeUpdate();
                connection.commit();
                Optional<MembershipFeeDTO> membershipFeeDTO = findById(id);
                if(membershipFeeDTO.isPresent()) {
                    return membershipFeeDTO.get();
                }
                throw new NoSuchElementException("Membership fee not found");
            } catch (SQLException ex) {
                if (connection != null) {

                    connection.rollback();

                }
                throw new RuntimeException(ex);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Optional<MembershipFeeDTO> findById(String id) {
        String sql = """
        SELECT id, eligible_from, frequency, amount, label ,status
        from membership_fees where id =?
        """;
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            MembershipFeeDTO membershipFeeDTO = null;
            if (rs.next()) {
                membershipFeeDTO = new MembershipFeeDTO();
                membershipFeeDTO.setId(rs.getString("id"));
                membershipFeeDTO.setEligibleFrom(rs.getDate("eligible_from").toLocalDate());
                membershipFeeDTO.setFrequency(FrequencyEnum.valueOf(rs.getString("frequency")));
                membershipFeeDTO.setAmount(rs.getBigDecimal("amount"));
                membershipFeeDTO.setLabel(rs.getString("label"));
                membershipFeeDTO.setStatus(ActivityStatusEnum.valueOf(rs.getString("status")));
            }
            return Optional.ofNullable(membershipFeeDTO);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<MembershipFeeDTO> getAll(String id) {
        String sql = """
                SELECT id, eligible_from, frequency, amount, label ,status
        from membership_fees where collectivity_id =?
                """;
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            List<MembershipFeeDTO> membershipFeeDTOList = new ArrayList<>();
            if(rs.next()){
                MembershipFeeDTO membershipFeeDTO = new MembershipFeeDTO();
                membershipFeeDTO.setId(rs.getString("id"));
                membershipFeeDTO.setEligibleFrom(rs.getDate("eligible_from").toLocalDate());
                membershipFeeDTO.setFrequency(FrequencyEnum.valueOf(rs.getString("frequency")));
                membershipFeeDTO.setAmount(rs.getBigDecimal("amount"));
                membershipFeeDTO.setLabel(rs.getString("label"));
                membershipFeeDTO.setStatus(ActivityStatusEnum.valueOf(rs.getString("status")));
                membershipFeeDTOList.add(membershipFeeDTO);
            }
            return membershipFeeDTOList;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }
}
