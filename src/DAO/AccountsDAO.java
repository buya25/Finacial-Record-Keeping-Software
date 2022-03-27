package DAO;

public class AccountsDAO extends DBclass.DataBase_connection {

    public static pace_Classes.Accounts ConfirmEmail(pace_Classes.Accounts accounts) throws java.sql.SQLException{
        StringBuilder sb = new StringBuilder(CONFIRM_EMAIL);
        sb.append("'"+accounts.getEmailAddress()+"'");
        pace_Classes.Accounts acc = new pace_Classes.Accounts();
        try(java.sql.Statement statement = conn.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery(sb.toString())){

            while (resultSet.next()){
                acc.setEmailAddress(resultSet.getString(COLUMN_ACCOUNTS_EMAIL));
            }
        }
        return acc;
    }
    public static pace_Classes.Accounts ConfirmPassword(pace_Classes.Accounts accounts) throws java.sql.SQLException{
        StringBuilder sb = new StringBuilder(CONFIRM_PASSWORD);
        sb.append("'"+accounts.getPassword()+"'");
        sb.append(" AND "+COLUMN_ACCOUNTS_EMAIL+" = '"+accounts.getEmailAddress()+"'");
        pace_Classes.Accounts acc = new pace_Classes.Accounts();
        try(java.sql.Statement statement = conn.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery(sb.toString())){

            while (resultSet.next()){
                acc.setPassword(resultSet.getString(COLUMN_ACCOUNTS_PASSWORD));
                acc.setEmailAddress(resultSet.getString(COLUMN_ACCOUNTS_EMAIL));
            }
        }
        return acc;
    }

    public static pace_Classes.Accounts update_SurName(pace_Classes.Accounts accounts){
        StringBuilder sb = new StringBuilder(UPDATE_SURNAME);
        sb.append("'"+accounts.getSirName()+"'");
        sb.append(" WHERE (`id_number` = ");
        sb.append(accounts.getIdNumber());
        sb.append(" )");


        try(java.sql.Statement statement = conn.createStatement()) {
            statement.executeUpdate(sb.toString());
            query_Details(accounts.getIdNumber());
        }catch (java.sql.SQLException e) {
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
            alert.setContentText(String.valueOf(e));
            alert.show();
        }
        return null;
    }
    public static pace_Classes.Accounts update_MiddleName(pace_Classes.Accounts accounts){
        StringBuilder sb = new StringBuilder(UPDATE_MIDDLENAME);
        sb.append("'"+accounts.getMiddleName()+"'");
        sb.append(" WHERE (`id_number` = ");
        sb.append(accounts.getIdNumber());
        sb.append(" )");


        try(java.sql.Statement statement = conn.createStatement()) {
            statement.executeUpdate(sb.toString());
            query_Details(accounts.getIdNumber());
        }catch (java.sql.SQLException e) {
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
            alert.setContentText(String.valueOf(e));
            alert.show();
        }
        return null;
    }
    public static pace_Classes.Accounts update_LastName(pace_Classes.Accounts accounts){
        StringBuilder sb = new StringBuilder(UPDATE_LASTNAME);
        sb.append("'"+accounts.getLastName()+"'");
        sb.append(" WHERE (`id_number` = ");
        sb.append(accounts.getIdNumber());
        sb.append(" )");


        try(java.sql.Statement statement = conn.createStatement()) {
            statement.executeUpdate(sb.toString());
            query_Details(accounts.getIdNumber());
        }catch (java.sql.SQLException e) {
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
            alert.setContentText(String.valueOf(e));
            alert.show();
        }
        return null;
    }
    public static pace_Classes.Accounts update_PhoneNumber(pace_Classes.Accounts accounts){
        StringBuilder sb = new StringBuilder(UPDATE_PHONE_NUMBER);
        sb.append("'"+accounts.getPhoneNumber() +"'");
        sb.append(" WHERE (`id_number` = ");
        sb.append(accounts.getIdNumber());
        sb.append(" )");


        try(java.sql.Statement statement = conn.createStatement()) {
            statement.executeUpdate(sb.toString());
            query_Details(accounts.getIdNumber());
        }catch (java.sql.SQLException e) {
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
            alert.setContentText(String.valueOf(e));
            alert.show();
        }
        return null;
    }
    public static pace_Classes.Accounts update_Address(pace_Classes.Accounts accounts){
        StringBuilder sb = new StringBuilder(UPDATE_ADDRESS);
        sb.append("'"+accounts.getAddress() +"'");
        sb.append(" WHERE (`id_number` = ");
        sb.append(accounts.getIdNumber());
        sb.append(" )");


        try(java.sql.Statement statement = conn.createStatement()) {
            statement.executeUpdate(sb.toString());
            query_Details(accounts.getIdNumber());
        }catch (java.sql.SQLException e) {
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
            alert.setContentText(String.valueOf(e));
            alert.show();
        }
        return null;
    }
    public static pace_Classes.Accounts UpdateUserPhoto(pace_Classes.Accounts accounts, int len, int idNumber) throws java.sql.SQLException, java.io.IOException {

        try(java.sql.PreparedStatement statement = conn.prepareStatement(UPDATE_USER_PROFILE_PHOTO)) {
            statement.setBinaryStream(1, accounts.getInputImage(), len);
            statement.setInt(2, idNumber);
            statement.executeUpdate();
        }catch (java.sql.SQLException e) {
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
            alert.setContentText(String.valueOf(e));
            alert.show();
        }

        return accounts;
    }

