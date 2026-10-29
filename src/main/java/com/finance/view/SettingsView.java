package com.finance.view;

import com.finance.CurrencyManager;
import com.finance.ThemeManager;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SettingsView {

    public VBox getView(Stage stage) {

        Label title = new Label("Settings");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // 1. Currency Dropdown
        Label currencyLabel = new Label("Currency:");
        currencyLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        ComboBox<String> currencyBox = new ComboBox<>();
        currencyBox.getItems().addAll("৳", "$", "€", "£");
        currencyBox.setValue(CurrencyManager.getCurrency());
        currencyBox.setPrefWidth(120);

        // 2. Theme Dropdown
        Label themeLabel = new Label("Theme:");
        themeLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        ComboBox<String> themeBox = new ComboBox<>();
        themeBox.getItems().addAll("LIGHT", "DARK");
        themeBox.setValue(ThemeManager.isDarkMode() ? "DARK" : "LIGHT");
        themeBox.setPrefWidth(120);

        // Form Layout
        GridPane grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setAlignment(Pos.CENTER);

        grid.add(currencyLabel, 0, 0);
        grid.add(currencyBox, 1, 0);
        grid.add(themeLabel, 0, 1);
        grid.add(themeBox, 1, 1);

        // 3. Save Button
        Button saveButton = new Button("Save Settings");
        saveButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 8 20; -fx-background-radius: 5; -fx-cursor: hand;");

        Label statusLabel = new Label();

        saveButton.setOnAction(event -> {
            // Save Currency
            if (currencyBox.getValue() != null) {
                CurrencyManager.setCurrency(currencyBox.getValue());
            }

            // Save Theme State
            String selectedTheme = themeBox.getValue();
            boolean isDark = "DARK".equalsIgnoreCase(selectedTheme);
            ThemeManager.setDarkMode(isDark);

            // Re-apply Theme directly to current Scene
            if (stage != null && stage.getScene() != null) {
                ThemeManager.applyTheme(stage.getScene());
            }

            statusLabel.setText("Settings saved successfully!");
            statusLabel.setStyle("-fx-text-fill: #27ae60; -fx-font-weight: bold;");
        });

        VBox layout = new VBox(25, title, grid, saveButton, statusLabel);
        layout.setPadding(new Insets(40));
        layout.setAlignment(Pos.TOP_CENTER);

        return layout;
    }
}