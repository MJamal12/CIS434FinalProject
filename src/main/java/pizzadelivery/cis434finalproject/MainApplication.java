package pizzadelivery.cis434finalproject;import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class MainApplication extends Application {
    private Stage primaryStage;
    private BorderPane mainLayout;
    private PizzaController controller;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Pizza Ordering System");

        controller = new PizzaController();
        mainLayout = new BorderPane();
        Scene scene = new Scene(mainLayout, 800, 600);

        // Load the global stylesheet
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        LoginView loginView = new LoginView(mainLayout, controller);
        loginView.display();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
