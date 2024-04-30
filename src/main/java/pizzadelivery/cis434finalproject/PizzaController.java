package pizzadelivery.cis434finalproject;
import java.util.List;
public class PizzaController {
    private Order currentOrder;

    public PizzaController() {
        currentOrder = new Order();
    }

    public void addPizzaToOrder(PizzaSize size, List<Topping> toppings) {
        Pizza pizza = new Pizza(size);
        toppings.forEach(pizza::addTopping);
        currentOrder.addPizza(pizza);
    }

    public double calculateOrderTotal() {
        return currentOrder.calculateTotalPrice();
    }

    public String generateReceipt() {
        StringBuilder receipt = new StringBuilder("===== Receipt =====\n");
        for (Pizza pizza : currentOrder.getPizzas()) {
            receipt.append(pizza.getSize()).append(" Pizza - $").append(String.format("%.2f", pizza.getBasePrice())).append("\n");
            for (Topping topping : pizza.getToppings()) {
                receipt.append(" + ").append(topping).append(" - $").append(String.format("%.2f", topping.getPrice())).append("\n");
            }
        }
        receipt.append("-------------------\n");
        receipt.append("Total: $").append(String.format("%.2f", calculateOrderTotal())).append("\n");
        receipt.append("===================\n");
        return receipt.toString();
    }

    // Resets the current order
    public void resetOrder() {
        currentOrder = new Order();  // Clear the current order and start new
    }
}
