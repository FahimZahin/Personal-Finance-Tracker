package com.finance.service;

import com.finance.database.IncomeDAO;
import com.finance.model.Income;

import java.util.List;

public class IncomeService {


    private final IncomeDAO incomeDAO;


    public IncomeService() {

        this.incomeDAO = new IncomeDAO();

    }



    // Add income
    public void addIncome(Income income) {


        if (income.getAmount() <= 0) {

            throw new IllegalArgumentException("Amount must be greater than zero.");

        }


        if (income.getSource() == null || income.getSource().isEmpty()) {

            throw new IllegalArgumentException("Source cannot be empty.");

        }


        incomeDAO.addIncome(income);

    }



    // Get all income records
    public List<Income> getAllIncome() {

        return incomeDAO.getAllIncome();

    }



    // Delete income
    public void deleteIncome(int id) {


        if (id <= 0) {

            throw new IllegalArgumentException("Invalid income id.");

        }


        incomeDAO.deleteIncome(id);

    }

    public double getTotalIncome() {

        double total = 0;

        for (Income income : incomeDAO.getAllIncome()) {

            total += income.getAmount();

        }

        return total;

    }

}