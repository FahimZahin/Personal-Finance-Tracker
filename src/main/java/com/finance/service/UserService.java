package com.finance.service;

import com.finance.database.UserDAO;
import com.finance.model.User;

public class UserService {


    private final UserDAO userDAO;



    public UserService() {

        this.userDAO = new UserDAO();

    }



    // Register user
    public void registerUser(User user) {


        if (user.getUsername() == null || user.getUsername().isEmpty()) {

            throw new IllegalArgumentException("Username cannot be empty.");

        }


        if (user.getEmail() == null || user.getEmail().isEmpty()) {

            throw new IllegalArgumentException("Email cannot be empty.");

        }


        if (user.getPassword() == null || user.getPassword().isEmpty()) {

            throw new IllegalArgumentException("Password cannot be empty.");

        }


        userDAO.addUser(user);

    }



    // Login user
    public User login(String email, String password) {


        User user = userDAO.getUserByEmail(email);


        if (user != null && user.getPassword().equals(password)) {

            return user;

        }


        return null;

    }



    // Delete user
    public void deleteUser(int id) {

        userDAO.deleteUser(id);

    }

}