    public static pace_Classes.Accounts update_Password(pace_Classes.Accounts accounts) {
        StringBuilder sb = new StringBuilder(UPDATE_PASSWORD);
        sb.append("'"+accounts.getPassword() +"'");
        sb.append(" WHERE (`id_number` = ");
        sb.append(accounts.getIdNumber());
        sb.append(" )");


        try(java.sql.Statement statement = conn.createStatement()) {
            statement.executeUpdate(sb.toString());
            query_Details(accounts.getIdNumber());
        }catch (java.sql.SQLException e) {
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
            alert.setContentText(String.valueOf(e));
            alert.show();
        }
        return null;
    }

    public static pace_Classes.Accounts update_Hobbies(pace_Classes.Accounts accounts) {
        StringBuilder sb = new StringBuilder(UPDATE_HOBBIES);
        sb.append("'"+accounts.getHobbies() +"'");
        sb.append(" WHERE (`id_number` = ");
        sb.append(accounts.getIdNumber());
        sb.append(" )");


        try(java.sql.Statement statement = conn.createStatement()) {
            statement.executeUpdate(sb.toString());
            query_Details(accounts.getIdNumber());
        }catch (java.sql.SQLException e) {
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
            alert.setContentText(String.valueOf(e));
            alert.show();
        }
        return null;
    }

    public static pace_Classes.Accounts update_AOB(pace_Classes.Accounts accounts) {
        StringBuilder sb = new StringBuilder(UPDATE_AOB);
        sb.append("'"+accounts.getAny_Other_Details() +"'");
        sb.append(" WHERE (`id_number` = ");
        sb.append(accounts.getIdNumber());
        sb.append(" )");


        try(java.sql.Statement statement = conn.createStatement()) {
            statement.executeUpdate(sb.toString());
            query_Details(accounts.getIdNumber());
        }catch (java.sql.SQLException e) {
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
            alert.setContentText(String.valueOf(e));
            alert.show();
        }
        return null;
    }

    public pace_Classes.Accounts query_login(pace_Classes.Accounts accounts){
        StringBuilder sb = new StringBuilder(LOGIN_QUERY);
        sb.append(accounts.getEmailAddress());
        sb.append("\"");
        sb.append(" AND " + COLUMN_ACCOUNTS_PASSWORD);
        sb.append(" = \"");
        sb.append(accounts.getPassword());
        sb.append("\"");

        try(java.sql.Statement statement = conn.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery(sb.toString())) {
            pace_Classes.Accounts accounts1 = getResulsetAccountDetails(resultSet);

            return accounts1;
        }catch (java.sql.SQLException | java.io.IOException e) {
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
            alert.setContentText(String.valueOf(e));
            alert.show();
        }
        return accounts;
    }

