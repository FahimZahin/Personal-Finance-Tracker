package com.finance.view;


import com.finance.model.Expense;
import com.finance.service.ExpenseService;
import com.finance.Session;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.stage.Stage;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


import java.time.LocalDate;



public class ExpenseView {



    private final ExpenseService expenseService;




    public ExpenseView(){

        expenseService =
                new ExpenseService();

    }





    public VBox getView(Stage stage){


        // 1. Title Styling
        Label title = new Label(
                "Add Expense"
        );
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");


        // 2. Input Fields Styling
        String inputStyle = "-fx-font-size: 14px; -fx-padding: 10; -fx-background-radius: 5; -fx-border-color: #bdc3c7; -fx-border-radius: 5;";


        TextField amountField = new TextField();
        amountField.setPromptText(
                "Amount (e.g. 50)"
        );
        amountField.setStyle(inputStyle);
        amountField.setMaxWidth(350);




        TextField categoryField = new TextField();
        categoryField.setPromptText(
                "Category (Food, Rent, etc.)"
        );
        categoryField.setStyle(inputStyle);
        categoryField.setMaxWidth(350);




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





        Label message = new Label();





        // 3. Button Styling
        Button saveButton = new Button(
                "Save Expense"
        );
        // Using red for Expense to thematically contrast with the green used in IncomeView
        saveButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-size: 15px; -fx-font-weight: bold; -fx-padding: 10 30; -fx-background-radius: 5; -fx-cursor: hand;");




        saveButton.setOnAction(event -> {


            try {


                if(amountField.getText().isEmpty()
                        ||
                        categoryField.getText().isEmpty()){


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



                Expense expense =
                        new Expense();



                expense.setAmount(
                        amount
                );



                expense.setCategory(
                        categoryField.getText()
                );



                expense.setDescription(
                        descriptionField.getText()
                );



                expense.setDate(
                        datePicker.getValue()
                );

                expense.setUserId(
                        Session.getUser().getId()
                );



                expenseService.addExpense(
                        expense
                );



                message.setText(
                        "Expense saved successfully!"
                );
                message.setStyle("-fx-text-fill: #27ae60; -fx-font-size: 14px; -fx-font-weight: bold;");



                amountField.clear();

                categoryField.clear();

                descriptionField.clear();

                datePicker.setValue(LocalDate.now());



            }
            catch(Exception e){


                message.setText(
                        "Invalid expense data."
                );
                message.setStyle("-fx-text-fill: #e74c3c; -fx-font-size: 14px; -fx-font-weight: bold;");


            }


        });







        Button deleteButton = new Button(
                "Delete Expense"
        );
        deleteButton.setStyle("-fx-background-color: #7f8c8d; -fx-text-fill: white; -fx-font-size: 15px; -fx-font-weight: bold; -fx-padding: 10 30; -fx-background-radius: 5; -fx-cursor: hand;");




        deleteButton.setOnAction(event -> {


            try {


                int id =
                        Integer.parseInt(
                                amountField.getText()
                        );



                expenseService.deleteExpense(
                        id
                );



                message.setText(
                        "Expense deleted successfully!"
                );
                message.setStyle("-fx-text-fill: #27ae60; -fx-font-size: 14px; -fx-font-weight: bold;");



            }
            catch(Exception e){


                message.setText(
                        "Invalid ID"
                );
                message.setStyle("-fx-text-fill: #e74c3c; -fx-font-size: 14px; -fx-font-weight: bold;");


            }



        });


        // Group buttons horizontally
        HBox buttonBox = new HBox(20, saveButton, deleteButton);
        buttonBox.setAlignment(Pos.CENTER);


        // 4. Layout Assembly (Removed Back Button)
        VBox layout = new VBox(
                20,
                title,
                amountField,
                categoryField,
                descriptionField,
                datePicker,
                buttonBox,
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