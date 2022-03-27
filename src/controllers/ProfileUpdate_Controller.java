package controllers;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

public class ProfileUpdate_Controller implements javafx.fxml.Initializable {
    private java.text.NumberFormat numFormat = java.text.NumberFormat.getInstance();
    @FXML
    private Label lbl_FullName;

    @FXML
    private javafx.scene.layout.Pane ProfileUpdatePane;

    @FXML
    private Circle circle_updatePhoto;

    @FXML
    private Circle display_Update;

    @FXML
    public Label lbl_SirName;

    @FXML
    private Label lbl_MiddleName;

    @FXML
    private Label lbl_LastName;

    @FXML
    public Label lbl_IDNumber;

    @FXML
    private Label lbl_DOB;

    @FXML
    private Label lbl_PhoneNumber;

    @FXML
    private Label lbl_PostalCode;

    @FXML
    private Label lbl_Address;

    @FXML
    private TextField txt_surName;

    @FXML
    private TextField txt_MidName;

    @FXML
    private TextField txt_LastName;

    @FXML
    private TextField txt_PhoneNumber;

    @FXML
    private DatePicker _DOB;

    @FXML
    private TextField txt_Address;

    @FXML
    private TextField txt_PostalCode;

    @FXML
    private TextField txt_IDNumber;

    @FXML
    private TextField txt_Fixed_Amount;

    @FXML
    private Button edit_Image;

    @FXML
    private Button edit_SurName;

    @FXML
    private Button edit_MiddleName;

    @FXML
    private Button edit_LastName;

    @FXML
    private Button edit_PhoneNumber;

    @FXML
    private Button edit_DOB;

    @FXML
    private Button edit_IDnumber;

    @FXML
    private Button edit_Location;

    @FXML
    private Button edit_Address;

    @FXML
    private Button btn_Fixed_Amount;

    @FXML
    private TextArea txt_AOB;

    @FXML
    private TextField txt_Hobies;

    @FXML
    private Button edit_Password;

    @FXML
    private Button edit_Hobbies;

    @FXML
    private Button edit_AOB;

    @FXML
    private TextField txt_Occupation;

    @FXML
    private Button edit_Occupation;

    @FXML
    private PasswordField txt_Passwotd;

    @FXML
    private ImageView img_invisible;

    @FXML
    private ImageView visible;

    @FXML
    private Button btn_checkVisible;

    @FXML
    private Label warning_surName;

    @FXML
    private Label warning_lastname;

    @FXML
    private Label warning_MiddleName;

    @FXML
    private Label warning_PhoneNumber;

    @FXML
    private Label warning_IDnumber;

    @FXML
    private Label warning_PostalCode;

    @FXML
    private Label warning_Address;

    @FXML
    private Label warning_BirthDay;

    @FXML
    private Label warning_password;

    @FXML
    private Label warning_AOB;

    @FXML
    private Label warning_FixedAmount;

    @FXML
    private Label warning_Occupation;

    @FXML
    private Label warning_Hobbies;

    @FXML
    private Label warning_lastname47;

    private javafx.scene.control.Tooltip tooltip = new javafx.scene.control.Tooltip();
    private javafx.beans.property.SimpleBooleanProperty showPassword = new javafx.beans.property.SimpleBooleanProperty();

    @FXML
    void on_Address(javafx.event.ActionEvent event) {
        if (txt_IDNumber.getText() == null){
            warning_IDnumber.setText("*");
        }else if (txt_Address.getText() == null){
            warning_Address.setText("*");
        }else {
            pace_Classes.Accounts accounts = new pace_Classes.Accounts();
            accounts.setIdNumber(Integer.parseInt(txt_IDNumber.getText()));
            accounts.setAddress(txt_Address.getText());
            Address_Update(accounts);
        }
    }

    @FXML
    void on_DOB(javafx.event.ActionEvent event) {

    }

    @FXML
    void on_Hobies(javafx.event.ActionEvent event) {

    }

    @FXML
    void on_IDNumber(javafx.event.ActionEvent event) {

    }

