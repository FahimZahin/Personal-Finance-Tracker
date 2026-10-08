package com.finance;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {

    public static void changeScene(Stage stage, Parent root) {

        Scene scene = new Scene(
                root,
                AppConstants.WIDTH,
                AppConstants.HEIGHT
        );

        ThemeManager.applyTheme(scene);

        stage.setScene(scene);
    }
}
