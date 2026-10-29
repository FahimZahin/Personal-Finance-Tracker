package com.finance;

import javafx.scene.Scene;

public class ThemeManager {

    // Default to light mode (false)
    private static boolean isDarkMode = false;

    public static void setDarkMode(boolean dark) {
        isDarkMode = dark;
    }

    public static boolean isDarkMode() {
        return isDarkMode;
    }

    public static void applyTheme(Scene scene) {
        if (scene == null) return;

        // Clear any old stylesheets
        scene.getStylesheets().clear();

        // Load the appropriate CSS file based on the current mode
        String stylesheet;
        if (isDarkMode) {
            stylesheet = ThemeManager.class.getResource("/dark-style.css").toExternalForm();
        } else {
            stylesheet = ThemeManager.class.getResource("/light-style.css").toExternalForm();
        }

        scene.getStylesheets().add(stylesheet);
    }
}