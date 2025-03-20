package application.controllers;
import application.FutsalManagementApp;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
public class AdminMenu {
    private FutsalManagementApp app;
    private VBox pane;
    public AdminMenu(FutsalManagementApp app) {
        this.app = app;
        pane = new VBox(20);
        pane.setAlignment(Pos.CENTER);
        Label header = new Label("Admin Dashboard");
        header.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        Label info = new Label("(Under Development)");
        Button logout = new Button("Logout");
        logout.setOnAction(e -> app.logout());
        pane.getChildren().addAll(header, info, logout);
    }
    public VBox getPane() {
        return pane;
    }
}