    @FXML
    void on_LastName(javafx.event.ActionEvent event) {
        if (txt_IDNumber.getText() == null){
            warning_IDnumber.setText("*");
        }else if (txt_LastName.getText() == null){
            warning_lastname.setText("*");
        }else {
            pace_Classes.Accounts accounts = new pace_Classes.Accounts();
            accounts.setIdNumber(Integer.parseInt(txt_IDNumber.getText()));
            accounts.setLastName(txt_LastName.getText());
            LastName_Update(accounts);
        }
    }

    @FXML
    void on_MidName(javafx.event.ActionEvent event) {
        if (txt_IDNumber.getText() == null){
            warning_MiddleName.setText("*");
        }else if (txt_MidName.getText() == null){
            warning_MiddleName.setText("*");
        }else {
            pace_Classes.Accounts accounts = new pace_Classes.Accounts();
            accounts.setIdNumber(Integer.parseInt(txt_IDNumber.getText()));
            accounts.setMiddleName(txt_MidName.getText());
            MiddleName_Update(accounts);
        }
    }

    @FXML
    void on_Occupation(javafx.event.ActionEvent event) {

    }

    @FXML
    void on_Passwotd(javafx.event.ActionEvent event) {
        if (txt_IDNumber.getText() == null){
            warning_IDnumber.setText("*");
        }else if (txt_Passwotd.getText() == null){
            warning_password.setText("*");
        }else {
            pace_Classes.Accounts accounts = new pace_Classes.Accounts();
            accounts.setIdNumber(Integer.parseInt(txt_IDNumber.getText()));
            accounts.setPassword(txt_Passwotd.getText());
            Password_Update(accounts);
        }

    }
    @FXML
    void typed_Password(javafx.scene.input.KeyEvent event) {
        if (showPassword.get()){
            showPassword();
        }
    }
    @FXML
    void pressed_Password(javafx.scene.input.KeyEvent event) {



    }

    @FXML
    void on_PhoneNumber(javafx.event.ActionEvent event) {
        if (txt_IDNumber.getText() == null){
            warning_PhoneNumber.setText("*");
        }else if (txt_PhoneNumber.getText() == null){
            warning_PhoneNumber.setText("*");
        }else {
            pace_Classes.Accounts accounts = new pace_Classes.Accounts();
            accounts.setIdNumber(Integer.parseInt(txt_IDNumber.getText()));
            accounts.setPhoneNumber(txt_PhoneNumber.getText());
            PhoneNumber_Update(accounts);
        }

    }

    @FXML
    void on_PostalCode(javafx.event.ActionEvent event) {

    }

    @FXML
    void on_surName(javafx.event.ActionEvent event) {
        if (txt_IDNumber.getText() == null){
            warning_surName.setText("*");
        }else if (txt_surName.getText() == null){
            warning_surName.setText("*");
        }else {
            pace_Classes.Accounts accounts = new pace_Classes.Accounts();
            accounts.setIdNumber(Integer.parseInt(txt_IDNumber.getText()));
            accounts.setSirName(txt_surName.getText());
            surName_Update(accounts);
        }

    }
    @FXML
    void on_Fixed_Amount(javafx.event.ActionEvent event) throws java.sql.SQLException {
        UpdateConstantAmount();
    }




    @Override
    public void initialize(java.net.URL url, java.util.ResourceBundle resourceBundle) {
        new animatefx.animation.FadeInRight(ProfileUpdatePane).play();


        DisplayIconPhoto();
        StringProperty();
        try {
            ConstantAmount();
        } catch (java.sql.SQLException throwables) {
            throwables.printStackTrace();
        }


        btn_checkVisible.setOnAction(actionEvent -> {
            displayPassword();
        });
        edit_Image.setOnAction(actionEvent -> {
            InsertUserPhoto();
        });
        edit_SurName.setOnAction(actionEvent ->{
            txt_surName.setEditable(true);
        });
        edit_MiddleName.setOnAction(actionEvent ->{
            txt_MidName.setEditable(true);
        });
        edit_LastName.setOnAction(actionEvent ->{
            txt_LastName.setEditable(true);
        });
        edit_PhoneNumber.setOnAction(actionEvent ->{
            txt_PhoneNumber.setEditable(true);
        });
        edit_Address.setOnAction(actionEvent ->{
            txt_Address.setEditable(true);
        });
        edit_Password.setOnAction(actionEvent ->{
            txt_Passwotd.setEditable(true);
        });
        edit_Hobbies.setOnAction(actionEvent ->{
            txt_Hobies.setEditable(true);
        });
        edit_Occupation.setOnAction(actionEvent ->{
            txt_Occupation.setEditable(true);
        });
        btn_Fixed_Amount.setOnAction(actionEvent ->{
            txt_Fixed_Amount.setEditable(true);
        });
        edit_AOB.setOnAction(actionEvent ->{
            txt_AOB.setEditable(true);
        });
        edit_Location.setOnAction(actionEvent ->{
            txt_PostalCode.setEditable(true);
        });
    }

