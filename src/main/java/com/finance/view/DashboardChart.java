package com.finance.view;

import com.finance.model.Expense;
import com.finance.model.Income;

import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DashboardChart {

    // ==========================================
    // 1. INCOME VS EXPENSE BAR CHART (LEGACY / DASHBOARD)
    // ==========================================
    public BarChart<String, Number> createIncomeExpenseChart(
            double totalIncome,
            double totalExpense
    ) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("Transaction Type");
        yAxis.setLabel("Amount");

        BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);
        chart.setTitle("Income vs Expense");
        chart.setAnimated(false);
        chart.setLegendVisible(false);
        chart.setPrefSize(400, 320);

        XYChart.Series<String, Number> series = new XYChart.Series<>();

        XYChart.Data<String, Number> incomeData = new XYChart.Data<>("Income", Math.max(0, totalIncome));
        XYChart.Data<String, Number> expenseData = new XYChart.Data<>("Expense", Math.max(0, totalExpense));

        series.getData().add(incomeData);
        series.getData().add(expenseData);

        chart.getData().add(series);

        if (expenseData.getNode() != null) {
            expenseData.getNode().setStyle("-fx-bar-fill: #e74c3c;");
        }

        if (incomeData.getNode() != null) {
            incomeData.getNode().setStyle("-fx-bar-fill: #27ae60;");
        }

        return chart;
    }

    // ==========================================
    // 2. EXPENSE BY CATEGORY (PIE CHART)
    // ==========================================
    public PieChart createExpenseCategoryChart(List<Expense> expenses) {
        PieChart chart = new PieChart();
        chart.setTitle("Expense Breakdown by Category");
        chart.setAnimated(true);
        chart.setLabelsVisible(true);
        chart.setLegendVisible(true);
        chart.setPrefSize(400, 320);

        if (expenses == null || expenses.isEmpty()) {
            return chart;
        }

        Map<String, Double> categoryTotals = new LinkedHashMap<>();
        double grandTotal = 0.0;

        for (Expense expense : expenses) {
            if (expense == null || expense.getAmount() <= 0) continue;
            String category = (expense.getCategory() == null || expense.getCategory().isBlank())
                    ? "Other" : expense.getCategory().trim();

            categoryTotals.merge(category, expense.getAmount(), Double::sum);
            grandTotal += expense.getAmount();
        }

        for (Map.Entry<String, Double> entry : categoryTotals.entrySet()) {
            double percentage = (entry.getValue() / grandTotal) * 100.0;
            String label = String.format("%s (%.1f%%)", entry.getKey(), percentage);
            chart.getData().add(new PieChart.Data(label, entry.getValue()));
        }

        return chart;
    }

    // ==========================================
    // 3. MONTHLY FINANCIAL TREND (AREA CHART)
    // ==========================================
    public AreaChart<String, Number> createMonthlyTrendChart(List<Income> incomes, List<Expense> expenses) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Month");
        yAxis.setLabel("Amount");

        AreaChart<String, Number> chart = new AreaChart<>(xAxis, yAxis);
        chart.setTitle("Income vs. Expense Trend");
        chart.setAnimated(true);
        chart.setPrefSize(450, 320);

        XYChart.Series<String, Number> incomeSeries = new XYChart.Series<>();
        incomeSeries.setName("Income");

        XYChart.Series<String, Number> expenseSeries = new XYChart.Series<>();
        expenseSeries.setName("Expenses");

        Map<String, Double> monthlyIncome = new TreeMap<>();
        Map<String, Double> monthlyExpense = new TreeMap<>();
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMM yyyy");

        if (incomes != null) {
            for (Income inc : incomes) {
                if (inc.getDate() != null) {
                    String monthKey = inc.getDate().format(monthFormatter);
                    monthlyIncome.merge(monthKey, inc.getAmount(), Double::sum);
                }
            }
        }

        if (expenses != null) {
            for (Expense exp : expenses) {
                if (exp.getDate() != null) {
                    String monthKey = exp.getDate().format(monthFormatter);
                    monthlyExpense.merge(monthKey, exp.getAmount(), Double::sum);
                }
            }
        }

        for (Map.Entry<String, Double> entry : monthlyIncome.entrySet()) {
            incomeSeries.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        for (Map.Entry<String, Double> entry : monthlyExpense.entrySet()) {
            expenseSeries.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        chart.getData().addAll(incomeSeries, expenseSeries);
        return chart;
    }
}