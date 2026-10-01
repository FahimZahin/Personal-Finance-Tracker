package com.finance.service;


import com.finance.database.UserDAO;
import com.finance.model.User;



public class UserService {


    private final UserDAO userDAO;



    public UserService() {

        userDAO = new UserDAO();

    }




    public void register(User user) {


        userDAO.addUser(user);


    }





    public User login(String email, String password) {


        return userDAO.login(
                email,
                password
        );


    }



}