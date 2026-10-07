package com.finance.model;


public class UserSettings {


    private int id;

    private int userId;

    private String currency;

    private String theme;



    public UserSettings(){

    }



    public UserSettings(
            int id,
            int userId,
            String currency,
            String theme
    ){

        this.id = id;

        this.userId = userId;

        this.currency = currency;

        this.theme = theme;

    }



    public int getId(){

        return id;

    }



    public void setId(int id){

        this.id = id;

    }



    public int getUserId(){

        return userId;

    }



    public void setUserId(int userId){

        this.userId = userId;

    }



    public String getCurrency(){

        return currency;

    }



    public void setCurrency(String currency){

        this.currency = currency;

    }



    public String getTheme(){

        return theme;

    }



    public void setTheme(String theme){

        this.theme = theme;

    }



}