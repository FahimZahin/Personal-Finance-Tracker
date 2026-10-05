package com.finance.view;


import com.finance.model.Expense;
import com.finance.model.Income;

import com.finance.service.ExpenseService;
import com.finance.service.IncomeService;
import com.finance.service.CsvExportService;


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

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;



public class TransactionView {


    private final ExpenseService expenseService;

    private final IncomeService incomeService;

    private final CsvExportService csvExportService;



    private TableView<TransactionRow> table;


    private ObservableList<TransactionRow> data;


    private ObservableList<TransactionRow> allTransactions;




    public TransactionView(){


        expenseService =
                new ExpenseService();


        incomeService =
                new IncomeService();


        csvExportService =
                new CsvExportService();


    }





    public VBox getView(Stage stage){



        data =
                FXCollections.observableArrayList();



        allTransactions =
                FXCollections.observableArrayList();




        table =
                new TableView<>();



        createColumns();



        loadTransactions();




        TextField searchField =
                new TextField();


        searchField.setPromptText(
                "Search transaction..."
        );



        ComboBox<String> filterBox =
                new ComboBox<>();


        filterBox.getItems().addAll(
                "All",
                "Income",
                "Expense"
        );


        filterBox.setValue(
                "All"
        );




        searchField.textProperty()
                .addListener(
                        (observable, oldValue, newValue) -> {


                            filterTransactions(
                                    searchField.getText(),
                                    filterBox.getValue()
                            );


                        }
                );



        filterBox.valueProperty()
                .addListener(
                        (observable, oldValue, newValue) -> {


                            filterTransactions(
                                    searchField.getText(),
                                    filterBox.getValue()
                            );


                        }
                );



        table.setItems(
                data
        );



        Button editButton =
                new Button(
                        "Edit Selected"
                );



        Button deleteButton =
                new Button(
                        "Delete Selected"
                );



        Button refreshButton =
                new Button(
                        "Refresh"
                );



        Button exportButton =
                new Button(
                        "Export CSV"
                );



        Button backButton =
                new Button(
                        "Back Dashboard"
                );



        editButton.setOnAction(event -> {


            editSelected(
                    stage
            );


        });



        deleteButton.setOnAction(event -> {


            deleteSelected();


        });



        refreshButton.setOnAction(event -> {


            loadTransactions();


        });



        exportButton.setOnAction(event -> {


            exportCSV(
                    stage
            );


        });



        backButton.setOnAction(event -> {


            DashboardView dashboardView =
                    new DashboardView();


            stage.setScene(
                    new Scene(
                            dashboardView.getView(stage),
                            700,
                            500
                    )
            );


        });


        VBox layout =
                new VBox(
                        15
                );


        setupLayout(
                layout,
                searchField,
                filterBox,
                editButton,
                deleteButton,
                refreshButton,
                exportButton,
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


    private void createColumns(){


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



            TableColumn<TransactionRow, Double> amountColumn =
                    new TableColumn<>(
                            "Amount"
                    );


            amountColumn.setCellValueFactory(
                    new PropertyValueFactory<>(
                            "amount"
                    )
            );



            table.getColumns().addAll(
                    dateColumn,
                    typeColumn,
                    detailColumn,
                    amountColumn
            );


        }






        private void loadTransactions(){


            allTransactions.clear();



            if(
                    com.finance.Session.getUser()
                            == null
            ){

                return;

            }



            int userId =
                    com.finance.Session.getUser()
                            .getId();





            List<Expense> expenses =
                    expenseService.getExpensesByUser(
                            userId
                    );



            for(Expense expense : expenses){


                allTransactions.add(

                        new TransactionRow(

                                expense.getId(),

                                expense.getDate()
                                        .toString(),

                                "Expense",

                                expense.getCategory()
                                        + " - "
                                        + expense.getDescription(),

                                expense.getAmount()

                        )

                );


            }






            List<Income> incomes =
                    incomeService.getIncomeByUser(
                            userId
                    );



            for(Income income : incomes){


                allTransactions.add(

                        new TransactionRow(

                                income.getId(),

                                income.getDate()
                                        .toString(),

                                "Income",

                                income.getSource()
                                        + " - "
                                        + income.getDescription(),

                                income.getAmount()

                        )

                );


            }



            data.setAll(
                    allTransactions
            );

        }







        private void filterTransactions(
                String keyword,
                String type
    ){


            keyword =
                    keyword.toLowerCase();



            data.clear();



            for(TransactionRow row : allTransactions){


                boolean textMatch =
                        row.getDetail()
                                .toLowerCase()
                                .contains(keyword);



                boolean typeMatch =
                        type.equals("All")
                                ||
                                row.getType()
                                        .equals(type);



                if(
                        textMatch
                                &&
                                typeMatch
                ){

                    data.add(
                            row
                    );

                }


            }


        }







        private void editSelected(Stage stage){


            TransactionRow selected =
                    table.getSelectionModel()
                            .getSelectedItem();



            if(selected == null){


                showAlert(
                        Alert.AlertType.WARNING,
                        "No Selection",
                        "Please select a transaction first."
                );


                return;

            }





            if(
                    selected.getType()
                            .equals("Income")
            ){


                EditIncomeView editIncomeView =
                        new EditIncomeView(
                                selected.getId()
                        );



                stage.setScene(

                        new Scene(

                                editIncomeView.getView(stage),

                                500,

                                500

                        )

                );


            }
            else{


                EditExpenseView editExpenseView =
                        new EditExpenseView(
                                selected.getId()
                        );



                stage.setScene(

                        new Scene(

                                editExpenseView.getView(stage),

                                500,

                                500

                        )

                );


            }


        }







        private void deleteSelected(){


            TransactionRow selected =
                    table.getSelectionModel()
                            .getSelectedItem();




            if(selected == null){


                showAlert(
                        Alert.AlertType.WARNING,
                        "No Selection",
                        "Select a transaction first."
                );


                return;

            }





            Alert confirm =
                    new Alert(
                            Alert.AlertType.CONFIRMATION
                    );



            confirm.setTitle(
                    "Confirm Delete"
            );


            confirm.setContentText(
                    "Delete this transaction?"
            );



            confirm.showAndWait()
                    .ifPresent(response -> {



                        if(
                                response ==
                                        ButtonType.OK
                        ){


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


                    });


        }
        private void exportCSV(Stage stage){


            FileChooser fileChooser =
                    new FileChooser();


            fileChooser.setTitle(
                    "Save Transactions CSV"
            );


            fileChooser.getExtensionFilters()
                    .add(
                            new FileChooser.ExtensionFilter(
                                    "CSV File",
                                    "*.csv"
                            )
                    );



            File file =
                    fileChooser.showSaveDialog(
                            stage
                    );



            if(file == null){

                return;

            }




            try{


                csvExportService.exportTransactions(
                        new ArrayList<>(
                                data
                        ),
                        file
                );



                showAlert(
                        Alert.AlertType.INFORMATION,
                        "Export Complete",
                        "Transactions exported successfully."
                );


            }
            catch(Exception e){


                e.printStackTrace();



                showAlert(
                        Alert.AlertType.ERROR,
                        "Export Failed",
                        "Could not export transactions."
                );


            }


        }







        private void showAlert(
                Alert.AlertType type,
                String title,
                String message
    ){


            Alert alert =
                    new Alert(
                            type
                    );


            alert.setTitle(
                    title
            );


            alert.setHeaderText(
                    null
            );


            alert.setContentText(
                    message
            );


            alert.showAndWait();


        }






        private void setupLayout(
                VBox layout,
                TextField searchField,
                ComboBox<String> filterBox,
                Button editButton,
                Button deleteButton,
                Button refreshButton,
                Button exportButton,
                Button backButton
    ){


            HBox searchBox =
                    new HBox(
                            10,
                            searchField,
                            filterBox
                    );


            searchBox.setAlignment(
                    Pos.CENTER
            );



            HBox buttonBox =
                    new HBox(
                            10,
                            editButton,
                            deleteButton,
                            refreshButton,
                            exportButton,
                            backButton
                    );


            buttonBox.setAlignment(
                    Pos.CENTER
            );



            layout.getChildren()
                    .addAll(
                            searchBox,
                            table,
                            buttonBox
                    );


        }







    }