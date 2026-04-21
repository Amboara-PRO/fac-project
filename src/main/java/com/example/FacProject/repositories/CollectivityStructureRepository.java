package com.example.FacProject.repositories;

import com.example.FacProject.dto.CollectivityDTO;
import com.example.FacProject.dto.CreateCollectivityDTO;
import com.example.FacProject.entities.CollectivityEntity;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Repository
public class CollectivityStructureRepository {
    private Connection connection;
    private MemberRepository memberRepository;

    public CollectivityStructureRepository(Connection connection, MemberRepository memberRepository) {
        this.connection = connection;
        this.memberRepository = memberRepository;
    }

    public void createCollectivityStructure(CollectivityDTO request) {
        String sql = """
            INSERT INTO collectivity_structure (
                                               collectivity_id,
                                               president_id,
                                               vice_president_id,
                                               treasurer_id,
                                               secretary_id
                                               )
            VALUES (?, ?, ?, ?, ?)
        """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
                ps.setString(1, request.getId());
                ps.setString(2, request.getStructure().getPresident());
                ps.setString(3, request.getStructure().getVicePresident());
                ps.setString(4, request.getStructure().getTreasurer());
                ps.setString(5, request.getStructure().getSecretary());
                ps.executeUpdate();
            connection.commit();
        }catch (SQLException e){
            if(connection != null){
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            throw new RuntimeException(e);
        }
    }
}
