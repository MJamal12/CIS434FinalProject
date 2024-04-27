module pizzadelivery.cis434finalproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens pizzadelivery.cis434finalproject to javafx.fxml;
    exports pizzadelivery.cis434finalproject;
}