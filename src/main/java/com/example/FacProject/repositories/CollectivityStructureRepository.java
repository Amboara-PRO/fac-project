package com.example.FacProject.repositories;

import com.example.FacProject.config.DataSource;
import com.example.FacProject.dto.CollectivityDTO;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


@Repository
public class CollectivityStructureRepository {
    private DataSource dataSource;
    private MemberRepository memberRepository;

    public CollectivityStructureRepository(DataSource dataSource, MemberRepository memberRepository) {
        this.dataSource = dataSource;
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
        try (Connection connection = dataSource.getConnection()){
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                connection.setAutoCommit(false);
                ps.setString(1, request.getId());
                ps.setString(2, request.getStructure().getPresident());
                ps.setString(3, request.getStructure().getVicePresident());
                ps.setString(4, request.getStructure().getTreasurer());
                ps.setString(5, request.getStructure().getSecretary());
                ps.executeUpdate();
                connection.commit();
            }catch(SQLException ex){
            connection.rollback();
            throw ex;
        }
    }catch (SQLException e){
        throw new RuntimeException(e);
    }
}
}
