package com.finance.view;


import com.finance.model.User;
import com.finance.service.UserService;

import com.finance.Session;
import javafx.geometry.Insets;
import javafx.geometry.Pos;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


import javafx.scene.layout.VBox;

import javafx.stage.Stage;



public class LoginView {



    private final UserService userService;



    public LoginView(){

        userService = new UserService();

    }




    public VBox getView(Stage stage){



        Label title =
                new Label(
                        "Personal Finance Tracker Login"
                );



        TextField emailField =
                new TextField();


        emailField.setPromptText(
                "Email"
        );




        PasswordField passwordField =
                new PasswordField();


        passwordField.setPromptText(
                "Password"
        );




        Button loginButton =
                new Button(
                        "Login"
                );

        Button registerButton =
                new Button(
                        "Create Account"
                );

        registerButton.setOnAction(event -> {


            RegisterView registerView =
                    new RegisterView();



            Scene scene =
                    new Scene(
                            registerView.getView(stage),
                            500,
                            400
                    );


            stage.setScene(scene);


        });



        Label message =
                new Label();






        loginButton.setOnAction(event -> {



            String email =
                    emailField.getText();



            String password =
                    passwordField.getText();




            User user =
                    userService.login(
                            email,
                            password
                    );




            if(user != null){


                Session.setUser(user);



                DashboardView dashboard =
                        new DashboardView();



                Scene scene =
                        new Scene(
                                dashboard.getView(stage),
                                700,
                                500
                        );


                stage.setScene(scene);


            }
            else{


                message.setText(
                        "Invalid email or password"
                );


            }



        });






        VBox layout =
                new VBox(

                        15,

                        title,

                        emailField,

                        passwordField,

                        loginButton,

                        registerButton,

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