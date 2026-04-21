package com.example.FacProject.config;


import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
@Component
public class DataSource {

    private String jdbcUrl = System.getenv("JDBC_URl");
    private String user = System.getenv("USER");
    private String password = System.getenv("PASSWORD");

    public Connection getConnection() throws Exception {
        return DriverManager.getConnection(jdbcUrl,user , password);
    }
}
