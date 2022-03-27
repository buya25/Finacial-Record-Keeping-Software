package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class update_controller implements javafx.fxml.Initializable {
    @javafx.fxml.FXML
    private TextField txt_search;

    @FXML
    private Button btn_Start;

    @FXML
    private Button btn_back;

    @FXML
    private Button btn_Next;

    @FXML
    private Button btn_End;

    @FXML
    private Button btn_Search;

    @FXML
    private TextField mcAmount;

    @FXML
    private TextArea txtArea_mc;

    @FXML
    private TextField mcBalance;

    @FXML
    private TextField mcStatus;

    @FXML
    private TextField mcDate;

    @FXML
    private Button btn_mcSubmit;

    @FXML
    private TextArea txtArea_mcFine;

    @FXML
    private TextField mcFineAmount;

    @FXML
    private Button mcFine_Update;

    @FXML
    private TextField mcFine_Date;

    @FXML
    private javafx.scene.layout.Pane pane_Update;

    @FXML
    private TextField mcFineBalance;

    @FXML
    private TextField mcFineStatus;

    @FXML
    private TextField loan_Status;

    @FXML
    private TextArea txtArea_Loan;

    @FXML
    private TextField loan_intialAmount;

    @FXML
    private TextField loan_Balance;

    @FXML
    private TextField loan_repaymentDate;

    @FXML
    private Button btn_loanSubmit;

    @FXML
    private TextField loan_BorrowedDate;

    @FXML
    private TextArea txtArea_LoanFine;

    @FXML
    private TextField loanFine_Amount;

    @FXML
    private Button btn_LoanFine;

    @FXML
    private TextField loanFineDate;

    @FXML
    private TextField loanFine_Status;

    @FXML
    private TextField loanFine_Balance;
    @FXML
    private TextField txt_idNumber;
    @FXML
    private TextField txt_Amount;
    @FXML
    private TextField txt_Fine;
    @FXML
    private TextField txt_Loan;
    @FXML
    private TextField txt_Date;
    @FXML
    private TextArea displayTextArea;
    @FXML
    private Button btn_update;
    @FXML
    private TextField txt_FineBalance;
    @FXML
    private TextField txt_AmountBalance;

    @FXML
    void on_mcSubmit(javafx.event.ActionEvent event) {

    }

    @FXML
    void pressed_mcAmount(javafx.scene.input.KeyEvent event) throws java.sql.SQLException {

    }

    @FXML
    void on_Search(java.awt.event.ActionEvent event) {

    }

    @FXML
    private void on_mcAmount(javafx.event.ActionEvent event) {

    }

    @FXML
    void on_mcFineAmount(javafx.event.ActionEvent event) {

    }

    @FXML
    void on_Search(javafx.event.ActionEvent event) throws java.sql.SQLException, ClassNotFoundException {
        if (txt_search.getText().equals("")){
            txtArea_mc.setText("Please Enter the ID Number to get the Details");
            txtArea_mcFine.setText("Please Enter the ID Number to get the Details");
            txtArea_Loan.setText("Please Enter the ID Number to get the Details");
            txtArea_LoanFine.setText("Please Enter the ID Number to get the Details");
        }else {
            displayDetails(Integer.parseInt(txt_search.getText()));
            displayLoanDetails(Integer.parseInt(txt_search.getText()));
            displayFineCreditedDetails(Integer.parseInt(txt_search.getText()));
            displayFineLoan(Integer.parseInt(txt_search.getText()));
        }
    }

    @Override
    public void initialize(java.net.URL url, java.util.ResourceBundle resourceBundle) {
        new animatefx.animation.FadeInUp(pane_Update).play();

        btn_mcSubmit.setOnAction(event -> {
            updMonthlyContribution();
        });
        mcFine_Update.setOnAction(event -> {
            try {
                updateTransactionFineCredited();
            } catch (java.sql.SQLException throwables) {
                throwables.printStackTrace();
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
        });
        btn_loanSubmit.setOnAction(event -> {
            try {
                updateLoan();
            } catch (java.sql.SQLException throwables) {
                throwables.printStackTrace();
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
        });
        btn_LoanFine.setOnAction(event -> {
            try {
                updateFineLoan();
            } catch (java.sql.SQLException throwables) {
                throwables.printStackTrace();
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
        });

    }
    public void updateFineLoan() throws java.sql.SQLException, java.text.ParseException {
        pace_Classes.Fine fine = new pace_Classes.Fine();
        fine.setCredited_amount(Double.parseDouble(loanFine_Amount.getText()));
        fine.setIdNumber(Integer.parseInt(txt_search.getText()));
        DAO.FineDAO.PayingFineLoan(fine);

        pace_Classes.Fine total = DAO.FineDAO.TotalFineLoan(fine);

        if (total.getFine_Total_amount() != null){
            double balance = total.getFine_Total_amount() - fine.getCredited_amount();
            if (balance < 0){
                loanFine_Balance.setText(String.valueOf(0.0));
                txtArea_LoanFine.setText("Paid Amount ksh: " + loanFine_Amount.getText() + "\n" +
                        "  Balance kshs: " +  (0.0) + "\n" +
                        "Paid Date: " + java.time.LocalDate.now());
                txt_search.setText("");
            }else {
                loanFine_Balance.setText(String.valueOf(-balance));
                txtArea_LoanFine.setText("Paid Amount ksh: " + loanFine_Amount.getText() + "\n" +
                        "  Balance kshs: " +  (balance) + "\n" +
                        "Paid Date: " + java.time.LocalDate.now());
            }

        }else {
            txtArea_mc.setText("Currently you don't have any Pending transaction");
        }
    }
    public void updateTransactionFineCredited() throws java.sql.SQLException, java.text.ParseException {
        pace_Classes.Fine fine = new pace_Classes.Fine();
        fine.setCredited_amount(Double.parseDouble(mcFineAmount.getText()));
        fine.setIdNumber(Integer.parseInt(txt_search.getText()));
        DAO.FineDAO.PayingFineCredited(fine);

        pace_Classes.Fine total = DAO.FineDAO.TotalFineCredit(fine);

        if (txt_search.getText() == null){
            txtArea_Loan.setText("Please Enter the ID Number to the Details");
        }

        if (total.getFine_Total_amount() != null){
            double balance = total.getFine_Total_amount() - fine.getCredited_amount();
            if (balance < 0){
                mcFineBalance.setText(String.valueOf(0.0));
                txtArea_mcFine.setText("Paid Amount ksh: " + mcFineAmount.getText() + "\n" +
                        "  Balance kshs: " +  (0.0) + "\n" +
                        "Paid Date: " + java.time.LocalDate.now());
                txt_search.setText("");
            }else {
                mcFineBalance.setText(String.valueOf(-balance));
                txtArea_mcFine.setText("Paid Amount ksh: " + mcFineAmount.getText() + "\n" +
                        "  Balance kshs: " +  (balance) + "\n" +
                        "Paid Date: " + java.time.LocalDate.now());
            }

        }else {
            txtArea_mc.setText("Currently you don't have any Pending transaction");
        }
    }
    public void updateLoan() throws java.sql.SQLException, java.text.ParseException {
        pace_Classes.Loan loan = new pace_Classes.Loan();
        loan.setCredited_amount(Double.parseDouble(loan_intialAmount.getText()));
        loan.setIdNumber(Integer.parseInt(txt_search.getText()));
        DAO.LoanDAO.PayingLoan(loan);

        if (txt_search.getText() == null){
            txtArea_Loan.setText("Please Enter the ID Number to the Details");
        }

        pace_Classes.Loan total = DAO.LoanDAO.TotalPendingLoan(loan);

        if (total.getLoan_Total() != null){
            double balance = total.getLoan_Total() - loan.getCredited_amount();
            if (balance < 0){
                loan_Balance.setText(String.valueOf(0.0));
                txtArea_Loan.setText("Loan is Fully Paid" + "\n" +
                        " Current Balance kshs: " +  (0.0) + "\n" +
                        "Paid Date: " + java.time.LocalDate.now());
                txt_search.setText("");
                loan_intialAmount.setText("");
            }else {
                loan_Balance.setText(String.valueOf(-balance));
                txtArea_Loan.setText("Paid Amount ksh: " + mcFineAmount.getText() + "\n" +
                        "  Balance kshs: " +  (balance) + "\n" +
                        "Paid Date: " + java.time.LocalDate.now());
            }

        }else {
            txtArea_mc.setText("Currently you don't have any Pending transaction");
        }
    }

    public void displayDetails(int idNumber) throws java.sql.SQLException, ClassNotFoundException {

        try {
            pace_Classes.Fine account = DAO.FineDAO.searchIDnumber(idNumber);

            showcreditedAccounts(account);
        } catch (java.sql.SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private void showcreditedAccounts(pace_Classes.Fine accounts) {

        if (accounts.getBalance() != null) {
            mcBalance.setText(Double.toString(accounts.getBalance()));
        } else {
            mcBalance.setText(Integer.toString(0));
        }
        if (accounts.getStatus() != null) {
            mcStatus.setText(accounts.getStatus());
        } else {
            mcStatus.setText("CREDITED");
        }
        java.text.DateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/YYYY");
        if (accounts.getDate() != null) {
            mcDate.setText(dateFormat.format(accounts.getDate()));
        } else {
            mcDate.setText("0000-00-00");
        }

        if (accounts.getCredited_amount() != null) {
            txtArea_mc.setText("Total amount paid: " + accounts.getCredited_amount() + "\n" +
                    "Please Pay the Remaining Balance to Avoid any Fine");
        } else {
            txtArea_mc.setText("Currently you don't have any Pending transaction");
        }

    }

    public void displayLoanDetails(int idNumber) throws java.sql.SQLException, ClassNotFoundException {
        try {
            pace_Classes.Fine account = DAO.FineDAO.searchIDLoan(idNumber);

            showLoanDetails(account);
        } catch (java.sql.SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private void showLoanDetails(pace_Classes.Fine accounts) {
        if (accounts.getLoanBalance() != null) {
            loan_Balance.setText(Double.toString(accounts.getLoanBalance()));
        } else {
            loan_Balance.setText(Double.toString(0));
        }
        if (accounts.getLoan_Status() != null) {
            loan_Status.setText(accounts.getLoan_Status());
        } else {
            loan_Status.setText("CREDITED");
        }

        java.text.DateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/YYYY");
        if (accounts.getDatePaid() != null) {
            loan_repaymentDate.setText(dateFormat.format(accounts.getDatePaid()));
        } else {
            loan_repaymentDate.setText("0000-00-00");
        }

        if (accounts.getLoanDate() != null) {
            loan_BorrowedDate.setText(dateFormat.format(accounts.getLoanDate()));
        } else {
            loan_BorrowedDate.setText("0000-00-00");
        }

        if (accounts.getLoanDate() != null) {
            txtArea_Loan.setText("Total amount borrowed: " + accounts.getLoan_amount() + "\n" +
                    "Please Pay the Remaining Balance to Avoid any Fine before date: " + accounts.getDatePaid());
        } else {
            txtArea_Loan.setText("No Existing record for Loan");
        }

    }

    public void displayFineCreditedDetails(int idNumber) throws java.sql.SQLException, ClassNotFoundException {
        try {
            pace_Classes.Fine account = DAO.FineDAO.searchIDFineMonthly(idNumber);
            account.setIdNumber(Integer.parseInt(txt_search.getText()));
            showFineCredited(account);
        } catch (java.sql.SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private void showFineCredited(pace_Classes.Fine accounts) throws java.sql.SQLException {
        java.text.DateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/YYYY");
        if (accounts.getFine_Credited_balance() != null) {
            mcFineStatus.setText(accounts.getFine_Credited_Status());
            mcFine_Date.setText(dateFormat.format(accounts.getFine_credited_date()));

            pace_Classes.Fine totalFine = DAO.FineDAO.TotalFineCredit(accounts);
            mcFineBalance.setText("-"+ totalFine.getFine_Total_amount());
            txtArea_mcFine.setText("You have been Fined total of Kshs: " + totalFine.getFine_Total_amount() + "\n" +
                    "Please Pay the Remaining Balance to Avoid any additional fine");
        } else {
            mcFineBalance.setText(Double.toString(0));
            mcFineStatus.setText("CREDITED");
            mcFine_Date.setText("0000-00-00");
            txtArea_mcFine.setText("No Existing for Fine");
        }

    }

    public void displayFineLoan(int idNumber) throws java.sql.SQLException, ClassNotFoundException {
        try {
            pace_Classes.Fine account = DAO.FineDAO.searchIDFineLoan(idNumber);

            showFineLoan(account);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private void showFineLoan(pace_Classes.Fine accounts) {
        java.text.DateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/YYYY");
        if (accounts.getFine_Loan_balance() != null) {
            loanFine_Balance.setText(Double.toString(accounts.getFine_Loan_balance()));
            loanFine_Status.setText(accounts.getFine_Loan_Status());
            loanFineDate.setText(dateFormat.format(accounts.getFine_Loan_date()));
            txtArea_LoanFine.setText("Additional of Kshs: " + accounts.getFine_Loan() + "\n" +
                    "Please Pay the Remaining Balance to Avoid any additional fine");
        } else {
            loanFine_Balance.setText(Double.toString(0));
            loanFine_Status.setText("CREDITED");
            loanFineDate.setText("0000-00-00");
            txtArea_LoanFine.setText("No Existing Records");
        }


    }

    private void updMonthlyContribution() {
        try {

            //create an object of that will call the pending transaction and use it to the amount that is currently in the account
            pace_Classes.Credited_Accounts credit = new pace_Classes.Credited_Accounts();
            //now you can update the amount in the account
            credit.setCredited_amount(Double.parseDouble(mcAmount.getText()));
            credit.setIdNumber(Integer.parseInt(txt_search.getText()));
            DAO.Credited_AccountsDAO.UpdatePendingTransaction(credit);
            //later we can display the amount after the update and make sure it was updated
            java.text.DateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/YYYY");

            if (credit.getCredited_amount() != null) {
                txtArea_mc.setText("You have successfully added Kshs:" + credit.getCredited_amount() + "\n\n" +
                                    "Current Date: " + java.time.LocalDate.now());

                mcAmount.setText("");
                mcBalance.setText("");
                mcStatus.setText("");
                mcDate.setText("");
            } else {
                System.out.println(credit.getCredited_amount());
            }

        } catch (java.sql.SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
