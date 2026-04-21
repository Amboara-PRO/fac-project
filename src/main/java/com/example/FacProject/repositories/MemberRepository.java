package com.example.FacProject.repositories;

import com.example.FacProject.config.DataSource;
import com.example.FacProject.dto.MemberDTO;
import com.example.FacProject.entities.*;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                        if (bDate != null) dto.setBirthDate(((java.sql.Date) bDate).toLocalDate());
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

}
