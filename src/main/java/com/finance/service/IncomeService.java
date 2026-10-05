package com.finance.service;


import com.finance.database.IncomeDAO;
import com.finance.model.Income;

import java.util.List;



public class IncomeService {


    private final IncomeDAO incomeDAO;



    public IncomeService(){

        incomeDAO =
                new IncomeDAO();

    }




    public void addIncome(Income income){

        incomeDAO.addIncome(
                income
        );

    }




    public List<Income> getIncomeByUser(int userId){

        return incomeDAO.getIncomeByUser(
                userId
        );

    }




    public Income getIncomeById(int id){

        return incomeDAO.getIncomeById(
                id
        );

    }




    public void updateIncome(Income income){

        incomeDAO.updateIncome(
                income
        );

    }




    public void deleteIncome(int id){

        incomeDAO.deleteIncome(
                id
        );

    }


}