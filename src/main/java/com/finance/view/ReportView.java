package com.finance.view;


import com.finance.Session;
import com.finance.service.ReportService;


import javafx.geometry.Insets;
import javafx.geometry.Pos;

import com.finance.service.PdfReportService;

import javafx.stage.FileChooser;

import java.io.File;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;


import javafx.scene.layout.VBox;


import javafx.stage.Stage;


import java.time.LocalDate;



public class ReportView {


    private final ReportService reportService;

    private final PdfReportService pdfReportService;

    public ReportView(){

        reportService =
                new ReportService();


        pdfReportService =
                new PdfReportService();

    }






    public VBox getView(Stage stage){



        int userId =
                Session.getUser()
                        .getId();




        Label title =
                new Label(
                        "Monthly Report"
                );






        ComboBox<Integer> monthBox =
                new ComboBox<>();



        for(int i = 1; i <= 12; i++){


            monthBox.getItems()
                    .add(i);


        }



        monthBox.setValue(
                LocalDate.now()
                        .getMonthValue()
        );






        ComboBox<Integer> yearBox =
                new ComboBox<>();



        int currentYear =
                LocalDate.now()
                        .getYear();



        for(
                int i = currentYear - 5;
                i <= currentYear;
                i++
        ){


            yearBox.getItems()
                    .add(i);


        }



        yearBox.setValue(
                currentYear
        );






        Label incomeLabel =
                new Label();



        Label expenseLabel =
                new Label();



        Label savingsLabel =
                new Label();

        final double[] reportData =
                new double[3];







        Button generateButton =
                new Button(
                        "Generate Report"
                );

        Button exportButton =
                new Button(
                        "Export PDF"
                );

        exportButton.setOnAction(event -> {


            FileChooser chooser =
                    new FileChooser();


            chooser.setTitle(
                    "Save Monthly Report"
            );


            chooser.getExtensionFilters()
                    .add(
                            new FileChooser.ExtensionFilter(
                                    "PDF Files",
                                    "*.pdf"
                            )
                    );



            File file =
                    chooser.showSaveDialog(
                            stage
                    );


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



            int month =
                    monthBox.getValue();



            int year =
                    yearBox.getValue();




            double income =
                    reportService.getMonthlyIncome(
                            userId,
                            month,
                            year
                    );




            double expense =
                    reportService.getMonthlyExpense(
                            userId,
                            month,
                            year
                    );



            double savings =
                    reportService.getSavings(
                            userId,
                            month,
                            year
                    );

            reportData[0] = income;
            reportData[1] = expense;
            reportData[2] = savings;





            incomeLabel.setText(
                    "Total Income: "
                            + income
            );



            expenseLabel.setText(
                    "Total Expense: "
                            + expense
            );



            savingsLabel.setText(
                    "Savings: "
                            + savings
            );



        });








        Button backButton =
                new Button(
                        "Back Dashboard"
                );



        backButton.setOnAction(event -> {



            DashboardView dashboard =
                    new DashboardView();



            stage.setScene(
                    new Scene(
                            dashboard.getView(stage),
                            900,
                            650
                    )
            );


        });









        VBox layout =
                new VBox(
                        20,
                        title,
                        monthBox,
                        yearBox,
                        generateButton,
                        exportButton,
                        incomeLabel,
                        expenseLabel,
                        savingsLabel,
                        backButton
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