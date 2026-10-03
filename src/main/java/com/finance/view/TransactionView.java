package com.finance.view;


import com.finance.Session;
import com.finance.model.Expense;
import com.finance.model.Income;
import com.finance.service.ExpenseService;
import com.finance.service.IncomeService;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import javafx.geometry.Insets;
import javafx.geometry.Pos;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


import javafx.scene.layout.VBox;

import javafx.stage.Stage;



public class TransactionView {



    private final ExpenseService expenseService;

    private final IncomeService incomeService;



    public TransactionView(){


        expenseService =
                new ExpenseService();


        incomeService =
                new IncomeService();

    }





    public VBox getView(Stage stage){



        int userId =
                Session.getUser().getId();



        TableView<TransactionRow> table =
                new TableView<>();




        TableColumn<TransactionRow, String> dateColumn =
                new TableColumn<>("Date");


        dateColumn.setCellValueFactory(
                new PropertyValueFactory<>("date")
        );



        TableColumn<TransactionRow,String> typeColumn =
                new TableColumn<>("Type");


        typeColumn.setCellValueFactory(
                new PropertyValueFactory<>("type")
        );



        TableColumn<TransactionRow,String> detailColumn =
                new TableColumn<>("Category / Source");


        detailColumn.setCellValueFactory(
                new PropertyValueFactory<>("detail")
        );



        TableColumn<TransactionRow,Double> amountColumn =
                new TableColumn<>("Amount");


        amountColumn.setCellValueFactory(
                new PropertyValueFactory<>("amount")
        );



        table.getColumns().addAll(
                dateColumn,
                typeColumn,
                detailColumn,
                amountColumn
        );




        ObservableList<TransactionRow> data =
                FXCollections.observableArrayList();




        for(Expense expense :
                expenseService.getExpensesByUser(userId)){


            data.add(
                    new TransactionRow(
                            expense.getDate().toString(),
                            "Expense",
                            expense.getCategory(),
                            expense.getAmount()
                    )
            );

        }





        for(Income income :
                incomeService.getIncomeByUser(userId)){


            data.add(
                    new TransactionRow(
                            income.getDate().toString(),
                            "Income",
                            income.getSource(),
                            income.getAmount()
                    )
            );

        }



        table.setItems(data);





        Button backButton =
                new Button(
                        "Back to Dashboard"
                );



        backButton.setOnAction(event -> {



            DashboardView dashboardView =
                    new DashboardView();



            Scene scene =
                    new Scene(
                            dashboardView.getView(stage),
                            600,
                            500
                    );


            stage.setScene(scene);


        });







        VBox layout =
                new VBox(

                        20,

                        table,

                        backButton

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