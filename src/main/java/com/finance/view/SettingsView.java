package com.finance.view;


import com.finance.Session;
import com.finance.model.UserSettings;
import com.finance.service.SettingsService;


import javafx.geometry.Insets;
import javafx.geometry.Pos;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;


import javafx.scene.layout.VBox;


import javafx.stage.Stage;



public class SettingsView {



    private final SettingsService settingsService;




    public SettingsView(){


        settingsService =
                new SettingsService();


    }








    public VBox getView(Stage stage){



        int userId =
                Session.getUser()
                        .getId();





        Label title =
                new Label(
                        "Settings"
                );






        ComboBox<String> currencyBox =
                new ComboBox<>();


        currencyBox.getItems()
                .addAll(
                        "৳",
                        "$",
                        "€",
                        "£"
                );






        ComboBox<String> themeBox =
                new ComboBox<>();


        themeBox.getItems()
                .addAll(
                        "LIGHT",
                        "DARK"
                );







        UserSettings settings =
                settingsService.getSettings(
                        userId
                );



        currencyBox.setValue(
                settings.getCurrency()
        );


        themeBox.setValue(
                settings.getTheme()
        );






        Label message =
                new Label();






        Button saveButton =
                new Button(
                        "Save Settings"
                );



        saveButton.setOnAction(event -> {



            UserSettings updated =
                    new UserSettings();



            updated.setUserId(
                    userId
            );


            updated.setCurrency(
                    currencyBox.getValue()
            );


            updated.setTheme(
                    themeBox.getValue()
            );



            settingsService.saveSettings(
                    updated
            );



            message.setText(
                    "Settings saved."
            );



        });








        Button backButton =
                new Button(
                        "Back Dashboard"
                );



        backButton.setOnAction(event -> {



            DashboardView dashboard =
                    new DashboardView();



            stage.setScene(
                    new Scene(
                            dashboard.getView(stage),
                            900,
                            650
                    )
            );



        });








        VBox layout =
                new VBox(
                        20,
                        title,
                        new Label("Currency"),
                        currencyBox,
                        new Label("Theme"),
                        themeBox,
                        saveButton,
                        backButton,
                        message
                );



        layout.setPadding(
                new Insets(30)
        );



        layout.setAlignment(
                Pos.CENTER
        );



        return layout;



    }



}