package DAO;

public class LoanDAO extends Credited_AccountsDAO{

    //Get the total amount of LOAN
    public static pace_Classes.Loan TotalPendingLoan(pace_Classes.Loan total) throws java.sql.SQLException{
        StringBuilder sb = new StringBuilder(TOTAL_PENDING_LOAN);
        sb.append(total.getIdNumber());

        try (java.sql.Statement statement = conn.createStatement();
             java.sql.ResultSet resultSet = statement.executeQuery(sb.toString())){
            while (resultSet.next()){
                total.setLoan_Total(resultSet.getDouble(COLUMN_LOAN_AMOUNT));
            }
        }
        return total;
    }
    //Here i am paying the Amount that has been fined
    public static pace_Classes.Loan PayingLoan(pace_Classes.Loan updateloan) throws java.sql.SQLException, java.text.ParseException {

        StringBuilder sb = new StringBuilder(PAY_LOAN);
        sb.append("`"+COLUMN_LOAN_BALANCE+"` = IF(`"+COLUMN_LOAN_AMOUNT+"` >= `"+COLUMN_LOAN_BALANCE+"`, IF(`"+COLUMN_LOAN_AMOUNT+"` >= ("+COLUMN_LOAN_BALANCE+" + "+updateloan.getCredited_amount()+"), (`"+COLUMN_LOAN_BALANCE+"` + "+updateloan.getCredited_amount()+"), "+COLUMN_LOAN_AMOUNT+") , `"+COLUMN_LOAN_BALANCE+"`), ");
        sb.append("`"+COLUMN_LOAN_STATUS+"` = IF(`"+COLUMN_LOAN_AMOUNT+"` <= `"+COLUMN_LOAN_BALANCE+"`, 'CREDITED', `"+COLUMN_LOAN_STATUS+"`), ");
        sb.append("`"+COLUMN_LOAN_PAID_DATE+"` = '"+ java.time.LocalDate.now() +"' ");
        sb.append("WHERE `"+COLUMN_LOAN_ID_NUMBER+"` = "+updateloan.getIdNumber()+" AND `STATUS` = 'PENDING'");
        sb.append("order by "+COLUMN_LOAN_DATE+" asc LIMIT 1;");

        double balance;
        //get the amount fined
        pace_Classes.Loan loanAmount = SelectLoanPending(updateloan);

        if (loanAmount.getLoan_amount() != null){


            try(java.sql.Statement statement = conn.createStatement();){
                statement.executeUpdate(sb.toString());
            }
            //CHECK IF THE AMOUNT GIVEN IS MORE THAN THE ON IN THE LAST FINE GIVEN
            balance  = updateloan.getCredited_amount() - loanAmount.getLoan_amount();
            if (balance > 0) {
                updateloan.setCredited_amount(balance);
                PayingLoan(updateloan);
            }

        }else {
            DAO.Credited_AccountsDAO credit = new Credited_AccountsDAO();
            credit.insertCreditedAccount(updateloan.getIdNumber(), updateloan.getCredited_amount(), java.time.LocalDate.now());
        }


        return updateloan;
    }
    //Here i am trying to get the amount LOANED and the setting it to the variable
    public static pace_Classes.Loan SelectLoanPending(pace_Classes.Loan loan) throws java.sql.SQLException {
        StringBuilder sb = new StringBuilder(SELECT_PENDING_LOAN);
        sb.append(loan.getIdNumber());
        sb.append(" order by "+COLUMN_LOAN_DATE+" asc limit 1;");

        try(java.sql.Statement statement = conn.createStatement();
            java.sql.ResultSet result = statement.executeQuery(sb.toString())){
            while (result.next()){
                loan.setLoan_amount(result.getDouble(COLUMN_LOAN_AMOUNT));
                loan.setLoanBalance(result.getDouble(COLUMN_LOAN_BALANCE));
            }
        }
        return loan;
    }
    public static pace_Classes.Loan TotalLoan() throws java.sql.SQLException {
        StringBuilder sb = new StringBuilder(TOTAL_LOAN_AMOUNT);

        pace_Classes.Loan loan = new pace_Classes.Loan();
        try (java.sql.Statement statement = conn.createStatement();
             java.sql.ResultSet resultSet = statement.executeQuery(sb.toString())){

            while (resultSet.next()){
                loan.setLoan_Total(resultSet.getDouble(COLUMN_TRANSACTION_LOAN_AMOUNT));
            }
        }
        return loan;
    }
    public static pace_Classes.Loan loanForIndividual(pace_Classes.Credited_Accounts cred)throws java.sql.SQLException {
        StringBuilder sb = new StringBuilder(LOAN_FOR_INDIVIDUAL);
        sb.append(cred.getIdNumber());

        pace_Classes.Loan loan = new pace_Classes.Loan();

        try(java.sql.Statement statement = conn.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery(sb.toString())){
            while (resultSet.next()){
                loan.setLoan_amount(resultSet.getDouble(COLUMN_TRANSACTION_LOAN_AMOUNT));
            }
        }
        return loan;
    }
    public static pace_Classes.Loan setRateobj(int rate) throws java.sql.SQLException {
        StringBuilder sb = new StringBuilder(SET_RATE);
        sb.append(rate);
        sb.append(" limit 1;");


        try(java.sql.Statement statement = conn.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery(sb.toString())) {

            pace_Classes.Loan loan = resultSetLoan(resultSet);

            return loan;
        }catch (java.sql.SQLException e) {
            System.out.println(e);
            throw e;
        }


    }


