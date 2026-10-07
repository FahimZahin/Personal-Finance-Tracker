package com.finance.view;


import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;



public class StatCard {


    public VBox create(
            String title,
            String value
    ){


        Label titleLabel =
                new Label(title);



        Label valueLabel =
                new Label(value);



        VBox box =
                new VBox(
                        10,
                        titleLabel,
                        valueLabel
                );



        box.setPadding(
                new Insets(20)
        );



        box.setAlignment(
                Pos.CENTER
        );



        box.setMinWidth(
                180
        );



        return box;


    }


}