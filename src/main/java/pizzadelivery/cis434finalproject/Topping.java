package pizzadelivery.cis434finalproject;
public enum Topping {
    PEPPERONI(1.00), MUSHROOMS(0.50), OLIVES(0.35);

    private final double price;

    Topping(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}