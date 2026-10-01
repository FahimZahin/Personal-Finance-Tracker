package com.finance.view;


import com.finance.model.Expense;
import com.finance.model.Income;

import com.finance.service.ExpenseService;
import com.finance.service.IncomeService;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import javafx.geometry.Insets;


import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.cell.PropertyValueFactory;


import javafx.scene.layout.VBox;



public class TransactionView {



    private final ExpenseService expenseService;

    private final IncomeService incomeService;



    public TransactionView() {


        expenseService = new ExpenseService();

        incomeService = new IncomeService();


    }





    public VBox getView() {



        TableView<Object> table = new TableView<>();



        TableColumn<Object,String> typeColumn =
                new TableColumn<>("Type");



        typeColumn.setCellValueFactory(data -> {


            if(data.getValue() instanceof Expense){

                return new javafx.beans.property.SimpleStringProperty(
                        "Expense"
                );

            }


            return new javafx.beans.property.SimpleStringProperty(
                    "Income"
            );


        });





        TableColumn<Object,Double> amountColumn =
                new TableColumn<>("Amount");



        amountColumn.setCellValueFactory(data -> {


            if(data.getValue() instanceof Expense expense){


                return new javafx.beans.property.SimpleObjectProperty<>(
                        expense.getAmount()
                );


            }


            Income income =
                    (Income)data.getValue();



            return new javafx.beans.property.SimpleObjectProperty<>(
                    income.getAmount()
            );


        });






        TableColumn<Object,String> detailColumn =
                new TableColumn<>("Category / Source");



        detailColumn.setCellValueFactory(data -> {



            if(data.getValue() instanceof Expense expense){


                return new javafx.beans.property.SimpleStringProperty(
                        expense.getCategory()
                );


            }



            Income income =
                    (Income)data.getValue();



            return new javafx.beans.property.SimpleStringProperty(
                    income.getSource()
            );



        });






        TableColumn<Object,String> dateColumn =
                new TableColumn<>("Date");



        dateColumn.setCellValueFactory(data -> {



            if(data.getValue() instanceof Expense expense){


                return new javafx.beans.property.SimpleStringProperty(
                        expense.getDate().toString()
                );


            }




            Income income =
                    (Income)data.getValue();



            return new javafx.beans.property.SimpleStringProperty(
                    income.getDate().toString()
            );



        });





        table.getColumns().addAll(

                typeColumn,

                amountColumn,

                detailColumn,

                dateColumn

        );






        ObservableList<Object> data =
                FXCollections.observableArrayList();




        data.addAll(
                expenseService.getAllExpenses()
        );



        data.addAll(
                incomeService.getAllIncome()
        );



        table.setItems(data);





        Button refreshButton =
                new Button(
                        "Refresh"
                );



        refreshButton.setOnAction(event -> {


            data.clear();


            data.addAll(
                    expenseService.getAllExpenses()
            );


            data.addAll(
                    incomeService.getAllIncome()
            );


        });





        VBox layout =
                new VBox(

                        15,

                        table,

                        refreshButton

                );



        layout.setPadding(
                new Insets(20)
        );



        return layout;


    }


}