package com.finance.service;


import com.finance.model.Expense;
import com.finance.model.Income;
import com.finance.model.CategoryExpense;


import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;



public class DashboardService {


    private final ExpenseService expenseService;

    private final IncomeService incomeService;



    public DashboardService(){


        expenseService =
                new ExpenseService();


        incomeService =
                new IncomeService();


    }





    public double getTotalExpense(int userId){


        double total = 0;


        List<Expense> expenses =
                expenseService.getExpensesByUser(userId);



        for(Expense expense : expenses){


            total += expense.getAmount();


        }


        return total;

    }







    public double getTotalIncome(int userId){


        double total = 0;


        List<Income> incomes =
                incomeService.getIncomeByUser(userId);



        for(Income income : incomes){


            total += income.getAmount();


        }


        return total;

    }






    public double getBalance(int userId){


        return getTotalIncome(userId)
                -
                getTotalExpense(userId);


    }






    public List<CategoryExpense> getExpenseByCategory(
            int userId
    ){


        Map<String,Double> map =
                new HashMap<>();



        List<Expense> expenses =
                expenseService.getExpensesByUser(userId);



        for(Expense expense : expenses){


            map.put(
                    expense.getCategory(),
                    map.getOrDefault(
                            expense.getCategory(),
                            0.0
                    )
                            +
                            expense.getAmount()
            );


        }





        List<CategoryExpense> result =
                new ArrayList<>();



        for(
                Map.Entry<String,Double> entry :
                map.entrySet()
        ){


            result.add(
                    new CategoryExpense(
                            entry.getKey(),
                            entry.getValue()
                    )
            );


        }



        return result;


    }

    public double getSavingsRate(int userId){

        double income =
                getTotalIncome(userId);


        if(income <= 0){

            return 0;

        }


        double expense =
                getTotalExpense(userId);


        double savings =
                income - expense;


        return (savings / income) * 100;

    }

    public String getTopExpenseCategory(int userId){


        var categories =
                getExpenseByCategory(userId);



        String topCategory =
                "None";


        double highest =
                0;



        for(var item : categories){


            if(item.getAmount() > highest){


                highest =
                        item.getAmount();


                topCategory =
                        item.getCategory();


            }


        }



        return topCategory;

    }


}