package com.finance.model;


public class CategoryExpense {


    private String category;

    private double amount;



    public CategoryExpense(
            String category,
            double amount
    ){

        this.category = category;
        this.amount = amount;

    }



    public String getCategory(){

        return category;

    }



    public double getAmount(){

        return amount;

    }

}