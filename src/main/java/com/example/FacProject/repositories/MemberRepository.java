package com.example.FacProject.repositories;

import com.example.FacProject.config.DataSource;
import com.example.FacProject.dto.CreateMemberPaymentDTO;
import com.example.FacProject.dto.MemberDTO;
import com.example.FacProject.entities.*;
import com.example.FacProject.entities.MemberEntity;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

@Repository
public class MemberRepository {
    private DataSource dataSource;

    public MemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Optional<MemberDTO> findById(String memberId) {
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
                                        occupation,
                                        collectivity_id FROM members WHERE id = ? 
                                        """;
        String refereesSql = "SELECT referee_id FROM member_referees WHERE member_id = ?";

        try (Connection connection = dataSource.getConnection()) {
            MemberDTO dto = null;

            try (PreparedStatement pstmt = connection.prepareStatement(memberSql)) {
                pstmt.setString(1, memberId);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        dto = new MemberDTO();
                        dto.setId(rs.getString("id"));
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
                    }
                }
            }
            if (dto != null) {
                List<String> refereeIds = new ArrayList<>();
                try (PreparedStatement pstmt = connection.prepareStatement(refereesSql)) {
                    pstmt.setString(1, memberId);
                    try (ResultSet rs = pstmt.executeQuery()) {
                        while (rs.next()) {
                            refereeIds.add(rs.getString("referee_id"));
                        }
                    }
                }
                dto.setReferees(refereeIds);
            }

            return Optional.ofNullable(dto);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public int countSeniorMembers(List<String> ids) {

        String sql = """
        SELECT COUNT(id)
        FROM members
        WHERE id = ANY(?)
        AND federation_join_date <= CURRENT_DATE - INTERVAL '6 months'
    """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            java.sql.Array sqlArray = connection.createArrayOf(
                    "varchar",
                    ids.toArray()
            );

            ps.setArray(1, sqlArray);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }
    public void save(MemberEntity m) {

        String sql = """
            INSERT INTO members(id, first_name, last_name, birth_date, gender,
                                address, profession, phone_number, email,
                                occupation, collectivity_id,
                                registration_fee_paid, membership_dues_paid)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, m.getId());
            stmt.setString(2, m.getFirstName());
            stmt.setString(3, m.getLastName());
            stmt.setDate(4, java.sql.Date.valueOf(m.getBirthDate()));
            stmt.setString(5, m.getGender().name());
            stmt.setString(6, m.getAddress());
            stmt.setString(7, m.getProfession());
            stmt.setLong(8, m.getPhoneNumber());
            stmt.setString(9, m.getEmail());
            stmt.setString(10, m.getOccupation().name());
            stmt.setString(11,
                    m.getCollectivity() != null
                            ? m.getCollectivity().getId()
                            : null
            );            stmt.setBoolean(12, m.getRegistrationFeePaid());
            stmt.setBoolean(13, m.getMembershipDuesPaid());

            stmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existsById(String id) {

        String sql = "SELECT COUNT(id) FROM members WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public void saveReferee(String memberId, String refereeId) {

        String sql = "INSERT INTO member_referees(member_id, referee_id) VALUES (?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, memberId);
            stmt.setString(2, refereeId);

            stmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void savePayment(String memberId, String paymentId, CreateMemberPaymentDTO p) {

        String sql = """
        INSERT INTO member_payments (
            id, member_id, amount, payment_mode, creation_date
        )
        VALUES (?, ?, ?, ?::payment_mode, CURRENT_DATE)
    """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, paymentId);
            stmt.setString(2, memberId);
            stmt.setDouble(3, p.getAmount());
            stmt.setString(4, p.getPaymentMode().name());

            stmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void assignToCollectivity(String memberId, String collectivityId) {
        String sql = "UPDATE members SET collectivity_id = ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, collectivityId);
            stmt.setString(2, memberId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
