package com.finance.database;

import com.finance.model.Income;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IncomeDAO {


    // Add new income
    public void addIncome(Income income) {

        String sql = """
                INSERT INTO income(amount, source, description, date)
                VALUES (?, ?, ?, ?)
                """;


        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {


            statement.setDouble(1, income.getAmount());
            statement.setString(2, income.getSource());
            statement.setString(3, income.getDescription());
            statement.setString(4, income.getDate().toString());


            statement.executeUpdate();


            System.out.println("Income added successfully.");


        } catch (SQLException e) {

            System.out.println("Failed to add income.");

            e.printStackTrace();

        }

    }



    // Get all incomes
    public List<Income> getAllIncome() {

        List<Income> incomes = new ArrayList<>();

        String sql = "SELECT * FROM income";


        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {


            while (resultSet.next()) {


                Income income = new Income();


                income.setId(resultSet.getInt("id"));

                income.setAmount(resultSet.getDouble("amount"));

                income.setSource(resultSet.getString("source"));

                income.setDescription(resultSet.getString("description"));

                income.setDate(
                        LocalDate.parse(resultSet.getString("date"))
                );


                incomes.add(income);

            }


        } catch (SQLException e) {

            System.out.println("Failed to fetch income.");

            e.printStackTrace();

        }


        return incomes;

    }



    // Delete income
    public void deleteIncome(int id) {


        String sql = "DELETE FROM income WHERE id = ?";


        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {


            statement.setInt(1, id);

            statement.executeUpdate();


            System.out.println("Income deleted successfully.");


        } catch (SQLException e) {

            System.out.println("Failed to delete income.");

            e.printStackTrace();

        }

    }

}