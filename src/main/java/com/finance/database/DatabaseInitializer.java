package com.finance.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {


    public static void createTables() {

        String createUsersTable = """
                CREATE TABLE IF NOT EXISTS users (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    username TEXT NOT NULL,
                    email TEXT UNIQUE NOT NULL,
                    password TEXT NOT NULL
                );
                """;


        String createExpensesTable = """
                CREATE TABLE IF NOT EXISTS expenses (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    amount REAL NOT NULL,
                    category TEXT NOT NULL,
                    description TEXT,
                    date TEXT NOT NULL
                );
                """;


        String createIncomeTable = """
                CREATE TABLE IF NOT EXISTS income (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    amount REAL NOT NULL,
                    source TEXT NOT NULL,
                    description TEXT,
                    date TEXT NOT NULL
                );
                """;


        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {


            statement.execute(createUsersTable);

            statement.execute(createExpensesTable);

            statement.execute(createIncomeTable);


            System.out.println("Database tables created successfully.");


        } catch (SQLException e) {

            System.out.println("Failed to create database tables.");

            e.printStackTrace();

        }

    }

}