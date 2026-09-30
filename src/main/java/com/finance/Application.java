package com.finance;

import com.finance.database.DatabaseInitializer;
import com.finance.view.DashboardView;

import javafx.stage.Stage;


public class Application extends javafx.application.Application {


    @Override
    public void start(Stage stage) {


        DatabaseInitializer.createTables();


        DashboardView dashboardView = new DashboardView();


        javafx.scene.Scene scene =
                new javafx.scene.Scene(
                        dashboardView.getView(),
                        600,
                        400
                );


        stage.setTitle("Personal Finance Tracker");

        stage.setScene(scene);

        stage.show();

    }


    public static void main(String[] args) {

        launch(args);

    }

}