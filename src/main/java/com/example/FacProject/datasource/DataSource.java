package com.example.FacProject.datasource;


import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
@Component
public class DataSource {

        private String URL = "jdbc:postgresql://localhost:5432/federation_db";
        private String USER = "federation_db_manager";
        private String PASSWORD = "123456";

        public Connection getConnection() throws Exception {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
    }
