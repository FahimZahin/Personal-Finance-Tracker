package com.finance.view;


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



                DashboardView dashboard =
                        new DashboardView();



                Scene scene =
                        new Scene(
                                dashboard.getView(),
                                600,
                                400
                        );



                stage.setScene(scene);



                stage.setTitle(
                        "Dashboard"
                );



            }
            else {



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