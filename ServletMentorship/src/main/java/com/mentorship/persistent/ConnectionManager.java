package com.mentorship.persistent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static final String url = "jdbc:mysql://localhost:3306/mentorship?useSSL=false&serverTimezone=UTC";
    private static final String user = "root";
    private static final String pass = "root";

    private static Connection connection;

    private ConnectionManager() {
    }

    public static Connection getConnection() {
        if (connection == null)
            initialization();
        return connection;
    }

    private static void initialization() {
        try { connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}