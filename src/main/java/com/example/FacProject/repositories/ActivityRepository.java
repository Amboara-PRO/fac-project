package com.example.FacProject.repositories;

import com.example.FacProject.config.DataSource;
import com.example.FacProject.dto.ActivityDTO;
import com.example.FacProject.dto.CreateCollectivityActivityDTO;
import com.example.FacProject.entities.ActivityTypeEnum;
import com.example.FacProject.entities.DayOfWeekEnum;
import com.example.FacProject.entities.MemberOccupationEnum;
import com.example.FacProject.entities.MonthlyRecurrenceRule;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.sql.Date;
import java.util.*;

@Repository
public class ActivityRepository {
    private final DataSource dataSource;

    public ActivityRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public List<ActivityDTO> getAllActivities(String collectivityId){

        String sql = """
            SELECT 
                id,
                executive_date,
                label,
                activity_type,
                member_occupations,
                recurrence_week_ordinal,
                recurrence_day_of_week FROM activities
            WHERE collectivity_id = ?
        """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, collectivityId);

            ResultSet rs = ps.executeQuery();

            List<ActivityDTO> result = new ArrayList<>();

            while (rs.next()) {
                ActivityDTO dto = new ActivityDTO();

                dto.setId(rs.getString("id"));
                dto.setLabel(rs.getString("label"));
                dto.setActivityType(ActivityTypeEnum.valueOf(rs.getString("activity_type")));

                Array arr = rs.getArray("member_occupations");
                String[] values = (String[]) arr.getArray();

                List<MemberOccupationEnum> list =
                        Arrays.stream(values)
                                .map(MemberOccupationEnum::valueOf)
                                .toList();
                dto.setMemberOccupationConcerned(list);
                MonthlyRecurrenceRule rule = new MonthlyRecurrenceRule();
                rule.setWeekOrdinal(rs.getInt("recurrence_week_ordinal"));
                rule.setDayOfWeek(DayOfWeekEnum.valueOf(rs.getString("recurrence_day_of_week")));
                dto.setRecurrenceRule(rule);

                Date date = rs.getDate("executive_date");
                if (date != null) dto.setExecutiveDate(date.toLocalDate());
                result.add(dto);
            }

            return result;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public List<String> createCollectivityActivity(List<CreateCollectivityActivityDTO> dtos, String collectivityId) {

        String sql = """
        INSERT INTO activities (
            id,
            collectivity_id,
            label,
            activity_type,
            member_occupations,
            executive_date,
            recurrence_week_ordinal,
            recurrence_day_of_week
        )
        VALUES (?, ?, ?, ?::activity_type, ?, ?, ?, ?::day_of_week)
    """;
        try (Connection connection = dataSource.getConnection()) {

            connection.setAutoCommit(false);
            List<String> result = new ArrayList<>();
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                for(CreateCollectivityActivityDTO dto : dtos){
                    String id = UUID.randomUUID().toString().substring(0, 9);

                    ps.setString(1, id);
                    ps.setString(2, collectivityId);
                    ps.setString(3, dto.getLabel());
                    ps.setString(4, dto.getActivityType().name());
                    Array occupationsArray = connection.createArrayOf(
                            "member_occupation",
                            dto.getMemberOccupationConcerned()
                                    .stream()
                                    .map(Enum::name)
                                    .toArray(String[]::new)
                    );
                    ps.setArray(5, occupationsArray);
                    if (dto.getExecutiveDate() != null) {
                        ps.setDate(6, java.sql.Date.valueOf(dto.getExecutiveDate()));
                    } else {
                        ps.setNull(6, java.sql.Types.DATE);
                    }
                    if (dto.getRecurrenceRule() != null) {
                        ps.setInt(7, dto.getRecurrenceRule().getWeekOrdinal());
                        ps.setString(8, dto.getRecurrenceRule().getDayOfWeek().name());
                    } else {
                        ps.setNull(7, java.sql.Types.INTEGER);
                        ps.setNull(8, java.sql.Types.VARCHAR);
                    }
                    ps.addBatch();
                    result.add(id);
                }
                ps.executeBatch();
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
    public Optional<ActivityDTO> findById(String id){

        String sql = """
            SELECT 
                id,
                executive_date,
                label,
                activity_type,
                member_occupations,
                recurrence_week_ordinal,
                recurrence_day_of_week FROM activities
            WHERE id = ?
        """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);

            ResultSet rs = ps.executeQuery();
            ActivityDTO dto = null;
            while (rs.next()) {
                dto = new ActivityDTO();
                dto.setId(rs.getString("id"));
                dto.setLabel(rs.getString("label"));
                dto.setActivityType(ActivityTypeEnum.valueOf(rs.getString("activity_type")));

                Array arr = rs.getArray("member_occupations");
                String[] values = (String[]) arr.getArray();

                List<MemberOccupationEnum> list =
                        Arrays.stream(values)
                                .map(MemberOccupationEnum::valueOf)
                                .toList();
                dto.setMemberOccupationConcerned(list);

                MonthlyRecurrenceRule rule = new MonthlyRecurrenceRule();
                rule.setWeekOrdinal(rs.getInt("recurrence_week_ordinal"));
                rule.setDayOfWeek(DayOfWeekEnum.valueOf(rs.getString("recurrence_day_of_week")));
                dto.setRecurrenceRule(rule);

                Date date = rs.getDate("executive_date");
                if (date != null) dto.setExecutiveDate(date.toLocalDate());
            }
            return Optional.ofNullable(dto);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
