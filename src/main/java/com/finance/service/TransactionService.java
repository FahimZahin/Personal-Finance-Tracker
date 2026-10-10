package com.finance.service;


import com.finance.view.TransactionRow;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;



public class TransactionService {


    public List<TransactionRow> filter(
            List<TransactionRow> transactions,
            String keyword,
            String type
    ){


        List<TransactionRow> result =
                new ArrayList<>();



        for(TransactionRow transaction : transactions){



            boolean matchText =
                    keyword == null
                            ||
                            keyword.isEmpty()
                            ||
                            transaction.getDetail()
                                    .toLowerCase()
                                    .contains(
                                            keyword.toLowerCase()
                                    );



            boolean matchType =
                    type.equals("All")
                            ||
                            transaction.getType()
                                    .equals(type);



            if(matchText && matchType){

                result.add(transaction);

            }



        }



        return result;


    }

    public List<TransactionRow> filterByDate(
            List<TransactionRow> transactions,
            LocalDate from,
            LocalDate to
    ){

        List<TransactionRow> result =
                new ArrayList<>();


        for(TransactionRow transaction : transactions){


            LocalDate date =
                    LocalDate.parse(
                            transaction.getDate()
                    );


            boolean afterFrom =
                    from == null
                            ||
                            !date.isBefore(from);



            boolean beforeTo =
                    to == null
                            ||
                            !date.isAfter(to);



            if(afterFrom && beforeTo){

                result.add(transaction);

            }


        }


        return result;

    }

    public List<TransactionRow> sortTransactions(
            List<TransactionRow> transactions,
            String option
    ){

        if(option.equals("Newest")){


            transactions.sort(
                    (a,b) ->
                            b.getDate()
                                    .compareTo(
                                            a.getDate()
                                    )
            );


        }


        else if(option.equals("Oldest")){


            transactions.sort(
                    (a,b) ->
                            a.getDate()
                                    .compareTo(
                                            b.getDate()
                                    )
            );


        }


        else if(option.equals("Highest Amount")){


            transactions.sort(
                    (a,b) ->
                            Double.compare(
                                    b.getAmount(),
                                    a.getAmount()
                            )
            );


        }


        else if(option.equals("Lowest Amount")){


            transactions.sort(
                    (a,b) ->
                            Double.compare(
                                    a.getAmount(),
                                    b.getAmount()
                            )
            );


        }



        return transactions;

    }


}