    private pace_Classes.Accounts getResulsetAccountDetails(java.sql.ResultSet resultSet) throws java.sql.SQLException, java.io.IOException {
        pace_Classes.Accounts accounts = new pace_Classes.Accounts();
        while (resultSet.next()){
            accounts.setSirName(resultSet.getString(COLUMN_ACCOUNTS_SIRNAME));
            accounts.setIdNumber(Integer.parseInt(resultSet.getString(INDEX_ACCOUNTS_IDNUMBER)));
            int idnumber = Integer.parseInt(resultSet.getString(INDEX_ACCOUNTS_IDNUMBER));
            accounts.setUser_photo(resultSet.getBlob(COLUMN_ACCOUNTS_PHOTO));
            //accounts.setInputImage(resultSet.getBinaryStream(COLUMN_ACCOUNTS_PHOTO));
            //show the dash_board fxml file
            showDashboardFXML(accounts);
            //send the parameter for idNUmber and
            shareIndividualRecords(accounts);

        }
        return accounts;
    }
    public static pace_Classes.Accounts query_Details(int idNumber){
        StringBuilder sb = new StringBuilder(GET_RECORDS_FROM_ACCOUNTS);
        sb.append(idNumber);
        sb.append(" ;");


        try(java.sql.Statement statement = conn.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery(sb.toString())) {
            pace_Classes.Accounts accounts = getResulsetDetails(resultSet);

            return accounts;
        }catch (java.sql.SQLException | java.io.IOException e) {
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
            alert.setContentText(String.valueOf(e));
            alert.show();
        }
        return null;
    }

    private static pace_Classes.Accounts getResulsetDetails(java.sql.ResultSet resultSet) throws java.sql.SQLException, java.io.IOException {
        pace_Classes.Accounts accounts = new pace_Classes.Accounts();
        while (resultSet.next()){
            accounts.setSirName(resultSet.getString(COLUMN_ACCOUNTS_SIRNAME));
            accounts.setMiddleName(resultSet.getString(COLUMN_ACCOUNTS_MIDDLENAME));
            accounts.setLastName(resultSet.getString(COLUMN_ACCOUNTS_LASTNAME));
            accounts.setIdNumber(Integer.parseInt(resultSet.getString(INDEX_ACCOUNTS_IDNUMBER)));
            accounts.setPassword(resultSet.getString(COLUMN_ACCOUNTS_PASSWORD));
            accounts.setBirthDay(resultSet.getDate(COLUMN_ACCOUNTS_BIRTHDAY));
            accounts.setUser_photo(resultSet.getBlob(COLUMN_ACCOUNTS_PHOTO));
            if (resultSet.getString(COLUMN_ACCOUNTS_ZIPCODE) != null){
                accounts.setZipCode(Integer.parseInt(resultSet.getString(COLUMN_ACCOUNTS_ZIPCODE)));
            }else {
                accounts.setZipCode(0);
            }
            if (resultSet.getString(COLUMN_ACCOUNTS_ADDRESS) != null){
                accounts.setAddress(resultSet.getString(COLUMN_ACCOUNTS_ADDRESS));
            }else {
                accounts.setAddress("");
            }
            if (resultSet.getString(COLUMN_ACCOUNTS_PHONE_NUMBER) != null){
                accounts.setPhoneNumber(resultSet.getString(COLUMN_ACCOUNTS_PHONE_NUMBER));
            }else {
                accounts.setPhoneNumber("");
            }
            if (resultSet.getString(COLUMN_ACCOUNTS_HOBBIES) != null){
                accounts.setHobbies(resultSet.getString(COLUMN_ACCOUNTS_HOBBIES));
            }else {
                accounts.setHobbies("");
            }
            if (resultSet.getString(COLUMN_ACCOUNTS_OCCUPATION) != null){
                accounts.setOccupation(resultSet.getString(COLUMN_ACCOUNTS_OCCUPATION));
            }else{
                accounts.setOccupation("");
            }



            accounts.setCountry(resultSet.getString(COLUMN_ACCOUNTS_COUNTRY));
            accounts.setCity(resultSet.getString(COLUMN_ACCOUNTS_CITY));
            accounts.setAny_Other_Details(resultSet.getString(COLUMN_ACCOUNTS_MOREDETAILS));
        }
        return accounts;
    }
    public static  pace_Classes.Accounts ReloadUserPhoto(int idNumber){
        StringBuilder sb = new StringBuilder(GET_RECORDS_FROM_ACCOUNTS);
        sb.append(idNumber);
        sb.append(" ;");


        try(java.sql.Statement statement = conn.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery(sb.toString())) {
            pace_Classes.Accounts accounts = ResulsetReloadUserPhoto(resultSet);

            return accounts;
        }catch (java.sql.SQLException | java.io.IOException e) {
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
            alert.setContentText(String.valueOf(e));
            alert.show();
        }
        return null;
    }

