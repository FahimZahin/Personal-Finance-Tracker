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

    public MainLayout(Stage stage) {
        this.stage = stage;
        root = new BorderPane();

        contentArea = new VBox();
        contentArea.setPadding(new Insets(20));
        contentArea.setStyle("-fx-background-color: transparent;");

        // FIX: Wrap the center content in a ScrollPane to prevent right-side cutoff
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

        // Spacer to push the Logout button to the very bottom of the sidebar
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        Button logoutBtn = new Button("Logout");

        // Apply CSS classes and make buttons fill the width
        Button[] navButtons = {dashBtn, incBtn, expBtn, transBtn, reportBtn, settingsBtn, logoutBtn};
        for (Button btn : navButtons) {
            btn.getStyleClass().add("nav-button");
            btn.setMaxWidth(Double.MAX_VALUE);
        }

        sidebar.getChildren().addAll(
                dashBtn, incBtn, expBtn, transBtn, reportBtn, settingsBtn, spacer, logoutBtn
        );

        // Navigation Logic
        dashBtn.setOnAction(e -> setContent(new DashboardView().getView(this.stage)));
        incBtn.setOnAction(e -> setContent(new IncomeView().getView(this.stage)));
        expBtn.setOnAction(e -> setContent(new ExpenseView().getView(this.stage)));
        transBtn.setOnAction(e -> setContent(new TransactionView().getView(this.stage)));
        reportBtn.setOnAction(e -> setContent(new ReportView().getView(this.stage)));
        settingsBtn.setOnAction(e -> setContent(new SettingsView().getView(this.stage)));

        // FIX: Re-integrated Logout Logic
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