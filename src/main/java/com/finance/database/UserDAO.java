package com.finance.database;


import com.finance.model.User;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class UserDAO {



    public void addUser(User user) {


        String sql =
                "INSERT INTO users(username,email,password) VALUES(?,?,?)";



        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){



            statement.setString(
                    1,
                    user.getUsername()
            );


            statement.setString(
                    2,
                    user.getEmail()
            );


            statement.setString(
                    3,
                    user.getPassword()
            );



            statement.executeUpdate();



            System.out.println(
                    "User registered successfully."
            );



        }catch(Exception e){

            e.printStackTrace();

        }


    }





    public User login(String email, String password){


        String sql =
                "SELECT * FROM users WHERE email=? AND password=?";



        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){



            statement.setString(
                    1,
                    email
            );


            statement.setString(
                    2,
                    password
            );



            ResultSet result =
                    statement.executeQuery();



            if(result.next()){


                User user =
                        new User();



                user.setId(
                        result.getInt("id")
                );


                user.setUsername(
                        result.getString("username")
                );


                user.setEmail(
                        result.getString("email")
                );


                user.setPassword(
                        result.getString("password")
                );


                return user;


            }



        }catch(Exception e){

            e.printStackTrace();

        }


        return null;


    }



}