    private void ConstantAmount() throws java.sql.SQLException {
        pace_Classes.Credited_Accounts credit = DAO.Credited_AccountsDAO.selectConstantAmount();
        txt_Fixed_Amount.setText(numFormat.format(credit.getContant_amount()));
    }
    private void UpdateConstantAmount()throws java.sql.SQLException{
        pace_Classes.Credited_Accounts credit = new pace_Classes.Credited_Accounts();
        credit.setContant_amount(Double.parseDouble(txt_Fixed_Amount.getText()));
        DAO.Credited_AccountsDAO.updateContant(credit);
    }

    private void InsertUserPhoto() {

        pace_Classes.Accounts accounts = new pace_Classes.Accounts();
        javafx.stage.FileChooser fileChooser = new javafx.stage.FileChooser();
        javafx.stage.FileChooser.ExtensionFilter extensionFilter = new javafx.stage.FileChooser.ExtensionFilter("JPG files(*.jpg)","*.JPG");
        javafx.stage.FileChooser.ExtensionFilter extensionFilter1 = new javafx.stage.FileChooser.ExtensionFilter("PNG files(*.png)","*.PNG");
        fileChooser.getExtensionFilters().addAll(extensionFilter,extensionFilter1);
        javafx.stage.Window primaryStage = null;
        java.io.File file = fileChooser.showOpenDialog(primaryStage);

        java.awt.image.BufferedImage bufferedImage;

        try {
            bufferedImage = javax.imageio.ImageIO.read(file);
            javafx.scene.image.Image image = javafx.embed.swing.SwingFXUtils.toFXImage(bufferedImage, null);
            //circle_updatePhoto.setFill(new javafx.scene.paint.ImagePattern(controllers.image));
            accounts.setInputImage(new java.io.FileInputStream(file));
            //java.io.FileInputStream fin = new java.io.FileInputStream(file);
            int len = (int)file.length();

            DAO.AccountsDAO.UpdateUserPhoto(accounts, len, Integer.parseInt(lbl_IDNumber.getText()));


            pace_Classes.Accounts acc = DAO.AccountsDAO.ReloadUserPhoto(Integer.parseInt(lbl_IDNumber.getText()));


            java.util.ResourceBundle resourceBundle = null;
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(DAO.AccountsDAO.class.getClassLoader().getResource("dash_board/dashboard.fxml"), resourceBundle);
            javafx.scene.Parent root = loader.load();
            //this is how we need to pass those paramenters next 3 lines
            dash_board.Dash_controller controller = loader.getController();//sending the parameter of the id number to the dashboard

//            javafx.stage.Stage stage = new javafx.stage.Stage();
//            stage.setTitle("Dashboard");
//            stage.setScene(new javafx.scene.Scene(root, 900, 720));
//            stage.initStyle(javafx.stage.StageStyle.UNDECORATED);
//            new animatefx.animation.FadeIn(root).play();
//            stage.show();

            controller.UserPhoto(acc);
            controller.setLbl_display_userID(lbl_IDNumber.getText());
            setProfilePhoto(acc);

        }catch (java.io.IOException | java.sql.SQLException e){
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
            alert.setContentText(String.valueOf(e));
            alert.show();
        }
    }

