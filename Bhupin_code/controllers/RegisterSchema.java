package application.controllers;
import application.FutsalManagementApp;
import application.models.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
public class RegisterScene {
    private FutsalManagementApp app;
    private VBox pane;
    private TextField usernameField;
    private PasswordField passwordField;
    private ComboBox<String> roleCombo;
    public RegisterScene(FutsalManagementApp app) {
        this.app = app;
        pane = new VBox(10);
        pane.setAlignment(Pos.CENTER);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));
        Label header = new Label("User Registration");
        header.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        Label usernameLabel = new Label("Username:");
        usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        passwordField = new PasswordField();
        Label roleLabel = new Label("Role:");
        roleCombo = new ComboBox<>();
        roleCombo.getItems().addAll("user", "owner", "admin");
        roleCombo.setValue("user");
        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(roleLabel, 0, 2);
        grid.add(roleCombo, 1, 2);
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        Button registerButton = new Button("Register");
        registerButton.setOnAction(e -> register());
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> app.showMainMenu());
        buttonBox.getChildren().addAll(registerButton, backButton);
        pane.getChildren().addAll(header, grid, buttonBox);
    }
    public VBox getPane() {
        return pane;
    }
    private void register() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String role = roleCombo.getValue();
        for(User u : app.users) {
            if(u.getUsername().equals(username)) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText("Username already exists!");
                alert.showAndWait();
                return;
            }
        }
        if(username.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("All fields are required!");
            alert.showAndWait();
            return;
        }
        User newUser = new User(username, password, role);
        app.users.add(newUser);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText("Registration successful!");
        alert.showAndWait();
        usernameField.clear();
        passwordField.clear();
        roleCombo.setValue("user");
        app.showMainMenu();
    }
}
