package com.finance.view;


import com.finance.Session;
import com.finance.model.Expense;
import com.finance.model.Income;
import com.finance.service.CsvExportService;
import com.finance.service.ExpenseService;
import com.finance.service.IncomeService;
import com.finance.service.TransactionService;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import javafx.geometry.Insets;
import javafx.geometry.Pos;


import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.util.ArrayList;
import java.util.List;



public class TransactionView {


    private final IncomeService incomeService;

    private final ExpenseService expenseService;

    private final CsvExportService csvExportService;

    private final TransactionService transactionService;



    private final TableView<TransactionRow> table;


    private final ObservableList<TransactionRow> transactionList;



    private TextField searchField;

    private ComboBox<String> filterBox;



    private List<TransactionRow> allTransactions;





    public TransactionView(){


        incomeService =
                new IncomeService();


        expenseService =
                new ExpenseService();


        csvExportService =
                new CsvExportService();


        transactionService =
                new TransactionService();



        table =
                new TableView<>();


        transactionList =
                FXCollections.observableArrayList();


        allTransactions =
                new ArrayList<>();


    }






    public VBox getView(Stage stage){



        Label title =
                new Label(
                        "Transactions"
                );




        searchField =
                new TextField();


        searchField.setPromptText(
                "Search details..."
        );




        filterBox =
                new ComboBox<>();


        filterBox.getItems()
                .addAll(
                        "All",
                        "Income",
                        "Expense"
                );


        filterBox.setValue(
                "All"
        );





        searchField.textProperty()
                .addListener(
                        (obs,oldValue,newValue)->{


                            applyFilter();


                        }
                );




        filterBox.setOnAction(
                event -> {


                    applyFilter();


                }
        );







        createTable();




        loadTransactions();






        Button exportButton =
                new Button(
                        "Export CSV"
                );



        exportButton.setOnAction(
                event -> {


                    exportCSV(
                            stage
                    );


                }
        );






        Button deleteButton =
                new Button(
                        "Delete Selected"
                );



        deleteButton.setOnAction(
                event -> {


                    deleteSelected();


                }
        );

        Button editButton =
                new Button(
                        "Edit Selected"
                );

        editButton.setOnAction(event -> {


            TransactionRow selected =
                    table.getSelectionModel()
                            .getSelectedItem();



            if(selected == null){


                showAlert(
                        "Please select a transaction first."
                );


                return;

            }



            EditTransactionView editView =
                    new EditTransactionView();



            stage.setScene(
                    new Scene(
                            editView.getView(
                                    stage,
                                    selected
                            ),
                            500,
                            500
                    )
            );


        });







        Button backButton =
                new Button(
                        "Back Dashboard"
                );



        backButton.setOnAction(
                event -> {


                    DashboardView dashboard =
                            new DashboardView();



                    stage.setScene(
                            new Scene(
                                    dashboard.getView(stage),
                                    900,
                                    650
                            )
                    );


                }
        );






        HBox controls =
                new HBox(
                        10,
                        searchField,
                        filterBox,
                        editButton,
                        exportButton,
                        deleteButton,
                        backButton
                );



        controls.setAlignment(
                Pos.CENTER
        );







        VBox layout =
                new VBox(
                        20,
                        title,
                        controls,
                        table
                );



        layout.setPadding(
                new Insets(30)
        );



        layout.setAlignment(
                Pos.CENTER
        );



        return layout;


    }






    private void createTable(){



        TableColumn<TransactionRow, Integer> idColumn =
                new TableColumn<>(
                        "ID"
                );


        idColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "id"
                )
        );





        TableColumn<TransactionRow, String> dateColumn =
                new TableColumn<>(
                        "Date"
                );


        dateColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "date"
                )
        );





        TableColumn<TransactionRow, String> typeColumn =
                new TableColumn<>(
                        "Type"
                );


        typeColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "type"
                )
        );





        TableColumn<TransactionRow, String> detailColumn =
                new TableColumn<>(
                        "Details"
                );


        detailColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "detail"
                )
        );





        TableColumn<TransactionRow, String> amountColumn =
                new TableColumn<>(
                        "Amount"
                );


        amountColumn.setCellValueFactory(
                cell ->
                        new javafx.beans.property.SimpleStringProperty(
                                String.format(
                                        "%.2f",
                                        cell.getValue()
                                                .getAmount()
                                )
                        )
        );





        table.getColumns()
                .addAll(
                        idColumn,
                        dateColumn,
                        typeColumn,
                        detailColumn,
                        amountColumn
                );



        table.setItems(
                transactionList
        );



        table.setPrefHeight(
                450
        );


    }
    private void loadTransactions(){


        allTransactions.clear();



        int userId =
                Session.getUser()
                        .getId();




        List<Income> incomes =
                incomeService.getIncomeByUser(
                        userId
                );



        for(Income income : incomes){


            allTransactions.add(
                    new TransactionRow(
                            income.getId(),
                            income.getDate().toString(),
                            "Income",
                            income.getSource()
                                    + " - "
                                    + income.getDescription(),
                            income.getAmount()
                    )
            );


        }






        List<Expense> expenses =
                expenseService.getExpensesByUser(
                        userId
                );



        for(Expense expense : expenses){


            allTransactions.add(
                    new TransactionRow(
                            expense.getId(),
                            expense.getDate().toString(),
                            "Expense",
                            expense.getCategory()
                                    + " - "
                                    + expense.getDescription(),
                            expense.getAmount()
                    )
            );


        }




        applyFilter();


    }







    private void applyFilter(){


        String keyword =
                searchField.getText();



        String selectedType =
                filterBox.getValue();



        transactionList.clear();



        transactionList.addAll(
                transactionService.filter(
                        allTransactions,
                        keyword,
                        selectedType
                )
        );


    }


    private void deleteSelected(){



        TransactionRow selected =
                table.getSelectionModel()
                        .getSelectedItem();




        if(selected == null){


            showAlert(
                    "Please select a transaction first."
            );


            return;


        }






        try {



            if(
                    selected.getType()
                            .equals("Income")
            ){


                incomeService.deleteIncome(
                        selected.getId()
                );


            }
            else{


                expenseService.deleteExpense(
                        selected.getId()
                );


            }





            loadTransactions();




        }
        catch(Exception e){



            e.printStackTrace();



            showAlert(
                    "Delete failed."
            );


        }



    }








    private void exportCSV(Stage stage){



        FileChooser chooser =
                new FileChooser();



        chooser.setTitle(
                "Save Transactions CSV"
        );



        chooser.getExtensionFilters()
                .add(
                        new FileChooser.ExtensionFilter(
                                "CSV Files",
                                "*.csv"
                        )
                );




        File file =
                chooser.showSaveDialog(
                        stage
                );



        if(file == null){

            return;

        }





        try {



            csvExportService.exportTransactions(
                    transactionList,
                    file
            );



            showAlert(
                    "CSV exported successfully."
            );



        }
        catch(Exception e){



            e.printStackTrace();



            showAlert(
                    "Export failed."
            );


        }



    }








    private void showAlert(String message){



        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );



        alert.setTitle(
                "Information"
        );



        alert.setHeaderText(
                null
        );



        alert.setContentText(
                message
        );



        alert.showAndWait();



    }



}