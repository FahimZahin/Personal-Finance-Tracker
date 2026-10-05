package com.finance.service;


import com.finance.database.ExpenseDAO;
import com.finance.model.Expense;


import java.util.List;



public class ExpenseService {


    private final ExpenseDAO expenseDAO;



    public ExpenseService(){

        expenseDAO = new ExpenseDAO();

    }



    public void addExpense(Expense expense){

        expenseDAO.addExpense(expense);

    }



    public List<Expense> getExpensesByUser(int userId){

        return expenseDAO.getExpensesByUser(userId);

    }



    public void deleteExpense(int id){

        expenseDAO.deleteExpense(id);

    }

    public void updateExpense(Expense expense){

        expenseDAO.updateExpense(
                expense
        );

    }

    public Expense getExpenseById(int id){

        return expenseDAO.getExpenseById(id);

    }



}