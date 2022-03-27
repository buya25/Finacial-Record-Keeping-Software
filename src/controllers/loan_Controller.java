package controllers;
import pace_Classes.*;

public class loan_Controller implements javafx.fxml.Initializable {
    private java.text.NumberFormat numFormat = java.text.NumberFormat.getInstance();
    @javafx.fxml.FXML
    private javafx.scene.layout.Pane pane_Loan;
    @javafx.fxml.FXML
    private javafx.scene.control.ProgressIndicator progressiveBar;

    @javafx.fxml.FXML
    private javafx.scene.control.Label monthlyPayments;

    @javafx.fxml.FXML
    private javafx.scene.control.Label totalInterest;

    @javafx.fxml.FXML
    private javafx.scene.control.Label totalAmount;

    @javafx.fxml.FXML
    private javafx.scene.control.TextField txt_Searchid;

    @javafx.fxml.FXML
    private javafx.scene.control.TextField txt_Name;

    @javafx.fxml.FXML
    private javafx.scene.control.TextField txt_Amount;

    @javafx.fxml.FXML
    private javafx.scene.control.TextField txt_Months;

    @javafx.fxml.FXML
    private javafx.scene.control.TextField txt_Rate;

    @javafx.fxml.FXML
    private javafx.scene.control.Button btn_submit;

    @javafx.fxml.FXML
    private javafx.scene.control.Label loan_approved;

    @javafx.fxml.FXML
    private javafx.scene.control.Label lbl_Success;

    @javafx.fxml.FXML
    private javafx.scene.control.Label search_warning;

    @javafx.fxml.FXML
    private javafx.scene.control.Label month_warning;

    @javafx.fxml.FXML
    private javafx.scene.control.Label amount_warning;

    @javafx.fxml.FXML
    private javafx.scene.control.CheckBox checkbox;

    @javafx.fxml.FXML
    void on_Rate(javafx.event.ActionEvent event){
        boolean rate = DAO.LoanDAO.updateRate(Float.parseFloat(txt_Rate.getText()));

        if (rate){
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
            alert.setContentText("You have Successfully changed the percentage rate");
            alert.show();
            txt_Rate.setVisible(false);
            txt_Rate.setText("");
            checkbox.setSelected(false);
        }else {
            txt_Rate.setVisible(true);
        }

    }

    @javafx.fxml.FXML
    void on_Checkbox(javafx.event.ActionEvent event) {
            if (checkbox.isSelected()){
                txt_Rate.setVisible(true);
            }
            else
            {
                txt_Rate.setVisible(false);
            }
    }

    @javafx.fxml.FXML
    void on_Submit(javafx.event.ActionEvent event){
        btn_submit.setStyle("");
    }

    @javafx.fxml.FXML
    void text_Search(javafx.event.ActionEvent event) {
            search_id_loan();
        progressiveBar.setProgress(progressiveBar.getProgress() + 0.25f);
    }
    @javafx.fxml.FXML
    void on_Amount(javafx.scene.input.KeyEvent event) throws java.sql.SQLException {
        if (!txt_Amount.getText().isEmpty() && txt_Months.getText().isEmpty()){
            totalInterest(Float.parseFloat(txt_Amount.getText()), 1);
        }
        else if(!txt_Amount.getText().isEmpty() && !txt_Months.getText().isEmpty())
        {
            totalInterest(Float.parseFloat(txt_Amount.getText()), Float.parseFloat(txt_Months.getText()));
        }
        else {
            totalInterest(1,0);
        }
    }

    @javafx.fxml.FXML
    void on_months(javafx.scene.input.KeyEvent event) throws java.sql.SQLException {
        if (txt_Amount.getText().isEmpty() && !txt_Months.getText().isEmpty()){
            //get the value of the amount if the
            totalInterest(0, Float.parseFloat(txt_Months.getText()));

        }
        else if(!txt_Amount.getText().isEmpty() && !txt_Months.getText().isEmpty())
        {
            totalInterest(Float.parseFloat(txt_Amount.getText()), Float.parseFloat(txt_Months.getText()));
        }
        else {
            totalInterest(0,1);
        }
    }
    @Override
    public void initialize(java.net.URL url, java.util.ResourceBundle resourceBundle) {
        new animatefx.animation.FadeInUp(pane_Loan).play();


        btn_submit.setOnAction(actionEvent -> {
            insertLoanRecord();
        });

    }
    public void insertLoanRecord(){
        if (txt_Searchid.getText().isEmpty()){
            search_warning.setText("*");
        }
        if (txt_Amount.getText().isEmpty()){
            amount_warning.setText("*");
        }
        if (txt_Months.getText().isEmpty()){
            month_warning.setText("*");
        }
        if (!txt_Searchid.getText().isEmpty() && !txt_Amount.getText().isEmpty() && !txt_Months.getText().isEmpty()){
            boolean display = DAO.LoanDAO.insertLoanRecords(Integer.parseInt(txt_Searchid.getText()), Double.parseDouble(txt_Amount.getText()), Integer.parseInt(txt_Months.getText()));
            if (display){
                progressiveBar.setProgress(progressiveBar.getProgress() + 0.25f);
                lbl_Success.setText("Successfully");
            }

        }

    }
    public void displayUsernameloan(int idNumber) throws java.sql.SQLException, ClassNotFoundException {
        try {
            Accounts account = DAO.FineDAO.searchDetails(idNumber);

            showUserNameloan(account);
        } catch (java.sql.SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private void showUserNameloan(pace_Classes.Accounts accounts) {
        txt_Name.setText(accounts.getSirName() + " " + accounts.getMiddleName() + " " + accounts.getLastName());
    }
    private void search_id_loan(){
        try {
            if (txt_Searchid.getText().isEmpty()) {
                search_warning.setText("*");
            } else {
                //checkPendingTransaction(Integer.parseInt(search_id.getText()));
                displayUsernameloan(Integer.parseInt(txt_Searchid.getText()));
                search_warning.setText("");
            }
        } catch (java.sql.SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void totalInterest(float principal, float time) throws java.sql.SQLException {


        pace_Classes.Loan rate = DAO.LoanDAO.setRateobj(1);

        rateset(rate, principal, time);


    }

    private void rateset(pace_Classes.Loan rate, float principal, float time) {
        float simpleInterest;

        simpleInterest = (principal * rate.getRate() * time)/ 100;

        totalInterest.setText(numFormat.format(Math.round(simpleInterest)));// displaying the intrest amount

        monthlyPayments(principal, time, simpleInterest);
        totalAmount(principal, simpleInterest);//getting the total amount

    }

    private float monthlyPayments(float pricinpal, float time, float simpleInterest){
        //here i want to get the monthly payment
        float monthlypay;

        monthlypay = (simpleInterest + pricinpal) / time;

        monthlyPayments.setText(numFormat.format(Math.round(monthlypay * 100) / 100));

        return monthlypay;
    }
    private float totalAmount(float principal, float totalInterest){
        //here i want to get the total amount a member should pay
        float totalAMT;

        totalAMT = principal + totalInterest;

        totalAmount.setText(numFormat.format(Math.round(totalAMT)));
        return totalAMT;
    }

}
