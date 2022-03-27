package controllers;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Hyperlink;
public class ForgotPassword_controller implements javafx.fxml.Initializable {
    @FXML
    private Hyperlink btn_minise;

    @FXML
    private Hyperlink btn_cancel;

    @FXML
    private javafx.scene.control.TextField txt_emailAddress;

    @FXML
    private Label warning_EmailAddress;

    @FXML
    private TextField txt_phoneNumber;

    @FXML
    private Label warning_phoneNumber;

    @FXML
    private Button btn_reset;

    @FXML
    private Hyperlink btn_Login;

    @FXML
    void omm_reset(javafx.scene.input.MouseEvent event) {
        ShadowButtons(btn_reset);
    }

    @FXML
    void on_phoneNumber(javafx.event.ActionEvent event) {

    }
    @FXML
    void omm_pane(javafx.scene.input.MouseEvent event) {
            setNullEffect(btn_reset);
    }

    @FXML
    void on_reset(javafx.event.ActionEvent event) {

    }

    @Override
    public void initialize(java.net.URL url, java.util.ResourceBundle resourceBundle) {
        btn_cancel.setOnAction(event -> {
                javafx.stage.Stage stage = (javafx.stage.Stage) btn_cancel.getScene().getWindow();
                stage.close();

        });
        btn_Login.setOnAction(actionEvent -> {

                try {
                    javafx.scene.Parent root = javafx.fxml.FXMLLoader.load(getClass().getClassLoader().getResource("login/login.fxml"), resourceBundle);
                    javafx.stage.Stage stage = new javafx.stage.Stage();
                    stage.setTitle("Login");
                    stage.setScene(new javafx.scene.Scene(root, 600, 400));
                    stage.initStyle(javafx.stage.StageStyle.UNDECORATED);
                    new animatefx.animation.Bounce(root).play();
                    stage.show();
                    //hiding the current window
                    ((javafx.scene.Node)(actionEvent.getSource())).getScene().getWindow().hide();
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }
        });
    }
    private void ShadowButtons(javafx.scene.control.Button but){
        javafx.scene.effect.DropShadow dropShadow = new javafx.scene.effect.DropShadow();
        dropShadow.setColor(javafx.scene.paint.Color.WHITE);
        dropShadow.setRadius(10);
        but.setEffect(dropShadow);
        new animatefx.animation.ZoomIn(but);
    }
    private void setNullEffect(javafx.scene.control.Button button){
        button.setEffect(null);
    }
}