    private static pace_Classes.Accounts ResulsetReloadUserPhoto(java.sql.ResultSet resultSet) throws java.sql.SQLException, java.io.IOException {
        pace_Classes.Accounts accounts = new pace_Classes.Accounts();
        while (resultSet.next()){
            accounts.setSirName(resultSet.getString(COLUMN_ACCOUNTS_SIRNAME));
            accounts.setMiddleName(resultSet.getString(COLUMN_ACCOUNTS_MIDDLENAME));
            accounts.setLastName(resultSet.getString(COLUMN_ACCOUNTS_LASTNAME));
            accounts.setIdNumber(Integer.parseInt(resultSet.getString(INDEX_ACCOUNTS_IDNUMBER)));
            accounts.setPassword(resultSet.getString(COLUMN_ACCOUNTS_PASSWORD));
            accounts.setBirthDay(resultSet.getDate(COLUMN_ACCOUNTS_BIRTHDAY));
            accounts.setUser_photo(resultSet.getBlob(COLUMN_ACCOUNTS_PHOTO));
            if (resultSet.getString(COLUMN_ACCOUNTS_ZIPCODE) == null){
                accounts.setZipCode(0);
            }else {
                accounts.setZipCode(Integer.parseInt(resultSet.getString(COLUMN_ACCOUNTS_ZIPCODE)));
            }

            accounts.setAddress(resultSet.getString(COLUMN_ACCOUNTS_ADDRESS));
            accounts.setPhoneNumber(resultSet.getString(COLUMN_ACCOUNTS_PHONE_NUMBER));
            accounts.setHobbies(resultSet.getString(COLUMN_ACCOUNTS_HOBBIES));
            accounts.setOccupation(resultSet.getString(COLUMN_ACCOUNTS_OCCUPATION));
            accounts.setCountry(resultSet.getString(COLUMN_ACCOUNTS_COUNTRY));
            accounts.setCity(resultSet.getString(COLUMN_ACCOUNTS_CITY));
            accounts.setAny_Other_Details(resultSet.getString(COLUMN_ACCOUNTS_MOREDETAILS));


        }
        return accounts;
    }
    public static void showDashboardFXML(pace_Classes.Accounts accounts) throws java.io.IOException, java.sql.SQLException {

        javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader();
        final java.net.URL resourceBundle = DAO.AccountsDAO.class.getClassLoader().getResource("dash_board/dashboard.fxml");
        loader.setLocation(resourceBundle);
        javafx.scene.Parent root = loader.load();
        //this is how we need to pass those paramenters next 3 lines
        dash_board.Dash_controller controller = loader.getController();
        controller.setLbl_display_userID(Integer.toString(accounts.getIdNumber()));//sending the parameter of the id number to the dashboard

        controller.UserPhoto(accounts);

        javafx.stage.Stage stage = new javafx.stage.Stage();
        stage.setTitle("Dashboard");
        stage.setScene(new javafx.scene.Scene(root, 900, 720));
        stage.initStyle(javafx.stage.StageStyle.UNDECORATED);
        new animatefx.animation.FadeIn(root).play();
        stage.show();
    }
    public static void DisplayPhotoReload(pace_Classes.Accounts accounts) throws java.io.IOException, java.sql.SQLException {
        java.util.ResourceBundle resourceBundle = null;
        javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(DAO.AccountsDAO.class.getClassLoader().getResource("dash_board/dashboard.fxml"), resourceBundle);
        javafx.scene.Parent root = loader.load();
        java.io.OutputStream os = new java.io.FileOutputStream("photo.jpg");

        accounts.setInputImage(accounts.getUser_photo().getBinaryStream());
        byte[] data = null;
        try {
            long nLen = accounts.getUser_photo().length();
            int nSize = (int) nLen;

            data = new byte[nSize];

            accounts.getInputImage().read(data);
            os.write(data, 0, data.length);
            dash_board.Dash_controller controller = loader.getController();
            if ((accounts.getUser_photo()) != null){
                javafx.scene.image.Image image = new javafx.scene.image.Image("file:photo.jpg");
                controller.displayProfilePhoto.setFill(new javafx.scene.paint.ImagePattern(image));
            }else {
                javafx.scene.image.Image img2 = new javafx.scene.image.Image("controllers.image/user.png");
                controller.displayProfilePhoto.setFill(new javafx.scene.paint.ImagePattern(img2));
            }
            javafx.stage.Stage stage = new javafx.stage.Stage();
            stage.setTitle("Dashboard");
            stage.setScene(new javafx.scene.Scene(root, 900, 720));
            stage.initStyle(javafx.stage.StageStyle.UNDECORATED);
            stage.show();
        }catch (java.io.IOException e){
            throw e;
        }
    }

