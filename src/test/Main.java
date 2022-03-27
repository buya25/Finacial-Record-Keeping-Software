package test;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends javafx.application.Application {

    @Override
    public void start(javafx.stage.Stage primaryStage) throws Exception{


        javafx.scene.Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("table.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new javafx.scene.Scene(root, 669, 645));
        primaryStage.show();


    }



    public static void main(String[] args) {
        launch(args);
    }
}
