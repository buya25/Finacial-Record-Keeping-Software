package pace_Classes;

public class Credited_Accounts extends pace_Classes.Accounts {
    private Double credited_amount;
    private Double contant_amount;
    private Double balance;
    private String status;
    private String monthName;
    private Double totalAmount;
    private Double individualtotalAmount;
    private Double creditedDeposit;
    private Double creditedWithdrawal;
    private java.sql.Date date;
    private String credit_date;

    public Credited_Accounts(controllers.NewRecords_controller newRecords_controller,
                             String sirName, String middleName, String lastName, int idNumber,
                             String emailAddress, String password, java.sql.Blob user_photo,
                             String phoneNumber, java.sql.Date birthDay, int zipCode, String address,
                             String occupation, String country, String city, String hobbies,
                             String any_Other_Details, java.io.InputStream inputImage,
                             Double credited_amount, Double contant_amount, Double balance,
                             String status, String monthName, Double totalAmount, Double individualtotalAmount,
                             Double creditedDeposit, Double creditedWithdrawal, java.sql.Date date, String credit_date) {

        super(newRecords_controller, sirName, middleName, lastName, idNumber,
                emailAddress, password, user_photo, phoneNumber, birthDay, zipCode,
                address, occupation, country, city, hobbies, any_Other_Details, inputImage);

        this.credited_amount = credited_amount;
        this.contant_amount = contant_amount;
        this.balance = balance;
        this.status = status;
        this.monthName = monthName;
        this.totalAmount = totalAmount;
        this.individualtotalAmount = individualtotalAmount;
        this.creditedDeposit = creditedDeposit;
        this.creditedWithdrawal = creditedWithdrawal;
        this.date = date;
        this.credit_date = credit_date;
    }

    public Credited_Accounts() {

    }

    public Double getCreditedDeposit() {
        return creditedDeposit;
    }

    public void setCreditedDeposit(Double creditedDeposit) {
        this.creditedDeposit = creditedDeposit;
    }

    public Double getCreditedWithdrawal() {
        return creditedWithdrawal;
    }

    public void setCreditedWithdrawal(Double creditedWithdrawal) {
        this.creditedWithdrawal = creditedWithdrawal;
    }

    public Double getIndividualtotalAmount() {
        return individualtotalAmount;
    }

    public void setIndividualtotalAmount(Double individualtotalAmount) {
        this.individualtotalAmount = individualtotalAmount;
    }

    public Double getContant_amount() {
        return contant_amount;
    }

    public void setContant_amount(Double contant_amount) {
        this.contant_amount = contant_amount;
    }


    public void setCredit_date(String credit_date) {
        this.credit_date = credit_date;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getCredited_amount() {
        return credited_amount;
    }

    public void setCredited_amount(Double credited_amount) {
        this.credited_amount = credited_amount;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public java.sql.Date getDate() {
        return date;
    }

    public String getCredit_date() {
        return credit_date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }
}