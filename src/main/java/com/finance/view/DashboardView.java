package com.finance.view;

import com.finance.service.ExpenseService;
import com.finance.service.IncomeService;

import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.VBox;

import javafx.stage.Stage;


public class DashboardView {


    private final ExpenseService expenseService;

    private final IncomeService incomeService;



    public DashboardView() {

        expenseService = new ExpenseService();

        incomeService = new IncomeService();

    }



    public VBox getView() {


        Label title = new Label(
                "Personal Finance Tracker"
        );


        double income =
                incomeService.getTotalIncome();


        double expense =
                expenseService.getTotalExpense();


        double balance =
                income - expense;



        Label incomeLabel =
                new Label(
                        "Total Income: " + income
                );


        Label expenseLabel =
                new Label(
                        "Total Expense: " + expense
                );


        Label balanceLabel =
                new Label(
                        "Balance: " + balance
                );



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
                        "View Transactions"
                );


        Button logoutButton =
                new Button(
                        "Logout"
                );



        expenseButton.setOnAction(event -> {


            ExpenseView expenseView =
                    new ExpenseView();


            Stage stage = new Stage();


            stage.setScene(
                    new Scene(
                            expenseView.getView(),
                            500,
                            500
                    )
            );


            stage.setTitle(
                    "Expenses"
            );


            stage.show();


        });



        incomeButton.setOnAction(event -> {


            IncomeView incomeView =
                    new IncomeView();


            Stage stage = new Stage();


            stage.setScene(
                    new Scene(
                            incomeView.getView(),
                            500,
                            500
                    )
            );


            stage.setTitle(
                    "Income"
            );


            stage.show();


        });



        transactionButton.setOnAction(event -> {


            TransactionView transactionView =
                    new TransactionView();


            Stage stage = new Stage();


            stage.setScene(
                    new Scene(
                            transactionView.getView(),
                            700,
                            500
                    )
            );


            stage.setTitle(
                    "Transactions"
            );


            stage.show();


        });



        VBox layout =
                new VBox(
                        20,
                        title,
                        incomeLabel,
                        expenseLabel,
                        balanceLabel,
                        expenseButton,
                        incomeButton,
                        transactionButton,
                        logoutButton
                );


        layout.setAlignment(
                Pos.CENTER
        );


        return layout;

    }

}