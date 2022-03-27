package controllers;

public class NewRecords_controller implements javafx.fxml.Initializable {
    @javafx.fxml.FXML
    private javafx.scene.layout.Pane newRec;
    @javafx.fxml.FXML
    private javafx.scene.control.TableView<pace_Classes.Credited_Accounts> table_new_record;

    @javafx.fxml.FXML
    private javafx.scene.control.TableColumn<pace_Classes.Credited_Accounts, String> col_Mname;

    @javafx.fxml.FXML
    private javafx.scene.control.TableColumn<pace_Classes.Credited_Accounts, String> col_Lname;

    @javafx.fxml.FXML
    private javafx.scene.control.TableColumn<pace_Classes.Credited_Accounts, Integer> col_ID;

    @javafx.fxml.FXML
    private javafx.scene.control.TableColumn<pace_Classes.Credited_Accounts, Double> col_Amount;

    @javafx.fxml.FXML
    private javafx.scene.control.TableColumn<pace_Classes.Credited_Accounts, Double> col_Balance;

    @javafx.fxml.FXML
    private javafx.scene.control.TableColumn<pace_Classes.Credited_Accounts, String> col_Status;

    @javafx.fxml.FXML
    private javafx.scene.control.TableColumn<pace_Classes.Credited_Accounts, java.util.Date> col_Date;


    javafx.collections.ObservableList<pace_Classes.Credited_Accounts> listM;

    @javafx.fxml.FXML
    private javafx.scene.control.TextField txt_Amount, search_id;

    @javafx.fxml.FXML
    private javafx.scene.control.DatePicker amountDate = null;
    @javafx.fxml.FXML
    private javafx.scene.control.Button btn_submit;
    @javafx.fxml.FXML
    javafx.scene.control.Button btn_search;
    @javafx.fxml.FXML
    javafx.scene.control.Label lbl_name;
    @javafx.fxml.FXML
    javafx.scene.control.Label lblSearch;
    @javafx.fxml.FXML
    javafx.scene.control.Label lblAmount;
    @javafx.fxml.FXML
    javafx.scene.control.Label lblDate;
    @javafx.fxml.FXML
    private javafx.scene.control.Label lbl_id_warning;
    @javafx.fxml.FXML
    private javafx.scene.control.Label lbl_amount_warning;
    @javafx.fxml.FXML
    private javafx.scene.control.Label date_warning;
    @javafx.fxml.FXML
    private javafx.scene.control.CheckBox multipleMonth;
    @javafx.fxml.FXML
    void on_multipleMonth(javafx.event.ActionEvent event) {

    }

    private java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("YYYY-MM-dd");

    @javafx.fxml.FXML
    void onsearch_id(javafx.event.ActionEvent event) {
        search_id();
    }
    @Override
    public void initialize(java.net.URL url, java.util.ResourceBundle resourceBundle) {
        new animatefx.animation.FadeInRight(newRec).play();

        /*
        The setCellValueFactory(...) that we set on the table columns are used to determine
        which field inside the Employee objects should be used for the particular column.
        The arrow -> indicates that we're using a Java 8 feature called Lambdas.
         (Another option would be to use a PropertyValueFactory, but this is not type-safe
         We're only using StringProperty values for our table columns in this example.
        When you want to use IntegerProperty or DoubleProperty, the setCellValueFactory(...)
        must have an additional asObject():
        */

        col_Mname.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("middleName"));
        col_Lname.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("lastName"));
        col_ID.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("idNumber"));
        col_Amount.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("credited_amount"));
        col_Balance.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("balance"));
        col_Status.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("status"));
        col_Date.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("date"));
        try {
            populateTable();
        } catch (java.sql.SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        btn_search.setOnAction(actionEvent -> {
            search_id();
        });
        btn_submit.setOnAction(actionEvent -> {
            insertNewRecords();
        });
    }
    private void insertNewRecords(){
        java.time.LocalDate date = amountDate.getValue();
        if (search_id.getText().isEmpty()) {
            lblSearch.setText("*");
            lbl_id_warning.setText("please enter id number");
        }
        if (txt_Amount.getText().isEmpty()) {
            lblAmount.setText("*");
            lbl_amount_warning.setText("empty!!");
        }
        if (amountDate.getValue() == null) {
            lblDate.setText("*");
            date_warning.setText("Please choose date");
        }

        if (multipleMonth.isSelected()){

            if (!txt_Amount.getText().equals("") && !search_id.getText().equals("")) {
                try {
                    pace_Classes.Credited_Accounts accounts = new pace_Classes.Credited_Accounts();
                    accounts.setIdNumber(Integer.parseInt(search_id.getText()));
                    accounts.setCredited_amount(Double.parseDouble(txt_Amount.getText()));
                    DAO.Credited_AccountsDAO.AddToAccount(accounts);

                    DAO.Credited_AccountsDAO.insertCreditedAccount(Integer.parseInt(search_id.getText()), Double.parseDouble(txt_Amount.getText()), date);
                    populateTable();
                    setextField();
                } catch (java.sql.SQLException | java.text.ParseException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
            }

        }else {
            try {
                DAO.Credited_AccountsDAO.insertOverpayement(Integer.parseInt(search_id.getText()), Double.parseDouble(txt_Amount.getText()), date);
                populateTable();
                setextField();
            } catch (java.sql.SQLException | java.text.ParseException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }


    }

    //populate the employee new records
    public void populatenewCreditedRecords(pace_Classes.Credited_Accounts credit){
        //Declare and ObservableList for table view
        javafx.collections.ObservableList<pace_Classes.Credited_Accounts> accData = javafx.collections.FXCollections.observableArrayList();
        //Add employee to the ObservableList
        accData.add(credit);
        //Set items to the employeeTable
        table_new_record.setItems(accData);
    }
    public void populateTable() throws java.sql.SQLException, ClassNotFoundException {
        try {
            //Get all Employees information
            javafx.collections.ObservableList<pace_Classes.Credited_Accounts> emData = DAO.Credited_AccountsDAO.searchEmployees();
            //Populate Employees on TableView
            populateMembers(emData);
        } catch (java.sql.SQLException | ClassNotFoundException e){
            System.out.println("Error occurred while getting employees information from DB.\n" + e);
            throw e;
        }
    }
    //Populate Employees for TableView
    public void populateMembers (javafx.collections.ObservableList<pace_Classes.Credited_Accounts> empData) throws
            ClassNotFoundException {
        //Set items to the employeeTable
        table_new_record.setItems(empData);
    }
    public void displayUsername(int idNumber) throws java.sql.SQLException, ClassNotFoundException {
        try {
            pace_Classes.Accounts account = DAO.FineDAO.searchDetails(idNumber);

            showUserName(account);
        } catch (java.sql.SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private void showUserName(pace_Classes.Accounts accounts) {
        lbl_name.setText(accounts.getSirName() + " " + accounts.getMiddleName() + " " + accounts.getLastName());
    }
    private void search_id(){
        try {
            if (search_id.getText().isEmpty()) {
                lblSearch.setText("*");
            } else {
                //checkPendingTransaction(Integer.parseInt(search_id.getText()));
                displayUsername(Integer.parseInt(search_id.getText()));
                lblSearch.setText("");
            }
        } catch (java.sql.SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void setextField(){
        lbl_name.setText("");
        lblSearch.setText("");
        lblAmount.setText("");
        lblDate.setText("");
        txt_Amount.setText("");
        search_id.setText("");
        amountDate.setValue(null);
    }

}
