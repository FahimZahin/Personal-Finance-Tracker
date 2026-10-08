package com.finance;


import javafx.scene.Scene;



public class ThemeManager {


    private static boolean darkMode = false;



    public static void setDarkMode(boolean value){

        darkMode = value;

    }





    public static boolean isDarkMode(){

        return darkMode;

    }






    public static void applyTheme(Scene scene){


        if(scene == null){

            return;

        }



        scene.getStylesheets()
                .removeIf(
                        css ->
                                css.contains(
                                        "dark.css"
                                )
                );



        if(darkMode){


            scene.getStylesheets()
                    .add(
                            ThemeManager.class
                                    .getResource(
                                            "/dark.css"
                                    )
                                    .toExternalForm()
                    );


        }



    }



}