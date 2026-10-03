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


    public DashboardView() {

        dashboardService = new DashboardService();

    }


    public VBox getView(Stage stage) {


        if (Session.getUser() == null) {

            Label error =
                    new Label("No user logged in.");

            VBox layout =
                    new VBox(
                            error
                    );

            layout.setAlignment(Pos.CENTER);

            return layout;
        }


        int userId =
                Session.getUser().getId();


        double totalIncome =
                dashboardService.getTotalIncome(userId);


        double totalExpense =
                dashboardService.getTotalExpense(userId);


        double balance =
                totalIncome - totalExpense;



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


        Button refreshButton =
                new Button(
                        "Refresh Dashboard"
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
                            expenseView.getView(stage),
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
                            incomeView.getView(stage),
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
                            transactionView.getView(stage),
                            700,
                            500
                    );


            stage.setScene(scene);


        });



        refreshButton.setOnAction(event -> {


            stage.setScene(
                    new Scene(
                            new DashboardView().getView(stage),
                            500,
                            500
                    )
            );


        });

        Button profileButton =
                new Button(
                        "Profile"
                );

        profileButton.setOnAction(event -> {


            ProfileView profileView =
                    new ProfileView();


            Scene scene =
                    new Scene(
                            profileView.getView(stage),
                            500,
                            400
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

                        refreshButton,

                        profileButton,

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