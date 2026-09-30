package com.finance.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DashboardView {


    public VBox getView() {


        Label title = new Label(
                "Personal Finance Tracker"
        );


        Button expenseButton = new Button(
                "Manage Expenses"
        );


        Button incomeButton = new Button(
                "Manage Income"
        );


        Button transactionButton = new Button(
                "View Transactions"
        );


        Button logoutButton = new Button(
                "Logout"
        );


        VBox layout = new VBox(
                20,
                title,
                expenseButton,
                incomeButton,
                transactionButton,
                logoutButton
        );


        layout.setAlignment(Pos.CENTER);


        return layout;

    }

}