package com.finance.view;


import com.finance.model.Income;
import com.finance.service.IncomeService;


import javafx.geometry.Insets;
import javafx.geometry.Pos;


import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.time.LocalDate;



public class EditIncomeView {


    private final int incomeId;

    private final IncomeService incomeService;

    private Income existingIncome;



    public EditIncomeView(int incomeId){


        this.incomeId =
                incomeId;


        incomeService =
                new IncomeService();


        existingIncome =
                incomeService.getIncomeById(
                        incomeId
                );

    }




    public VBox getView(Stage stage){



        Label title =
                new Label(
                        "Edit Income"
                );



        TextField amountField =
                new TextField();


        amountField.setPromptText(
                "Amount"
        );



        TextField sourceField =
                new TextField();


        sourceField.setPromptText(
                "Source"
        );



        TextField descriptionField =
                new TextField();


        descriptionField.setPromptText(
                "Description"
        );



        DatePicker datePicker =
                new DatePicker();





        if(existingIncome != null){


            amountField.setText(
                    String.valueOf(
                            existingIncome.getAmount()
                    )
            );


            sourceField.setText(
                    existingIncome.getSource()
            );


            descriptionField.setText(
                    existingIncome.getDescription()
            );


            datePicker.setValue(
                    existingIncome.getDate()
            );


        }
        else{

            datePicker.setValue(
                    LocalDate.now()
            );

        }





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
                        "Invalid income data."
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
                        sourceField,
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



        return layout;

    }


}