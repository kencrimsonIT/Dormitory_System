package com.dormiroty.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnect {
    private static HikariDataSource dataSource;

    static {
        try {
            HikariConfig config = new HikariConfig();
            config.setDriverClassName(DBProperties.getProperty("db.driver"));
            config.setJdbcUrl(DBProperties.getProperty("db.url"));

            // HikariCP optimization settings
            config.setMaximumPoolSize(Integer.parseInt(DBProperties.getProperty("db.pool.maximumPoolSize")));
            config.setMinimumIdle(Integer.parseInt(DBProperties.getProperty("db.pool.minimumIdle")));
            config.setConnectionTimeout(Long.parseLong(DBProperties.getProperty("db.pool.connectionTimeout")));
            config.setIdleTimeout(Long.parseLong(DBProperties.getProperty("db.pool.idleTimeout")));
            config.setMaxLifetime(Long.parseLong(DBProperties.getProperty("db.pool.maxLifetime")));

            dataSource = new HikariDataSource(config);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("HikariCP Connection established successfully!");
            }
        } catch (SQLException e) {
            System.err.println("HikariCP Connection failed!");
            e.printStackTrace();
        }
    }
}
