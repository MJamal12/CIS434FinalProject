package pizzadelivery.cis434finalproject;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class MainApplication extends Application {
    private Stage primaryStage;
    private PizzaController controller;
    private BorderPane mainLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Pizza Ordering System");

        controller = new PizzaController();
        mainLayout = new BorderPane();
        Scene scene = new Scene(mainLayout, 800, 600);

        // Apply the CSS
        scene.getStylesheets().add("style.css"); // Ensure the CSS file is in your resources folder

        MenuView menuView = new MenuView(mainLayout, controller);
        menuView.displayMenu();

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
