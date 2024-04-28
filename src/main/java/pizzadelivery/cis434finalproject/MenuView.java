package pizzadelivery.cis434finalproject;

import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;

public class MenuView {
    private BorderPane mainLayout;

    public MenuView(BorderPane mainLayout) {
        this.mainLayout = mainLayout;
    }

    public void displayMenu() {
        VBox menuBox = new VBox(10);
        Label menuLabel = new Label("Select Your Pizza Size:");
        ComboBox<String> sizeComboBox = new ComboBox<>();
        sizeComboBox.getItems().addAll("Small", "Medium", "Large");

        Label toppingLabel = new Label("Select Toppings:");
        GridPane toppingsGrid = new GridPane();
        CheckBox topping1 = new CheckBox("Pepperoni");
        CheckBox topping2 = new CheckBox("Mushrooms");
        CheckBox topping3 = new CheckBox("Olives");

        toppingsGrid.add(topping1, 0, 0);
        toppingsGrid.add(topping2, 1, 0);
        toppingsGrid.add(topping3, 2, 0);

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

        Button orderButton = new Button("Place Order");
        orderButton.setOnAction(e -> handleOrder(sizeComboBox.getValue(), topping1.isSelected(), topping2.isSelected(), topping3.isSelected(), streetField.getText(), cityField.getText(), stateField.getText(), postalCodeField.getText()));

        menuBox.getChildren().addAll(menuLabel, sizeComboBox, toppingLabel, toppingsGrid, addressLabel, streetField, cityField, stateField, postalCodeField, orderButton);
        mainLayout.setCenter(menuBox);
    }

    private void handleOrder(String size, boolean topping1, boolean topping2, boolean topping3, String street, String city, String state, String postalCode) {
        if (size == null || size.isEmpty() || (!topping1 && !topping2 && !topping3) || street.isEmpty() || city.isEmpty() || state.isEmpty() || postalCode.isEmpty()) {
            // Display an error message or prompt the user to fill out all fields
            System.out.println("Please fill out all fields.");
            return;
        }
        // Proceed with placing the order
        System.out.println("Order placed!");
        // Include logic for order processing with address details
    }
}