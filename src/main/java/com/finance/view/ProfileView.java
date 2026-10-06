package com.finance.view;


import com.finance.Session;
import com.finance.model.User;
import com.finance.service.UserService;


import javafx.geometry.Insets;
import javafx.geometry.Pos;


import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.scene.layout.VBox;

import javafx.stage.Stage;



public class ProfileView {


    private final UserService userService;



    public ProfileView(){

        userService =
                new UserService();

    }





    public VBox getView(Stage stage){



        User user =
                Session.getUser();




        Label title =
                new Label(
                        "My Profile"
                );



        TextField usernameField =
                new TextField();



        usernameField.setText(
                user.getUsername()
        );



        usernameField.setPromptText(
                "Username"
        );




        PasswordField passwordField =
                new PasswordField();



        passwordField.setPromptText(
                "New Password"
        );





        Label message =
                new Label();





        Button saveButton =
                new Button(
                        "Save Changes"
                );





        saveButton.setOnAction(event -> {


            try{


                if(usernameField.getText().isEmpty()){


                    message.setText(
                            "Username cannot be empty."
                    );


                    return;

                }



                if(
                        !passwordField.getText()
                                .isEmpty()
                ){

                    user.setPassword(
                            passwordField.getText()
                    );

                }



                userService.updateUser(
                        user
                );



                Session.setUser(
                        user
                );



                message.setText(
                        "Profile updated successfully!"
                );



            }
            catch(Exception e){


                message.setText(
                        "Update failed."
                );


                e.printStackTrace();


            }



        });







        Button backButton =
                new Button(
                        "Back Dashboard"
                );



        backButton.setOnAction(event -> {



            DashboardView dashboard =
                    new DashboardView();



            stage.setScene(
                    new Scene(
                            dashboard.getView(stage),
                            900,
                            650
                    )
            );


        });







        VBox layout =
                new VBox(
                        15,
                        title,
                        usernameField,
                        passwordField,
                        saveButton,
                        backButton,
                        message
                );



        layout.setPadding(
                new Insets(30)
        );



        layout.setAlignment(
                Pos.CENTER
        );



        return layout;


    }


}