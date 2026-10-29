package com.finance.view;


import com.finance.model.Income;
import com.finance.service.IncomeService;
import com.finance.Session;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

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


        // 1. Title Styling
        Label title = new Label(
                "Add Income"
        );
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");


        // 2. Input Fields Styling
        String inputStyle = "-fx-font-size: 14px; -fx-padding: 10; -fx-background-radius: 5; -fx-border-color: #bdc3c7; -fx-border-radius: 5;";


        TextField amountField = new TextField();
        amountField.setPromptText(
                "Amount (e.g. 5000)"
        );
        amountField.setStyle(inputStyle);
        amountField.setMaxWidth(350);



        TextField sourceField = new TextField();
        sourceField.setPromptText(
                "Source (Salary, Freelance, etc.)"
        );
        sourceField.setStyle(inputStyle);
        sourceField.setMaxWidth(350);



        TextField descriptionField = new TextField();
        descriptionField.setPromptText(
                "Description (Optional)"
        );
        descriptionField.setStyle(inputStyle);
        descriptionField.setMaxWidth(350);



        DatePicker datePicker = new DatePicker();
        datePicker.setValue(
                LocalDate.now()
        );
        datePicker.setStyle("-fx-font-size: 14px;");
        datePicker.setMaxWidth(350);



        // 3. Button Styling
        Button saveButton = new Button(
                "Save Income"
        );
        saveButton.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white; -fx-font-size: 15px; -fx-font-weight: bold; -fx-padding: 10 30; -fx-background-radius: 5; -fx-cursor: hand;");



        Label message = new Label();



        // 4. Save logic with dynamic message styling
        saveButton.setOnAction(event -> {

            try {

                if(amountField.getText().isEmpty()
                        ||
                        sourceField.getText().isEmpty()){

                    message.setText(
                            "Please fill all required fields."
                    );
                    message.setStyle("-fx-text-fill: #e74c3c; -fx-font-size: 14px; -fx-font-weight: bold;");

                    return;

                }



                double amount =
                        Double.parseDouble(
                                amountField.getText()
                        );



                if(amount <= 0){

                    message.setText(
                            "Amount must be greater than zero."
                    );
                    message.setStyle("-fx-text-fill: #e74c3c; -fx-font-size: 14px; -fx-font-weight: bold;");

                    return;

                }

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



                incomeService.addIncome(income);



                message.setText(
                        "Income saved successfully!"
                );
                message.setStyle("-fx-text-fill: #27ae60; -fx-font-size: 14px; -fx-font-weight: bold;");



                amountField.clear();

                sourceField.clear();

                descriptionField.clear();

                datePicker.setValue(LocalDate.now());




            } catch(Exception e) {

                message.setText(
                        "Invalid income data."
                );
                message.setStyle("-fx-text-fill: #e74c3c; -fx-font-size: 14px; -fx-font-weight: bold;");

            }



        });



        // 5. Layout Assembly (Removed the Back button completely)
        VBox layout = new VBox(
                20,
                title,
                amountField,
                sourceField,
                descriptionField,
                datePicker,
                saveButton,
                message
        );





        layout.setPadding(
                new Insets(40)
        );



        layout.setAlignment(
                Pos.TOP_CENTER
        );



        return layout;



    }


}