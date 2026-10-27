package com.finance.view;


import com.finance.model.User;
import com.finance.service.UserService;

import com.finance.Session;
import com.finance.AppConstants;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import com.finance.CurrencyManager;
import com.finance.ThemeManager;
import com.finance.model.UserSettings;
import com.finance.service.SettingsService;

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



                SettingsService settingsService =
                        new SettingsService();



                UserSettings settings =
                        settingsService.getSettings(
                                user.getId()
                        );

                CurrencyManager.setCurrency(
                        settings.getCurrency()
                );



                ThemeManager.setDarkMode(
                        settings.getTheme()
                                .equals("DARK")
                );



                // ON SUCCESSFUL LOGIN: Load the new Sidebar Layout
                MainLayout mainLayout = new MainLayout(stage);

                // Set the dashboard as the default starting screen in the center
                mainLayout.setContent(new DashboardView().getView(stage));

                Scene mainScene = new Scene(
                        mainLayout.getLayout(),
                        AppConstants.WIDTH,
                        AppConstants.HEIGHT
                );

                // Make sure the CSS is applied to the new layout
                java.net.URL styleResource = getClass().getResource("/style.css");
                if (styleResource != null) {
                    mainScene.getStylesheets().add(styleResource.toExternalForm());
                }

                ThemeManager.applyTheme(mainScene);
                stage.setScene(mainScene);



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