package com.finance.database;


import com.finance.model.UserSettings;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class SettingsDAO {



    public UserSettings getSettings(int userId){


        String sql =
                "SELECT * FROM settings WHERE user_id=?";



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



            if(result.next()){


                UserSettings settings =
                        new UserSettings();



                settings.setId(
                        result.getInt("id")
                );


                settings.setUserId(
                        result.getInt("user_id")
                );


                settings.setCurrency(
                        result.getString("currency")
                );


                settings.setTheme(
                        result.getString("theme")
                );



                return settings;


            }



        }
        catch(Exception e){

            e.printStackTrace();

        }



        return createDefault(userId);


    }







    public void saveSettings(UserSettings settings){



        String sql =
                """
                INSERT INTO settings
                (user_id,currency,theme)

                VALUES(?,?,?)

                ON CONFLICT(user_id)

                DO UPDATE SET

                currency=?,

                theme=?
                """;




        try(
                Connection connection =
                        DatabaseConnection.getConnection();


                PreparedStatement statement =
                        connection.prepareStatement(sql)

        ){



            statement.setInt(
                    1,
                    settings.getUserId()
            );


            statement.setString(
                    2,
                    settings.getCurrency()
            );


            statement.setString(
                    3,
                    settings.getTheme()
            );


            statement.setString(
                    4,
                    settings.getCurrency()
            );


            statement.setString(
                    5,
                    settings.getTheme()
            );



            statement.executeUpdate();



        }
        catch(Exception e){

            e.printStackTrace();

        }



    }






    private UserSettings createDefault(int userId){


        UserSettings settings =
                new UserSettings();



        settings.setUserId(
                userId
        );


        settings.setCurrency(
                "৳"
        );


        settings.setTheme(
                "LIGHT"
        );


        return settings;


    }



}