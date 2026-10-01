package com.finance.database;

import com.finance.model.Expense;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDAO {


    // Add new expense
    public void addExpense(Expense expense) {

        String sql = """
                INSERT INTO expenses(amount, category, description, date)
                VALUES (?, ?, ?, ?)
                """;


        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {


            statement.setDouble(1, expense.getAmount());
            statement.setString(2, expense.getCategory());
            statement.setString(3, expense.getDescription());
            statement.setString(4, expense.getDate().toString());


            statement.executeUpdate();


            System.out.println("Expense added successfully.");


        } catch (SQLException e) {

            System.out.println("Failed to add expense.");

            e.printStackTrace();

        }

    }



    // Get all expenses
    public List<Expense> getAllExpenses() {

        List<Expense> expenses = new ArrayList<>();

        String sql = "SELECT * FROM expenses";


        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {


            while (resultSet.next()) {


                Expense expense = new Expense();


                expense.setId(resultSet.getInt("id"));

                expense.setAmount(resultSet.getDouble("amount"));

                expense.setCategory(resultSet.getString("category"));

                expense.setDescription(resultSet.getString("description"));

                expense.setDate(
                        LocalDate.parse(resultSet.getString("date"))
                );


                expenses.add(expense);

            }


        } catch (SQLException e) {

            System.out.println("Failed to fetch expenses.");

            e.printStackTrace();

        }


        return expenses;

    }



    // Delete expense

    public void deleteExpense(int id) {


        String sql =
                "DELETE FROM expenses WHERE id = ?";


        try (var connection = DatabaseConnection.getConnection();
             var statement = connection.prepareStatement(sql)) {


            statement.setInt(1, id);


            statement.executeUpdate();


            System.out.println(
                    "Expense deleted successfully."
            );


        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}