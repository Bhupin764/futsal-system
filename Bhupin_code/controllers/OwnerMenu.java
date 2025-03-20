package application.controllers;
import application.FutsalManagementApp;
import application.models.Futsal;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
public class OwnerMenu {
    private FutsalManagementApp app;
    private VBox pane;
    private TextField nameField;
    private TextField locationField;
    private TextField priceField;
    public OwnerMenu(FutsalManagementApp app) {
        this.app = app;
        pane = new VBox(10);
        pane.setPadding(new Insets(20));
        pane.setAlignment(Pos.CENTER);
        Label header = new Label("Owner Dashboard");
        header.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        Label nameLabel = new Label("Futsal Name:");
        nameField = new TextField();
        Label locationLabel = new Label("Location:");
        locationField = new TextField();
        Label priceLabel = new Label("Price per hour:");
        priceField = new TextField();
        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(locationLabel, 0, 1);
        grid.add(locationField, 1, 1);
        grid.add(priceLabel, 0, 2);
        grid.add(priceField, 1, 2);
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        Button addFutsal = new Button("Add Futsal");
        addFutsal.setOnAction(e -> addFutsal());
        Button viewFutsals = new Button("View My Futsals");
        viewFutsals.setOnAction(e -> viewFutsals());
        Button logout = new Button("Logout");
        logout.setOnAction(e -> app.logout());
        buttonBox.getChildren().addAll(addFutsal, viewFutsals, logout);
        pane.getChildren().addAll(header, grid, buttonBox);
    }
    public VBox getPane() {
        return pane;
    }
    private void addFutsal() {
        String name = nameField.getText();
        String location = locationField.getText();
        double price;
        try {
            price = Double.parseDouble(priceField.getText());
        } catch(Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Price must be a number!");
            alert.showAndWait();
            return;
        }
        if(name.isEmpty() || location.isEmpty() || price <= 0) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("All fields are required!");
            alert.showAndWait();
            return;
        }
        Futsal futsal = new Futsal(name, location, price, app.currentUser);
        app.futsals.add(futsal);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText("Futsal added successfully!");
        alert.showAndWait();
        nameField.clear();
        locationField.clear();
        priceField.clear();
    }
    private void viewFutsals() {
        StringBuilder sb = new StringBuilder();
        for(Futsal f : app.futsals) {
            if(f.getOwner().equals(app.currentUser)) {
                sb.append(f.getName()).append(" - ").append(f.getLocation()).append(" (Rs.").append(f.getPrice()).append("/hour)").append("\n");
            }
        }
        if(sb.length() == 0) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("No futsals registered!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText(sb.toString());
            alert.showAndWait();
        }
    }
}
