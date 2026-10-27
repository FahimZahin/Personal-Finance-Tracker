package com.finance;

import com.finance.database.DatabaseInitializer;
import com.finance.view.LoginView;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) {

        DatabaseInitializer.createTables();

        stage.setTitle("Personal Finance Tracker");

        stage.setWidth(AppConstants.WIDTH);
        stage.setHeight(AppConstants.HEIGHT);
        stage.setResizable(false); // Prevents resizing safely

        stage.sceneProperty().addListener(
                (obs, oldScene, newScene) -> {
                    if(newScene != null){
                        ThemeManager.applyTheme(newScene);
                    }
                }
        );

        LoginView loginView = new LoginView();
        Scene scene = new Scene(
                loginView.getView(stage),
                AppConstants.WIDTH,
                AppConstants.HEIGHT
        );

        // Safely load CSS
        java.net.URL styleResource = getClass().getResource("/style.css");
        if (styleResource != null) {
            scene.getStylesheets().add(styleResource.toExternalForm());
        } else {
            System.err.println("Warning: /style.css not found.");
        }

        ThemeManager.applyTheme(scene);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}