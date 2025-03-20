package application.controllers;
import application.FutsalManagementApp;
import application.models.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
public class LoginScene {
    private FutsalManagementApp app;
    private VBox pane;
    private TextField usernameField;
    private PasswordField passwordField;
    public LoginScene(FutsalManagementApp app) {
        this.app = app;
        pane = new VBox(10);
        pane.setAlignment(Pos.CENTER);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));
        Label header = new Label("User Login");
        header.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        Label usernameLabel = new Label("Username:");
        usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        passwordField = new PasswordField();
        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> login());
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> app.showMainMenu());
        buttonBox.getChildren().addAll(loginButton, backButton);
        pane.getChildren().addAll(header, grid, buttonBox);
    }
    public VBox getPane() {
        return pane;
    }
    private void login() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        User user = null;
        for(User u : app.users) {
            if(u.getUsername().equals(username) && u.getPassword().equals(password)) {
                user = u;
                break;
            }
        }
        if(user != null) {
            app.currentUser = user;
            usernameField.clear();
            passwordField.clear();
            if(user.getRole().equalsIgnoreCase("user")) {
                app.showUserMenu();
            } else if(user.getRole().equalsIgnoreCase("owner")) {
                app.showOwnerMenu();
            } else if(user.getRole().equalsIgnoreCase("admin")) {
                app.showAdminMenu();
            }
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Invalid credentials!");
            alert.showAndWait();
        }
    }
}
