package com.finance.database;


public class DatabaseInitializer {


    public static void createTables(){


        String users =
                """
                CREATE TABLE IF NOT EXISTS users
                (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,

                    username TEXT NOT NULL,

                    email TEXT UNIQUE NOT NULL,

                    password TEXT NOT NULL
                );
                """;





        String expenses =
                """
                CREATE TABLE IF NOT EXISTS expenses
                (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,

                    user_id INTEGER NOT NULL,

                    amount REAL NOT NULL,

                    category TEXT NOT NULL,

                    description TEXT,

                    date TEXT NOT NULL,

                    FOREIGN KEY(user_id)
                    REFERENCES users(id)
                );
                """;






        String income =
                """
                CREATE TABLE IF NOT EXISTS income
                (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,

                    user_id INTEGER NOT NULL,

                    amount REAL NOT NULL,

                    source TEXT NOT NULL,

                    description TEXT,

                    date TEXT NOT NULL,

                    FOREIGN KEY(user_id)
                    REFERENCES users(id)
                );
                """;






        String settings =
                """
                CREATE TABLE IF NOT EXISTS settings
                (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,

                    user_id INTEGER UNIQUE NOT NULL,

                    currency TEXT DEFAULT '৳',

                    theme TEXT DEFAULT 'LIGHT',

                    FOREIGN KEY(user_id)
                    REFERENCES users(id)
                );
                """;






        try(
                var connection =
                        DatabaseConnection.getConnection();


                var statement =
                        connection.createStatement()

        ){


            statement.execute(users);


            statement.execute(expenses);


            statement.execute(income);


            statement.execute(settings);




            System.out.println(
                    "Database tables created successfully."
            );



        }
        catch(Exception e){


            e.printStackTrace();


        }



    }



}