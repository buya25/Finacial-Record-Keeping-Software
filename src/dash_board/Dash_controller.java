package dash_board;
import javafx.scene.control.Button;
import javafx.fxml.FXML;

public class Dash_controller implements javafx.fxml.Initializable {
    @FXML
    private Button btn_Dashboard;

    @FXML
    private Button btn_IndividualRecords;

    @FXML
    private Button btn_Update;

    @FXML
    private Button btn_NewRecord;

    @FXML
    private Button btn_Report;

    @javafx.fxml.FXML
    javafx.scene.layout.BorderPane borderPane;
    @javafx.fxml.FXML
    javafx.scene.control.Label lbl_display_userID;
    @javafx.fxml.FXML
    javafx.scene.control.Label display_time;
    @javafx.fxml.FXML
    javafx.scene.control.Button logout;
    @javafx.fxml.FXML
    javafx.scene.image.ImageView member_photo;
    @javafx.fxml.FXML
    javafx.scene.control.Label totalAmount;
    @FXML
    private javafx.scene.shape.Circle connected;
    @FXML
    private Button ChatBox;
    @FXML
    private javafx.scene.control.MenuItem Logout;
    @FXML
    private javafx.scene.control.Label lbl_connection;
    @FXML
    private javafx.scene.shape.Circle NewMessage;
    @javafx.fxml.FXML
    private javafx.scene.control.Button btn_Loan;
    @FXML
    private javafx.scene.control.MenuButton MenuButton;
    @FXML
    public javafx.scene.shape.Circle displayProfilePhoto;
    @javafx.fxml.FXML
    private javafx.scene.control.ComboBox<String> combobox;
    private volatile boolean stop = false ;
    javafx.scene.effect.DropShadow shadow = new javafx.scene.effect.DropShadow();

    @javafx.fxml.FXML
    void on_Loan(javafx.event.ActionEvent event) {
        ShadowButtons(btn_Loan);
        setNullEffect(btn_NewRecord);
        setNullEffect(btn_Update);
        setNullEffect(btn_Report);
        setNullEffect(btn_Dashboard);
        setNullEffect(btn_IndividualRecords);
    }

    @FXML
    void omm_Dashboard(javafx.scene.input.MouseEvent event) {

    }

    @FXML
    void omm_IndividualRecords(javafx.scene.input.MouseEvent event) {
    }

    @FXML
    void omm_Loans(javafx.scene.input.MouseEvent event) {

    }

    @FXML
    void omm_NewRecord(javafx.scene.input.MouseEvent event) {

    }

    @FXML
    void omm_Report(javafx.scene.input.MouseEvent event) {

    }

    @FXML
    void omm_Update(javafx.scene.input.MouseEvent event) {


    }
    @FXML
    void on_IndividualReports(javafx.event.ActionEvent event) {
        ShadowButtons(btn_IndividualRecords);
        setNullEffect(btn_NewRecord);
        setNullEffect(btn_Update);
        setNullEffect(btn_Report);
        setNullEffect(btn_Loan);
        setNullEffect(btn_Dashboard);
    }
    @FXML
    void on_Report(javafx.event.ActionEvent event){
        ShadowButtons(btn_Report);
        setNullEffect(btn_NewRecord);
        setNullEffect(btn_Update);
        setNullEffect(btn_IndividualRecords);
        setNullEffect(btn_Loan);
        setNullEffect(btn_Dashboard);
    }

    @FXML
    void on_NewRecord(javafx.event.ActionEvent event) {
        ShadowButtons(btn_NewRecord);
        setNullEffect(btn_Dashboard);
        setNullEffect(btn_Update);
        setNullEffect(btn_Report);
        setNullEffect(btn_Loan);
        setNullEffect(btn_IndividualRecords);
    }


    @FXML
    void on_Update(javafx.event.ActionEvent event) {
        ShadowButtons(btn_Update);
        setNullEffect(btn_NewRecord);
        setNullEffect(btn_Dashboard);
        setNullEffect(btn_Report);
        setNullEffect(btn_Loan);
        setNullEffect(btn_IndividualRecords);
    }

    @FXML
    void on_Dashboard(javafx.event.ActionEvent event) {
        ShadowButtons(btn_Dashboard);
        setNullEffect(btn_NewRecord);
        setNullEffect(btn_Update);
        setNullEffect(btn_Report);
        setNullEffect(btn_Loan);
        setNullEffect(btn_IndividualRecords);
    }

    @FXML
    void omm_ButtonPane(javafx.scene.input.MouseEvent event) {
    }

