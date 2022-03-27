package signup;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class Signup_Controller implements javafx.fxml.Initializable {

    @FXML
    private Hyperlink btn_Cancel;

    @FXML
    private TextField txt_surName;

    @FXML
    private TextField txt_MiddleName;

    @FXML
    private Label warning_surName;

    @FXML
    private Label warning_middleName;

    @FXML
    private TextField txt_lastName;

    @FXML
    private TextField txt_idNumber;

    @FXML
    private Label warning_lastname;

    @FXML
    private Label warning_idnumber;

    @FXML
    private TextField txt_gmail;

    @FXML
    private Label warning_emailAddress;

    @FXML
    private PasswordField txt_password;

    @FXML
    private PasswordField txt_confrimPassword;

    @FXML
    private Label warning_Passowrd;

    @FXML
    private Label warning_confirmPassword;

    @FXML
    private Button btn_reset;

    @FXML
    private Button btn_signUp;

    @FXML
    private Hyperlink link_login;

    @FXML
    void omm_reset(javafx.scene.input.MouseEvent event) {

    }

    @FXML
    void on_reset(javafx.event.ActionEvent event) {

    }

    @FXML
    void on_signUp(javafx.event.ActionEvent event) {

    }

    @Override
    public void initialize(java.net.URL url, java.util.ResourceBundle resourceBundle) {
        link_login.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {

            @Override
            public void handle(javafx.event.ActionEvent actionEvent) {
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

            }
        });
        btn_Cancel.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent actionEvent) {
                javafx.stage.Stage stage = (javafx.stage.Stage) btn_Cancel.getScene().getWindow();
                stage.close();
            }
        });

    }
}
