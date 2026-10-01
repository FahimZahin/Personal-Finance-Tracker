package com.finance.view;


import com.finance.model.Expense;
import com.finance.service.ExpenseService;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.layout.VBox;

import java.time.LocalDate;



public class ExpenseView {


    private final ExpenseService expenseService;


    public ExpenseView() {

        expenseService = new ExpenseService();

    }



    public VBox getView() {


        Label title = new Label(
                "Add Expense"
        );


        TextField amountField = new TextField();

        amountField.setPromptText(
                "Amount"
        );


        TextField categoryField = new TextField();

        categoryField.setPromptText(
                "Category"
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
                "Save Expense"
        );


        Button deleteButton = new Button(
                "Delete Expense"
        );


        Label message = new Label();



        // Save expense

        saveButton.setOnAction(event -> {


            try {


                double amount =
                        Double.parseDouble(
                                amountField.getText()
                        );


                Expense expense = new Expense();


                expense.setAmount(amount);


                expense.setCategory(
                        categoryField.getText()
                );


                expense.setDescription(
                        descriptionField.getText()
                );


                expense.setDate(
                        datePicker.getValue()
                );


                expenseService.addExpense(expense);


                message.setText(
                        "Expense saved successfully!"
                );


                amountField.clear();

                categoryField.clear();

                descriptionField.clear();



            } catch(Exception e) {


                message.setText(
                        "Invalid expense data."
                );


            }


        });




        // Delete expense

        deleteButton.setOnAction(event -> {


            try {


                int id =
                        Integer.parseInt(
                                amountField.getText()
                        );


                expenseService.deleteExpense(id);


                message.setText(
                        "Expense deleted successfully!"
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

                categoryField,

                descriptionField,

                datePicker,

                saveButton,

                deleteButton,

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