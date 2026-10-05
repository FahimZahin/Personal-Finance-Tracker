package com.finance.view;


import com.finance.model.Income;
import com.finance.service.IncomeService;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.LocalDate;


public class EditIncomeView {


    private final int incomeId;

    private final IncomeService incomeService;


    public EditIncomeView(int incomeId){

        this.incomeId =
                incomeId;

        incomeService =
                new IncomeService();

    }



    public VBox getView(Stage stage){


        Label title =
                new Label(
                        "Edit Income"
                );


        TextField amountField =
                new TextField();


        TextField sourceField =
                new TextField();


        TextField descriptionField =
                new TextField();


        DatePicker datePicker =
                new DatePicker(
                        LocalDate.now()
                );


        Button updateButton =
                new Button(
                        "Update Income"
                );


        Label message =
                new Label();



        updateButton.setOnAction(event -> {


            try{


                Income income =
                        new Income();


                income.setId(
                        incomeId
                );


                income.setAmount(
                        Double.parseDouble(
                                amountField.getText()
                        )
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


                incomeService.updateIncome(
                        income
                );


                message.setText(
                        "Income updated successfully!"
                );


            }
            catch(Exception e){

                message.setText(
                        "Invalid data"
                );

            }

        });



        Button back =
                new Button(
                        "Back"
                );


        back.setOnAction(event -> {


            TransactionView transactionView =
                    new TransactionView();


            stage.setScene(
                    new Scene(
                            transactionView.getView(stage),
                            700,
                            500
                    )
            );

        });



        VBox layout =
                new VBox(
                        15,
                        title,
                        amountField,
                        sourceField,
                        descriptionField,
                        datePicker,
                        updateButton,
                        back,
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