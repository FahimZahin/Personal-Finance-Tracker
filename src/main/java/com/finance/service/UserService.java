package com.finance.service;


import com.finance.database.UserDAO;
import com.finance.model.User;



public class UserService {


    private final UserDAO userDAO;



    public UserService(){


        userDAO =
                new UserDAO();


    }





    // LOGIN

    public User login(
            String email,
            String password
    ){

        return userDAO.login(
                email,
                password
        );

    }







    // REGISTER

    public void register(
            User user
    ){

        userDAO.addUser(
                user
        );

    }







    // UPDATE PROFILE

    public void updateUser(
            User user
    ){

        userDAO.updateUser(
                user
        );

    }



}