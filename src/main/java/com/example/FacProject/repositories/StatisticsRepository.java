package com.example.FacProject.repositories;

import com.example.FacProject.config.DataSource;
import com.example.FacProject.dto.*;
import com.example.FacProject.entities.PaymentModeEnum;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StatisticsRepository {
    private final DataSource dataSource;;

    public StatisticsRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public List<CollectivityLocalStatisticsDTO> findCollectivityStatisticsById(String id, LocalDate from, LocalDate to){
        String sql = """

                WITH period AS (
    SELECT ?::date AS start_date, ?::date AS end_date
),

members_cte AS (
    SELECT m.id, m.first_name, m.last_name, m.email, m.occupation, m.collectivity_id
    FROM members m
    WHERE m.collectivity_id = ?
),

earned AS (
    SELECT
        t.member_id,
        SUM(t.amount) AS earned_amount
    FROM transactions t
    JOIN members m ON m.id = t.member_id
    JOIN period p ON TRUE
    WHERE m.collectivity_id = ?
      AND t.creation_date BETWEEN p.start_date AND p.end_date
    GROUP BY t.member_id
),

expected AS (
    SELECT
        m.id AS member_id,
        SUM(
            CASE mf.frequency
                WHEN 'WEEKLY' THEN
                       mf.amount * FLOOR((p.end_date - p.start_date) / 7.0)
                WHEN 'MONTHLY' THEN
                    mf.amount * (
                        DATE_PART('year', p.end_date) * 12 + DATE_PART('month', p.end_date)
                        - (DATE_PART('year', p.start_date) * 12 + DATE_PART('month', p.start_date))
                        + 1
                    )
                WHEN 'ANNUALLY' THEN
                    mf.amount * EXTRACT(YEAR FROM age(p.end_date, p.start_date))
                WHEN 'PUNCTUALLY' THEN
                    mf.amount
            END
        ) AS expected_amount
    FROM members_cte m
    JOIN membership_fees mf
        ON mf.collectivity_id = m.collectivity_id
    JOIN period p ON TRUE
    WHERE mf.status = 'ACTIVE'
      AND mf.eligible_from <= p.end_date
    GROUP BY m.id
)

SELECT
    m.id,
    m.first_name,
    m.last_name,
    m.email,
    m.occupation,

    COALESCE(e.earned_amount, 0) AS "earnedAmount",

    GREATEST(
        COALESCE(ex.expected_amount, 0) - COALESCE(e.earned_amount, 0),
        0
    ) AS "unpaidAmount"

FROM members_cte m
LEFT JOIN earned e ON e.member_id = m.id
LEFT JOIN expected ex ON ex.member_id = m.id;
                """;
        List<CollectivityLocalStatisticsDTO> list = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(from));
            stmt.setDate(2, Date.valueOf(to));
            stmt.setString(3, id);
            stmt.setString(4, id);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                CollectivityLocalStatisticsDTO dto = new CollectivityLocalStatisticsDTO();
                MemberDescriptionDTO memberDescriptionDTO = new MemberDescriptionDTO();
                memberDescriptionDTO.setId(rs.getString("id"));
                memberDescriptionDTO.setFirstName(rs.getString("first_name"));
                memberDescriptionDTO.setLastName(rs.getString("last_name"));
                memberDescriptionDTO.setEmail(rs.getString("email"));
                memberDescriptionDTO.setOccupation(rs.getString("occupation"));
                dto.setMemberDescription(memberDescriptionDTO);
                dto.setEarnedAmount(rs.getBigDecimal("earnedAmount"));
                dto.setUnpaidAmount(rs.getBigDecimal("unpaidAmount"));
                list.add(dto);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
    }
    public List<CollectivityOverallStatisticsDTO> getGlobalStatistics(LocalDate from, LocalDate to) {

        String sql = """
        WITH period AS (
            SELECT ?::date AS start_date, ?::date AS end_date
        ),

        earned AS (
            SELECT
                t.member_id,
                SUM(t.amount) AS earned_amount
            FROM transactions t
            JOIN period p ON TRUE
            WHERE t.creation_date BETWEEN p.start_date AND p.end_date
            GROUP BY t.member_id
        ),

        expected AS (
            SELECT
                m.id AS member_id,
                SUM(
                    CASE mf.frequency
                        WHEN 'WEEKLY' THEN
                            mf.amount * FLOOR(EXTRACT(DAY FROM (p.end_date - p.start_date)) / 7)
                        WHEN 'MONTHLY' THEN
                            mf.amount * (
                                (DATE_PART('year', p.end_date) - DATE_PART('year', p.start_date)) * 12 +
                                (DATE_PART('month', p.end_date) - DATE_PART('month', p.start_date)) + 1
                            )
                        WHEN 'ANNUALLY' THEN
                            mf.amount * EXTRACT(YEAR FROM age(p.end_date, p.start_date))
                        WHEN 'PUNCTUALLY' THEN
                            mf.amount
                    END
                ) AS expected_amount
            FROM members m
            JOIN membership_fees mf ON mf.collectivity_id = m.collectivity_id
            JOIN period p ON TRUE
            WHERE mf.status = 'ACTIVE'
              AND mf.eligible_from <= p.end_date
            GROUP BY m.id
        ),

        member_status AS (
            SELECT
                m.collectivity_id,
                m.id,
                COALESCE(e.earned_amount, 0) AS earned,
                COALESCE(ex.expected_amount, 0) AS expected,
                CASE
                    WHEN COALESCE(e.earned_amount, 0) >= COALESCE(ex.expected_amount, 0)
                    THEN 1 ELSE 0
                END AS is_up_to_date
            FROM members m
            LEFT JOIN earned e ON e.member_id = m.id
            LEFT JOIN expected ex ON ex.member_id = m.id
        ),

        new_members AS (
            SELECT
                m.collectivity_id,
                COUNT(*) AS new_members_count
            FROM members m
            JOIN period p ON TRUE
            WHERE m.federation_join_date BETWEEN p.start_date AND p.end_date
            GROUP BY m.collectivity_id
        )

        SELECT
            c.name,
            c.number,

            COALESCE(nm.new_members_count, 0) AS newMembersNumber,

            CASE
                WHEN COUNT(ms.id) FILTER (WHERE ms.expected > 0) = 0 THEN 0
                ELSE ROUND(
                    SUM(ms.is_up_to_date) FILTER (WHERE ms.expected > 0) * 100.0
                    / COUNT(ms.id) FILTER (WHERE ms.expected > 0),
                2)
            END AS overallMemberCurrentDuePercentage

        FROM collectivities c
        LEFT JOIN member_status ms ON ms.collectivity_id = c.id
        LEFT JOIN new_members nm ON nm.collectivity_id = c.id

        GROUP BY c.id, c.name, c.number, nm.new_members_count
    """;

        List<CollectivityOverallStatisticsDTO> list = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(from));
            stmt.setDate(2, Date.valueOf(to));

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                CollectivityOverallStatisticsDTO dto = new CollectivityOverallStatisticsDTO();

                CreateCollectivityInformationsDTO info = new CreateCollectivityInformationsDTO();
                info.setName(rs.getString("name"));
                info.setNumber(rs.getInt("number"));

                dto.setCollectivityInformation(info);
                dto.setNewMembersNumber(rs.getInt("newMembersNumber"));
                dto.setOverallMemberCurrentDuePercentage(
                        rs.getDouble("overallMemberCurrentDuePercentage")
                );

                list.add(dto);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
    }
}
