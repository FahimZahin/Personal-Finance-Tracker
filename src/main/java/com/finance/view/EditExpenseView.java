package com.finance.view;

import javafx.scene.control.DatePicker;
import com.finance.model.Expense;
import com.finance.service.ExpenseService;
import com.finance.Session;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;


public class EditExpenseView {


    private final int expenseId;
    private Expense existingExpense;

    private final ExpenseService expenseService;



    public EditExpenseView(int expenseId){

        this.expenseId = expenseId;

        expenseService =
                new ExpenseService();


        existingExpense =
                expenseService.getExpenseById(expenseId);

    }



    public VBox getView(Stage stage){


        Label title =
                new Label(
                        "Edit Expense"
                );



        TextField amountField =
                new TextField();

        amountField.setPromptText(
                "Amount"
        );



        TextField categoryField =
                new TextField();

        categoryField.setPromptText(
                "Category"
        );



        TextField descriptionField =
                new TextField();

        descriptionField.setPromptText(
                "Description"
        );



        DatePicker datePicker =
                new DatePicker(
                        LocalDate.now()
                );



        Button updateButton =
                new Button(
                        "Update Expense"
                );



        Label message =
                new Label();




        updateButton.setOnAction(event -> {


            try {


                Expense expense =
                        new Expense();



                expense.setId(
                        expenseId
                );



                expense.setUserId(
                        Session.getUser().getId()
                );



                expense.setAmount(
                        Double.parseDouble(
                                amountField.getText()
                        )
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



                expenseService.updateExpense(
                        expense
                );



                TransactionView transactionView =
                        new TransactionView();


                stage.setScene(
                        new Scene(
                                transactionView.getView(stage),
                                700,
                                500
                        )
                );



            }
            catch(Exception e){

                message.setText(
                        "Invalid expense data"
                );

            }


        });




        Button backButton =
                new Button(
                        "Back to Transactions"
                );



        backButton.setOnAction(event -> {


            TransactionView transactionView =
                    new TransactionView();



            Scene scene =
                    new Scene(
                            transactionView.getView(stage),
                            700,
                            500
                    );



            stage.setScene(scene);



        });






        VBox layout =
                new VBox(
                        15,
                        title,
                        amountField,
                        categoryField,
                        descriptionField,
                        datePicker,
                        updateButton,
                        backButton,
                        message
                );



        layout.setPadding(
                new Insets(20)
        );



        layout.setAlignment(
                Pos.CENTER
        );

        if(existingExpense != null){


            amountField.setText(
                    String.valueOf(
                            existingExpense.getAmount()
                    )
            );


            categoryField.setText(
                    existingExpense.getCategory()
            );


            descriptionField.setText(
                    existingExpense.getDescription()
            );


            datePicker.setValue(
                    existingExpense.getDate()
            );


        }

        return layout;


    }

}