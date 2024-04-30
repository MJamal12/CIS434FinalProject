package pizzadelivery.cis434finalproject;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Pizza> pizzas = new ArrayList<>();

    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    public double calculateTotalPrice() {
        double total = 0;
        for (Pizza pizza : pizzas) {
            total += pizza.calculatePrice();
        }
        return total;
    }

    public List<Pizza> getPizzas() {
        return new ArrayList<>(pizzas);
    }
}
