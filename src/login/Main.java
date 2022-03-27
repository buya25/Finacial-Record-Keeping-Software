package login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import animatefx.animation.*;


public class Main extends javafx.application.Application {

    @Override
    public void start(javafx.stage.Stage primaryStage) throws Exception{
        javafx.scene.Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new javafx.scene.Scene(root, 600, 400));
        primaryStage.initStyle(javafx.stage.StageStyle.UNDECORATED);
        new FadeIn(root).play();
        primaryStage.show();

        DBclass.DataBase_connection conn = new DBclass.DataBase_connection();
        conn.open();

        if (!conn.open()){
            System.out.println("cant connect to database:");
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
            alert.setContentText("Could not connect to database");
            alert.show();
            return;
        }else {
            System.out.println("connected");
        }


        //hiding the current window
//        javafx.scene.Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("login.fxml"));
//        primaryStage.setTitle("Login");
//        primaryStage.setScene(new javafx.scene.Scene(root, 600, 400));
//        primaryStage.initStyle(javafx.stage.StageStyle.UNDECORATED);
//        new FadeIn(root).play();
//        primaryStage.show();

    }




    public static void main(String[] args) {
        launch(args);
    }
}