    private void DisplayUserPhoto(){
        javafx.scene.image.Image img = new javafx.scene.image.Image("controllers/image/Mullo.png");
        circle_updatePhoto.setFill(new javafx.scene.paint.ImagePattern(img));
    }
    private void DisplayIconPhoto(){
        javafx.scene.image.Image img = new javafx.scene.image.Image("controllers/aperture.jpg");
        display_Update.setFill(new javafx.scene.paint.ImagePattern(img));
    }
    private void showPassword(){
        tooltip.setShowDelay(javafx.util.Duration.millis(5000));
        tooltip.setAutoHide(true);
        tooltip.setMinWidth(60);
        javafx.geometry.Point2D point2D = txt_Passwotd.localToScene(txt_Passwotd.getBoundsInLocal().getMaxX(), txt_Passwotd.getBoundsInLocal().getMaxY());
        tooltip.setText(txt_Passwotd.getText());
        tooltip.setContentDisplay(javafx.scene.control.ContentDisplay.BOTTOM);
        tooltip.show(txt_Passwotd,
                        point2D.getX(),
                        point2D.getY());
    }

    private void hidePassword(){
        tooltip.setText("");
        tooltip.hide();
    }
    private void StringProperty(){
        showPassword.addListener((javafx.beans.value.ChangeListener<Boolean>) (observable, oldValue, newValue) ->{
            if (newValue){
                showPassword();
            }else {
                hidePassword();
            }
        } );
        showPassword.bind(visible.visibleProperty());
    }
    private void displayPassword(){
        if (img_invisible.isVisible()){
            visible.setVisible(true);
            img_invisible.setVisible(false);

            showPassword();
        }else if (!img_invisible.isVisible()){
            visible.setVisible(false);
            img_invisible.setVisible(true);
            hidePassword();
        }
    }
    public pace_Classes.Accounts getRecordsFromAccounts(int idNumber) throws java.sql.SQLException, java.io.IOException {
        pace_Classes.Accounts accounts = DAO.AccountsDAO.query_Details(idNumber);

        setLabelsDetails(accounts);

        return accounts;
    }

    private void setLabelsDetails(pace_Classes.Accounts accounts) throws java.io.IOException, java.sql.SQLException {
            lbl_FullName.setText(accounts.getSirName() + " " + accounts.getMiddleName() + " " + accounts.getLastName());
            lbl_SirName.setText(accounts.getSirName());
            txt_surName.setText(accounts.getSirName());
            lbl_MiddleName.setText(accounts.getMiddleName());
            txt_MidName.setText(accounts.getMiddleName());
            lbl_LastName.setText(accounts.getLastName());
            txt_LastName.setText(accounts.getLastName());
            if (Integer.toString(accounts.getIdNumber()) == null){
                lbl_IDNumber.setText("");
                txt_IDNumber.setText("");
            }else {
                lbl_IDNumber.setText(Integer.toString(accounts.getIdNumber()));
                txt_IDNumber.setText(Integer.toString(accounts.getIdNumber()));
            }
            if (accounts.getPhoneNumber() != null){
                txt_PhoneNumber.setText(accounts.getPhoneNumber());
                lbl_PhoneNumber.setText(accounts.getPhoneNumber());
            }else {
                txt_PhoneNumber.setText("");
                lbl_PhoneNumber.setText("");
            }
            if (accounts.getBirthDay() != null){

                java.text.SimpleDateFormat smp = new java.text.SimpleDateFormat("dd/MM/yyyy");
                String bday = smp.format(accounts.getBirthDay());
                lbl_DOB.setText(bday);
            }else {
                lbl_DOB.setText("");
                //_DOB.setValue(accounts.getBirthDay());
            }
            if (Integer.toString(accounts.getZipCode()) != null){
                txt_PostalCode.setText(Integer.toString(accounts.getZipCode()));
                lbl_PostalCode.setText(Integer.toString(accounts.getZipCode()));
            }else{
                txt_PostalCode.setText("");
                lbl_PostalCode.setText("");
            }
            if (accounts.getAddress() != null){
                txt_Address.setText(accounts.getAddress());
                lbl_Address.setText(accounts.getAddress());
            }else {
                txt_Address.setText("");
                lbl_Address.setText("");
            }
            if (accounts.getHobbies() != null){
                txt_Hobies.setText(accounts.getHobbies());
            }else {
                txt_Hobies.setText("");
            }
            if (accounts.getOccupation() != null){
                txt_Occupation.setText(accounts.getOccupation());
            }else {
                txt_Occupation.setText("");
            }
            if (accounts.getAny_Other_Details() != null){
                txt_AOB.setText(accounts.getAny_Other_Details());
            }else {
                txt_AOB.setText("");
            }

            txt_Passwotd.setText(accounts.getPassword());
            setProfilePhoto(accounts);

    }
    public void setProfilePhoto(pace_Classes.Accounts accounts) throws java.io.IOException, java.sql.SQLException {
        if ((accounts.getUser_photo()) != null ){
            java.io.OutputStream os = new java.io.FileOutputStream("photo.jpg");

            accounts.setInputImage(accounts.getUser_photo().getBinaryStream());
            byte[] data = null;
            try {
                long nLen = accounts.getUser_photo().length();
                int nSize = (int) nLen;

                data = new byte[nSize];

                accounts.getInputImage().read(data);
                os.write(data, 0, data.length);
            }catch (java.io.IOException | java.sql.SQLException e){
                throw e;
            }


            javafx.scene.image.Image image = new javafx.scene.image.Image("file:photo.jpg");
            circle_updatePhoto.setFill(new javafx.scene.paint.ImagePattern(image));
        }else {
            javafx.scene.image.Image img2 = new javafx.scene.image.Image("/controllers.image/user.png");
            circle_updatePhoto.setFill(new javafx.scene.paint.ImagePattern(img2));
        }

    }
    public void surName_Update(pace_Classes.Accounts accounts){
        DAO.AccountsDAO.update_SurName(accounts);
        txt_surName.setEditable(false);
        accounts = DAO.AccountsDAO.query_Details(accounts.getIdNumber());
        lbl_SirName.setText(accounts.getSirName());
        lbl_FullName.setText(accounts.getSirName() + " " + accounts.getMiddleName() + " " + accounts.getLastName());
    }

