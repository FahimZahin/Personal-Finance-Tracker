package com.finance.service;


import com.finance.database.SettingsDAO;
import com.finance.model.UserSettings;



public class SettingsService {


    private final SettingsDAO settingsDAO;



    public SettingsService(){


        settingsDAO =
                new SettingsDAO();


    }






    public UserSettings getSettings(int userId){


        return settingsDAO.getSettings(
                userId
        );


    }








    public void saveSettings(UserSettings settings){


        settingsDAO.saveSettings(
                settings
        );


    }



}