package application.controllers;
import application.FutsalManagementApp;
import application.models.Booking;
import application.models.Futsal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
public class UserMenu {
    private FutsalManagementApp app;
    private VBox pane;
    private TableView<Futsal> table;
    public UserMenu(FutsalManagementApp app) {
        this.app = app;
        pane = new VBox(10);
        pane.setPadding(new Insets(10));
        pane.setAlignment(Pos.CENTER);
        table = new TableView<>();
        TableColumn<Futsal, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setPrefWidth(200);
        TableColumn<Futsal, String> locationCol = new TableColumn<>("Location");
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        locationCol.setPrefWidth(200);
        TableColumn<Futsal, String> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("priceString"));
        priceCol.setPrefWidth(150);
        table.getColumns().addAll(nameCol, locationCol, priceCol);
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        Button viewFutsals = new Button("View Futsals");
        viewFutsals.setOnAction(e -> viewFutsals());
        Button bookFutsal = new Button("Book Futsal");
        bookFutsal.setOnAction(e -> bookFutsal());
        Button viewBookings = new Button("View Bookings");
        viewBookings.setOnAction(e -> viewBookings());
        Button logout = new Button("Logout");
        logout.setOnAction(e -> app.logout());
        buttonBox.getChildren().addAll(viewFutsals, bookFutsal, viewBookings, logout);
        pane.getChildren().addAll(table, buttonBox);
    }
    public VBox getPane() {
        return pane;
    }
    public void onShow() {
        viewFutsals();
    }
    private void viewFutsals() {
        ObservableList<Futsal> data = FXCollections.observableArrayList(app.futsals);
        table.setItems(data);
    }
    private void bookFutsal() {
        Futsal selected = table.getSelectionModel().getSelectedItem();
        if(selected == null) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setContentText("Please select a futsal first!");
            alert.showAndWait();
            return;
        }
        TextInputDialog timeDialog = new TextInputDialog();
        timeDialog.setHeaderText("Enter preferred time (HH:MM format):");
        String timeSlot = timeDialog.showAndWait().orElse("");
        TextInputDialog dateDialog = new TextInputDialog();
        dateDialog.setHeaderText("Enter date (YYYY-MM-DD):");
        String date = dateDialog.showAndWait().orElse("");
        if(timeSlot.isEmpty() || date.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Time slot and date are required!");
            alert.showAndWait();
            return;
        }
        if(selected.getSlot(timeSlot)) {
            Booking booking = new Booking(app.currentUser, selected, date, timeSlot);
            app.bookings.add(booking);
            selected.bookSlot(timeSlot);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Booking successful!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Slot not available!");
            alert.showAndWait();
        }
    }
    private void viewBookings() {
        StringBuilder sb = new StringBuilder();
        for(Booking b : app.bookings) {
            if(b.getUser().equals(app.currentUser)) {
                sb.append(b.getFutsal().getName()).append(" on ").append(b.getDate()).append(" at ").append(b.getTimeSlot()).append("\n");
            }
        }
        if(sb.length() == 0) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("No bookings found!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText(sb.toString());
            alert.showAndWait();
        }
    }
}
