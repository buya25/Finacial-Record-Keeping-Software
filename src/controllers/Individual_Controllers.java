package controllers;
import javafx.scene.control.TableColumn;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.event.ActionEvent;
import pace_Classes.Credited_Accounts;
import pace_Classes.Loan;

public class Individual_Controllers implements javafx.fxml.Initializable {
    @FXML
    private javafx.scene.layout.Pane pane_individualRecords;
    @FXML
    private TableView<Loan> table_Loan;

    @FXML
    private TableColumn<Loan, java.util.Date> col_RequestedDate;

    @FXML
    private TableColumn<Loan, java.util.Date> col_RepaymentDate;

    @FXML
    private TableColumn<Loan, Double> col_LAmount;

    @FXML
    private TableColumn<Loan, Double> col_Lbalance;

    @FXML
    private TableColumn<Loan, String> col_Lstatus;
    @FXML
    private TableView<Credited_Accounts> table_Monthly_Contribution;

    @FXML
    private TableColumn<Credited_Accounts, Double> col_Months;

    @FXML
    private TableColumn<Credited_Accounts, Double> col_Amount;

    @FXML
    private TableColumn<Credited_Accounts, Double> col_balance;

    @FXML
    private TableColumn<Credited_Accounts, String> col_Status;

    @FXML
    private Label lbl_Username_Display;

    @FXML
    private Label lbl_total_amount;

    @FXML
    private TextField id_number;

    @FXML
    private ComboBox<String> monthly_Contribution;

    @FXML
    private ComboBox<Integer> intmonths;

    @FXML
    private Label lbl_total_Loan;

    @FXML
    void on_Monthly_contribution(ActionEvent event) {

    }

    @FXML
    void on_Search_ID(ActionEvent event) {
        try {
            populateIndividualTable(Integer.parseInt(id_number.getText()));
            displayTotalAmount(Integer.parseInt(id_number.getText()));
            displayUsername(Integer.parseInt(id_number.getText()));
            populateLoanTable(Integer.parseInt(id_number.getText()));
        } catch (java.sql.SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void on_intmonths(ActionEvent event) {

    }

    @FXML
    void on_monthlyContribution(ActionEvent event) {
        if (monthly_Contribution.getValue().equals("Monthly Contribution")){
            table_Monthly_Contribution.setVisible(true);
            lbl_total_amount.setVisible(true);
            table_Loan.setVisible(false);
            lbl_total_Loan.setVisible(false);
        }
        else if (monthly_Contribution.getValue().equals("Loan"))
        {
            //display loan table
            table_Loan.setVisible(true);
            table_Monthly_Contribution.setVisible(false);
            lbl_total_amount.setVisible(false);
            lbl_total_Loan.setVisible(true);
        }
        else if (monthly_Contribution.getValue().equals("Select Table"))
        {
            lbl_total_Loan.setVisible(false);
            table_Monthly_Contribution.setVisible(false);
            table_Loan.setVisible(false);
            lbl_total_amount.setVisible(false);
            //set loan table to false
        }
    }


    @Override
    public void initialize(java.net.URL url, java.util.ResourceBundle resourceBundle) {
        new animatefx.animation.FadeInDown(pane_individualRecords).play();


        //for table monthly contribution please dont change it
        col_Months.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("credit_date"));
        col_Amount.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("credited_amount"));
        col_balance.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("balance"));
        col_Status.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("status"));

        //for table loan please dont change it
        col_RequestedDate.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("loanDate"));
        col_RepaymentDate.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("datePaid"));
        col_LAmount.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("loan_amount"));
        col_Lbalance.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("loanBalance"));
        col_Lstatus.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("loan_Status"));

        //setting the combobox with component to choose or select
        monthly_Contribution.getItems().addAll( "Select Table", "Monthly Contribution", "Loan", "Monthly Fine", "Loan Fine");
        intmonths.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);



    }
    public void displayUsername(int idNumber){
        try {
            pace_Classes.Accounts accounts = DAO.AccountsDAO.SearchEmployee(idNumber);

            UserName(accounts);
        } catch (java.sql.SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void UserName(pace_Classes.Accounts accounts) {
        lbl_Username_Display.setText(accounts.getSirName() + " " + accounts.getMiddleName() + " " + accounts.getLastName());
    }

    public void displayTotalAmount(int idNumber){
        try {
            Credited_Accounts credit = DAO.Credited_AccountsDAO.TotalAmountMonthly(idNumber);

            UserDisplay(credit);
        } catch (java.sql.SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void UserDisplay(pace_Classes.Credited_Accounts credit) {
        lbl_total_amount.setText(Double.toString(credit.getTotalAmount()));
    }

    private void UserLoan(pace_Classes.Loan loan) {
        lbl_total_Loan.setText(Double.toString(loan.getLoan_Total()));
    }


    public void populateIndividualTable(int idNumber) throws java.sql.SQLException, ClassNotFoundException {
        try {
            //Get all Employees information
            javafx.collections.ObservableList<pace_Classes.Credited_Accounts> emData = DAO.Credited_AccountsDAO.searchIndividualID(idNumber);
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
        table_Monthly_Contribution.setItems(empData);
    }
    public void populateLoanTable(int idNumber) throws java.sql.SQLException, ClassNotFoundException, java.text.ParseException {
        try {
            //Get all Employees information
            javafx.collections.ObservableList<pace_Classes.Loan> emData = DAO.LoanDAO.searchLoanID(idNumber);
            //Populate Employees on TableView
            populateLoanMembers(emData);
        } catch (java.sql.SQLException | ClassNotFoundException | java.text.ParseException e){
            System.out.println("Error occurred while getting employees information from DB.\n" + e);
            throw e;
        }
    }
    //Populate Employees for TableView
    public void populateLoanMembers(javafx.collections.ObservableList<pace_Classes.Loan> empData) throws
            ClassNotFoundException {
        //Set items to the employeeTable
        table_Loan.setItems(empData);
    }

    public void on_Loan(javafx.scene.control.SortEvent<javafx.scene.control.TableView<pace_Classes.Loan>> tableViewSortEvent) {
    }
}
