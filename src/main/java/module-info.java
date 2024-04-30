module pizzadelivery.cis434finalproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens pizzadelivery.cis434finalproject to javafx.fxml;
    exports pizzadelivery.cis434finalproject;
}