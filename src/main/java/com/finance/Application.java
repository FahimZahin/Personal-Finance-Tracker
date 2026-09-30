package com.finance;

import com.finance.database.DatabaseInitializer;

public class Application {


    public static void main(String[] args) {


        System.out.println("Starting Personal Finance Tracker...");


        // Initialize database
        DatabaseInitializer.createTables();


        System.out.println("Application started successfully.");

    }

}