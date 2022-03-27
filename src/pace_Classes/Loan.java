package pace_Classes;

public class Loan extends Credited_Accounts{
    private Double loan_amount;
    private Double loanBalance;
    private Double loanDeposit;
    private Double loanWithdrawal;
    private String loan_Status;
    private Double loan_Total;
    private Float rate;
    private java.util.Date datePaid;
    private java.util.Date loanDate;


    public Loan(controllers.NewRecords_controller newRecords_controller,
                String sirName, String middleName, String lastName, int idNumber,
                String emailAddress, String password, java.sql.Blob user_photo,
                String phoneNumber, java.sql.Date birthDay, int zipCode, String address,
                String occupation, String country, String city, String hobbies,
                String any_Other_Details, java.io.InputStream inputImage,
                Double credited_amount, Double contant_amount, Double balance,
                String status, String monthName, Double totalAmount, Double individualtotalAmount,
                Double creditedDeposit, Double creditedWithdrawal, java.sql.Date date,
                String credit_date, Double loan_amount, Double loanBalance, Double loanDeposit
            , Double loanWithdrawal, String loan_Status, Double loan_Total, Float rate,
                java.util.Date datePaid, java.util.Date loanDate) {

        super(newRecords_controller, sirName, middleName, lastName, idNumber,
                emailAddress, password, user_photo, phoneNumber, birthDay,
                zipCode, address, occupation, country, city, hobbies, any_Other_Details,
                inputImage, credited_amount, contant_amount, balance, status, monthName,
                totalAmount, individualtotalAmount, creditedDeposit, creditedWithdrawal, date, credit_date);

        this.loan_amount = loan_amount;
        this.loanBalance = loanBalance;
        this.loanDeposit = loanDeposit;
        this.loanWithdrawal = loanWithdrawal;
        this.loan_Status = loan_Status;
        this.loan_Total = loan_Total;
        this.rate = rate;
        this.datePaid = datePaid;
        this.loanDate = loanDate;
    }

    public Loan() {

    }

    public Double getLoanDeposit() {
        return loanDeposit;
    }

    public void setLoanDeposit(Double loanDeposit) {
        this.loanDeposit = loanDeposit;
    }

    public Double getLoanWithdrawal() {
        return loanWithdrawal;
    }

    public void setLoanWithdrawal(Double loanWithdrawal) {
        this.loanWithdrawal = loanWithdrawal;
    }

    public String getLoan_Status() {
        return loan_Status;
    }

    public void setLoan_Status(String loan_Status) {
        this.loan_Status = loan_Status;
    }

    public Double getLoan_Total() {
        return loan_Total;
    }

    public void setLoan_Total(Double loan_Total) {
        this.loan_Total = loan_Total;
    }

    public Double getLoan_amount() {
        return loan_amount;
    }

    public void setLoan_amount(Double loan_amount) {
        this.loan_amount = loan_amount;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public Double getLoanBalance() {
        return loanBalance;
    }

    public void setLoanBalance(Double loanBalance) {
        this.loanBalance = loanBalance;
    }

    public java.util.Date getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(java.util.Date datePaid) {
        this.datePaid = datePaid;
    }

    public java.util.Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(java.util.Date loanDate) {
        this.loanDate = loanDate;
    }
}
