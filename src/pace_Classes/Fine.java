package pace_Classes;

public class Fine extends Loan{
    private Double fine_constant;
    private Double fine_Credited;
    private Double fine_Loan;
    private String fine_Credited_Status;
    private String fine_Loan_Status;
    private Double fine_Credited_balance;
    private Double fine_Loan_balance;
    private Double fine_Total_amount;
    private java.sql.Date fine_credited_date;
    private java.sql.Date fine_Loan_date;

    public Fine(controllers.NewRecords_controller newRecords_controller, String sirName,
                String middleName, String lastName, int idNumber, String emailAddress,
                String password, java.sql.Blob user_photo, String phoneNumber,
                java.sql.Date birthDay, int zipCode, String address, String occupation,
                String country, String city, String hobbies, String any_Other_Details,
                java.io.InputStream inputImage, Double credited_amount, Double contant_amount,
                Double balance, String status, String monthName, Double totalAmount,
                Double individualtotalAmount, Double creditedDeposit, Double creditedWithdrawal,
                java.sql.Date date, String credit_date, Double loan_amount, Double loanBalance,
                Double loanDeposit, Double loanWithdrawal, String loan_Status, Double loan_Total,
                Float rate, java.util.Date datePaid, java.util.Date loanDate, Double fine_constant,
                Double fine_Credited, Double fine_Loan, String fine_Credited_Status,
                String fine_Loan_Status, Double fine_Credited_balance, Double fine_Loan_balance,
                Double fine_Total_amount, java.sql.Date fine_credited_date, java.sql.Date fine_Loan_date) {

        super(newRecords_controller, sirName, middleName, lastName, idNumber, emailAddress,
                password, user_photo, phoneNumber, birthDay, zipCode, address, occupation,
                country, city, hobbies, any_Other_Details, inputImage, credited_amount,
                contant_amount, balance, status, monthName, totalAmount, individualtotalAmount,
                creditedDeposit, creditedWithdrawal, date, credit_date, loan_amount, loanBalance,
                loanDeposit, loanWithdrawal, loan_Status, loan_Total, rate, datePaid, loanDate);

        this.fine_constant = fine_constant;
        this.fine_Credited = fine_Credited;
        this.fine_Loan = fine_Loan;
        this.fine_Credited_Status = fine_Credited_Status;
        this.fine_Loan_Status = fine_Loan_Status;
        this.fine_Credited_balance = fine_Credited_balance;
        this.fine_Loan_balance = fine_Loan_balance;
        this.fine_Total_amount = fine_Total_amount;
        this.fine_credited_date = fine_credited_date;
        this.fine_Loan_date = fine_Loan_date;
    }

    public Fine() {

    }

    public Double getFine_Total_amount() {
        return fine_Total_amount;
    }

    public void setFine_Total_amount(Double fine_Total_amount) {
        this.fine_Total_amount = fine_Total_amount;
    }

    public Double getFine_constant() {
        return fine_constant;
    }

    public String getFine_Credited_Status() {
        return fine_Credited_Status;
    }

    public void setFine_Credited_Status(String fine_Credited_Status) {
        this.fine_Credited_Status = fine_Credited_Status;
    }

    public String getFine_Loan_Status() {
        return fine_Loan_Status;
    }

    public void setFine_Loan_Status(String fine_Loan_Status) {
        this.fine_Loan_Status = fine_Loan_Status;
    }

    public void setFine_constant(Double fine_constant) {
        this.fine_constant = fine_constant;
    }

    public Double getFine_Credited() {
        return fine_Credited;
    }

    public void setFine_Credited(Double fine_Credited) {
        this.fine_Credited = fine_Credited;
    }

    public Double getFine_Loan() {
        return fine_Loan;
    }

    public void setFine_Loan(Double fine_Loan) {
        this.fine_Loan = fine_Loan;
    }

    public Double getFine_Credited_balance() {
        return fine_Credited_balance;
    }

    public void setFine_Credited_balance(Double fine_Credited_balance) {
        this.fine_Credited_balance = fine_Credited_balance;
    }

    public Double getFine_Loan_balance() {
        return fine_Loan_balance;
    }

    public void setFine_Loan_balance(Double fine_Loan_balance) {
        this.fine_Loan_balance = fine_Loan_balance;
    }

    public java.sql.Date getFine_credited_date() {
        return fine_credited_date;
    }

    public void setFine_credited_date(java.sql.Date fine_credited_date) {
        this.fine_credited_date = fine_credited_date;
    }

    public java.sql.Date getFine_Loan_date() {
        return fine_Loan_date;
    }

    public void setFine_Loan_date(java.sql.Date fine_Loan_date) {
        this.fine_Loan_date = fine_Loan_date;
    }
}
