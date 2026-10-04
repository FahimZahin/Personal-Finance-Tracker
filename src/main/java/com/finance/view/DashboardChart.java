package com.finance.view;

import com.finance.model.Expense;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DashboardChart {

    // ==========================================
    // INCOME VS EXPENSE BAR CHART
    // ==========================================

    public BarChart<String, Number> createIncomeExpenseChart(
            double totalIncome,
            double totalExpense
    ) {

        CategoryAxis xAxis =
                new CategoryAxis();

        NumberAxis yAxis =
                new NumberAxis();

        xAxis.setLabel(
                "Transaction Type"
        );

        yAxis.setLabel(
                "Amount"
        );

        BarChart<String, Number> chart =
                new BarChart<>(
                        xAxis,
                        yAxis
                );

        chart.setTitle(
                "Income vs Expense"
        );

        chart.setAnimated(false);

        chart.setLegendVisible(false);

        chart.setPrefSize(
                400,
                320
        );

        XYChart.Series<String, Number> series =
                new XYChart.Series<>();

        series.getData().add(
                new XYChart.Data<>(
                        "Income",
                        Math.max(0, totalIncome)
                )
        );

        series.getData().add(
                new XYChart.Data<>(
                        "Expense",
                        Math.max(0, totalExpense)
                )
        );

        chart.getData().add(
                series
        );

        return chart;
    }

    // ==========================================
    // EXPENSE BY CATEGORY PIE CHART
    // ==========================================

    public PieChart createExpenseCategoryChart(
            List<Expense> expenses
    ) {

        PieChart chart =
                new PieChart();

        chart.setTitle(
                "Expense by Category"
        );

        chart.setAnimated(false);

        chart.setLabelsVisible(true);

        chart.setLegendVisible(true);

        chart.setPrefSize(
                400,
                320
        );

        Map<String, Double> categoryTotals =
                new LinkedHashMap<>();

        if (expenses != null) {

            for (Expense expense : expenses) {

                if (expense == null) {
                    continue;
                }

                double amount =
                        expense.getAmount();

                if (amount <= 0) {
                    continue;
                }

                String category =
                        expense.getCategory();

                if (
                        category == null
                                ||
                                category.isBlank()
                ) {

                    category = "Other";
                }

                category =
                        category.trim();

                categoryTotals.merge(
                        category,
                        amount,
                        Double::sum
                );
            }
        }

        // ==========================================
        // ADD DATA TO PIE CHART
        // ==========================================

        for (
                Map.Entry<String, Double> entry :
                categoryTotals.entrySet()
        ) {

            chart.getData().add(
                    new PieChart.Data(
                            entry.getKey(),
                            entry.getValue()
                    )
            );
        }

        return chart;
    }
}