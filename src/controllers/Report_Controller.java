package controllers;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import eu.hansolo.medusa.Gauge;


public class Report_Controller implements javafx.fxml.Initializable {
    @FXML
    private javafx.scene.layout.Pane pane_Reports;
    @FXML
    private Label lbl_revenue;

    @FXML
    private Label revenue_percentage;

    @FXML
    private Gauge gauge_Revenue;

    @FXML
    private Label lbl_totalContribution;

    @FXML
    private Label totalCon_percentage;

    @FXML
    private Gauge gauge_total_contribution;

    @FXML
    private Label lbl_Loan;

    @FXML
    private Label Loan_peercentage;

    @FXML
    private Label loan_Balance;

    @FXML
    private Gauge loan_Grauge;

    @FXML
    private Label lbl_MonthlyContribution;

    @FXML
    private Label mc_percentage;

    @FXML
    private Label mc_Balance;

    @FXML
    private Gauge monthly_contribution_gauge;

    @FXML
    private Gauge loanFine_Gauge;

    @FXML
    private Label loanFine_Percentage;

    @FXML
    private Label lbl_loanFine;

    @FXML
    private Label lbl_mcFine;

    @FXML
    private Label mcf_percentage;

    @FXML
    private Label mcf_balance;

    @FXML
    private Gauge MCF_gauge;

    @FXML
    private javafx.scene.layout.Pane pane_totalContribution;

    @FXML
    void omr_TotalContribution(javafx.scene.input.MouseEvent event) {
    }

    @Override
    public void initialize(java.net.URL url, java.util.ResourceBundle resourceBundle) {
        new animatefx.animation.Bounce(pane_Reports).play();
    }

    public void IndividualTotalAmount(pace_Classes.Credited_Accounts credit) throws java.sql.SQLException {
        credit = DAO.Credited_AccountsDAO.individual_Total_amount(credit);

         pace_Classes.Credited_Accounts cred = DAO.Credited_AccountsDAO.totalAmountContributed();

        if (cred.getTotalAmount() == null || credit.getIndividualtotalAmount() == null){
            lbl_totalContribution.setText("KSHS" + 0);
        }else {
            double answer = ((credit.getIndividualtotalAmount()) * 100) / cred.getTotalAmount();

            lbl_totalContribution.setText("KSHS: "+ credit.getIndividualtotalAmount());
            totalCon_percentage.setText("("+ (int) answer +"%)");
            gauge_total_contribution.setValue(answer);

        }
    }
    public void currentMonth(pace_Classes.Credited_Accounts credit) throws java.sql.SQLException {
        credit = DAO.Credited_AccountsDAO.currentMonthContribution(credit);

        pace_Classes.Credited_Accounts cred = DAO.Credited_AccountsDAO.CurrentTotalAmount();

        if (credit.getCredited_amount() == null || cred.getTotalAmount() == null){
            lbl_MonthlyContribution.setText("kSHS: " + 0);
        }else {
            double answer = ((credit.getCredited_amount()) * 100) / cred.getTotalAmount();

            lbl_MonthlyContribution.setText("KSHS: "+ credit.getCredited_amount());
            mc_percentage.setText("("+ (int) answer +"%)");
            monthly_contribution_gauge.setValue(answer);

        }
    }
    public void LoanIndividual(pace_Classes.Credited_Accounts credit) throws java.sql.SQLException{
        pace_Classes.Loan loan = DAO.LoanDAO.loanForIndividual(credit);

        pace_Classes.Loan loan1 = DAO.LoanDAO.TotalLoan();

        if (loan.getLoan_amount() == null || loan1.getLoan_Total() == null){
            lbl_Loan.setText("kSHS: " + 0);
        }else {
            double answer = ((loan.getLoan_amount()) * 100) / loan1.getLoan_Total();

            lbl_Loan.setText("KSHS: "+ loan.getLoan_amount());
            Loan_peercentage.setText("("+ (int) answer +"%)");
            loan_Grauge.setValue(answer);

        }

    }
    public void FineLoan(pace_Classes.Credited_Accounts credit) throws java.sql.SQLException {
        pace_Classes.Fine loan = DAO.FineDAO.individualFine(credit);

        pace_Classes.Fine fine = DAO.FineDAO.TotalFineLoan();

        if (loan.getFine_Loan() == null || fine.getFine_Total_amount() == null){
            lbl_loanFine.setText("kSHS: " + 0);
        }else {
            double answer = ((loan.getFine_Loan()) * 100) / fine.getFine_Total_amount();

            lbl_loanFine.setText("KSHS: "+ loan.getFine_Loan());
            loanFine_Percentage.setText("("+ (int) answer +"%)");
            loanFine_Gauge.setValue(answer);

        }
    }

    public void FineCredited(pace_Classes.Credited_Accounts credit) throws java.sql.SQLException {
        pace_Classes.Fine loan = DAO.FineDAO.IndividualCreditedFine(credit);

        pace_Classes.Fine fine = DAO.FineDAO.CreditedFine();

        if (loan.getFine_Credited() == null || fine.getFine_Total_amount() == null){
            lbl_mcFine.setText("kSHS: " + 0);
        }else {
            double answer = ((loan.getFine_Credited()) * 100) / fine.getFine_Total_amount();

            lbl_mcFine.setText("KSHS: "+ loan.getFine_Credited());
            mcf_percentage.setText("("+ (int) answer +"%)");
            MCF_gauge.setValue(answer);

        }
    }


}
