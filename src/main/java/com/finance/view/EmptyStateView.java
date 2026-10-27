package com.finance.view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class EmptyStateView {

    public static VBox create(String titleText, String subtitleText) {
        Label iconLabel = new Label("📊");
        iconLabel.setStyle("-fx-font-size: 48px;");

        Label title = new Label(titleText);
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #7f8c8d;");

        Label subtitle = new Label(subtitleText);
        subtitle.setStyle("-fx-font-size: 13px; -fx-text-fill: #95a5a6;");

        VBox box = new VBox(10, iconLabel, title, subtitle);
        box.setAlignment(Pos.CENTER);
        box.setStyle("-fx-padding: 40px; -fx-background-color: white; -fx-background-radius: 12px;");
        return box;
    }
}