package com.finance;


import com.finance.database.DatabaseInitializer;
import com.finance.view.LoginView;

import javafx.scene.Scene;
import javafx.stage.Stage;



public class Application extends javafx.application.Application {


    @Override
    public void start(Stage stage) {


        DatabaseInitializer.createTables();


        LoginView loginView =
                new LoginView();


        Scene scene =
                new Scene(
                        loginView.getView(stage),
                        500,
                        400
                );


        stage.setTitle(
                "Personal Finance Tracker"
        );


        stage.setScene(scene);


        stage.show();

    }



    public static void main(String[] args) {


        launch(args);


    }


}