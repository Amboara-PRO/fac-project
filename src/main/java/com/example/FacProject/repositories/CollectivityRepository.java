package com.example.FacProject.repositories;

import com.example.FacProject.config.DataSource;
import com.example.FacProject.dto.CollectivityDTO;
import com.example.FacProject.dto.CreateCollectivityDTO;
import com.example.FacProject.entities.CollectivityEntity;
import com.example.FacProject.entities.CollectivityStructureEntity;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;

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
}