    private static pace_Classes.Loan resultSetLoan(java.sql.ResultSet resultSet) throws java.sql.SQLException {
        pace_Classes.Loan loan = null;

        while (resultSet.next()){
            loan =  new pace_Classes.Loan();
            loan.setRate(resultSet.getFloat(1));
        }
        return loan;
    }

    public  static boolean updateRate(float rate){
        StringBuilder sb = new StringBuilder(UPDATE_RATE);
        sb.append(rate);
        sb.append(" WHERE COL_RATE = 1 LIMIT 1;");

        javafx.scene.control.Alert alert1 = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
        alert1.setContentText("You are about to update the Percentage rate to " + rate + " are you sure you want to update");

        java.util.Optional<javafx.scene.control.ButtonType> optional = alert1.showAndWait();

        if (optional.get() == javafx.scene.control.ButtonType.OK)
        {
            try(java.sql.Statement statement = conn.createStatement();) {
                statement.executeUpdate(sb.toString());

            }catch (java.sql.SQLException e) {
                javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
                alert.setContentText(String.valueOf(e));
                alert.show();
            }
        }else if (optional.get() == javafx.scene.control.ButtonType.CANCEL)
        {
            return false;
        }


        return true;
    }
    public static boolean insertLoanRecords(int idNumber, double amount, int month) {
        StringBuilder sb = new StringBuilder(LOAN_INSERT_RECORD);
        sb.append(idNumber);
        sb.append(", ");
        sb.append(amount);
        sb.append(", ");
        java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.util.Calendar cal = java.util.Calendar.getInstance();

        java.util.Calendar calNext = java.util.Calendar.getInstance();
        calNext.set(java.util.Calendar.YEAR, cal.get(java.util.Calendar.YEAR));
        calNext.set(java.util.Calendar.MONTH, cal.get(java.util.Calendar.MONTH));
        calNext.set(java.util.Calendar.DAY_OF_MONTH, cal.get(java.util.Calendar.DAY_OF_MONTH));


        //getting the date paid for the date automaticaly that is the current date
        String date = simpleDateFormat.format(cal.getTime());
        java.sql.Date loanDate = java.sql.Date.valueOf(date);
        sb.append("'"+loanDate+"'");//getting the current date
        sb.append(", ");
        String paidDate;
        paidDate = simpleDateFormat.format(calNext.getTime());
        for (int i = 0; i <= month ; i++) {
            paidDate = simpleDateFormat.format(calNext.getTime());
            calNext.add(java.util.Calendar.MONTH, 1);

        }
        java.sql.Date datepaid = java.sql.Date.valueOf(paidDate);
        sb.append("'"+datepaid+"'");
        sb.append(" );");

        try(java.sql.Statement statement = conn.createStatement();) {
            statement.executeUpdate(sb.toString());

        }catch (java.sql.SQLException e) {
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
            alert.setContentText(String.valueOf(e));
            alert.show();
        }


        return true;
    }


    public static javafx.collections.ObservableList<pace_Classes.Loan> searchLoanID(int idNumber) throws java.sql.SQLException, java.text.ParseException {
        open();
        StringBuilder sb = new StringBuilder(LOAN_TABLE_QUERY);
        sb.append(idNumber);
        sb.append(" and "+ COLUMN_LOAN_DATE+" > now() - interval 12 month  group by " +COLUMN_LOAN_AMOUNT+
                ", monthname("+COLUMN_LOAN_PAID_DATE+")  order by "+COLUMN_LOAN_PAID_DATE+", "+COLUMN_LOAN_ID_NUMBER+";");
        //Get ResultSet from dbExecuteQuery method
        try(java.sql.Statement statement = conn.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery(sb.toString())) {
            //Send ResultSet to the getEmployeeList method and get employee object
            javafx.collections.ObservableList<pace_Classes.Loan> empList = resultSetLoanDetails(resultSet);
            //return employee object
            return empList;
        }catch (java.sql.SQLException | java.text.ParseException e) {
            System.out.println(e);
            throw e;
        }
    }

    private static javafx.collections.ObservableList<pace_Classes.Loan> resultSetLoanDetails(java.sql.ResultSet resultSet) throws java.sql.SQLException, java.text.ParseException {
        //Declare a observable List which comprises of Employee objects
        javafx.collections.ObservableList<pace_Classes.Loan> list = javafx.collections.FXCollections.observableArrayList();
        while (resultSet.next()){
            //creating an object of Credited accounts
            pace_Classes.Loan loan = new pace_Classes.Loan();
            //capture the date that loan was requested
            loan.setLoanDate(resultSet.getDate(COLUMN_LOAN_DATE));
            //capture the date that is to be paid
            loan.setDatePaid(resultSet.getDate(COLUMN_LOAN_PAID_DATE));
            loan.setLoan_amount(resultSet.getDouble(COLUMN_LOAN_AMOUNT));
            loan.setLoanBalance(resultSet.getDouble(COLUMN_LOAN_BALANCE));
            loan.setLoan_Status(resultSet.getString(COLUMN_LOAN_STATUS));

            //add employee to the observable list
            list.add(loan);
        }
        return list;
    }
}
