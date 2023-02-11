package com.example.bags.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class ConnectionConfig {

    @Bean
    public Connection getNewConnection() {
        String url = "jdbc:postgresql:homeDB";
        String user = "postgres";
        String passwd = "postgres";
        try {
            return DriverManager.getConnection(url, user, passwd);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
