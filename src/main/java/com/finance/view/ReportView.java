package com.finance.view;

import com.finance.Session;
import com.finance.service.ReportService;
import com.finance.service.PdfReportService;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.time.LocalDate;

public class ReportView {

    private final ReportService reportService;
    private final PdfReportService pdfReportService;

    public ReportView(){
        reportService = new ReportService();
        pdfReportService = new PdfReportService();
    }

    public VBox getView(Stage stage){

        int userId = Session.getUser().getId();

        // 1. Title Styling
        Label title = new Label("Monthly Report");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        // 2. ComboBoxes & Labels Styling
        String comboStyle = "-fx-font-size: 14px; -fx-pref-width: 100px; -fx-background-color: white; -fx-border-color: #bdc3c7; -fx-border-radius: 4px;";
        String filterLabelStyle = "-fx-font-size: 14px; -fx-text-fill: #34495e; -fx-font-weight: bold;";

        Label monthLabel = new Label("Month:");
        monthLabel.setStyle(filterLabelStyle);

        ComboBox<Integer> monthBox = new ComboBox<>();
        for(int i = 1; i <= 12; i++){
            monthBox.getItems().add(i);
        }
        monthBox.setValue(LocalDate.now().getMonthValue());
        monthBox.setStyle(comboStyle);

        Label yearLabel = new Label("Year:");
        yearLabel.setStyle(filterLabelStyle);

        ComboBox<Integer> yearBox = new ComboBox<>();
        int currentYear = LocalDate.now().getYear();
        for(int i = currentYear - 5; i <= currentYear; i++){
            yearBox.getItems().add(i);
        }
        yearBox.setValue(currentYear);
        yearBox.setStyle(comboStyle);

        // Group selectors horizontally
        HBox selectionBox = new HBox(15, monthLabel, monthBox, yearLabel, yearBox);
        selectionBox.setAlignment(Pos.CENTER);

        // 3. Output Labels
        String resultLabelStyle = "-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;";
        Label incomeLabel = new Label();
        incomeLabel.setStyle(resultLabelStyle);

        Label expenseLabel = new Label();
        expenseLabel.setStyle(resultLabelStyle);

        Label savingsLabel = new Label();
        savingsLabel.setStyle(resultLabelStyle);

        final double[] reportData = new double[3];

        // 4. Buttons Styling & Logic
        Button generateButton = new Button("Generate Report");
        generateButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 8 20; -fx-background-radius: 5; -fx-cursor: hand;");

        Button exportButton = new Button("Export PDF");
        exportButton.setStyle("-fx-background-color: #95a5a6; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 8 20; -fx-background-radius: 5; -fx-cursor: hand;");

        exportButton.setOnAction(event -> {
            if (reportData[0] == 0 && reportData[1] == 0 && reportData[2] == 0) {
                // Prevents exporting a blank report
                return;
            }

            FileChooser chooser = new FileChooser();
            chooser.setTitle("Save Monthly Report");
            chooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("PDF Files", "*.pdf")
            );

            File file = chooser.showSaveDialog(stage);
            if(file == null){
                return;
            }

            pdfReportService.createReport(
                    file,
                    monthBox.getValue(),
                    yearBox.getValue(),
                    reportData[0],
                    reportData[1],
                    reportData[2]
            );
        });

        generateButton.setOnAction(event -> {
            int month = monthBox.getValue();
            int year = yearBox.getValue();

            double income = reportService.getMonthlyIncome(userId, month, year);
            double expense = reportService.getMonthlyExpense(userId, month, year);
            double savings = reportService.getSavings(userId, month, year);

            reportData[0] = income;
            reportData[1] = expense;
            reportData[2] = savings;

            incomeLabel.setText("Total Income: " + income);
            incomeLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #27ae60;"); // Green for income

            expenseLabel.setText("Total Expense: " + expense);
            expenseLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #e74c3c;"); // Red for expense

            savingsLabel.setText("Savings: " + savings);
            savingsLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #2980b9;"); // Blue for savings
        });

        HBox buttonBox = new HBox(20, generateButton, exportButton);
        buttonBox.setAlignment(Pos.CENTER);

        VBox resultsBox = new VBox(15, incomeLabel, expenseLabel, savingsLabel);
        resultsBox.setAlignment(Pos.CENTER);

        // 5. Layout Assembly (No Back Button)
        VBox layout = new VBox(30, title, selectionBox, buttonBox, resultsBox);
        layout.setPadding(new Insets(40));
        layout.setAlignment(Pos.TOP_CENTER);

        return layout;
    }
}