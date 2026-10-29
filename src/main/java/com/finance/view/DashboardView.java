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
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.Node;
import javafx.stage.Stage;

public class DashboardView {

    private final DashboardService dashboardService;

    public DashboardView(){
        dashboardService = new DashboardService();
    }

    public VBox getView(Stage stage){

        int userId = Session.getUser().getId();

        // Welcome Header
        Label welcome = new Label("Welcome back, " + Session.getUser().getUsername() + " 👋");
        welcome.setStyle("-fx-font-size: 26px; -fx-font-weight: bold;");

        // Fetch Data
        double income = dashboardService.getTotalIncome(userId);
        double expense = dashboardService.getTotalExpense(userId);
        double balance = dashboardService.getBalance(userId);
        double savingsRate = dashboardService.getSavingsRate(userId);
        String topCategory = dashboardService.getTopExpenseCategory(userId);

        // Metric Badges (Savings Rate & Top Expense)
        Label savingsBadge = new Label("Savings Rate: " + String.format("%.2f%%", savingsRate));
        savingsBadge.getStyleClass().add("info-pill"); // Let CSS handle the colors

        Label topExpenseBadge = new Label(
                (topCategory == null || topCategory.isEmpty())
                        ? "Top Expense: None"
                        : "Top Expense: " + topCategory
        );
        topExpenseBadge.getStyleClass().add("info-pill"); // Let CSS handle the colors

        HBox badges = new HBox(15, savingsBadge, topExpenseBadge);
        badges.setAlignment(Pos.CENTER);

        // Stat Cards Generation
        StatCard card = new StatCard();
        VBox incomeCard = card.create("Total Income", CurrencyManager.format(income), "#10b981");
        VBox expenseCard = card.create("Total Expense", CurrencyManager.format(expense), "#ef4444");
        VBox balanceCard = card.create("Net Balance", CurrencyManager.format(balance), "#3b82f6");

        HBox cards = new HBox(20, incomeCard, expenseCard, balanceCard);
        cards.setAlignment(Pos.CENTER);

        // --- Custom Modern Chart Setup ---
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Transaction Type");
        yAxis.setLabel("Amount (" + CurrencyManager.getCurrency() + ")");

        // Hardcoded text colors removed so CSS can target .axis, .axis-label, and .chart-title
        BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);
        chart.setTitle("Income vs Expense Overview");
        chart.setLegendVisible(false);

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        XYChart.Data<String, Number> incomeData = new XYChart.Data<>("Income", income);
        XYChart.Data<String, Number> expenseData = new XYChart.Data<>("Expense", expense);

        series.getData().add(incomeData);
        series.getData().add(expenseData);
        chart.getData().add(series);

        // Apply distinct green/red colors once rendered
        chart.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                Node b1 = incomeData.getNode();
                if (b1 != null) b1.setStyle("-fx-bar-fill: #10b981; -fx-background-radius: 6 6 0 0;");
                Node b2 = expenseData.getNode();
                if (b2 != null) b2.setStyle("-fx-bar-fill: #ef4444; -fx-background-radius: 6 6 0 0;");
            }
        });

        chart.setPrefSize(600, 320);
        chart.setMaxWidth(680);

        // Card wrapper for the chart (Hardcoded white background removed)
        VBox chartCard = new VBox(chart);
        chartCard.setAlignment(Pos.CENTER);
        chartCard.setPadding(new Insets(15));
        chartCard.getStyleClass().add("chart-card-container");
        chartCard.setStyle("-fx-background-radius: 12px; -fx-border-radius: 12px; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.04), 12, 0, 0, 4);");

        // Assembly
        VBox layout = new VBox(25, welcome, cards, badges, chartCard);
        layout.setPadding(new Insets(25, 40, 40, 40));
        layout.setAlignment(Pos.TOP_CENTER);

        return layout;
    }
}