    public void shareIndividualRecords(pace_Classes.Accounts accounts) throws java.io.IOException {
        java.util.ResourceBundle resourceBundle = null;
        javafx.fxml.FXMLLoader loaderindividual = new javafx.fxml.FXMLLoader(getClass().getClassLoader().getResource("dash_board/individual_record.fxml"), resourceBundle);
        javafx.scene.Parent rootindividual = loaderindividual.load();
        //this is how we need to pass those paramenters next 3 lines


    }

    public static pace_Classes.Accounts SearchEmployee(int id) throws java.sql.SQLException {

        StringBuilder sb = new StringBuilder(SEARCH_ID_QUERY);
        sb.append(id);
        sb.append("\"");
        sb.append("LIMIT 1");

        try(java.sql.Statement statement = conn.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery(sb.toString())) {

            pace_Classes.Accounts accounts = getDetailsFromResultset(resultSet);

        return accounts;

        }catch (java.sql.SQLException e) {
            System.out.println(e);
            throw e;
        }

    }
    public static javafx.collections.ObservableList<pace_Classes.Accounts> searchEmployee() throws java.sql.SQLException {
        StringBuilder sb = new StringBuilder();
        sb.append(SEARCH_EMPLOYEE);

        try(java.sql.Statement statement = conn.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery(sb.toString())) {

            javafx.collections.ObservableList<pace_Classes.Accounts> empList = getEmployeeList(resultSet);
            return empList;
        }catch (java.sql.SQLException e) {
            System.out.println(e);
            throw e;
        }
    }

    public static pace_Classes.Accounts getDetailsFromResultset(java.sql.ResultSet resultSet) throws java.sql.SQLException {
        pace_Classes.Accounts accounts = null;
        if (resultSet.next()){
                accounts = new pace_Classes.Accounts();
                accounts.setSirName(resultSet.getString(INDEX_ACCOUNTS_SIRNAME));
                accounts.setMiddleName(resultSet.getString(INDEX_ACCOUNTS_MIDDLENAME));
                accounts.setLastName(resultSet.getString(INDEX_ACCOUNTS_LASTNAME));
                accounts.setIdNumber(Integer.parseInt(resultSet.getString(INDEX_ACCOUNTS_IDNUMBER)));
                accounts.setEmailAddress(resultSet.getString(INDEX_ACCOUNTS_EMAIL));
        }
        return accounts;
    }

    public static javafx.collections.ObservableList<pace_Classes.Accounts> getEmployeeList(java.sql.ResultSet resultSet) throws java.sql.SQLException {

        javafx.collections.ObservableList<pace_Classes.Accounts> empList = javafx.collections.FXCollections.observableArrayList();
        while (resultSet.next()){
            pace_Classes.Accounts accounts = new pace_Classes.Accounts();
            accounts.setSirName(resultSet.getString(INDEX_ACCOUNTS_SIRNAME));
            accounts.setMiddleName(resultSet.getString(INDEX_ACCOUNTS_MIDDLENAME));
            accounts.setLastName(resultSet.getString(INDEX_ACCOUNTS_LASTNAME));
            accounts.setIdNumber(Integer.parseInt(resultSet.getString(INDEX_ACCOUNTS_IDNUMBER)));
            accounts.setEmailAddress(resultSet.getString(INDEX_ACCOUNTS_EMAIL));

            //Add employee to the ObservableList
            empList.add(accounts);
        }
        //return empList (ObservableList of Employees)
        return empList;
    }

}
