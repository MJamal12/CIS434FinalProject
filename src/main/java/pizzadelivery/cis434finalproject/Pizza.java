package pizzadelivery.cis434finalproject;
import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private PizzaSize size;
    private List<Topping> toppings;
    private final double basePrice;

    public Pizza(PizzaSize size) {
        this.size = size;
        this.basePrice = size.getPrice();
        this.toppings = new ArrayList<>();
    }

    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    public double calculatePrice() {
        double totalPrice = basePrice;
        for (Topping topping : toppings) {
            totalPrice += topping.getPrice();
        }
        return totalPrice;
    }

    public PizzaSize getSize() {
        return size;
    }

    public List<Topping> getToppings() {
        return new ArrayList<>(toppings);
    }

    public double getBasePrice() {
        return basePrice;
    }
}