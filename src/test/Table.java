package test;

public class Table extends DBclass.DataBase_connection {
    private String fname;
    private String lname;
    private String dname;
    private int idnumber;
    private Double amount;
    private Double balance;
    private String status;
    private java.sql.Date date;

    public Table() {

    }

    public Table(String fname, String lname, String dname, int idnumber, Double amount, Double balance, String status, java.sql.Date date) {
        this.fname = fname;
        this.lname = lname;
        this.dname = dname;
        this.idnumber = idnumber;
        this.amount = amount;
        this.balance = balance;
        this.status = status;
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
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

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    public int getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(int idnumber) {
        this.idnumber = idnumber;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }
}
