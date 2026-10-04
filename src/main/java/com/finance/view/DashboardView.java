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

        dashboardService = new DashboardService();
    }

    public VBox getView(Stage stage) {

        // ==========================================
        // CHECK SESSION
        // ==========================================

        if (Session.getUser() == null) {

            LoginView loginView = new LoginView();

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
        // GET DASHBOARD DATA
        // ==========================================

        double totalIncome =
                dashboardService.getTotalIncome(userId);

        double totalExpense =
                dashboardService.getTotalExpense(userId);

        double balance =
                totalIncome - totalExpense;

        // ==========================================
        // MAIN TITLE
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
        // WELCOME MESSAGE
        // ==========================================

        Label welcome =
                new Label(
                        "Welcome, " + username
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
        // INCOME CARD
        // ==========================================

        VBox incomeCard =
                createSummaryCard(
                        "TOTAL INCOME",
                        formatMoney(totalIncome)
                );

        // ==========================================
        // EXPENSE CARD
        // ==========================================

        VBox expenseCard =
                createSummaryCard(
                        "TOTAL EXPENSE",
                        formatMoney(totalExpense)
                );

        // ==========================================
        // BALANCE CARD
        // ==========================================

        VBox balanceCard =
                createSummaryCard(
                        "BALANCE",
                        formatMoney(balance)
                );

        // ==========================================
        // SUMMARY CARD CONTAINER
        // ==========================================

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

        // Make all cards share available width
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
        // MANAGE EXPENSES BUTTON
        // ==========================================

        Button expenseButton =
                new Button(
                        "Manage Expenses"
                );

        expenseButton.setPrefWidth(220);
        expenseButton.setPrefHeight(40);

        expenseButton.setStyle(
                "-fx-font-size: 14px;"
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

            stage.setScene(scene);
        });

        // ==========================================
        // MANAGE INCOME BUTTON
        // ==========================================

        Button incomeButton =
                new Button(
                        "Manage Income"
                );

        incomeButton.setPrefWidth(220);
        incomeButton.setPrefHeight(40);

        incomeButton.setStyle(
                "-fx-font-size: 14px;"
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

            stage.setScene(scene);
        });

        // ==========================================
        // TRANSACTIONS BUTTON
        // ==========================================

        Button transactionButton =
                new Button(
                        "Transactions"
                );

        transactionButton.setPrefWidth(220);
        transactionButton.setPrefHeight(40);

        transactionButton.setStyle(
                "-fx-font-size: 14px;"
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

            stage.setScene(scene);
        });

        // ==========================================
        // REFRESH DASHBOARD BUTTON
        // ==========================================

        Button refreshButton =
                new Button(
                        "Refresh Dashboard"
                );

        refreshButton.setPrefWidth(220);
        refreshButton.setPrefHeight(40);

        refreshButton.setStyle(
                "-fx-font-size: 14px;"
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

            stage.setScene(scene);
        });

        // ==========================================
        // LOGOUT BUTTON
        // ==========================================

        Button logoutButton =
                new Button(
                        "Logout"
                );

        logoutButton.setPrefWidth(220);
        logoutButton.setPrefHeight(40);

        logoutButton.setStyle(
                "-fx-font-size: 14px;"
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

            stage.setScene(scene);
        });

        // ==========================================
        // ACTION BUTTON CONTAINER
        // ==========================================

        VBox actionBox =
                new VBox(
                        12,
                        expenseButton,
                        incomeButton,
                        transactionButton,
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

        // ==========================================
        // WINDOW SIZE
        // ==========================================

        stage.setTitle(
                "Personal Finance Tracker"
        );

        return layout;
    }

    // ==========================================
    // CREATE SUMMARY CARD
    // ==========================================

    private VBox createSummaryCard(
            String title,
            String value
    ) {

        Label titleLabel =
                new Label(title);

        titleLabel.setStyle(
                "-fx-font-size: 13px;" +
                        "-fx-font-weight: bold;"
        );

        Label valueLabel =
                new Label(value);

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

        card.setPrefWidth(230);
        card.setPrefHeight(110);

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