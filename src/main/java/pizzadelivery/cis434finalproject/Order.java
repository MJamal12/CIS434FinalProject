package pizzadelivery.cis434finalproject;
import java.util.List;

public class Order {
    private List<Pizza> pizzas;

    public Order(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public double calculateTotalPrice() {
        double total = 0;
        for (Pizza pizza : pizzas) {
            total += pizza.calculatePrice();
        }
        return total;
    }

    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }
    public void printOrderDetails() {
        System.out.println("Order Details:");
        for (Pizza pizza : pizzas) {
            System.out.println(pizza.getDescription() + " - Price: $" + String.format("%.2f", pizza.calculatePrice()));
        }
        double subtotal = calculateTotalPrice();
        double tax = subtotal * 0.08;  // 8% tax
        double total = subtotal + tax;
        System.out.println("Subtotal: $" + String.format("%.2f", subtotal));
        System.out.println("Tax (8%): $" + String.format("%.2f", tax));
        System.out.println("Total: $" + String.format("%.2f", total));
    }
}
