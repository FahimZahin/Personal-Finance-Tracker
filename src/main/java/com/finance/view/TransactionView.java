package com.finance.view;

import com.finance.Session;
import com.finance.model.Expense;
import com.finance.model.Income;
import com.finance.service.CsvExportService;
import com.finance.service.ExpenseService;
import com.finance.service.IncomeService;
import javafx.scene.control.Button;


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
import java.util.Comparator;

public class TransactionView {

    private final ExpenseService expenseService;
    private final IncomeService incomeService;
    private final CsvExportService csvExportService;

    private TableView<TransactionRow> table;
    private ObservableList<TransactionRow> data;

    public TransactionView() {

        expenseService = new ExpenseService();
        incomeService = new IncomeService();
        csvExportService = new CsvExportService();
    }

    public VBox getView(Stage stage) {

        // ==========================================
        // TABLE
        // ==========================================

        table = new TableView<>();

        data = FXCollections.observableArrayList();

        // ==========================================
        // DATE COLUMN
        // ==========================================

        TableColumn<TransactionRow, String> dateColumn =
                new TableColumn<>("Date");

        dateColumn.setCellValueFactory(
                new PropertyValueFactory<>("date")
        );

        // ==========================================
        // TYPE COLUMN
        // ==========================================

        TableColumn<TransactionRow, String> typeColumn =
                new TableColumn<>("Type");

        typeColumn.setCellValueFactory(
                new PropertyValueFactory<>("type")
        );

        // ==========================================
        // DETAILS COLUMN
        // ==========================================

        TableColumn<TransactionRow, String> detailColumn =
                new TableColumn<>("Details");

        detailColumn.setCellValueFactory(
                new PropertyValueFactory<>("detail")
        );

        // ==========================================
        // AMOUNT COLUMN
        // ==========================================

        TableColumn<TransactionRow, Double> amountColumn =
                new TableColumn<>("Amount");

        amountColumn.setCellValueFactory(
                new PropertyValueFactory<>("amount")
        );

        // ==========================================
        // ADD COLUMNS
        // ==========================================

        table.getColumns().addAll(
                dateColumn,
                typeColumn,
                detailColumn,
                amountColumn
        );

        // ==========================================
        // COLUMN WIDTHS
        // ==========================================

        dateColumn.setPrefWidth(130);
        typeColumn.setPrefWidth(120);
        detailColumn.setPrefWidth(220);
        amountColumn.setPrefWidth(120);

        // ==========================================
        // LOAD DATA
        // ==========================================

        loadTransactions();

        table.setItems(data);

        // ==========================================
        // DELETE BUTTON
        // ==========================================

        Button deleteButton =
                new Button("Delete Selected");

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

            try {

                if ("Income".equals(selected.getType())) {

                    incomeService.deleteIncome(
                            selected.getId()
                    );

                } else {

                    expenseService.deleteExpense(
                            selected.getId()
                    );
                }

                loadTransactions();

                showAlert(
                        Alert.AlertType.INFORMATION,
                        "Deleted",
                        "Transaction deleted successfully."
                );

            } catch (Exception e) {

                e.printStackTrace();

                showAlert(
                        Alert.AlertType.ERROR,
                        "Delete Failed",
                        "Could not delete the selected transaction."
                );
            }
        });

        // ==========================================
        // REFRESH BUTTON
        // ==========================================

        Button refreshButton =
                new Button("Refresh");

        refreshButton.setOnAction(event -> {

            loadTransactions();
        });

        // ==========================================
        // EXPORT CSV BUTTON
        // ==========================================

        Button exportButton =
                new Button("Export CSV");

        exportButton.setOnAction(event -> {

            exportTransactions(stage);
        });

        // ==========================================
        // BACK BUTTON
        // ==========================================

        Button backButton =
                new Button("Back to Dashboard");

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

        Button editButton =
                new Button(
                        "Edit Selected"
                );
        editButton.setOnAction(event -> {


            TransactionRow selected =
                    table.getSelectionModel()
                            .getSelectedItem();



            if(selected == null){

                return;

            }



            if(selected.getType().equals("Income")){


                EditIncomeView editIncomeView =
                        new EditIncomeView(
                                selected.getId()
                        );



                Scene scene =
                        new Scene(
                                editIncomeView.getView(stage),
                                500,
                                500
                        );


                stage.setScene(scene);


            }
            else{


                EditExpenseView editExpenseView =
                        new EditExpenseView(
                                selected.getId()
                        );


                Scene scene =
                        new Scene(
                                editExpenseView.getView(stage),
                                500,
                                500
                        );


                stage.setScene(scene);


            }


        });

        // ==========================================
        // BUTTON LAYOUT
        // ==========================================

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

        // ==========================================
        // MAIN LAYOUT
        // ==========================================

        VBox layout =
                new VBox(
                        20,
                        table,
                        buttonLayout,
                        editButton
                );

        layout.setPadding(
                new Insets(20)
        );

        layout.setAlignment(
                Pos.CENTER
        );

        // Allow table to grow
        VBox.setVgrow(
                table,
                javafx.scene.layout.Priority.ALWAYS
        );

        return layout;
    }

    // ==========================================
    // LOAD TRANSACTIONS
    // ==========================================

    private void loadTransactions() {

        if (data == null) {
            data =
                    FXCollections.observableArrayList();
        }

        data.clear();

        // ==========================================
        // CHECK LOGIN SESSION
        // ==========================================

        if (Session.getUser() == null) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Session Error",
                    "No logged-in user was found."
            );

            return;
        }

        int userId =
                Session.getUser().getId();

        // ==========================================
        // LOAD EXPENSES
        // ==========================================

        for (
                Expense expense :
                expenseService.getExpensesByUser(userId)
        ) {

            String date =
                    expense.getDate() == null
                            ? ""
                            : expense.getDate().toString();

            String category =
                    expense.getCategory() == null
                            ? ""
                            : expense.getCategory();

            data.add(
                    new TransactionRow(
                            expense.getId(),
                            date,
                            "Expense",
                            category,
                            expense.getAmount()
                    )
            );
        }

        // ==========================================
        // LOAD INCOME
        // ==========================================

        for (
                Income income :
                incomeService.getIncomeByUser(userId)
        ) {

            String date =
                    income.getDate() == null
                            ? ""
                            : income.getDate().toString();

            String source =
                    income.getSource() == null
                            ? ""
                            : income.getSource();

            data.add(
                    new TransactionRow(
                            income.getId(),
                            date,
                            "Income",
                            source,
                            income.getAmount()
                    )
            );
        }

        // ==========================================
        // SORT BY DATE
        // ==========================================

        data.sort(
                Comparator.comparing(
                        TransactionRow::getDate,
                        Comparator.nullsLast(
                                Comparator.reverseOrder()
                        )
                )
        );
    }

    // ==========================================
    // EXPORT TRANSACTIONS
    // ==========================================

    private void exportTransactions(Stage stage) {

        if (data == null || data.isEmpty()) {

            showAlert(
                    Alert.AlertType.INFORMATION,
                    "No Transactions",
                    "There are no transactions to export."
            );

            return;
        }

        // ==========================================
        // FILE CHOOSER
        // ==========================================

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

        // ==========================================
        // ENSURE CSV EXTENSION
        // ==========================================

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

        // ==========================================
        // EXPORT
        // ==========================================

        try {

            csvExportService.exportTransactions(
                    data,
                    file
            );

            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Export Successful",
                    "Transactions exported successfully.\n\n"
                            + file.getAbsolutePath()
            );

        } catch (Exception e) {

            e.printStackTrace();

            showAlert(
                    Alert.AlertType.ERROR,
                    "Export Failed",
                    "Could not export transactions.\n\n"
                            + e.getMessage()
            );
        }
    }

    // ==========================================
    // ALERT
    // ==========================================

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