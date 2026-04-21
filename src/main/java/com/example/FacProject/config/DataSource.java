package com.example.FacProject.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class DataSource {

    private String jdbcUrl = System.getenv("JDBC_URl");
    private String user = System.getenv("USER");
    private String password = System.getenv("PASSWORD");

    @Bean
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(jdbcUrl, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
