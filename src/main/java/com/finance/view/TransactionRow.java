package com.finance.view;


public class TransactionRow {



    private String date;

    private String type;

    private String detail;

    private double amount;




    public TransactionRow(
            String date,
            String type,
            String detail,
            double amount
    ){

        this.date = date;
        this.type = type;
        this.detail = detail;
        this.amount = amount;

    }





    public String getDate(){

        return date;

    }




    public String getType(){

        return type;

    }




    public String getDetail(){

        return detail;

    }




    public double getAmount(){

        return amount;

    }


}