    @Override
    public void initialize(java.net.URL url, java.util.ResourceBundle resourceBundle) {
        ShadowButtons(btn_Dashboard);

        try {
            DisplayUserPhoto();
            boolean connect = DBclass.DataBase_connection.open();
            if (connect){
                connected.setFill(javafx.scene.paint.Color.LIMEGREEN);
                lbl_connection.setText("CONNECTED");
            }else {
                connected.setFill(javafx.scene.paint.Color.RED);
                lbl_connection.setText("DISCONNECTED");
            }
            DisplayMessage();
            displayBarGraphCreditAccount();
            DisplaySettingIcon();
            Timeshow();
            loadUI("graph_pane");
        } catch (java.io.IOException | java.sql.SQLException e) {
            e.printStackTrace();
        }
        logout.setOnAction(actionEvent -> {
            DBclass.DataBase_connection conn = new DBclass.DataBase_connection();
            conn.close();
            javafx.application.Platform.exit();
            System.exit(0);
        });
        Logout.setOnAction(actionEvent -> {
            boolean logout = false;
            try {
                logout = LoginUI();
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
            if (logout){
                DBclass.DataBase_connection connection = new DBclass.DataBase_connection();
                connection.close();
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

    }
    @FXML
    void on_Logout(javafx.event.ActionEvent actionEvent) throws java.io.IOException {
//        loadUI("settings");

    }
    @FXML
    void on_Settings(javafx.event.ActionEvent event) throws java.sql.SQLException, java.io.IOException {
        ProfileUpdate();
        setNullEffect(btn_Dashboard);
        setNullEffect(btn_NewRecord);
        setNullEffect(btn_Update);
        setNullEffect(btn_Report);
        setNullEffect(btn_Loan);
        setNullEffect(btn_IndividualRecords);
    }
    private void DisplaySettingIcon(){
        try {
            java.io.FileInputStream input = new java.io.FileInputStream("file:/Users/user/Desktop/Software-Programs/PaceFX/src/controllers/image/settings.jpg");
            javafx.scene.image.Image image = new javafx.scene.image.Image(input);
            javafx.scene.image.ImageView imageView = new javafx.scene.image.ImageView(image);

            MenuButton.setGraphic(imageView);
        } catch (java.io.FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void setLbl_display_userID(String id){
        lbl_display_userID.setText(id);
    };
    public void Timeshow(){
        Thread thread = new Thread(() -> {
            java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy  hh:mm:ss");

            while (!stop){
                try {
                    Thread.sleep(1000);
                }catch (Exception e){
                    System.out.println(e);
                }
                final String timeshow = simpleDateFormat.format(new java.util.Date());
                javafx.application.Platform.runLater(() -> {
                    display_time.setText(timeshow);
                });
            }
        });
        thread.start();
    }
    private void displayBarGraphCreditAccount() throws java.sql.SQLException {
        try {
            pace_Classes.Credited_Accounts credit = DAO.Credited_AccountsDAO.totalAmountContributed();

            barGraphCreditedAccounts(credit);

        }catch (java.sql.SQLException throwables){
            throwables.printStackTrace();
        }
    }
    private void barGraphCreditedAccounts(pace_Classes.Credited_Accounts credit) {
        java.text.NumberFormat format = java.text.NumberFormat.getInstance();

        totalAmount.setText(format.format(credit.getTotalAmount()));
    }


    public void dashboard(javafx.scene.input.MouseEvent mouseEvent) throws java.io.IOException {
        loadUI("graph_pane");
    }

    public void individual_records(javafx.scene.input.MouseEvent mouseEvent) throws java.io.IOException {
        loadUI("individual_record");
    }

    public void update(javafx.scene.input.MouseEvent mouseEvent) throws java.io.IOException {
        loadUI("update");
    }

    public void new_record(javafx.scene.input.MouseEvent mouseEvent) throws java.io.IOException {
        loadUI("newrecord");

    }
    public void loans(javafx.scene.input.MouseEvent mouseEvent) throws java.io.IOException {
        loadUI("loan");
    }
    public void reports(javafx.scene.input.MouseEvent mouseEvent) throws java.io.IOException, java.sql.SQLException {
        //loadUI("reports");
        java.util.ResourceBundle resourceBundle = null;
        javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getClassLoader().getResource("dash_board/reports.fxml"), resourceBundle);
        javafx.scene.Parent root = loader.load();
        //this is how we need to pass those paramenters next 3 lines
        pace_Classes.Credited_Accounts credit = new pace_Classes.Credited_Accounts();
        credit.setIdNumber(Integer.parseInt(lbl_display_userID.getText()));
        controllers.Report_Controller controller = loader.getController();
        controller.IndividualTotalAmount(credit);
        controller.currentMonth(credit);
        controller.LoanIndividual(credit);
        controller.FineLoan(credit);
        controller.FineCredited(credit);

        borderPane.setCenter(root);

        ShadowButtons(btn_Report);
        setNullEffect(btn_NewRecord);
        setNullEffect(btn_Update);
        setNullEffect(btn_Dashboard);
        setNullEffect(btn_Loan);
        setNullEffect(btn_IndividualRecords);
    }
    public void loadUI(String ui) throws java.io.IOException {
        javafx.scene.Parent root;
        root = javafx.fxml.FXMLLoader.load(getClass().getResource(ui + ".fxml"));
        borderPane.setCenter(root);
    }
    private void DisplayUserPhoto(){
        javafx.scene.image.Image image =  new javafx.scene.image.Image("dash_board/Mullo.png");
        displayProfilePhoto.setFill(new javafx.scene.paint.ImagePattern(image));
    }
    private void ShadowButtons(javafx.scene.control.Button but){
        javafx.scene.effect.DropShadow dropShadow = new javafx.scene.effect.DropShadow();
        dropShadow.setOffsetX(10.0);
        dropShadow.setColor(javafx.scene.paint.Color.WHITE);
        dropShadow.setRadius(2);
        but.setEffect(dropShadow);
        new animatefx.animation.ZoomIn(but);
    }
    private void setNullEffect(Button button){
        button.setEffect(null);
    }
    private void DisplayMessage(){
        javafx.scene.image.Image img2 = new javafx.scene.image.Image("dash_board/getMessage.png");
        NewMessage.setFill(new javafx.scene.paint.ImagePattern(img2));
        //file:/Users/user/Desktop/PaceFX/src/controllers.image/getMessage.png
    }

    //sending a parameter to the Profile Update
    private void ProfileUpdate() throws java.io.IOException, java.sql.SQLException {
        java.util.ResourceBundle resourceBundle = null;
        javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getClassLoader().getResource("dash_board/ProfileUpdate.fxml"), resourceBundle);
        javafx.scene.Parent root = loader.load();
        //this is how we need to pass those paramenters next 3 lines
        controllers.ProfileUpdate_Controller controller = loader.getController();
        controller.getRecordsFromAccounts(Integer.parseInt(lbl_display_userID.getText()));


        borderPane.setCenter(root);
    }
    public void UserPhoto(pace_Classes.Accounts accounts) throws java.sql.SQLException, java.io.IOException {
        if ((accounts.getUser_photo()) != null){
        java.io.OutputStream os = new java.io.FileOutputStream("photo.jpg");
        accounts.setInputImage(accounts.getUser_photo().getBinaryStream());
        byte[] data = null;
        try {
            long nLen = accounts.getUser_photo().length();
            int nSize = (int) nLen;

            data = new byte[nSize];

            accounts.getInputImage().read(data);
            os.write(data, 0, data.length);


            }catch (java.io.IOException e){
                throw e;
            }
                javafx.scene.image.Image image = new javafx.scene.image.Image("file:photo.jpg");
                displayProfilePhoto.setFill(new javafx.scene.paint.ImagePattern(image));
            }else {
                javafx.scene.image.Image img2 = new javafx.scene.image.Image("controllers.image/user.png");
                displayProfilePhoto.setFill(new javafx.scene.paint.ImagePattern(img2));
            }

    }

    public void onChatBox(javafx.event.ActionEvent event) throws java.io.IOException {
        java.util.ResourceBundle resourceBundle = null;
        javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(DAO.AccountsDAO.class.getClassLoader().getResource("dash_board/ChatBox.fxml"), resourceBundle);
        javafx.scene.Parent root = loader.load();

        javafx.stage.Stage stage = new javafx.stage.Stage();
        stage.setTitle("Dashboard");
        stage.setScene(new javafx.scene.Scene(root, 600, 400));
        stage.initStyle(javafx.stage.StageStyle.UNDECORATED);
        stage.show();
//        javafx.scene.Parent root;
//        root = javafx.fxml.FXMLLoader.load(getClass().getResource("ChatBox.fxml"));
//        borderPane.setRight(root);
    }
    private Boolean LoginUI() throws java.io.IOException {

//        javafx.fxml.FXMLLoader loader1 = new javafx.fxml.FXMLLoader();

//                DAO.AccountsDAO.class.getClassLoader().getResource("dash_board/dashboard.fxml");
//        loader1.setLocation(resourceBundle1);
//        javafx.scene.Parent root1 = loader1.load();
//        javafx.stage.Stage stage1 = new javafx.stage.Stage();
//        stage1.setTitle("Dashboard");
//        stage1.setScene(new javafx.scene.Scene(root1, 900, 720));
//        stage1.initStyle(javafx.stage.StageStyle.UNDECORATED);
//        stage1.close();

        javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader();
        final java.net.URL resourceBundle = DAO.AccountsDAO.class.getClassLoader().getResource("login/login.fxml");
        loader.setLocation(resourceBundle);
        javafx.scene.Parent root = loader.load();

        javafx.stage.Stage stage = new javafx.stage.Stage();
        stage.setTitle("Login");
        stage.setScene(new javafx.scene.Scene(root, 600, 400));
        stage.initStyle(javafx.stage.StageStyle.UNDECORATED);
        new animatefx.animation.FadeIn(root).play();
        stage.show();

        return true;
    }
}
