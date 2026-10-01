package com.finance.view;


import com.finance.Session;


import javafx.geometry.Insets;
import javafx.geometry.Pos;


import javafx.scene.control.Button;
import javafx.scene.control.Label;


import javafx.scene.layout.VBox;


import javafx.scene.Scene;

import javafx.stage.Stage;



public class DashboardView {



    public VBox getView(Stage stage){



        Label welcome =
                new Label();



        if(Session.getUser() != null){


            welcome.setText(
                    "Welcome, "
                            +
                            Session.getUser().getUsername()
            );


        }
        else{


            welcome.setText(
                    "Welcome"
            );


        }






        Button logoutButton =
                new Button(
                        "Logout"
                );





        logoutButton.setOnAction(event -> {



            Session.clear();



            LoginView login =
                    new LoginView();



            Scene scene =
                    new Scene(
                            login.getView(stage),
                            500,
                            400
                    );



            stage.setScene(scene);



        });






        VBox layout =
                new VBox(

                        20,

                        welcome,

                        logoutButton

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