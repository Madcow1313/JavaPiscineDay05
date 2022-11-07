package edu.school21.chat.repositories;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcDataSource {
    private static final String DB_USERNAME = "wabathur";
    private static final String DB_PASSWORD = "postgres";
    private static final String D_URL = "jdbc:postgresql://localhost:5432/postgres";

    private static HikariDataSource hikariDataSource;
    private static HikariConfig config;

    public JdbcDataSource(){
        config.setJdbcUrl(D_URL);
        config.setUsername(DB_USERNAME);
        config.setPassword(DB_PASSWORD);

        hikariDataSource = new HikariDataSource(config);
    }

    public HikariDataSource getHikariDataSource(){
        return hikariDataSource;
    }

    public Connection getConnection() throws SQLException {
        return hikariDataSource.getConnection();
    }
}
