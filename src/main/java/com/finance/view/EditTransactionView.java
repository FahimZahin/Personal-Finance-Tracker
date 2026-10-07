package com.finance.view;


import com.finance.model.Expense;
import com.finance.model.Income;

import com.finance.service.ExpenseService;
import com.finance.service.IncomeService;


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



public class EditTransactionView {


    private final IncomeService incomeService;

    private final ExpenseService expenseService;



    public EditTransactionView(){


        incomeService =
                new IncomeService();


        expenseService =
                new ExpenseService();


    }







    public VBox getView(
            Stage stage,
            TransactionRow transaction
    ){



        Label title =
                new Label(
                        "Edit " + transaction.getType()
                );




        TextField amountField =
                new TextField();



        amountField.setText(
                String.valueOf(
                        transaction.getAmount()
                )
        );





        TextField detailField =
                new TextField();



        detailField.setText(
                transaction.getDetail()
        );





        DatePicker datePicker =
                new DatePicker();




        datePicker.setValue(
                LocalDate.parse(
                        transaction.getDate()
                )
        );







        Label message =
                new Label();






        Button saveButton =
                new Button(
                        "Save Changes"
                );





        saveButton.setOnAction(event -> {


            try{


                double amount =
                        Double.parseDouble(
                                amountField.getText()
                        );



                if(
                        transaction.getType()
                                .equals("Income")
                ){



                    Income income =
                            new Income();



                    income.setId(
                            transaction.getId()
                    );


                    income.setUserId(
                            com.finance.Session
                                    .getUser()
                                    .getId()
                    );


                    income.setAmount(
                            amount
                    );



                    income.setSource(
                            detailField.getText()
                    );



                    income.setDescription(
                            "Updated"
                    );



                    income.setDate(
                            datePicker.getValue()
                    );



                    incomeService.updateIncome(
                            income
                    );


                }
                else{


                    Expense expense =
                            new Expense();



                    expense.setId(
                            transaction.getId()
                    );



                    expense.setUserId(
                            com.finance.Session
                                    .getUser()
                                    .getId()
                    );



                    expense.setAmount(
                            amount
                    );



                    expense.setCategory(
                            detailField.getText()
                    );



                    expense.setDescription(
                            "Updated"
                    );



                    expense.setDate(
                            datePicker.getValue()
                    );



                    expenseService.updateExpense(
                            expense
                    );


                }





                message.setText(
                        "Updated successfully"
                );



            }
            catch(Exception e){


                e.printStackTrace();


                message.setText(
                        "Update failed"
                );


            }


        });







        Button backButton =
                new Button(
                        "Back"
                );



        backButton.setOnAction(event -> {



            TransactionView transactionView =
                    new TransactionView();



            stage.setScene(
                    new Scene(
                            transactionView.getView(stage),
                            900,
                            650
                    )
            );


        });







        VBox layout =
                new VBox(
                        15,
                        title,
                        amountField,
                        detailField,
                        datePicker,
                        saveButton,
                        backButton,
                        message
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