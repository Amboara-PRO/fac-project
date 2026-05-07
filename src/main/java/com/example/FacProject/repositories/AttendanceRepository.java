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
                               String collectivityId,
                               String activityId) {

        String sqlInsert = """
        INSERT INTO activity_attendance (
            id,
            member_id,
            activity_id,
            attendance_status
        )
        VALUES (?, ?, ?, ?::attendance_status)
        RETURNING id;
    """;

        String sqlUpdate = """
        UPDATE activity_attendance
        SET attendance_status = ?::attendance_status
        WHERE member_id = ?
          AND activity_id = ?
        RETURNING id;
    """;

        try (Connection connection = dataSource.getConnection()) {

            connection.setAutoCommit(false);

            List<String> result = new ArrayList<>();

            try {

                for (CreateActivityMemberAttendanceDTO dto : dtos) {

                    String[] table =
                            findMemberAndActivityId(
                                    dto.getMemberIdentifier(),
                                    activityId
                            );
                    if (table == null) {

                        try (PreparedStatement ps =
                                     connection.prepareStatement(sqlInsert)) {

                            String id =
                                    UUID.randomUUID()
                                            .toString()
                                            .substring(0, 9);

                            ps.setString(1, id);
                            ps.setString(2, dto.getMemberIdentifier());
                            ps.setString(3, activityId);
                            ps.setString(4, dto.getStatus().name());

                            ResultSet rs = ps.executeQuery();

                            if (rs.next()) {
                                result.add(rs.getString("id"));
                            }
                        }

                    }

                    else if (
                            table[2].equals(
                                    AttendanceStatusEnum.UNDEFINED.name()
                            )
                    ) {

                        try (PreparedStatement ps =
                                     connection.prepareStatement(sqlUpdate)) {

                            ps.setString(1, dto.getStatus().name());
                            ps.setString(2, dto.getMemberIdentifier());
                            ps.setString(3, activityId);

                            ResultSet rs = ps.executeQuery();

                            if (rs.next()) {
                                result.add(rs.getString("id"));
                            }
                        }
                    }
                }

                connection.commit();

                return result;

            } catch (Exception e) {

                connection.rollback();
                throw e;
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
    public String[] findMemberAndActivityId(String memberId, String activityId) {

        String sql = """
            SELECT member_id, activity_id, attendance_status
            FROM activity_attendance
            WHERE member_id = ? AND activity_id = ?;
            """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, memberId);
            stmt.setString(2, activityId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                String[] table = new String[3];

                table[0] = rs.getString("member_id");
                table[1] = rs.getString("activity_id");
                table[2] = rs.getString("attendance_status");

                return table;
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
