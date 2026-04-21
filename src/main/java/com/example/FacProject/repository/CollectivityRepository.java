package com.example.FacProject.repository;

import com.example.FacProject.config.DataSource;
import com.example.FacProject.entity.CollectivityEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CollectivityRepository {
    private DataSource dataSource;
    public CollectivityRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public CollectivityEntity create(List<CollectivityEntity> collectivityEntity) {
        return null;
    }
}
