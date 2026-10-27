package com.finance.view;

import com.finance.Session;
import com.finance.service.DashboardService;
import com.finance.CurrencyManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.chart.BarChart;
import javafx.stage.Stage;

public class DashboardView {

    private final DashboardService dashboardService;

    public DashboardView(){
        dashboardService = new DashboardService();
    }

    public VBox getView(Stage stage){

        int userId = Session.getUser().getId();

        Label welcome = new Label(
                "Welcome, " + Session.getUser().getUsername()
        );

        double income = dashboardService.getTotalIncome(userId);
        double expense = dashboardService.getTotalExpense(userId);
        double balance = dashboardService.getBalance(userId);
        double savingsRate = dashboardService.getSavingsRate(userId);
        String topCategory = dashboardService.getTopExpenseCategory(userId);

        Label savingsLabel = new Label(
                "Savings Rate: " + String.format("%.2f%%", savingsRate)
        );

        Label topExpenseLabel = new Label(
                (topCategory == null || topCategory.isEmpty())
                        ? "Top Expense: None"
                        : "Top Expense: " + topCategory
        );

        DashboardChart dashboardChart = new DashboardChart();
        BarChart<String, Number> chart = dashboardChart.createIncomeExpenseChart(income, expense);

        StatCard card = new StatCard();
        VBox incomeCard = card.create("Total Income", CurrencyManager.format(income));
        VBox expenseCard = card.create("Total Expense", CurrencyManager.format(expense));
        VBox balanceCard = card.create("Balance", CurrencyManager.format(balance));

        // FIX: Using FlowPane instead of HBox so cards wrap nicely if the screen is narrow
        FlowPane cards = new FlowPane(20, 20);
        cards.setAlignment(Pos.CENTER);
        cards.getChildren().addAll(incomeCard, expenseCard, balanceCard);

        VBox layout = new VBox(
                30,
                welcome,
                cards,
                savingsLabel,
                topExpenseLabel,
                chart
        );

        // FIX: Reduced padding from 40 to 15 to free up horizontal space
        layout.setPadding(new Insets(15));
        layout.setAlignment(Pos.CENTER);

        return layout;
    }
}