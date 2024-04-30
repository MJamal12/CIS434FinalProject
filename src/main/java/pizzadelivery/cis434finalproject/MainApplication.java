package pizzadelivery.cis434finalproject;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) {
        try{
            Parent root= FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            Scene scene= new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch(Exception e){
            e.printStackTrace();
        }


        //stage.setTitle("Pizza Ordering System");
        //initMainLayout();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
