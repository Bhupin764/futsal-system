package application;
import application.controllers.AdminMenu;
import application.controllers.LoginScene;
import application.controllers.MainMenu;
import application.controllers.OwnerMenu;
import application.controllers.RegisterScene;
import application.controllers.UserMenu;
import application.models.User;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
public class FutsalManagementApp extends Application {
    public Stage primaryStage;
    public StackPane root;
    public List<User> users = new ArrayList<>();
    public List<application.models.Futsal> futsals = new ArrayList<>();
    public List<application.models.Booking> bookings = new ArrayList<>();
    public User currentUser = null;
    private MainMenu mainMenu;
    private RegisterScene registerScene;
    private LoginScene loginScene;
    private UserMenu userMenu;
    private OwnerMenu ownerMenu;
    private AdminMenu adminMenu;
    public DB db = new DB();
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        root = new StackPane();
        mainMenu = new MainMenu(this);
        registerScene = new RegisterScene(this);
        loginScene = new LoginScene(this);
        userMenu = new UserMenu(this);
        ownerMenu = new OwnerMenu(this);
        adminMenu = new AdminMenu(this);
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Futsal Management System");
        showMainMenu();
        primaryStage.show();
    }
    public void showMainMenu() {
        root.getChildren().clear();
        root.getChildren().add(mainMenu.getPane());
    }
    public void showRegister() {
        root.getChildren().clear();
        root.getChildren().add(registerScene.getPane());
    }
    public void showLogin() {
        root.getChildren().clear();
        root.getChildren().add(loginScene.getPane());
    }
    public void showUserMenu() {
        root.getChildren().clear();
        userMenu.onShow();
        root.getChildren().add(userMenu.getPane());
    }
    public void showOwnerMenu() {
        root.getChildren().clear();
        root.getChildren().add(ownerMenu.getPane());
    }
    public void showAdminMenu() {
        root.getChildren().clear();
        root.getChildren().add(adminMenu.getPane());
    }
    public void logout() {
        currentUser = null;
        showMainMenu();
    }
}
