package com.finance.view;

import com.finance.Session;
import com.finance.AppConstants;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainLayout {

    private final BorderPane root;
    private final VBox contentArea;
    private final Stage stage;
    private final Button[] navButtons; // Array to hold navigation buttons for state updates

    public MainLayout(Stage stage) {
        this.stage = stage;
        root = new BorderPane();

        contentArea = new VBox();
        contentArea.setPadding(new Insets(20));
        contentArea.setStyle("-fx-background-color: transparent;");

        ScrollPane scrollPane = new ScrollPane(contentArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        scrollPane.setBorder(null);

        // Setup Sidebar
        VBox sidebar = new VBox(15);
        sidebar.setPadding(new Insets(30, 20, 20, 20));
        sidebar.setPrefWidth(200);
        sidebar.getStyleClass().add("sidebar");

        Button dashBtn = new Button("Dashboard");
        Button incBtn = new Button("Income");
        Button expBtn = new Button("Expenses");
        Button transBtn = new Button("Transactions");
        Button reportBtn = new Button("Monthly Report");
        Button settingsBtn = new Button("Settings");

        // Spacer to push the Logout button to the very bottom
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        Button logoutBtn = new Button("Logout");

        navButtons = new Button[]{dashBtn, incBtn, expBtn, transBtn, reportBtn, settingsBtn};
        for (Button btn : navButtons) {
            btn.getStyleClass().add("nav-button");
            btn.setMaxWidth(Double.MAX_VALUE);
        }
        logoutBtn.getStyleClass().add("nav-button");
        logoutBtn.setMaxWidth(Double.MAX_VALUE);

        sidebar.getChildren().addAll(
                dashBtn, incBtn, expBtn, transBtn, reportBtn, settingsBtn, spacer, logoutBtn
        );

        // Set Dashboard as visually active by default when logging in
        setActiveButton(dashBtn);

        // Navigation Logic - Now updates the content AND the active button style
        dashBtn.setOnAction(e -> {
            setContent(new DashboardView().getView(this.stage));
            setActiveButton(dashBtn);
        });

        incBtn.setOnAction(e -> {
            setContent(new IncomeView().getView(this.stage));
            setActiveButton(incBtn);
        });

        expBtn.setOnAction(e -> {
            setContent(new ExpenseView().getView(this.stage));
            setActiveButton(expBtn);
        });

        transBtn.setOnAction(e -> {
            setContent(new TransactionView().getView(this.stage));
            setActiveButton(transBtn);
        });

        reportBtn.setOnAction(e -> {
            setContent(new ReportView().getView(this.stage));
            setActiveButton(reportBtn);
        });

        settingsBtn.setOnAction(e -> {
            setContent(new SettingsView().getView(this.stage));
            setActiveButton(settingsBtn);
        });

        logoutBtn.setOnAction(e -> {
            Session.clear();
            Scene loginScene = new Scene(new LoginView().getView(this.stage), AppConstants.WIDTH, AppConstants.HEIGHT);
            java.net.URL styleResource = getClass().getResource("/style.css");
            if (styleResource != null) {
                loginScene.getStylesheets().add(styleResource.toExternalForm());
            }
            this.stage.setScene(loginScene);
        });

        root.setLeft(sidebar);
        root.setCenter(scrollPane);
    }

    /**
     * Highlights the clicked button and removes highlighting from all others.
     */
    private void setActiveButton(Button activeBtn) {
        // 1. Clear inline styles from all buttons so they return to normal CSS
        for (Button btn : navButtons) {
            btn.setStyle("");
        }

        // 2. Apply a distinct inline style to the active button (blue border left + darker background)
        if (activeBtn != null) {
            activeBtn.setStyle(
                    "-fx-background-color: #34495e; " +
                            "-fx-border-color: #3498db; " +
                            "-fx-border-width: 0 0 0 4; " +
                            "-fx-font-weight: bold;"
            );
        }
    }

    public void setContent(Node node) {
        contentArea.getChildren().clear();
        contentArea.getChildren().add(node);
        try {
            ViewAnimator.fadeIn(node);
        } catch (Exception ignored) {}
    }

    public BorderPane getLayout() {
        return root;
    }
}