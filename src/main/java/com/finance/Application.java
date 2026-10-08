package com.finance;


import com.finance.database.DatabaseInitializer;
import com.finance.view.LoginView;

import javafx.scene.Scene;
import javafx.stage.Stage;



public class Application extends javafx.application.Application {


    private static final double APP_WIDTH = 900;
    private static final double APP_HEIGHT = 650;



    @Override
    public void start(Stage stage) {


        DatabaseInitializer.createTables();


        stage.setTitle(
                "Personal Finance Tracker"
        );


        stage.setWidth(APP_WIDTH);
        stage.setHeight(APP_HEIGHT);

        stage.setMinWidth(APP_WIDTH);
        stage.setMinHeight(APP_HEIGHT);

        stage.setMaxWidth(APP_WIDTH);
        stage.setMaxHeight(APP_HEIGHT);



        stage.sceneProperty().addListener(
                (obs, oldScene, newScene) -> {

                    if(newScene != null){

                        ThemeManager.applyTheme(newScene);

                    }

                }
        );



        LoginView loginView =
                new LoginView();



        Scene scene =
                new Scene(
                        loginView.getView(stage),
                        APP_WIDTH,
                        APP_HEIGHT
                );


        scene.getStylesheets()
                .add(
                        getClass()
                                .getResource("/style.css")
                                .toExternalForm()
                );


        ThemeManager.applyTheme(scene);


        stage.setScene(scene);


        stage.show();

    }



    public static void main(String[] args) {

        launch(args);

    }


}
