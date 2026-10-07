package com.finance.view;


import com.finance.Session;
import com.finance.service.DashboardService;


import javafx.geometry.Insets;
import javafx.geometry.Pos;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


import javafx.stage.Stage;



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






        double income =
                dashboardService.getTotalIncome(
                        userId
                );



        double expense =
                dashboardService.getTotalExpense(
                        userId
                );



        double balance =
                dashboardService.getBalance(
                        userId
                );







        StatCard card =
                new StatCard();




        VBox incomeCard =
                card.create(
                        "Total Income",
                        String.valueOf(income)
                );



        VBox expenseCard =
                card.create(
                        "Total Expense",
                        String.valueOf(expense)
                );



        VBox balanceCard =
                card.create(
                        "Balance",
                        String.valueOf(balance)
                );






        HBox cards =
                new HBox(
                        20,
                        incomeCard,
                        expenseCard,
                        balanceCard
                );



        cards.setAlignment(
                Pos.CENTER
        );









        Button incomeButton =
                new Button(
                        "Manage Income"
                );



        incomeButton.setOnAction(event -> {


            IncomeView view =
                    new IncomeView();



            stage.setScene(
                    new Scene(
                            view.getView(stage),
                            700,
                            500
                    )
            );


        });








        Button expenseButton =
                new Button(
                        "Manage Expense"
                );



        expenseButton.setOnAction(event -> {


            ExpenseView view =
                    new ExpenseView();



            stage.setScene(
                    new Scene(
                            view.getView(stage),
                            700,
                            500
                    )
            );


        });








        Button transactionButton =
                new Button(
                        "Transactions"
                );



        transactionButton.setOnAction(event -> {


            TransactionView view =
                    new TransactionView();



            stage.setScene(
                    new Scene(
                            view.getView(stage),
                            900,
                            650
                    )
            );


        });








        Button reportButton =
                new Button(
                        "Monthly Report"
                );



        reportButton.setOnAction(event -> {


            ReportView view =
                    new ReportView();



            stage.setScene(
                    new Scene(
                            view.getView(stage),
                            600,
                            500
                    )
            );


        });








        Button profileButton =
                new Button(
                        "Profile"
                );



        profileButton.setOnAction(event -> {


            ProfileView view =
                    new ProfileView();



            stage.setScene(
                    new Scene(
                            view.getView(stage),
                            600,
                            500
                    )
            );


        });








        Button logoutButton =
                new Button(
                        "Logout"
                );



        logoutButton.setOnAction(event -> {


            Session.clear();



            LoginView login =
                    new LoginView();



            stage.setScene(
                    new Scene(
                            login.getView(stage),
                            500,
                            400
                    )
            );


        });








        HBox buttons =
                new HBox(
                        10,
                        incomeButton,
                        expenseButton,
                        transactionButton,
                        reportButton,
                        profileButton,
                        logoutButton
                );



        buttons.setAlignment(
                Pos.CENTER
        );








        VBox layout =
                new VBox(
                        30,
                        welcome,
                        cards,
                        buttons
                );



        layout.setPadding(
                new Insets(40)
        );



        layout.setAlignment(
                Pos.CENTER
        );



        return layout;



    }



}