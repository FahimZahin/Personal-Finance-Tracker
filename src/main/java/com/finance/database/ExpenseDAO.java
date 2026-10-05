package com.finance.database;


import com.finance.model.Expense;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import java.time.LocalDate;


import java.util.ArrayList;
import java.util.List;



public class ExpenseDAO {



    public void addExpense(Expense expense){



        String sql =
                """
                INSERT INTO expenses
                (user_id, amount, category, description, date)
                VALUES(?,?,?,?,?)
                """;



        try(Connection connection =
                    DatabaseConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql)){



            statement.setInt(
                    1,
                    expense.getUserId()
            );


            statement.setDouble(
                    2,
                    expense.getAmount()
            );


            statement.setString(
                    3,
                    expense.getCategory()
            );


            statement.setString(
                    4,
                    expense.getDescription()
            );


            statement.setString(
                    5,
                    expense.getDate().toString()
            );


            statement.executeUpdate();

            System.out.println(
                    "Saved expense for user id: "
                            + expense.getUserId()
            );



            System.out.println(
                    "Expense added successfully."
            );



        }
        catch(Exception e){

            e.printStackTrace();

        }


    }






    public List<Expense> getExpensesByUser(int userId){



        List<Expense> expenses =
                new ArrayList<>();



        String sql =
                """
                SELECT * FROM expenses
                WHERE user_id = ?
                """;



        try(Connection connection =
                    DatabaseConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql)){



            statement.setInt(
                    1,
                    userId
            );



            ResultSet result =
                    statement.executeQuery();



            while(result.next()){



                Expense expense =
                        new Expense();



                expense.setId(
                        result.getInt("id")
                );



                expense.setUserId(
                        result.getInt("user_id")
                );



                expense.setAmount(
                        result.getDouble("amount")
                );



                expense.setCategory(
                        result.getString("category")
                );



                expense.setDescription(
                        result.getString("description")
                );



                expense.setDate(
                        LocalDate.parse(
                                result.getString("date")
                        )
                );



                expenses.add(expense);



            }



        }
        catch(Exception e){

            e.printStackTrace();

        }



        return expenses;


    }

    public void deleteExpense(int id){


        String sql =
                "DELETE FROM expenses WHERE id = ?";



        try(Connection connection =
                    DatabaseConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql)){



            statement.setInt(
                    1,
                    id
            );


            statement.executeUpdate();



            System.out.println(
                    "Expense deleted successfully."
            );



        }
        catch(Exception e){

            e.printStackTrace();

        }


    }

    public void updateExpense(Expense expense) {


        String sql =
                """
                UPDATE expenses
                SET amount = ?,
                    category = ?,
                    description = ?,
                    date = ?
                WHERE id = ?
                """;


        try(
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)

        ){

            statement.setDouble(
                    1,
                    expense.getAmount()
            );


            statement.setString(
                    2,
                    expense.getCategory()
            );


            statement.setString(
                    3,
                    expense.getDescription()
            );


            statement.setString(
                    4,
                    expense.getDate().toString()
            );


            statement.setInt(
                    5,
                    expense.getId()
            );


            statement.executeUpdate();


            System.out.println(
                    "Expense updated successfully."
            );


        }
        catch(Exception e){

            e.printStackTrace();

        }

    }



}