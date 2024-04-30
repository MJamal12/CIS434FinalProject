package pizzadelivery.cis434finalproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

    public class PizzaController {

        private Stage stage;
        private Scene scene;
        private Parent root;
        private BorderPane mainLayout;
        private VBox orderResults;

        @FXML
        private Label loginMessage;

        // Other FXML injected fields...

        public void loadLoginScene(ActionEvent event) throws IOException {
            Parent loginRoot = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(loginRoot);  // Corrected to use loginRoot
            stage.setScene(scene);
            stage.show();
        }

        public void loadPizzaMenu(ActionEvent event) throws IOException{


        }

        // Other methods...



    @FXML
    private Button cancelButton;

    public void cancelButtononAction(ActionEvent e){
        Stage stage=(Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordPasswordField;

    public void loginButtonOnAction(ActionEvent e) throws SQLException {
        if (!usernameTextField.getText().isBlank() && !passwordPasswordField.getText().isBlank()) {
            //validateLogin();
            initMainLayout((Stage)((Node)e.getSource()).getScene().getWindow());
        } else {
            loginMessage.setText("Please enter the username and password!");
        }
    }

        public void validateLogin() throws SQLException {
            // Initialize the DatabaseConnection object
            DatabaseConnection connection = new DatabaseConnection();

            // Establish the database connection
            Connection connectDB = connection.getConnection();

            // Check if connectDB is not null before proceeding
            if (connectDB != null) {
                // Construct the SQL query with parameterized queries to prevent SQL injection
                String verifyLogin = "SELECT count(1) FROM userAccounts WHERE username = ? AND password = ?";

                try {
                    // Prepare the SQL statement with parameterized queries
                    PreparedStatement preparedStatement = connectDB.prepareStatement(verifyLogin);
                    preparedStatement.setString(1, usernameTextField.getText());
                    preparedStatement.setString(2, passwordPasswordField.getText());

                    // Execute the query
                    ResultSet queryResult = preparedStatement.executeQuery();

                    // Process the query results
                    if (queryResult.next()) {
                        if (queryResult.getInt(1) == 1) {
                            loginMessage.setText("Welcome!");

                          //  initMainLayout((Stage)(.getSource()).getScene().getWindow());
                        } else {
                            loginMessage.setText("Invalid Login. Please try again");
                        }
                    }
                } catch (SQLException e) {
                    // Handle SQL exceptions
                    e.printStackTrace();
                } finally {
                    // Close the database connection in the finally block to ensure it's always closed
                    if (connectDB != null) {
                        connectDB.close();
                    }
                }
            } else {
                // Handle the case where connectDB is null
                System.err.println("Failed to establish database connection.");
            }
        }


    private void initMainLayout(Stage stage) {
        this.stage = stage;
        mainLayout = new BorderPane();
        Scene scene = new Scene(mainLayout, 800, 600);

        setupMenu();
        setupOrderResultsDisplay();

        stage.setScene(scene);
        stage.show();
    }

    private void setupMenu() {
        VBox menuBox = new VBox(20);
        menuBox.setPadding(new Insets(15, 12, 15, 12));
        menuBox.setStyle("-fx-background-color: #f8f8f8;");

        // Header
        Label headerLabel = new Label("Welcome to the Pizza Ordering System!");
        headerLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: #333;");

        // Pizza Size Selection
        HBox sizeSelection = new HBox(10);
        sizeSelection.setAlignment(Pos.CENTER_LEFT);
        Label sizeLabel = new Label("Select Size:");
        ComboBox<String> sizeComboBox = new ComboBox<>();
        sizeComboBox.getItems().addAll("Small - $5", "Medium - $8", "Large - $10");
        sizeSelection.getChildren().addAll(sizeLabel, sizeComboBox);

        // Topping Selection
        Label toppingLabel = new Label("Choose Toppings:");
        CheckBox pepperoniCheckbox = new CheckBox("Pepperoni - $1.00");
        CheckBox mushroomsCheckbox = new CheckBox("Mushrooms - $0.50");
        CheckBox olivesCheckbox = new CheckBox("Olives - $0.35");
        GridPane toppingsGrid = new GridPane();
        toppingsGrid.setHgap(10);
        toppingsGrid.setVgap(10);
        toppingsGrid.setAlignment(Pos.CENTER);
        toppingsGrid.add(pepperoniCheckbox, 0, 0);
        toppingsGrid.add(mushroomsCheckbox, 1, 0);
        toppingsGrid.add(olivesCheckbox, 2, 0);

        // Address Fields
        Label addressLabel = new Label("Enter Delivery Address:");
        TextField streetField = new TextField();
        streetField.setPromptText("Street Address");
        TextField cityField = new TextField();
        cityField.setPromptText("City");
        TextField stateField = new TextField();
        stateField.setPromptText("State");
        TextField postalCodeField = new TextField();
        postalCodeField.setPromptText("Postal Code");

        // Order Button
        Button orderButton = new Button("Place Order");
        orderButton.setStyle("-fx-background-color: darksalmon; -fx-text-fill: white;");
        orderButton.setOnAction(e -> displayOrder(sizeComboBox.getValue(),streetField.getText(), cityField.getText(), postalCodeField.getText(),stateField.getText(), pepperoniCheckbox, mushroomsCheckbox, olivesCheckbox));

        menuBox.getChildren().addAll(headerLabel, sizeSelection, toppingLabel, toppingsGrid, addressLabel, streetField, cityField, stateField, postalCodeField, orderButton);
        mainLayout.setTop(menuBox);
    }

    private void setupOrderResultsDisplay() {
        orderResults = new VBox(20);
        orderResults.setPadding(new Insets(15, 12, 15, 12));
        orderResults.getChildren().add(new Label("Order Results Will Appear Here"));
        mainLayout.setCenter(orderResults);
    }

    private void displayOrder(String selectedSize, String street, String city, String state, String postalCode, CheckBox... toppings) {
        orderResults.getChildren().clear(); // Clear previous results
        if (selectedSize == null || selectedSize.isEmpty()) {
            orderResults.getChildren().add(new Label("Please select a pizza size."));
            return;
        }
        double total = 0.0;
        StringBuilder receipt = new StringBuilder("Receipt\n------\n");

        // Calculate pizza size price
        String size = selectedSize.split(" - ")[0];
        double sizePrice = Double.parseDouble(selectedSize.split(" - ")[1].substring(1));
        total += sizePrice;
        receipt.append(size).append(" Pizza: $").append(String.format("%.2f", sizePrice)).append("\n");

        // Calculate topping prices
        for (CheckBox topping : toppings) {
            if (topping.isSelected()) {
                String toppingName = topping.getText().split(" - ")[0];
                double toppingPrice = Double.parseDouble(topping.getText().split(" - ")[1].substring(1));
                total += toppingPrice;
                receipt.append(toppingName).append(": $").append(String.format("%.2f", toppingPrice)).append("\n");
            }
        }

        // Add service fee
        double serviceFee = 2.00;
        total += serviceFee;
        receipt.append("Service Fee: $").append(String.format("%.2f", serviceFee)).append("\n");

        // Calculate total
        receipt.append("Total: $").append(String.format("%.2f", total)).append("\n------\n");

        // Address details
        receipt.append("Delivery Address:\n");
        receipt.append(street).append(", ").append(city).append(", ").append(postalCode).append(", ").append(state);

        orderResults.getChildren().add(new Label(receipt.toString()));

    }



}
