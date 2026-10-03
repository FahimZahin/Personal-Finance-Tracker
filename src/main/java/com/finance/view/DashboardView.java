package com.finance.view;


import com.finance.Session;
import com.finance.service.DashboardService;


import javafx.geometry.Insets;
import javafx.geometry.Pos;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;



public class DashboardView {


    private final DashboardService dashboardService;



    public DashboardView(){

        dashboardService = new DashboardService();

    }




    public VBox getView(Stage stage){



        int userId =
                Session.getUser().getId();




        Label welcome =
                new Label(
                        "Welcome, "
                                +
                                Session.getUser().getUsername()
                );



        Label incomeLabel =
                new Label(
                        "Total Income: "
                                +
                                dashboardService.getTotalIncome(userId)
                );



        Label expenseLabel =
                new Label(
                        "Total Expense: "
                                +
                                dashboardService.getTotalExpense(userId)
                );



        Label balanceLabel =
                new Label(
                        "Balance: "
                                +
                                dashboardService.getBalance(userId)
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
                        "Transactions"
                );


        Button logoutButton =
                new Button(
                        "Logout"
                );





        expenseButton.setOnAction(event -> {


            ExpenseView expenseView =
                    new ExpenseView();



            Scene scene =
                    new Scene(
                            expenseView.getView(),
                            500,
                            500
                    );



            stage.setScene(scene);


        });






        incomeButton.setOnAction(event -> {


            IncomeView incomeView =
                    new IncomeView();



            Scene scene =
                    new Scene(
                            incomeView.getView(),
                            500,
                            500
                    );



            stage.setScene(scene);


        });







        transactionButton.setOnAction(event -> {


            TransactionView transactionView =
                    new TransactionView();



            Scene scene =
                    new Scene(
                            transactionView.getView(),
                            700,
                            500
                    );



            stage.setScene(scene);


        });







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







        VBox layout =
                new VBox(

                        20,

                        welcome,

                        incomeLabel,

                        expenseLabel,

                        balanceLabel,

                        expenseButton,

                        incomeButton,

                        transactionButton,

                        logoutButton

                );




        layout.setPadding(
                new Insets(30)
        );



        layout.setAlignment(
                Pos.CENTER
        );



        return layout;


    }


}