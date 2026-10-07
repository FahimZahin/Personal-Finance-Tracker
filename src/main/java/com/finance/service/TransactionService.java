package com.finance.service;


import com.finance.view.TransactionRow;

import java.util.ArrayList;
import java.util.List;



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


}