package com.finance.view;


import com.finance.model.Expense;
import com.finance.service.ExpenseService;

import com.finance.Session;
import javafx.geometry.Insets;
import javafx.geometry.Pos;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


import javafx.scene.layout.VBox;


import javafx.stage.Stage;


import java.time.LocalDate;



public class ExpenseView {



    private final ExpenseService expenseService;




    public ExpenseView(){

        expenseService =
                new ExpenseService();

    }





    public VBox getView(Stage stage){



        Label title =
                new Label(
                        "Add Expense"
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
                new DatePicker();


        datePicker.setValue(
                LocalDate.now()
        );





        Label message =
                new Label();





        Button saveButton =
                new Button(
                        "Save Expense"
                );





        saveButton.setOnAction(event -> {


            try {


                double amount =
                        Double.parseDouble(
                                amountField.getText()
                        );



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



                amountField.clear();

                categoryField.clear();

                descriptionField.clear();



            }
            catch(Exception e){


                message.setText(
                        "Invalid expense data."
                );


            }


        });







        Button deleteButton =
                new Button(
                        "Delete Expense"
                );





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



            }
            catch(Exception e){


                message.setText(
                        "Invalid ID"
                );


            }



        });







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
                            700,
                            500
                    );


            stage.setScene(scene);



            stage.setScene(
                    scene
            );


        });







        VBox layout =
                new VBox(

                        15,

                        title,

                        amountField,

                        categoryField,

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