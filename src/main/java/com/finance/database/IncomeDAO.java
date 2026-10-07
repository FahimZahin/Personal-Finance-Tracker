package com.finance.database;


import com.finance.model.Income;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;



public class IncomeDAO {


    // ADD INCOME
    public void addIncome(Income income){


        String sql =
                """
                INSERT INTO income
                (user_id, amount, source, description, date)
                VALUES(?,?,?,?,?)
                """;


        try(
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)

        ){


            statement.setInt(
                    1,
                    income.getUserId()
            );


            statement.setDouble(
                    2,
                    income.getAmount()
            );


            statement.setString(
                    3,
                    income.getSource()
            );


            statement.setString(
                    4,
                    income.getDescription()
            );


            statement.setString(
                    5,
                    income.getDate().toString()
            );


            statement.executeUpdate();


            System.out.println(
                    "Income added successfully."
            );


        }
        catch(Exception e){

            e.printStackTrace();

        }


    }



    // GET ALL INCOME BY USER
    public List<Income> getIncomeByUser(int userId){


        List<Income> incomes =
                new ArrayList<>();


        String sql =
                """
                SELECT *
                FROM income
                WHERE user_id = ?
                """;


        try(
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)

        ){


            statement.setInt(
                    1,
                    userId
            );


            ResultSet result =
                    statement.executeQuery();



            while(result.next()){


                Income income =
                        new Income();


                income.setId(
                        result.getInt("id")
                );


                income.setUserId(
                        result.getInt("user_id")
                );


                income.setAmount(
                        result.getDouble("amount")
                );


                income.setSource(
                        result.getString("source")
                );


                income.setDescription(
                        result.getString("description")
                );


                income.setDate(
                        LocalDate.parse(
                                result.getString("date")
                        )
                );


                incomes.add(income);

            }


        }
        catch(Exception e){

            e.printStackTrace();

        }


        return incomes;

    }




    // GET SINGLE INCOME
    public Income getIncomeById(int id){


        Income income = null;


        String sql =
                "SELECT * FROM income WHERE id=?";


        try(
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)

        ){


            statement.setInt(
                    1,
                    id
            );


            ResultSet result =
                    statement.executeQuery();



            if(result.next()){


                income =
                        new Income();


                income.setId(
                        result.getInt("id")
                );


                income.setUserId(
                        result.getInt("user_id")
                );


                income.setAmount(
                        result.getDouble("amount")
                );


                income.setSource(
                        result.getString("source")
                );


                income.setDescription(
                        result.getString("description")
                );


                income.setDate(
                        LocalDate.parse(
                                result.getString("date")
                        )
                );


            }


        }
        catch(Exception e){

            e.printStackTrace();

        }


        return income;

    }





    // UPDATE INCOME
    public void updateIncome(Income income){


        String sql =
                """
                UPDATE income
                SET amount=?,
                    source=?,
                    description=?,
                    date=?
                WHERE id=?
                """;



        try(
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)

        ){


            statement.setDouble(
                    1,
                    income.getAmount()
            );


            statement.setString(
                    2,
                    income.getSource()
            );


            statement.setString(
                    3,
                    income.getDescription()
            );


            statement.setString(
                    4,
                    income.getDate().toString()
            );


            statement.setInt(
                    5,
                    income.getId()
            );


            statement.executeUpdate();


            System.out.println(
                    "Income updated successfully."
            );


        }
        catch(Exception e){

            e.printStackTrace();

        }


    }





    // DELETE INCOME
    public void deleteIncome(int id){


        String sql =
                "DELETE FROM income WHERE id=?";


        try(
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)

        ){


            statement.setInt(
                    1,
                    id
            );


            statement.executeUpdate();


            System.out.println(
                    "Income deleted successfully."
            );


        }
        catch(Exception e){

            e.printStackTrace();

        }


    }


}