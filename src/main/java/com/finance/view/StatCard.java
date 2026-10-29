package com.finance.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class StatCard {

    // Main method with custom color for the value text
    public VBox create(String titleText, String valueText, String valueColor) {
        Label title = new Label(titleText);
        // Force dark grey title so it is always visible
        title.setStyle("-fx-font-size: 14px; -fx-text-fill: #7f8c8d; -fx-font-weight: bold;");

        Label value = new Label(valueText);
        // Force the value color (e.g., green for income, red for expense)
        value.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: " + valueColor + ";");

        VBox card = new VBox(10, title, value);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(20));

        // Lock the width so they always fit exactly 3 in a row
        card.setPrefWidth(190);
        card.setMaxWidth(190);

        // Bulletproof styling to ensure white background and shadow, ignoring broken CSS
        card.setStyle("-fx-background-color: white; " +
                "-fx-background-radius: 8px; " +
                "-fx-border-radius: 8px; " +
                "-fx-border-color: #e0e0e0; " +
                "-fx-border-width: 1px; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.06), 10, 0, 0, 4);");

        return card;
    }

    // Overloaded method for backward compatibility
    public VBox create(String titleText, String valueText) {
        return create(titleText, valueText, "#2c3e50"); // Default dark color
    }
}