package pizzadelivery.cis434finalproject;
import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private String size;
    private List<Topping> toppings;
    private double basePrice;

    public String getDescription() {
        StringBuilder description = new StringBuilder();
        description.append("Size: ").append(size).append(", Toppings: ");
        if (toppings.isEmpty()) {
            description.append("None");
        } else {
            for (Topping topping : toppings) {
                description.append(topping.getName()).append(", ");
            }
            description.setLength(description.length() - 2);  // Remove the last comma and space
        }
        return description.toString();
    }

    public Pizza(String size, double basePrice) {
        this.size = size;
        this.toppings = new ArrayList<>();
        this.basePrice = basePrice;
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

    // Getters and Setters
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public List<Topping> getToppings() {
        return new ArrayList<>(toppings);
    }

    public double getBasePrice() {
        return basePrice;
    }
}
