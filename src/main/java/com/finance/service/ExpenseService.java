package com.finance.service;

import com.finance.database.ExpenseDAO;
import com.finance.model.Expense;

import java.util.List;

public class ExpenseService {

    private final ExpenseDAO expenseDAO;


    public ExpenseService() {
        this.expenseDAO = new ExpenseDAO();
    }


    // Add expense
    public void addExpense(Expense expense) {

        if (expense.getAmount() <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }

        if (expense.getCategory() == null || expense.getCategory().isEmpty()) {
            throw new IllegalArgumentException("Category cannot be empty.");
        }

        expenseDAO.addExpense(expense);
    }


    // Get all expenses
    public List<Expense> getAllExpenses() {

        return expenseDAO.getAllExpenses();

    }


    // Delete expense
    public void deleteExpense(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException("Invalid expense id.");
        }

        expenseDAO.deleteExpense(id);

    }

}