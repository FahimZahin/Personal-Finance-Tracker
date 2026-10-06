package com.finance.service;


import com.finance.database.UserDAO;
import com.finance.model.User;

import org.mindrot.jbcrypt.BCrypt;



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


        User user =
                userDAO.findByEmail(email);



        if(user == null){

            return null;

        }



        try {


            if(
                    BCrypt.checkpw(
                            password,
                            user.getPassword()
                    )
            ){

                return user;

            }


        }
        catch(Exception e){

            e.printStackTrace();

        }



        return null;


    }







    // REGISTER

    public void register(
            User user
    ){


        String hashedPassword =
                BCrypt.hashpw(
                        user.getPassword(),
                        BCrypt.gensalt()
                );



        user.setPassword(
                hashedPassword
        );



        userDAO.addUser(
                user
        );


    }







    // PROFILE UPDATE

    public void updateUser(
            User user
    ){


        userDAO.updateUser(
                user
        );


    }



}