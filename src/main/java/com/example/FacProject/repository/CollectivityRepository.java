package com.example.FacProject.repository;

import com.example.FacProject.config.DataSource;
import com.example.FacProject.entity.CollectivityEntity;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.util.List;

@Repository
public class CollectivityRepository {
    private Connection connection;

    public CollectivityRepository(Connection connection) {
        this.connection = connection;
    }

    public CollectivityEntity create(List<CollectivityEntity> collectivityEntity) {
        return null;
    }
}
