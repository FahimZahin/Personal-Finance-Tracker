package com.finance.view;

import com.finance.Session;
import com.finance.service.DashboardService;
import com.finance.CurrencyManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
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

        Label welcome = new Label("Welcome, " + Session.getUser().getUsername());
        // Force text color to be visible
        welcome.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        double income = dashboardService.getTotalIncome(userId);
        double expense = dashboardService.getTotalExpense(userId);
        double balance = dashboardService.getBalance(userId);
        double savingsRate = dashboardService.getSavingsRate(userId);
        String topCategory = dashboardService.getTopExpenseCategory(userId);

        Label savingsLabel = new Label("Savings Rate: " + String.format("%.2f%%", savingsRate));
        savingsLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #34495e;");

        Label topExpenseLabel = new Label(
                (topCategory == null || topCategory.isEmpty())
                        ? "Top Expense: None"
                        : "Top Expense: " + topCategory
        );
        topExpenseLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #34495e;");

        DashboardChart dashboardChart = new DashboardChart();
        BarChart<String, Number> chart = dashboardChart.createIncomeExpenseChart(income, expense);
        // Lock chart dimensions so it doesn't stretch off the screen
        chart.setPrefSize(500, 300);
        chart.setMaxWidth(600);

        // Generate the styled cards (Green for income, Red for expense, Blue for balance)
        StatCard card = new StatCard();
        VBox incomeCard = card.create("Total Income", CurrencyManager.format(income), "#27ae60");
        VBox expenseCard = card.create("Total Expense", CurrencyManager.format(expense), "#e74c3c");
        VBox balanceCard = card.create("Balance", CurrencyManager.format(balance), "#2980b9");

        // Use HBox to force them onto exactly one row
        HBox cards = new HBox(20);
        cards.setAlignment(Pos.CENTER);
        cards.getChildren().addAll(incomeCard, expenseCard, balanceCard);

        VBox layout = new VBox(
                25,
                welcome,
                cards,
                savingsLabel,
                topExpenseLabel,
                chart
        );

        layout.setPadding(new Insets(20));
        // Align TOP_CENTER so it anchors nicely to the top of the scroll pane
        layout.setAlignment(Pos.TOP_CENTER);

        return layout;
    }
}