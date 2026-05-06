package com.example.FacProject.repositories;

import com.example.FacProject.config.DataSource;
import com.example.FacProject.dto.*;
import com.example.FacProject.entities.*;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;

@Repository
public class AttendanceRepository {
    private final DataSource dataSource;

    public AttendanceRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public List<String> create(List<CreateActivityMemberAttendanceDTO> dtos,
                               String collectivityId, String activityId) {

        String sql = """
        INSERT INTO activity_attendance (
            id,
            member_id,
            activity_id,
            attendance_status
        )
        VALUES (?, ?, ?, ?::attendance_status)
        ON CONFLICT (member_id, activity_id)
        DO UPDATE SET
            attendance_status = EXCLUDED.attendance_status
        WHERE activity_attendance.attendance_status = 'UNDEFINED'
        RETURNING id;
    """;

        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);

            List<String> result = new ArrayList<>();

            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                for (CreateActivityMemberAttendanceDTO dto : dtos) {
                    String id = UUID.randomUUID().toString().substring(0, 9);

                    ps.setString(1, id);
                    ps.setString(2, dto.getMemberIdentifier());
                    ps.setString(3, activityId);
                    ps.setString(4, dto.getStatus().name());

                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        result.add(rs.getString("id"));
                    }
                }

                connection.commit();

                return result;

            } catch (SQLException ex) {
                connection.rollback();
                throw ex;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Optional<ActivityMemberAttendanceDTO> findById(String id){

        String sql = """
            SELECT
                          a.id,
                          a.attendance_status,
                          m.id as member_id,
                          m.email,
                          m.first_name,
                          m.last_name,
                          m.occupation
                      FROM activity_attendance a
                      JOIN members m ON a.member_id = m.id
                      WHERE a.id = ? AND (attendance_status = 'ATTENDED' OR attendance_status = 'MISSING')
        """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);

            ResultSet rs = ps.executeQuery();
            ActivityMemberAttendanceDTO dto = null;
            while (rs.next()) {
                dto = new ActivityMemberAttendanceDTO();
                MemberDescriptionDTO memberDescriptionDTO = new MemberDescriptionDTO();
                memberDescriptionDTO.setId(rs.getString("member_id"));
                memberDescriptionDTO.setEmail(rs.getString("email"));
                memberDescriptionDTO.setFirstName(rs.getString("first_name"));
                memberDescriptionDTO.setLastName(rs.getString("last_name"));
                memberDescriptionDTO.setOccupation(rs.getString("occupation"));
                dto.setId(rs.getString("id"));
                dto.setMemberDescription(memberDescriptionDTO);
                dto.setAttendanceStatus(AttendanceStatusEnum.valueOf(rs.getString("attendance_status")));

            }
            return Optional.ofNullable(dto);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }
    public List<ActivityMemberAttendanceDTO> getAllAttendances(String id,String activityId){
        String sql = """
            SELECT
                      a.id,
                      a.attendance_status,
                      m.id as member_id,
                      m.email,
                      m.first_name,
                      m.last_name,
                      m.occupation
                  FROM activity_attendance a
                  JOIN members m ON a.member_id = m.id
                  WHERE a.activity_id = ? and attendance_status='ATTENDED'
        """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, activityId);

            ResultSet rs = ps.executeQuery();

            List<ActivityMemberAttendanceDTO> result = new ArrayList<>();

            while (rs.next()) {
                ActivityMemberAttendanceDTO dto = new ActivityMemberAttendanceDTO();
                MemberDescriptionDTO memberDescriptionDTO = new MemberDescriptionDTO();
                memberDescriptionDTO.setId(rs.getString("member_id"));
                memberDescriptionDTO.setEmail(rs.getString("email"));
                memberDescriptionDTO.setFirstName(rs.getString("first_name"));
                memberDescriptionDTO.setLastName(rs.getString("last_name"));
                memberDescriptionDTO.setOccupation(rs.getString("occupation"));
                dto.setId(rs.getString("id"));
                dto.setMemberDescription(memberDescriptionDTO);
                dto.setAttendanceStatus(AttendanceStatusEnum.valueOf(rs.getString("attendance_status")));
                result.add(dto);
            }

            return result;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
