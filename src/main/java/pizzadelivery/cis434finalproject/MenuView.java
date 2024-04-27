package pizzadelivery.cis434finalproject;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.control.CheckBox;

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

        Button orderButton = new Button("Place Order");
        orderButton.setOnAction(e -> handleOrder());

        menuBox.getChildren().addAll(menuLabel, sizeComboBox, toppingLabel, toppingsGrid, orderButton);
        mainLayout.setCenter(menuBox);
    }

    private void handleOrder() {
        // Handle order logic here
        System.out.println("Order placed!");
    }
}
