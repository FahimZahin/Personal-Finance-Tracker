package com.finance;


public class CurrencyManager {


    private static String currency = "৳";



    public static void setCurrency(String value){

        currency = value;

    }



    public static String getCurrency(){

        return currency;

    }



    public static String format(double amount){


        return currency
                +
                String.format(
                        "%.2f",
                        amount
                );


    }



}