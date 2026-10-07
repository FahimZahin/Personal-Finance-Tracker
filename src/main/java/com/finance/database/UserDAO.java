package com.finance.database;


import com.finance.model.User;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class UserDAO {


    public void addUser(User user) {


        String sql =
                "INSERT INTO users(username,email,password) VALUES(?,?,?)";


        try(
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)

        ){


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


        }
        catch(Exception e){


            if(e.getMessage().contains("UNIQUE")){


                System.out.println(
                        "Email already exists."
                );


            }
            else{


                e.printStackTrace();


            }


        }


    }






    // Find user by email for BCrypt login

    public User findByEmail(String email){


        String sql =
                "SELECT * FROM users WHERE email=?";



        try(
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)

        ){


            statement.setString(
                    1,
                    email
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


        }
        catch(Exception e){

            e.printStackTrace();

        }



        return null;


    }







    public void updateUser(User user){


        String sql =
                "UPDATE users SET username=?, password=? WHERE id=?";



        try(
                Connection connection =
                        DatabaseConnection.getConnection();


                PreparedStatement statement =
                        connection.prepareStatement(sql)

        ){


            statement.setString(
                    1,
                    user.getUsername()
            );



            statement.setString(
                    2,
                    user.getPassword()
            );



            statement.setInt(
                    3,
                    user.getId()
            );



            statement.executeUpdate();



            System.out.println(
                    "User updated successfully."
            );


        }
        catch(Exception e){

            e.printStackTrace();

        }


    }

    public boolean emailExists(String email){


        String sql =
                "SELECT id FROM users WHERE email=?";


        try(
                Connection connection =
                        DatabaseConnection.getConnection();


                PreparedStatement statement =
                        connection.prepareStatement(sql)

        ){


            statement.setString(
                    1,
                    email
            );


            ResultSet result =
                    statement.executeQuery();



            return result.next();


        }
        catch(Exception e){

            e.printStackTrace();

        }


        return false;

    }



}