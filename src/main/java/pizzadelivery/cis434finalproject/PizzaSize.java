package pizzadelivery.cis434finalproject;


public enum PizzaSize {
    SMALL(5), MEDIUM(8), LARGE(10);

    private final double price;

    PizzaSize(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}