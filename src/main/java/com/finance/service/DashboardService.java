package com.finance.service;


import com.finance.model.Expense;
import com.finance.model.Income;


import java.util.List;



public class DashboardService {



    private final ExpenseService expenseService;

    private final IncomeService incomeService;




    public DashboardService(){


        expenseService =
                new ExpenseService();


        incomeService =
                new IncomeService();


    }





    public double getTotalExpense(int userId){


        List<Expense> expenses =
                expenseService.getExpensesByUser(userId);



        double total = 0;



        for(Expense expense : expenses){


            total += expense.getAmount();


        }



        return total;


    }





    public double getTotalIncome(int userId){


        List<Income> incomes =
                incomeService.getIncomeByUser(userId);



        double total = 0;



        for(Income income : incomes){


            total += income.getAmount();


        }



        return total;


    }





    public double getBalance(int userId){


        return getTotalIncome(userId)
                -
                getTotalExpense(userId);


    }



}