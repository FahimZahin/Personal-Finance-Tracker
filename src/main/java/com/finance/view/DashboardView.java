package com.finance.view;

import com.finance.Session;
import com.finance.service.DashboardService;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;

import java.util.Locale;


public class DashboardView {


    private final DashboardService dashboardService;


    public DashboardView() {

        dashboardService =
                new DashboardService();
    }


    public VBox getView(Stage stage) {


        // ==========================================
        // SESSION CHECK
        // ==========================================

        if (Session.getUser() == null) {

            LoginView loginView =
                    new LoginView();

            return loginView.getView(stage);
        }


        // ==========================================
        // USER INFORMATION
        // ==========================================

        int userId =
                Session.getUser().getId();


        String username =
                Session.getUser().getUsername();


        // ==========================================
        // DASHBOARD VALUES
        // ==========================================

        double totalIncome =
                dashboardService.getTotalIncome(
                        userId
                );


        double totalExpense =
                dashboardService.getTotalExpense(
                        userId
                );


        double balance =
                totalIncome - totalExpense;


        // ==========================================
        // TITLE
        // ==========================================

        Label title =
                new Label(
                        "Personal Finance Tracker"
                );


        title.setStyle(
                "-fx-font-size: 28px;" +
                        "-fx-font-weight: bold;"
        );


        // ==========================================
        // WELCOME
        // ==========================================

        Label welcome =
                new Label(
                        "Welcome, "
                                +
                                username
                );


        welcome.setStyle(
                "-fx-font-size: 18px;" +
                        "-fx-font-weight: bold;"
        );


        // ==========================================
        // SUBTITLE
        // ==========================================

        Label subtitle =
                new Label(
                        "Manage your income, expenses and transactions"
                );


        subtitle.setStyle(
                "-fx-font-size: 14px;"
        );


        // ==========================================
        // SUMMARY CARDS
        // ==========================================

        VBox incomeCard =
                createSummaryCard(
                        "TOTAL INCOME",
                        formatMoney(totalIncome)
                );


        VBox expenseCard =
                createSummaryCard(
                        "TOTAL EXPENSE",
                        formatMoney(totalExpense)
                );


        VBox balanceCard =
                createSummaryCard(
                        "BALANCE",
                        formatMoney(balance)
                );


        HBox summaryBox =
                new HBox(
                        15,
                        incomeCard,
                        expenseCard,
                        balanceCard
                );


        summaryBox.setAlignment(
                Pos.CENTER
        );


        HBox.setHgrow(
                incomeCard,
                Priority.ALWAYS
        );


        HBox.setHgrow(
                expenseCard,
                Priority.ALWAYS
        );


        HBox.setHgrow(
                balanceCard,
                Priority.ALWAYS
        );


        // ==========================================
        // PROFILE BUTTON
        // ==========================================

        Button profileButton =
                createButton(
                        "Profile"
                );


        profileButton.setOnAction(event -> {


            ProfileView profileView =
                    new ProfileView();


            Scene scene =
                    new Scene(
                            profileView.getView(stage),
                            500,
                            400
                    );


            stage.setScene(
                    scene
            );
        });


        // ==========================================
        // EXPENSE BUTTON
        // ==========================================

        Button expenseButton =
                createButton(
                        "Manage Expenses"
                );


        expenseButton.setOnAction(event -> {


            ExpenseView expenseView =
                    new ExpenseView();


            Scene scene =
                    new Scene(
                            expenseView.getView(stage),
                            500,
                            500
                    );


            stage.setScene(
                    scene
            );
        });


        // ==========================================
        // INCOME BUTTON
        // ==========================================

        Button incomeButton =
                createButton(
                        "Manage Income"
                );


        incomeButton.setOnAction(event -> {


            IncomeView incomeView =
                    new IncomeView();


            Scene scene =
                    new Scene(
                            incomeView.getView(stage),
                            500,
                            500
                    );


            stage.setScene(
                    scene
            );
        });


        // ==========================================
        // TRANSACTION BUTTON
        // ==========================================

        Button transactionButton =
                createButton(
                        "Transactions"
                );


        transactionButton.setOnAction(event -> {


            TransactionView transactionView =
                    new TransactionView();


            Scene scene =
                    new Scene(
                            transactionView.getView(stage),
                            700,
                            500
                    );


            stage.setScene(
                    scene
            );
        });


        // ==========================================
        // ANALYTICS BUTTON
        // ==========================================

        Button analyticsButton =
                createButton(
                        "Financial Analytics"
                );


        analyticsButton.setOnAction(event -> {


            AnalyticsView analyticsView =
                    new AnalyticsView();


            Scene scene =
                    new Scene(
                            analyticsView.getView(stage),
                            900,
                            700
                    );


            stage.setScene(
                    scene
            );
        });


        // ==========================================
        // REFRESH BUTTON
        // ==========================================

        Button refreshButton =
                createButton(
                        "Refresh Dashboard"
                );


        refreshButton.setOnAction(event -> {


            DashboardView refreshedDashboard =
                    new DashboardView();


            Scene scene =
                    new Scene(
                            refreshedDashboard.getView(stage),
                            850,
                            600
                    );


            stage.setScene(
                    scene
            );
        });


        // ==========================================
        // LOGOUT BUTTON
        // ==========================================

        Button logoutButton =
                createButton(
                        "Logout"
                );


        logoutButton.setOnAction(event -> {


            Session.clear();


            LoginView loginView =
                    new LoginView();


            Scene scene =
                    new Scene(
                            loginView.getView(stage),
                            500,
                            400
                    );


            stage.setScene(
                    scene
            );
        });


        // ==========================================
        // ACTION BUTTONS
        // ==========================================

        VBox actionBox =
                new VBox(
                        10,

                        profileButton,

                        expenseButton,

                        incomeButton,

                        transactionButton,

                        analyticsButton,

                        refreshButton,

                        logoutButton
                );


        actionBox.setAlignment(
                Pos.CENTER
        );


        // ==========================================
        // SPACER
        // ==========================================

        Region spacer =
                new Region();


        VBox.setVgrow(
                spacer,
                Priority.ALWAYS
        );


        // ==========================================
        // MAIN LAYOUT
        // ==========================================

        VBox layout =
                new VBox(

                        20,

                        title,

                        welcome,

                        subtitle,

                        summaryBox,

                        spacer,

                        actionBox
                );


        layout.setPadding(
                new Insets(30)
        );


        layout.setAlignment(
                Pos.CENTER
        );


        layout.setStyle(
                "-fx-background-color: #f5f7fa;"
        );


        return layout;
    }


