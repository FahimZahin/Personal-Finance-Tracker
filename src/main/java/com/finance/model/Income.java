package com.finance.model;


import java.time.LocalDate;



public class Income {


    private int id;

    private int userId;

    private double amount;

    private String source;

    private String description;

    private LocalDate date;



    public int getId(){

        return id;

    }


    public void setId(int id){

        this.id=id;

    }



    public int getUserId(){

        return userId;

    }


    public void setUserId(int userId){

        this.userId=userId;

    }




    public double getAmount(){

        return amount;

    }


    public void setAmount(double amount){

        this.amount=amount;

    }




    public String getSource(){

        return source;

    }


    public void setSource(String source){

        this.source=source;

    }




    public String getDescription(){

        return description;

    }


    public void setDescription(String description){

        this.description=description;

    }




    public LocalDate getDate(){

        return date;

    }


    public void setDate(LocalDate date){

        this.date=date;

    }


}