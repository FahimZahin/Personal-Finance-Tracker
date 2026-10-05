package com.finance.view;


import com.finance.Session;
import com.finance.model.CategoryExpense;
import com.finance.service.DashboardService;


import javafx.geometry.Insets;
import javafx.geometry.Pos;


import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;

import javafx.scene.chart.XYChart;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;


import java.util.List;



public class DashboardView {


    private final DashboardService dashboardService;



    public DashboardView(){


        dashboardService =
                new DashboardService();


    }





    public VBox getView(Stage stage){


        int userId =
                Session.getUser()
                        .getId();




        Label welcome =
                new Label(
                        "Welcome, "
                                +
                                Session.getUser()
                                        .getUsername()
                );




        double totalIncome =
                dashboardService.getTotalIncome(
                        userId
                );


        double totalExpense =
                dashboardService.getTotalExpense(
                        userId
                );


        double balance =
                dashboardService.getBalance(
                        userId
                );





        Label incomeLabel =
                new Label(
                        "Total Income: "
                                +
                                totalIncome
                );



        Label expenseLabel =
                new Label(
                        "Total Expense: "
                                +
                                totalExpense
                );



        Label balanceLabel =
                new Label(
                        "Balance: "
                                +
                                balance
                );





        CategoryAxis xAxis =
                new CategoryAxis();


        NumberAxis yAxis =
                new NumberAxis();



        BarChart<String,Number> chart =
                new BarChart<>(
                        xAxis,
                        yAxis
                );



        chart.setTitle(
                "Income vs Expense"
        );



        XYChart.Series<String,Number> series =
                new XYChart.Series<>();


        series.setName(
                "Amount"
        );



        series.getData()
                .add(
                        new XYChart.Data<>(
                                "Income",
                                totalIncome
                        )
                );



        series.getData()
                .add(
                        new XYChart.Data<>(
                                "Expense",
                                totalExpense
                        )
                );



        chart.getData()
                .add(series);




        Label categoryTitle =
                new Label(
                        "Expense Categories"
                );



        VBox categoryBox =
                new VBox(
                        5
                );



        List<CategoryExpense> categories =
                dashboardService.getExpenseByCategory(
                        userId
                );



        for(CategoryExpense category : categories){


            categoryBox.getChildren()
                    .add(
                            new Label(
                                    category.getCategory()
                                            +
                                            " : "
                                            +
                                            category.getAmount()
                            )
                    );


        }






        Button expenseButton =
                new Button(
                        "Manage Expenses"
                );



        Button incomeButton =
                new Button(
                        "Manage Income"
                );



        Button transactionButton =
                new Button(
                        "Transactions"
                );



        Button logoutButton =
                new Button(
                        "Logout"
                );






        expenseButton.setOnAction(event -> {


            ExpenseView view =
                    new ExpenseView();



            stage.setScene(
                    new Scene(
                            view.getView(stage),
                            500,
                            500
                    )
            );


        });






        incomeButton.setOnAction(event -> {


            IncomeView view =
                    new IncomeView();



            stage.setScene(
                    new Scene(
                            view.getView(stage),
                            500,
                            500
                    )
            );


        });






        transactionButton.setOnAction(event -> {


            TransactionView view =
                    new TransactionView();



            stage.setScene(
                    new Scene(
                            view.getView(stage),
                            700,
                            500
                    )
            );


        });







        logoutButton.setOnAction(event -> {


            Session.clear();


            LoginView view =
                    new LoginView();



            stage.setScene(
                    new Scene(
                            view.getView(stage),
                            500,
                            400
                    )
            );


        });







        HBox buttons =
                new HBox(
                        10,
                        expenseButton,
                        incomeButton,
                        transactionButton,
                        logoutButton
                );



        buttons.setAlignment(
                Pos.CENTER
        );







        VBox layout =
                new VBox(
                        15,
                        welcome,
                        incomeLabel,
                        expenseLabel,
                        balanceLabel,
                        chart,
                        categoryTitle,
                        categoryBox,
                        buttons
                );



        layout.setPadding(
                new Insets(20)
        );


        layout.setAlignment(
                Pos.CENTER
        );



        return layout;


    }


}