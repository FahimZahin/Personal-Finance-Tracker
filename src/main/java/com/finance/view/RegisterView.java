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



public class RegisterView {


    private final UserService userService;



    public RegisterView() {

        userService = new UserService();

    }




    public VBox getView(Stage stage) {



        Label title =
                new Label(
                        "Create Account"
                );



        TextField usernameField =
                new TextField();


        usernameField.setPromptText(
                "Username"
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



        Button registerButton =
                new Button(
                        "Register"
                );



        Button backButton =
                new Button(
                        "Back to Login"
                );



        Label message =
                new Label();





        registerButton.setOnAction(event -> {



            try {



                User user =
                        new User();



                user.setUsername(
                        usernameField.getText()
                );



                user.setEmail(
                        emailField.getText()
                );



                user.setPassword(
                        passwordField.getText()
                );


                if(
                        usernameField.getText().isEmpty()
                                ||
                                emailField.getText().isEmpty()
                                ||
                                passwordField.getText().isEmpty()
                ){

                    message.setText(
                            "All fields are required."
                    );

                    return;

                }
                userService.register(user);



                message.setText(
                        "Account created successfully!"
                );




            } catch(Exception e) {



                message.setText(
                        "Registration failed."
                );



            }



        });






        backButton.setOnAction(event -> {



            LoginView loginView =
                    new LoginView();



            Scene scene =
                    new Scene(
                            loginView.getView(stage),
                            500,
                            400
                    );



            stage.setScene(scene);



        });







        VBox layout =
                new VBox(

                        15,

                        title,

                        usernameField,

                        emailField,

                        passwordField,

                        registerButton,

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