    public void MiddleName_Update(pace_Classes.Accounts accounts){
        DAO.AccountsDAO.update_MiddleName(accounts);
        txt_MidName.setEditable(false);
        accounts = DAO.AccountsDAO.query_Details(accounts.getIdNumber());
        lbl_MiddleName.setText(accounts.getMiddleName());
        lbl_FullName.setText(accounts.getSirName() + " " + accounts.getMiddleName() + " " + accounts.getLastName());
    }
    public void LastName_Update(pace_Classes.Accounts accounts){
        DAO.AccountsDAO.update_LastName(accounts);
        txt_LastName.setEditable(false);
        accounts = DAO.AccountsDAO.query_Details(accounts.getIdNumber());
        lbl_LastName.setText(accounts.getLastName());
        lbl_FullName.setText(accounts.getSirName() + " " + accounts.getMiddleName() + " " + accounts.getLastName());
    }
    public void PhoneNumber_Update(pace_Classes.Accounts accounts){
        DAO.AccountsDAO.update_PhoneNumber(accounts);
        txt_PhoneNumber.setEditable(false);
        accounts = DAO.AccountsDAO.query_Details(accounts.getIdNumber());
        lbl_PhoneNumber.setText(accounts.getPhoneNumber());
    }
    public void Address_Update(pace_Classes.Accounts accounts){
        DAO.AccountsDAO.update_Address(accounts);
        txt_Address.setEditable(false);
        accounts = DAO.AccountsDAO.query_Details(accounts.getIdNumber());
        lbl_Address.setText(accounts.getAddress());
    }
    public void Password_Update(pace_Classes.Accounts accounts){
        DAO.AccountsDAO.update_Password(accounts);
        txt_Passwotd.setEditable(false);
    }
    public void Hobbies_Update(pace_Classes.Accounts accounts){
        DAO.AccountsDAO.update_Hobbies(accounts);
        txt_Hobies.setEditable(false);
    }
    public void AOB_Update(pace_Classes.Accounts accounts){
        DAO.AccountsDAO.update_AOB(accounts);
        txt_Hobies.setEditable(false);
    }


}
