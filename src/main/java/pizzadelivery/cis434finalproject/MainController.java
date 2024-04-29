package pizzadelivery.cis434finalproject;


import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;

public class MainController {

    @FXML
    private ComboBox<String> sizeComboBox;

    @FXML
    private CheckBox pepperoniCheckbox, mushroomsCheckbox, olivesCheckbox;

    @FXML
    private VBox orderResults;

    @FXML
    private void initialize() {
        // Initial setup can be performed here if needed.
    }

    @FXML
    public void handleOrderButtonAction(ActionEvent event) {
        orderResults.getChildren().clear(); // Clear previous results
        String selectedSize = sizeComboBox.getValue();
        if (selectedSize == null || selectedSize.trim().isEmpty()) {
            orderResults.getChildren().add(new Label("Please select a pizza size."));
            return;
        }
        double total = 0.0;
        StringBuilder receipt = new StringBuilder("Receipt\n------\n");

        // Extract size and price
        String size = selectedSize.split(" - ")[0];
        double sizePrice = Double.parseDouble(selectedSize.split(" - ")[1].substring(1));
        total += sizePrice;
        receipt.append(size).append(" Pizza: $").append(String.format("%.2f", sizePrice)).append("\n");

        // Toppings
        total += addToppingToReceipt(pepperoniCheckbox, receipt);
        total += addToppingToReceipt(mushroomsCheckbox, receipt);
        total += addToppingToReceipt(olivesCheckbox, receipt);

        // Add service fee
        double serviceFee = 2.00;
        total += serviceFee;
        receipt.append("Service Fee: $").append(String.format("%.2f", serviceFee)).append("\n");

        // Calculate total
        receipt.append("Total: $").append(String.format("%.2f", total));
        orderResults.getChildren().add(new Label(receipt.toString()));
    }

    private double addToppingToReceipt(CheckBox topping, StringBuilder receipt) {
        if (topping.isSelected()) {
            String toppingInfo = topping.getText();
            String toppingName = toppingInfo.split(" - ")[0];
            double toppingPrice = Double.parseDouble(toppingInfo.split(" - ")[1].substring(1));
            receipt.append(toppingName).append(": $").append(String.format("%.2f", toppingPrice)).append("\n");
            return toppingPrice;
        }
        return 0;
    }
}
