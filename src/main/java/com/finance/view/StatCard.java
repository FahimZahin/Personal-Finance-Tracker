package com.finance.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class StatCard {

    public VBox create(String titleText, String valueText, String valueColor) {
        Label title = new Label(titleText.toUpperCase());
        title.getStyleClass().add("stat-card-title");

        Label value = new Label(valueText);
        value.getStyleClass().add("stat-card-value");
        // Keep the dynamic green/red/blue for the money values
        value.setStyle("-fx-text-fill: " + valueColor + ";");

        VBox card = new VBox(8, title, value);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(18, 25, 18, 25));
        card.setPrefWidth(210);
        card.setMaxWidth(220);
        card.getStyleClass().add("stat-card");

        return card;
    }

    public VBox create(String titleText, String valueText) {
        return create(titleText, valueText, "#3b82f6");
    }
}