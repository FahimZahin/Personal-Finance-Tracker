package com.finance.view;


import javafx.scene.chart.PieChart;


public class DashboardChart {



    public PieChart createChart(
            double income,
            double expense
    ){



        PieChart chart =
                new PieChart();



        PieChart.Data incomeData =
                new PieChart.Data(
                        "Income",
                        income
                );



        PieChart.Data expenseData =
                new PieChart.Data(
                        "Expense",
                        expense
                );



        chart.getData()
                .addAll(
                        incomeData,
                        expenseData
                );



        chart.setTitle(
                "Income vs Expense"
        );



        chart.setPrefSize(
                350,
                300
        );



        return chart;


    }


}