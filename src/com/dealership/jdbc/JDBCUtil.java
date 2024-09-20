package com.dealership.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
    // JDBC connection parameters
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/car_dealer";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "your_password";

    // Static block to load JDBC driver
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Method to get connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

}
