package com.finance.view;


import com.finance.model.Income;
import com.finance.service.IncomeService;
import com.finance.Session;

import javafx.geometry.Insets;
import javafx.geometry.Pos;


import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


import javafx.scene.layout.VBox;


import java.time.LocalDate;



public class IncomeView {


    private final IncomeService incomeService;



    public IncomeView() {


        incomeService = new IncomeService();


    }




    public VBox getView(Stage stage) {



        Label title = new Label(
                "Add Income"
        );



        TextField amountField = new TextField();


        amountField.setPromptText(
                "Amount"
        );



        TextField sourceField = new TextField();


        sourceField.setPromptText(
                "Source (Salary, Freelance, etc.)"
        );



        TextField descriptionField = new TextField();


        descriptionField.setPromptText(
                "Description"
        );



        DatePicker datePicker = new DatePicker();



        datePicker.setValue(
                LocalDate.now()
        );



        Button saveButton = new Button(
                "Save Income"
        );



        Button deleteButton = new Button(
                "Delete Income"
        );

        Button backButton = new Button(
                "Back to Dashboard"
        );


        backButton.setOnAction(event -> {


            DashboardView dashboardView =
                    new DashboardView();


            Scene scene =
                    new Scene(
                            dashboardView.getView(stage),
                            700,
                            500
                    );


            stage.setScene(scene);


        });



        Label message = new Label();





        // Save income

        saveButton.setOnAction(event -> {



            try {



                double amount =
                        Double.parseDouble(
                                amountField.getText()
                        );



                Income income = new Income();



                income.setAmount(amount);
                income.setUserId(
                        Session.getUser().getId()
                );



                income.setSource(
                        sourceField.getText()
                );



                income.setDescription(
                        descriptionField.getText()
                );



                income.setDate(
                        datePicker.getValue()
                );

                income.setUserId(
                        Session.getUser().getId()
                );



                incomeService.addIncome(income);



                message.setText(
                        "Income saved successfully!"
                );



                amountField.clear();

                sourceField.clear();

                descriptionField.clear();




            } catch(Exception e) {



                message.setText(
                        "Invalid income data."
                );



            }



        });






        // Delete income

        deleteButton.setOnAction(event -> {



            try {



                int id =
                        Integer.parseInt(
                                amountField.getText()
                        );



                incomeService.deleteIncome(id);



                message.setText(
                        "Income deleted successfully!"
                );



            } catch(Exception e) {



                message.setText(
                        "Invalid ID"
                );



            }



        });







        VBox layout = new VBox(
                15,
                title,
                amountField,
                sourceField,
                descriptionField,
                datePicker,
                saveButton,
                deleteButton,
                backButton,
                message
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