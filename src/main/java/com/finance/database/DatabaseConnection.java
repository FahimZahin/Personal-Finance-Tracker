package com.finance.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String DATABASE_URL = "jdbc:sqlite:finance_tracker.db";

    private static Connection connection;


    public static Connection getConnection() {

        try {

            if (connection == null || connection.isClosed()) {

                connection = DriverManager.getConnection(DATABASE_URL);

                System.out.println("Database connected successfully.");

            }

        } catch (SQLException e) {

            System.out.println("Database connection failed.");
            e.printStackTrace();

        }

        return connection;
    }


    public static void closeConnection() {

        try {

            if (connection != null && !connection.isClosed()) {

                connection.close();

                System.out.println("Database connection closed.");

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }
    }
}