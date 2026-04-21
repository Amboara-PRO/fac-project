package com.example.FacProject.repository;

import com.example.FacProject.datasource.DataSource;
import com.example.FacProject.entity.CollectivityEntity;
import org.springframework.stereotype.Repository;

@Repository
public class CollectivityRepository {
    private DataSource dataSource;
    public CollectivityRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public CollectivityEntity create(CollectivityEntity collectivityEntity) {
        return null;
    }
}
