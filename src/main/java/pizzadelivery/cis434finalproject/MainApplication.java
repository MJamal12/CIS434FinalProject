package pizzadelivery.cis434finalproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class MainApplication extends Application {

    private Stage primaryStage;
    private BorderPane mainLayout;
    private VBox orderResults;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Pizza Ordering System");

        initMainLayout();
    }

    private void initMainLayout() {
        mainLayout = new BorderPane();
        Scene scene = new Scene(mainLayout, 800, 600);

        setupMenu();
        setupOrderResultsDisplay();

        primaryStage.setScene(scene);
        primaryStage.show();
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

        // Order Button
        Button orderButton = new Button("Place Order");
        orderButton.setStyle("-fx-background-color: darksalmon; -fx-text-fill: white;");
        orderButton.setOnAction(e -> displayOrder(sizeComboBox.getValue(), pepperoniCheckbox, mushroomsCheckbox, olivesCheckbox));

        menuBox.getChildren().addAll(headerLabel, sizeSelection, toppingLabel, toppingsGrid, orderButton);
        mainLayout.setTop(menuBox);
    }

    private void setupOrderResultsDisplay() {
        orderResults = new VBox(20);
        orderResults.setPadding(new Insets(15, 12, 15, 12));
        orderResults.getChildren().add(new Label("Order Results Will Appear Here"));
        mainLayout.setCenter(orderResults);
    }

    private void displayOrder(String selectedSize, CheckBox... toppings) {
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
        receipt.append("Total: $").append(String.format("%.2f", total));
        orderResults.getChildren().add(new Label(receipt.toString()));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