    // ==========================================
    // SUMMARY CARD
    // ==========================================

    private VBox createSummaryCard(
            String title,
            String value
    ) {


        Label titleLabel =
                new Label(
                        title
                );


        titleLabel.setStyle(
                "-fx-font-size: 13px;" +
                        "-fx-font-weight: bold;"
        );


        Label valueLabel =
                new Label(
                        value
                );


        valueLabel.setStyle(
                "-fx-font-size: 22px;" +
                        "-fx-font-weight: bold;"
        );


        VBox card =
                new VBox(
                        10,

                        titleLabel,

                        valueLabel
                );


        card.setAlignment(
                Pos.CENTER
        );


        card.setPrefWidth(
                230
        );


        card.setPrefHeight(
                110
        );


        card.setMaxWidth(
                Double.MAX_VALUE
        );


        card.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 10;" +
                        "-fx-border-radius: 10;" +
                        "-fx-border-color: #d9dee5;" +
                        "-fx-padding: 20;"
        );


        return card;
    }


    // ==========================================
    // CREATE BUTTON
    // ==========================================

    private Button createButton(
            String text
    ) {


        Button button =
                new Button(
                        text
                );


        button.setPrefWidth(
                220
        );


        button.setPrefHeight(
                40
        );


        button.setStyle(
                "-fx-font-size: 14px;"
        );


        return button;
    }


    // ==========================================
    // FORMAT MONEY
    // ==========================================

    private String formatMoney(
            double amount
    ) {


        return String.format(
                Locale.US,
                "%.2f",
                amount
        );
    }
}