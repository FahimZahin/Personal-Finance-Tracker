package com.finance.service;


import com.finance.model.Expense;
import com.finance.model.Income;


import java.time.LocalDate;
import java.util.List;



public class ReportService {


    private final IncomeService incomeService;

    private final ExpenseService expenseService;




    public ReportService(){


        incomeService =
                new IncomeService();


        expenseService =
                new ExpenseService();


    }







    public double getMonthlyIncome(
            int userId,
            int month,
            int year
    ){


        double total = 0;



        List<Income> incomes =
                incomeService.getIncomeByUser(
                        userId
                );



        for(Income income : incomes){


            LocalDate date =
                    income.getDate();



            if(
                    date.getMonthValue() == month
                            &&
                            date.getYear() == year
            ){

                total += income.getAmount();

            }


        }



        return total;


    }








    public double getMonthlyExpense(
            int userId,
            int month,
            int year
    ){


        double total = 0;



        List<Expense> expenses =
                expenseService.getExpensesByUser(
                        userId
                );



        for(Expense expense : expenses){


            LocalDate date =
                    expense.getDate();



            if(
                    date.getMonthValue() == month
                            &&
                            date.getYear() == year
            ){

                total += expense.getAmount();

            }


        }



        return total;


    }







    public double getSavings(
            int userId,
            int month,
            int year
    ){


        return getMonthlyIncome(
                userId,
                month,
                year
        )
                -
                getMonthlyExpense(
                        userId,
                        month,
                        year
                );


    }



}