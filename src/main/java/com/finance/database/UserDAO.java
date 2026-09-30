package com.finance.database;

import com.finance.model.User;

import java.sql.*;

public class UserDAO {


    // Register new user
    public void addUser(User user) {


        String sql = """
                INSERT INTO users(username, email, password)
                VALUES (?, ?, ?)
                """;


        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {


            statement.setString(1, user.getUsername());

            statement.setString(2, user.getEmail());

            statement.setString(3, user.getPassword());


            statement.executeUpdate();


            System.out.println("User created successfully.");


        } catch (SQLException e) {

            System.out.println("Failed to create user.");

            e.printStackTrace();

        }

    }



    // Find user by email
    public User getUserByEmail(String email) {


        String sql = "SELECT * FROM users WHERE email = ?";


        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {


            statement.setString(1, email);


            ResultSet resultSet = statement.executeQuery();


            if (resultSet.next()) {


                User user = new User();


                user.setId(resultSet.getInt("id"));

                user.setUsername(resultSet.getString("username"));

                user.setEmail(resultSet.getString("email"));

                user.setPassword(resultSet.getString("password"));


                return user;

            }


        } catch (SQLException e) {

            System.out.println("Failed to find user.");

            e.printStackTrace();

        }


        return null;

    }



    // Delete user
    public void deleteUser(int id) {


        String sql = "DELETE FROM users WHERE id = ?";


        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {


            statement.setInt(1, id);


            statement.executeUpdate();


            System.out.println("User deleted successfully.");


        } catch (SQLException e) {

            System.out.println("Failed to delete user.");

            e.printStackTrace();

        }

    }

}