package pizzadelivery.cis434finalproject;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class LoginView {
    private BorderPane mainLayout;
    private PizzaController controller;

    public LoginView(BorderPane mainLayout, PizzaController controller) {
        this.mainLayout = mainLayout;
        this.controller = controller;
    }

    public void display() {
        VBox loginBox = new VBox(10);
        loginBox.setStyle("-fx-padding: 10; -fx-alignment: center;");

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Button loginButton = new Button("Login");
        Button createAccountButton = new Button("Create Account");

        loginButton.setOnAction(e -> handleLogin(usernameField.getText(), passwordField.getText()));
        createAccountButton.setOnAction(e -> showCreateAccount());

        loginBox.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, loginButton, createAccountButton);
        mainLayout.setCenter(loginBox);
    }

    private void handleLogin(String username, String password) {
        // Placeholder for actual authentication logic
        System.out.println("Login attempt with: " + username + " / " + password);
        // Assuming credentials are valid for demo purposes
        showMenuView();
    }

    private void showMenuView() {
        MenuView menuView = new MenuView(mainLayout, controller);
        menuView.displayMenu();
    }

    private void showCreateAccount() {
        // Here you would display the account creation screen
        System.out.println("Navigating to create account screen.");
    }
}
