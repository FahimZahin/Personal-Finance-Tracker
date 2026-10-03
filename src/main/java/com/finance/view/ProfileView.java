package com.finance.view;


import com.finance.Session;
import com.finance.model.User;


import javafx.geometry.Insets;
import javafx.geometry.Pos;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.VBox;

import javafx.stage.Stage;



public class ProfileView {



    public VBox getView(Stage stage){



        User user =
                Session.getUser();




        Label title =
                new Label(
                        "User Profile"
                );




        Label usernameLabel =
                new Label(
                        "Username: "
                                +
                                user.getUsername()
                );



        Label emailLabel =
                new Label(
                        "Email: "
                                +
                                user.getEmail()
                );






        Button backButton =
                new Button(
                        "Back to Dashboard"
                );




        backButton.setOnAction(event -> {



            DashboardView dashboardView =
                    new DashboardView();



            Scene scene =
                    new Scene(
                            dashboardView.getView(stage),
                            600,
                            500
                    );



            stage.setScene(scene);



        });







        VBox layout =
                new VBox(

                        20,

                        title,

                        usernameLabel,

                        emailLabel,

                        backButton

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