package login;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;

public class login_Controller implements javafx.fxml.Initializable {

    @FXML
    private javafx.scene.control.Hyperlink link_forgot_password;
    @javafx.fxml.FXML
    private javafx.scene.control.Hyperlink link_signup;
    @FXML
    private javafx.scene.control.Label lbl_Password;
    @javafx.fxml.FXML
    private javafx.scene.control.Hyperlink lbl_Cancel;
    @javafx.fxml.FXML
    private javafx.scene.control.Button btn_login;
    @javafx.fxml.FXML
    private javafx.scene.control.TextField tx_username;
    @javafx.fxml.FXML
    private javafx.scene.control.PasswordField tx_password;
    @javafx.fxml.FXML
    javafx.scene.control.Label lbl_email;
    javafx.scene.effect.DropShadow shadow = new javafx.scene.effect.DropShadow();

    @FXML
    void on_Login(ActionEvent event) {
    }

    @FXML
    void omm_btnLogin(javafx.scene.input.MouseEvent event) {
        ShadowButtons(btn_login);
    }

    @FXML
    void omm_Login(javafx.scene.input.MouseEvent event) {
        setNullEffect(btn_login);
    }



    @FXML
    void okp_txtUsername(javafx.scene.input.KeyEvent event) {
        if (tx_username.getText().length() > 0 ){
            lbl_email.setText(null);
        }
    }
    @FXML
    void okr_Passowrd(javafx.scene.input.KeyEvent event) {
        if (tx_password.getText().length() > 0 ){
            lbl_Password.setText(null);
        }
    }
    @Override
    public void initialize(java.net.URL url, java.util.ResourceBundle resourceBundle) {
        link_forgot_password.setOnAction(actionEvent -> {
            try {
                javafx.scene.Parent root = javafx.fxml.FXMLLoader.load(getClass().getClassLoader().getResource("dash_board/forgotPassword.fxml"), resourceBundle);
                javafx.stage.Stage stage = new javafx.stage.Stage();
                stage.setScene(new javafx.scene.Scene(root, 606, 407));
                stage.initStyle(javafx.stage.StageStyle.UNDECORATED);
                new animatefx.animation.Bounce(root).play();
                stage.show();
                //hiding the current window
                ((javafx.scene.Node)(actionEvent.getSource())).getScene().getWindow().hide();
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        });

        link_signup.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {

            @Override
            public void handle(javafx.event.ActionEvent actionEvent) {

                try {
                    javafx.scene.Parent root = javafx.fxml.FXMLLoader.load(getClass().getClassLoader().getResource("signup/signup.fxml"), resourceBundle);
                    javafx.stage.Stage stage = new javafx.stage.Stage();
                    stage.setTitle("SignUp");
                    stage.setScene(new javafx.scene.Scene(root, 600, 510));
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
        lbl_Cancel.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent actionEvent) {
                javafx.stage.Stage stage = (javafx.stage.Stage) lbl_Cancel.getScene().getWindow();
                stage.close();
            }
        });
        btn_login.setOnAction(actionEvent -> {

            try {
              boolean check_Verification = LoginCredentials();

              if (check_Verification){
                  ((javafx.scene.Node)(actionEvent.getSource())).getScene().getWindow().hide();
              }
            } catch (java.sql.SQLException throwables) {
                throwables.printStackTrace();
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
    private Boolean LoginCredentials() throws java.sql.SQLException{
        DAO.AccountsDAO accounts = new DAO.AccountsDAO();
        pace_Classes.Accounts accounts1 = new pace_Classes.Accounts();
        accounts1.setEmailAddress(tx_username.getText());
        accounts1.setPassword(tx_password.getText());
        pace_Classes.Accounts acc = DAO.AccountsDAO.ConfirmEmail(accounts1);
        pace_Classes.Accounts pass = DAO.AccountsDAO.ConfirmPassword(accounts1);

        if (acc.getEmailAddress() == null){
            lbl_email.setText("Invalid Email Address");
        }
        if (pass.getPassword() == null){
            lbl_Password.setText("Invalid password");
        }
        if (acc.getEmailAddress() != null && pass.getPassword() != null){
            pace_Classes.Accounts details = new pace_Classes.Accounts();
            details.setEmailAddress(tx_username.getText());
            details.setPassword(tx_password.getText());
            accounts.query_login(details);
            return true;
        }


        if (tx_username.getText().equals("")) {
            lbl_email.setText("Please Enter email Address");
        } if (tx_password.getText().equals("")){
            lbl_Password.setText("Please Enter Your Password");
        } if (tx_username.getText().equals("") && tx_password.getText().equals("")){
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
            alert.setContentText("Username and Password is empty");
            alert.show();
        }
        return false;
    }


}
