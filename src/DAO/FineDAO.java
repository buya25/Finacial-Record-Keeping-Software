package DAO;

public class FineDAO extends LoanDAO{


    public static pace_Classes.Fine FineCredited(pace_Classes.Fine fine) throws java.sql.SQLException{
        StringBuilder sb = new StringBuilder(FINE_CREDITED);
        sb.append(" select "+fine.getIdNumber()+", "+fine.getCredited_amount()+", 0, 'PENDING', '"+fine.getCredit_date()+"' ");
        sb.append(" from "+DB_NAME+"."+TABLE_CREDITED+" where  "+COLUMN_CREDITED_ID_NUMBER+" = "+fine.getIdNumber()+" and ");
        sb.append(" if(EXISTS (SELECT * FROM "+DB_NAME+"."+TABLE_CREDITED+" ");
        sb.append(" where ID_NUMBER = "+fine.getIdNumber()+" ");
        sb.append("  and month("+COLUMN_CREDITED_DATE+") = "+ java.sql.Date.valueOf(fine.getCredit_date()).toLocalDate().getMonthValue()+" ");
        sb.append("  and year("+COLUMN_CREDITED_DATE+") = "+java.sql.Date.valueOf(fine.getCredit_date()).toLocalDate().getYear()+" ");
        sb.append(" and (month(now()) - "+java.sql.Date.valueOf(fine.getCredit_date()).toLocalDate().getMonthValue()+") > 0 ");
        sb.append(" and  (year(now()) - "+java.sql.Date.valueOf(fine.getCredit_date()).toLocalDate().getYear()+") >= 0), ");
        sb.append(" if(EXISTS(SELECT * FROM "+DB_NAME+"."+TABLE_CREDITED+" ");
        sb.append(" where ID_NUMBER = "+fine.getIdNumber()+" ");
        sb.append(" and "+COLUMN_CREDITED_STATUS+" = 'CREDITED' OR "+COLUMN_CREDITED_STATUS+" = 'OVERPAYED'),false, ");
        sb.append(" if(EXISTS(SELECT * FROM "+DB_NAME+"."+TABLE_FINE_CREDIT+" ");
        sb.append(" where year("+COLUMN_FINE_CREDIT_DATE+") = "+java.sql.Date.valueOf(fine.getCredit_date()).toLocalDate().getYear()+" ");
        sb.append(" and month("+COLUMN_FINE_CREDIT_DATE+") = "+java.sql.Date.valueOf(fine.getCredit_date()).toLocalDate().getMonthValue()+"),false,true)), ");
        sb.append(" if(EXISTS(SELECT * FROM "+DB_NAME+"."+TABLE_FINE_CREDIT+" ");
        sb.append(" where year("+COLUMN_FINE_CREDIT_DATE+") = "+java.sql.Date.valueOf(fine.getCredit_date()).toLocalDate().getYear()+" ");
        sb.append(" and month("+COLUMN_FINE_CREDIT_DATE+") = "+java.sql.Date.valueOf(fine.getCredit_date()).toLocalDate().getMonthValue()+"), ");
        sb.append(" false,if(EXISTS(SELECT * FROM "+DB_NAME+"."+TABLE_CREDITED+" where ");
        sb.append(" (month(now()) - "+java.sql.Date.valueOf(fine.getCredit_date()).toLocalDate().getMonthValue()+") > 0 ");
        sb.append("  and  (year(now()) - "+java.sql.Date.valueOf(fine.getCredit_date()).toLocalDate().getYear()+") >= 0),true,false))) limit 1;");

        try (java.sql.Statement statement = conn.createStatement();){
            System.out.println(sb.toString());
            statement.executeUpdate(sb.toString());
        }
        return fine;
    }
    //Get the total amount of fine in Loan
    public static pace_Classes.Fine TotalFineLoan(pace_Classes.Fine total) throws java.sql.SQLException{
        StringBuilder sb = new StringBuilder(TOTAL_LOAN_FINE);
        sb.append(total.getIdNumber());

        try (java.sql.Statement statement = conn.createStatement();
             java.sql.ResultSet resultSet = statement.executeQuery(sb.toString())){
            while (resultSet.next()){
                total.setFine_Total_amount(resultSet.getDouble(COLUMN_FINE_LOAN_AMOUNT));
            }
        }
        return total;
    }
    //Get the total amount of fine
    public static pace_Classes.Fine TotalFineCredit(pace_Classes.Fine total) throws java.sql.SQLException{
        StringBuilder sb = new StringBuilder(TOTAL_CREDITED_FINE);
        sb.append(total.getIdNumber());

        try (java.sql.Statement statement = conn.createStatement();
             java.sql.ResultSet resultSet = statement.executeQuery(sb.toString())){
            while (resultSet.next()){
                total.setFine_Total_amount(resultSet.getDouble(COLUMN_FINE_AMOUNT));
            }
        }
        return total;
    }
    //Here i am paying the Amount that has been fined
    public static pace_Classes.Fine PayingFineCredited(pace_Classes.Fine updateFine) throws java.sql.SQLException, java.text.ParseException {

        StringBuilder sb = new StringBuilder(UPDATE_FINE_CREDITED);
        sb.append(" `"+COLUMN_FINE_BALANCE+"` = IF(`"+COLUMN_FINE_AMOUNT+"` >= `"+COLUMN_FINE_BALANCE+"`, IF("+COLUMN_FINE_AMOUNT+" >= ("+COLUMN_FINE_BALANCE+" + "+updateFine.getCredited_amount()+"), (`"+COLUMN_FINE_BALANCE+"` + "+updateFine.getCredited_amount()+"), "+COLUMN_FINE_AMOUNT+") , `"+COLUMN_FINE_BALANCE+"`),");
        sb.append(" `STATUS` = IF(`"+COLUMN_FINE_AMOUNT+"` <= `"+COLUMN_FINE_BALANCE+"`, '"+COLUMN_FINE_AMOUNT+"', `"+COLUMN_FINE_STATUS+"`),");
        sb.append(" `"+COLUMN_FINE_CREDIT_PAIDDATE+"` = '"+ java.time.LocalDate.now() +"'");
        sb.append(" WHERE `"+COLUMN_FINE_ID_NUMBER+"` = "+updateFine.getIdNumber()+" AND `"+COLUMN_FINE_STATUS+"` = 'PENDING'");
        sb.append(" order by "+COLUMN_FINE_CREDIT_DATE+" asc LIMIT 1;");

        double balance;
        //get the amount fined
        pace_Classes.Fine fineAmount = FineCreditedAmount(updateFine);

        if (fineAmount.getFine_Credited() != null){


            try(java.sql.Statement statement = conn.createStatement();){
                statement.executeUpdate(sb.toString());
            }
            //CHECK IF THE AMOUNT GIVEN IS MORE THAN THE ON IN THE LAST FINE GIVEN
            balance  = updateFine.getCredited_amount() - fineAmount.getFine_Credited();
            if (balance > 0) {
                updateFine.setCredited_amount(balance);
                PayingFineCredited(updateFine);
            }

        }else {
            DAO.Credited_AccountsDAO credit = new Credited_AccountsDAO();
            credit.insertCreditedAccount(updateFine.getIdNumber(), updateFine.getCredited_amount(), java.time.LocalDate.now());
        }


        return updateFine;
    }
    //Here i am paying the Amount that has been fined
    public static pace_Classes.Fine PayingFineLoan(pace_Classes.Fine updateFine) throws java.sql.SQLException, java.text.ParseException {

        StringBuilder sb = new StringBuilder(UPDATE_FINE_LOAN);
        sb.append(" `"+COLUMN_FINE_LOAN_BALANCE+"` = IF(`"+COLUMN_FINE_LOAN_AMOUNT+"` >= `"+COLUMN_FINE_LOAN_BALANCE+"`, IF("+COLUMN_FINE_LOAN_AMOUNT+" >= ("+COLUMN_FINE_LOAN_BALANCE+" + "+updateFine.getCredited_amount()+"), (`"+COLUMN_FINE_LOAN_BALANCE+"` + "+updateFine.getCredited_amount()+"), "+COLUMN_FINE_LOAN_AMOUNT+") , `"+COLUMN_FINE_LOAN_BALANCE+"`),");
        sb.append(" `"+COLUMN_FINE_LOAN_STATUS+"` = IF(`"+COLUMN_FINE_LOAN_AMOUNT+"` <= `"+COLUMN_FINE_LOAN_BALANCE+"`, '"+COLUMN_FINE_LOAN_AMOUNT+"', `"+COLUMN_FINE_LOAN_STATUS+"`),");
        sb.append(" `"+COLUMN_FINE_LOAN_PAID_DATE+"` = '"+ java.time.LocalDate.now() +"'");
        sb.append(" WHERE `"+COLUMN_FINE_LOAN_ID_NUMBER+"` = "+updateFine.getIdNumber()+" AND `"+COLUMN_FINE_LOAN_STATUS+"` = 'PENDING'");
        sb.append(" order by "+COLUMN_FINE_LOAN_DATE+" asc LIMIT 1;");

        double balance;
        //get the amount fined
        pace_Classes.Fine fineAmount = FineLoanAmount(updateFine);
        if (fineAmount.getFine_Credited() != null){


            try(java.sql.Statement statement = conn.createStatement();){
                statement.executeUpdate(sb.toString());
            }
            //CHECK IF THE AMOUNT GIVEN IS MORE THAN THE ON IN THE LAST FINE GIVEN
            balance  = updateFine.getCredited_amount() - fineAmount.getFine_Credited();
            if (balance > 0) {
                updateFine.setCredited_amount(balance);
                PayingFineLoan(updateFine);
            }

        }else {
            DAO.Credited_AccountsDAO credit = new Credited_AccountsDAO();
            credit.insertCreditedAccount(updateFine.getIdNumber(), updateFine.getCredited_amount(), java.time.LocalDate.now());
        }


        return updateFine;
    }
    //Here i am trying to get the amount fined and the setting it to the variable
    public static pace_Classes.Fine FineCreditedAmount(pace_Classes.Fine cred) throws java.sql.SQLException {
        StringBuilder sb = new StringBuilder(SELECT_FINE_CREDITED);
        sb.append(cred.getIdNumber());
        sb.append(" order by "+COLUMN_FINE_CREDIT_DATE+" asc limit 1;");

        try(java.sql.Statement statement = conn.createStatement();
            java.sql.ResultSet result = statement.executeQuery(sb.toString())){
            while (result.next()){
                cred.setFine_Credited(result.getDouble(COLUMN_FINE_AMOUNT));
                cred.setFine_Credited_balance(result.getDouble(COLUMN_FINE_BALANCE));
            }
        }
        return cred;
    }
    //Here i am trying to get the amount fined and the setting it to the variable
    public static pace_Classes.Fine FineLoanAmount(pace_Classes.Fine cred) throws java.sql.SQLException {
        StringBuilder sb = new StringBuilder(SELECT_FINE_LOAN_PENDING);
        sb.append(cred.getIdNumber());
        sb.append(" order by "+COLUMN_FINE_LOAN_DATE+" asc limit 1;");

        try(java.sql.Statement statement = conn.createStatement();
            java.sql.ResultSet result = statement.executeQuery(sb.toString())){
            while (result.next()){
                cred.setFine_Credited(result.getDouble(COLUMN_FINE_LOAN_AMOUNT));
                cred.setFine_Credited_balance(result.getDouble(COLUMN_FINE_LOAN_BALANCE));
            }
        }
        return cred;
    }
    public static pace_Classes.Fine IndividualCreditedFine(pace_Classes.Credited_Accounts credit) throws java.sql.SQLException{
        StringBuilder sb = new StringBuilder(CURRENT_CREDITED_FINE);
        sb.append(credit.getIdNumber());

        pace_Classes.Fine fine = new pace_Classes.Fine();

        try(java.sql.Statement statement = conn.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery(sb.toString())){
            while (resultSet.next()){
                fine.setFine_Credited(resultSet.getDouble(COLUMN_TRANSACTION_FINE_CREDITED_AMOUNT));
            }
        }
        return fine;
    }
    public static pace_Classes.Fine CreditedFine() throws java.sql.SQLException {
        StringBuilder sb = new StringBuilder(FINE_CREDITED_ACCOUNT);

        pace_Classes.Fine fine = new pace_Classes.Fine();

        try (java.sql.Statement statement = conn.createStatement();
             java.sql.ResultSet resultSet = statement.executeQuery(sb.toString())){
            while (resultSet.next()){
                fine.setFine_Total_amount(resultSet.getDouble(COLUMN_TRANSACTION_FINE_CREDITED_AMOUNT));
            }
        }
        return fine;
    }
    public static pace_Classes.Fine individualFine(pace_Classes.Credited_Accounts credit) throws java.sql.SQLException {
        StringBuilder sb = new StringBuilder(INDIVIDUAL_FINE_LOAN);
        sb.append(credit.getIdNumber());

        pace_Classes.Fine fine = new pace_Classes.Fine();
        try (java.sql.Statement statement = conn.createStatement();
             java.sql.ResultSet resultSet = statement.executeQuery(sb.toString())){
            while (resultSet.next()){
                fine.setFine_Loan(resultSet.getDouble(COLUMN_TRANSACTION_FINE_AMOUNT));
            }
        }
        return fine;
    }
    public static pace_Classes.Fine TotalFineLoan() throws java.sql.SQLException{
        StringBuilder sb = new StringBuilder(GET_FINE_LOAN_RECORDS);
        pace_Classes.Fine fine = new pace_Classes.Fine();
        try (java.sql.Statement statement = conn.createStatement();
             java.sql.ResultSet resultSet = statement.executeQuery(sb.toString())){

            while (resultSet.next()){
                fine.setFine_Total_amount(resultSet.getDouble(COLUMN_TRANSACTION_FINE_AMOUNT));
            }
        }
        return fine;
    }
    public static void insertFineLoanRecords(int idNumber, double loan_amount, double loan_balance, java.sql.Date loan_date){
        StringBuilder sb = new StringBuilder(INSERT_FINE_LOAN_RECORDS);
        sb.append(idNumber);
        sb.append(", ");
        sb.append(loan_amount);
        sb.append(", ");
        sb.append(loan_balance);
        sb.append(", ");
        sb.append(loan_date);
        sb.append(" );");

        try (java.sql.Statement statement = conn.createStatement();){
            statement.executeUpdate(sb.toString());

        } catch (java.sql.SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void insertFineCreditRecords(int idNumber, double loan_amount, double loan_balance, java.sql.Date loan_date){
        StringBuilder sb = new StringBuilder(INSERT_FINE_CREDIT_RECORDS);
        sb.append(idNumber);
        sb.append(", ");
        sb.append(loan_amount);
        sb.append(", ");
        sb.append(loan_balance);
        sb.append(", ");
        sb.append(loan_date);
        sb.append(" );");

        try (java.sql.Statement statement = conn.createStatement();){
            statement.executeUpdate(sb.toString());

        } catch (java.sql.SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static pace_Classes.Fine searchIDnumber(int idNumber) throws java.sql.SQLException, ClassNotFoundException {
        //looping through the records of the credited accounts
        StringBuilder sb = new StringBuilder(GET_PENDING_RECORDS_CREDITED_ACCOUNTS);
        sb.append(idNumber);
        sb.append("\"");

        //Get ResultSet from dbExecuteQuery method
        try(java.sql.Statement statement = conn.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery(sb.toString())) {

            pace_Classes.Fine accounts = getAllDetails(resultSet);

            return accounts;

        }catch (java.sql.SQLException e) {
            System.out.println(e);
            throw e;
        }

    }
    private static pace_Classes.Fine getAllDetails(java.sql.ResultSet rs) throws java.sql.SQLException {
        pace_Classes.Fine accounts = null;

        if (rs.next()){
            accounts = new pace_Classes.Fine();
            accounts.setCredited_amount(rs.getDouble(COLUMN_CREDITED_AMOUNT));
            accounts.setBalance(rs.getDouble(COLUMN_CREDITED_BALANCE));
            accounts.setStatus(rs.getString(COLUMN_CREDITED_STATUS));
            accounts.setDate(rs.getDate(COLUMN_CREDITED_DATE));
        }else {
            accounts = new pace_Classes.Fine();
            accounts.setCredited_amount(null);
            accounts.setBalance(null);
            accounts.setStatus(null);
            accounts.setDate(null);
        }
        return accounts;
    }

    public static pace_Classes.Fine searchIDLoan(int idNumber) throws java.sql.SQLException, ClassNotFoundException {
        //looping through the records of the credited accounts
        StringBuilder sb = new StringBuilder(GET_RECORDS_PENDING_LOAN);
        sb.append(idNumber);
        sb.append("\"");
        open();

        //Get ResultSet from dbExecuteQuery method
        try(java.sql.Statement statement = conn.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery(sb.toString())) {

            pace_Classes.Fine accounts = getLoanDetails(resultSet);

            return accounts;

        }catch (java.sql.SQLException e) {
            System.out.println(e);
            throw e;
        }

    }

    private static pace_Classes.Fine getLoanDetails(java.sql.ResultSet resultSet) throws java.sql.SQLException {
        pace_Classes.Fine accounts = null;

        if (resultSet.next()){
            accounts = new pace_Classes.Fine();
            accounts.setLoan_amount(resultSet.getDouble(COLUMN_LOAN_AMOUNT));
            accounts.setLoanBalance(resultSet.getDouble(COLUMN_LOAN_BALANCE));
            accounts.setLoan_Status(resultSet.getString(COLUMN_LOAN_STATUS));
            accounts.setLoanDate(resultSet.getDate(COLUMN_LOAN_DATE));
            accounts.setDatePaid(resultSet.getDate(COLUMN_LOAN_PAID_DATE));

        }else {
            accounts = new pace_Classes.Fine();
            accounts.setLoan_amount(null);
            accounts.setLoan_Status(null);
            accounts.setLoanDate(null);
            accounts.setDatePaid(null);
        }
        return accounts;
    }
    public static pace_Classes.Fine searchIDFineMonthly(int idNumber) throws java.sql.SQLException, ClassNotFoundException {
        //looping through the records of the credited accounts
        StringBuilder sb = new StringBuilder(GET_FINE_MONTHLY_RECORDS);
        sb.append(idNumber);
        open();

        //Get ResultSet from dbExecuteQuery method
        try(java.sql.Statement statement = conn.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery(sb.toString())) {

            pace_Classes.Fine accounts = getMonthlyFine(resultSet);

            return accounts;

        }catch (java.sql.SQLException e) {
            System.out.println(e);
            throw e;
        }

    }

    private static pace_Classes.Fine getMonthlyFine(java.sql.ResultSet resultSet) throws java.sql.SQLException {
        pace_Classes.Fine accounts = null;

        if (resultSet.next()){
            accounts = new pace_Classes.Fine();
            accounts.setFine_Credited(resultSet.getDouble(COLUMN_FINE_AMOUNT));
            accounts.setFine_Credited_balance(resultSet.getDouble(COLUMN_FINE_BALANCE));
            accounts.setFine_Credited_Status(resultSet.getString(COLUMN_FINE_STATUS));
            accounts.setFine_credited_date(resultSet.getDate(COLUMN_FINE_CREDIT_DATE));

        }else {
            accounts = new pace_Classes.Fine();
            accounts.setFine_Credited(null);
            accounts.setFine_Credited_balance(null);
            accounts.setFine_Credited_Status(null);
            accounts.setFine_credited_date(null);
        }
        return accounts;
    }

    public static pace_Classes.Fine searchIDFineLoan(int idNumber) throws java.sql.SQLException {
        //looping through the records of the credited accounts
        StringBuilder sb = new StringBuilder(GET_LOAN_FINE_RECORDS);
        sb.append(idNumber);
        open();

        //Get ResultSet from dbExecuteQuery method
        try(java.sql.Statement statement = conn.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery(sb.toString())) {

            pace_Classes.Fine accounts = getLoanFine(resultSet);

            return accounts;

        }catch (java.sql.SQLException e) {
            System.out.println(e);
            throw e;
        }
    }

    private static pace_Classes.Fine getLoanFine(java.sql.ResultSet resultSet) throws java.sql.SQLException {
        pace_Classes.Fine accounts = null;

        if (resultSet.next()){
            accounts = new pace_Classes.Fine();
            accounts.setFine_Loan(resultSet.getDouble(COLUMN_FINE_LOAN_AMOUNT));
            accounts.setFine_Loan_balance(resultSet.getDouble(COLUMN_FINE_LOAN_BALANCE));
            accounts.setFine_Loan_Status(resultSet.getString(COLUMN_FINE_STATUS));
            accounts.setFine_Loan_date(resultSet.getDate(COLUMN_FINE_LOAN_DATE));

        }else {
            accounts = new pace_Classes.Fine();
            accounts.setFine_Loan(null);
            accounts.setFine_Loan_balance(null);
            accounts.setFine_Loan_Status(null);
            accounts.setFine_Loan_date(null);
        }
        return accounts;
    }
}
