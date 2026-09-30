package com.finance;

import com.finance.database.DatabaseInitializer;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Application extends javafx.application.Application {


    @Override
    public void start(Stage stage) {


        System.out.println("Starting Personal Finance Tracker...");


        DatabaseInitializer.createTables();


        Label welcomeLabel = new Label(
                "Welcome to Personal Finance Tracker"
        );


        StackPane root = new StackPane();

        root.getChildren().add(welcomeLabel);


        Scene scene = new Scene(root, 600, 400);


        stage.setTitle("Personal Finance Tracker");

        stage.setScene(scene);

        stage.show();

    }


    public static void main(String[] args) {

        launch();

    }

}