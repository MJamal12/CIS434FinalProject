package pizzadelivery.cis434finalproject;

import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextArea;
import java.util.ArrayList;
import java.util.List;

public class MenuView {
    private BorderPane mainLayout;
    private PizzaController controller;
    private CheckBox topping1 = new CheckBox(Topping.PEPPERONI.name() + " - $" + Topping.PEPPERONI.getPrice());
    private CheckBox topping2 = new CheckBox(Topping.MUSHROOMS.name() + " - $" + Topping.MUSHROOMS.getPrice());
    private CheckBox topping3 = new CheckBox(Topping.OLIVES.name() + " - $" + Topping.OLIVES.getPrice());
    private TextArea receiptArea = new TextArea(); // Area to display the receipt
    private ComboBox<PizzaSize> sizeComboBox = new ComboBox<>();
    private Button addToOrderButton = new Button("Add to Order");
    private Button placeOrderButton = new Button("Place Order");
    private VBox menuBox;

    public MenuView(BorderPane mainLayout, PizzaController controller) {
        this.mainLayout = mainLayout;
        this.controller = controller;
        receiptArea.setEditable(false);
        placeOrderButton.setDisable(true); // Initially disabled
        setupSizeComboBox();
        initializeMenu();
    }

    private void setupSizeComboBox() {
        sizeComboBox.getItems().addAll(PizzaSize.values());
        sizeComboBox.setPromptText("Select a size");
    }

    private void initializeMenu() {
        menuBox = new VBox(10);
        menuBox.setSpacing(10);
        menuBox.setStyle("-fx-padding: 10;");

        Label menuLabel = new Label("Select Your Pizza Size:");
        Label toppingLabel = new Label("Select Toppings:");
        GridPane toppingsGrid = new GridPane();
        toppingsGrid.add(topping1, 0, 0);
        toppingsGrid.add(topping2, 1, 0);
        toppingsGrid.add(topping3, 2, 0);

        addToOrderButton.setOnAction(e -> handleAddToOrder(sizeComboBox.getValue(), topping1, topping2, topping3));
        placeOrderButton.setOnAction(e -> showThankYouPage());

        menuBox.getChildren().addAll(menuLabel, sizeComboBox, toppingLabel, toppingsGrid, addToOrderButton, placeOrderButton, receiptArea);
    }

    public void displayMenu() {
        if (!mainLayout.getChildren().contains(menuBox)) {
            mainLayout.setCenter(menuBox);
        }
    }

    private void handleAddToOrder(PizzaSize size, CheckBox... toppings) {
        if (size == null) {
            receiptArea.setText("Please select a pizza size before adding to order.");
            return;
        }

        List<Topping> selectedToppings = new ArrayList<>();
        if (toppings[0].isSelected()) selectedToppings.add(Topping.PEPPERONI);
        if (toppings[1].isSelected()) selectedToppings.add(Topping.MUSHROOMS);
        if (toppings[2].isSelected()) selectedToppings.add(Topping.OLIVES);

        controller.addPizzaToOrder(size, selectedToppings);
        receiptArea.setText(controller.generateReceipt());
        placeOrderButton.setDisable(false); // Enable the Place Order button after adding items
        resetSelections();
    }

    private void showThankYouPage() {
        VBox thankYouBox = new VBox(20);
        thankYouBox.setStyle("-fx-padding: 20; -fx-alignment: center;");
        double total = controller.calculateOrderTotal(); // Get the total cost of the order

        Label thankYouLabel = new Label("Thank you for placing your order! \n        Your order was $" + String.format("%.2f", total));
        thankYouLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: #FFFFFF;");

        Button backButton = new Button("Place Another Order");
        backButton.setOnAction(e -> {
            controller.resetOrder();  // Reset the order when going back
            displayMenu();
            resetSelections();  // Ensure the form is cleared
            addToOrderButton.setDisable(false); // Re-enable adding more to the order
            placeOrderButton.setDisable(true);  // Keep place order button disabled until new items are added
        });

        thankYouBox.getChildren().addAll(thankYouLabel, backButton);
        mainLayout.setCenter(thankYouBox); // Change the central content of the main layout
    }



    private void resetSelections() {
        sizeComboBox.getSelectionModel().clearSelection();
        sizeComboBox.setPromptText("Select a size");
        topping1.setSelected(false);
        topping2.setSelected(false);
        topping3.setSelected(false);
    }
}
