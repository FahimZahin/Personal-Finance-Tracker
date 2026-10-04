package com.finance.view;

import com.finance.Session;
import com.finance.model.Expense;
import com.finance.service.DashboardService;
import com.finance.service.ExpenseService;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Scene;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.ScrollPane;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;

import java.util.List;
import java.util.Locale;

public class AnalyticsView {

    private final DashboardService dashboardService;

    private final ExpenseService expenseService;

    private final DashboardChart dashboardChart;


    public AnalyticsView() {

        dashboardService =
                new DashboardService();

        expenseService =
                new ExpenseService();

        dashboardChart =
                new DashboardChart();
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
        // USER ID
        // ==========================================

        int userId =
                Session.getUser().getId();


        // ==========================================
        // DASHBOARD DATA
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
        // EXPENSE DATA
        // ==========================================

        List<Expense> expenses =
                expenseService.getExpensesByUser(
                        userId
                );


        // ==========================================
        // TITLE
        // ==========================================

        Label title =
                new Label(
                        "Financial Analytics"
                );

        title.setStyle(
                "-fx-font-size: 28px;" +
                        "-fx-font-weight: bold;"
        );


        // ==========================================
        // DESCRIPTION
        // ==========================================

        Label description =
                new Label(
                        "Analyze your income, expenses and spending categories"
                );

        description.setStyle(
                "-fx-font-size: 14px;"
        );


        // ==========================================
        // SUMMARY
        // ==========================================

        Label summary =
                new Label(
                        "Income: "
                                +
                                formatMoney(totalIncome)
                                +
                                "    |    Expense: "
                                +
                                formatMoney(totalExpense)
                                +
                                "    |    Balance: "
                                +
                                formatMoney(balance)
                );

        summary.setStyle(
                "-fx-font-size: 16px;" +
                        "-fx-font-weight: bold;"
        );


        // ==========================================
        // INCOME VS EXPENSE CHART
        // ==========================================

        BarChart<String, Number> incomeExpenseChart =
                dashboardChart.createIncomeExpenseChart(
                        totalIncome,
                        totalExpense
                );


        // ==========================================
        // EXPENSE CATEGORY CHART
        // ==========================================

        PieChart expenseCategoryChart =
                dashboardChart.createExpenseCategoryChart(
                        expenses
                );


        // ==========================================
        // CHART CONTAINER
        // ==========================================

        HBox charts =
                new HBox(
                        25,
                        incomeExpenseChart,
                        expenseCategoryChart
                );

        charts.setAlignment(
                Pos.CENTER
        );


        // ==========================================
        // NO EXPENSE MESSAGE
        // ==========================================

        Label noExpenseMessage =
                new Label();


        if (
                expenses == null
                        ||
                        expenses.isEmpty()
        ) {

            noExpenseMessage.setText(
                    "No expenses have been recorded yet."
            );

            noExpenseMessage.setStyle(
                    "-fx-font-size: 14px;"
            );

        }


        // ==========================================
        // BACK BUTTON
        // ==========================================

        Button backButton =
                new Button(
                        "Back to Dashboard"
                );

        backButton.setPrefWidth(
                220
        );

        backButton.setPrefHeight(
                40
        );

        backButton.setStyle(
                "-fx-font-size: 14px;"
        );


        backButton.setOnAction(event -> {

            DashboardView dashboardView =
                    new DashboardView();


            Scene scene =
                    new Scene(
                            dashboardView.getView(stage),
                            850,
                            600
                    );


            stage.setScene(
                    scene
            );
        });


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
        // CONTENT
        // ==========================================

        VBox content =
                new VBox(
                        20,

                        title,

                        description,

                        summary,

                        charts,

                        noExpenseMessage,

                        spacer,

                        backButton
                );


        content.setPadding(
                new Insets(30)
        );

        content.setAlignment(
                Pos.CENTER
        );


        // ==========================================
        // SCROLL PANE
        // ==========================================

        ScrollPane scrollPane =
                new ScrollPane(
                        content
                );

        scrollPane.setFitToWidth(
                true
        );

        scrollPane.setFitToHeight(
                true
        );

        scrollPane.setPannable(
                true
        );


        // ==========================================
        // MAIN LAYOUT
        // ==========================================

        VBox layout =
                new VBox(
                        scrollPane
                );


        VBox.setVgrow(
                scrollPane,
                Priority.ALWAYS
        );


        layout.setStyle(
                "-fx-background-color: #f5f7fa;"
        );


        return layout;
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