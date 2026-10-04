package com.finance.view;


import com.finance.Session;

import com.finance.model.Expense;
import com.finance.model.Income;

import com.finance.service.CsvExportService;
import com.finance.service.ExpenseService;
import com.finance.service.IncomeService;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import javafx.geometry.Insets;
import javafx.geometry.Pos;


import javafx.scene.Scene;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.cell.PropertyValueFactory;


import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;

import java.time.LocalDate;



public class TransactionView {


    private final ExpenseService expenseService;

    private final IncomeService incomeService;

    private final CsvExportService csvExportService;


    private TableView<TransactionRow> table;

    private ObservableList<TransactionRow> data;



    public TransactionView() {


        expenseService =
                new ExpenseService();


        incomeService =
                new IncomeService();


        csvExportService =
                new CsvExportService();

    }




    public VBox getView(Stage stage) {


        table =
                new TableView<>();


        data =
                FXCollections.observableArrayList();



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



        dateColumn.setPrefWidth(130);

        typeColumn.setPrefWidth(120);

        detailColumn.setPrefWidth(220);

        amountColumn.setPrefWidth(120);



        loadTransactions();


        table.setItems(data);



        // ============================
        // DELETE BUTTON
        // ============================

        Button deleteButton =
                new Button(
                        "Delete Selected"
                );



        deleteButton.setOnAction(event -> {


            TransactionRow selected =
                    table.getSelectionModel()
                            .getSelectedItem();



            if (selected == null) {


                showAlert(
                        Alert.AlertType.WARNING,
                        "No Selection",
                        "Please select a transaction first."
                );


                return;

            }



            if (
                    selected.getType()
                            .equals("Income")
            ) {


                incomeService.deleteIncome(
                        selected.getId()
                );


            }
            else {


                expenseService.deleteExpense(
                        selected.getId()
                );

            }



            loadTransactions();


        });



        // ============================
        // REFRESH BUTTON
        // ============================

        Button refreshButton =
                new Button(
                        "Refresh"
                );



        refreshButton.setOnAction(event -> {


            loadTransactions();


        });



        // ============================
        // EXPORT CSV BUTTON
        // ============================

        Button exportButton =
                new Button(
                        "Export CSV"
                );



        exportButton.setOnAction(event -> {


            exportTransactions(stage);


        });



        // ============================
        // BACK BUTTON
        // ============================

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



        // ============================
        // BUTTON LAYOUT
        // ============================

        HBox buttonLayout =
                new HBox(

                        10,

                        deleteButton,

                        refreshButton,

                        exportButton,

                        backButton

                );



        buttonLayout.setAlignment(
                Pos.CENTER
        );



        // ============================
        // MAIN LAYOUT
        // ============================

        VBox layout =
                new VBox(

                        20,

                        table,

                        buttonLayout

                );



        layout.setPadding(
                new Insets(20)
        );



        layout.setAlignment(
                Pos.CENTER
        );



        return layout;

    }




    // ============================
    // LOAD TRANSACTIONS
    // ============================

    private void loadTransactions() {


        data.clear();



        int userId =
                Session.getUser().getId();



        for (
                Expense expense :
                expenseService.getExpensesByUser(
                        userId
                )
        ) {


            data.add(
                    new TransactionRow(

                            expense.getId(),

                            expense.getDate().toString(),

                            "Expense",

                            expense.getCategory(),

                            expense.getAmount()

                    )
            );


        }



        for (
                Income income :
                incomeService.getIncomeByUser(
                        userId
                )
        ) {


            data.add(
                    new TransactionRow(

                            income.getId(),

                            income.getDate().toString(),

                            "Income",

                            income.getSource(),

                            income.getAmount()

                    )
            );


        }

    }




    // ============================
    // EXPORT TRANSACTIONS
    // ============================

    private void exportTransactions(Stage stage) {


        if (data.isEmpty()) {


            showAlert(
                    Alert.AlertType.INFORMATION,
                    "No Transactions",
                    "There are no transactions to export."
            );


            return;

        }



        FileChooser fileChooser =
                new FileChooser();



        fileChooser.setTitle(
                "Save Transactions"
        );



        fileChooser.setInitialFileName(
                "transactions.csv"
        );



        FileChooser.ExtensionFilter csvFilter =
                new FileChooser.ExtensionFilter(
                        "CSV Files (*.csv)",
                        "*.csv"
                );



        fileChooser.getExtensionFilters()
                .add(csvFilter);



        File file =
                fileChooser.showSaveDialog(stage);



        if (file == null) {

            return;

        }



        if (
                !file.getName()
                        .toLowerCase()
                        .endsWith(".csv")
        ) {


            file =
                    new File(
                            file.getAbsolutePath()
                                    + ".csv"
                    );

        }



        try {


            csvExportService.exportTransactions(
                    data,
                    file
            );


            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Export Successful",
                    "Transactions exported successfully.\n\n"
                            +
                            file.getAbsolutePath()
            );


        }
        catch(Exception e) {


            e.printStackTrace();


            showAlert(
                    Alert.AlertType.ERROR,
                    "Export Failed",
                    "Could not export transactions."
            );

        }

    }




    // ============================
    // ALERT
    // ============================

    private void showAlert(
            Alert.AlertType type,
            String title,
            String message
    ) {


        Alert alert =
                new Alert(type);


        alert.setTitle(title);

        alert.setHeaderText(null);

        alert.setContentText(message);


        alert.showAndWait();

    }


}