package application.controllers;
import application.FutsalManagementApp;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
public class MainMenu {
    private FutsalManagementApp app;
    private VBox pane;
    public MainMenu(FutsalManagementApp app) {
        this.app = app;
        pane = new VBox(20);
        pane.setAlignment(Pos.CENTER);
        Label header = new Label("Futsal Management System");
        header.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        Button registerButton = new Button("Register");
        registerButton.setOnAction(e -> app.showRegister());
        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> app.showLogin());
        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> app.primaryStage.close());
        pane.getChildren().addAll(header, registerButton, loginButton, exitButton);
    }
    public VBox getPane() {
        return pane;
    }
}
