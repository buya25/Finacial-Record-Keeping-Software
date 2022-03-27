package DAO;

public class Credited_AccountsDAO extends DAO.AccountsDAO {


    //*******************************
    //USE THE SQL STRING STRUCTURE TO ACCESS THE DATABASE
    //*******************************
    //Now i want to get the amount, date, balance and status then update it
    public static pace_Classes.Credited_Accounts UpdatePendingTransaction(pace_Classes.Credited_Accounts credit) throws java.sql.SQLException{

        //calling the object to loop through the amount and check the status
         CheckAmountOverpayment(credit);

        StringBuilder sb = new StringBuilder(UPDATE_PENDING_TRANSACTION);
        sb.append("`"+COLUMN_CREDITED_AMOUNT+"` = `"+COLUMN_CREDITED_BALANCE+"` + "+credit.getCredited_amount()+", ");
        sb.append("`"+COLUMN_CREDITED_BALANCE+"` = "+credit.getBalance()+", ");
        sb.append("`"+COLUMN_CREDITED_STATUS+"` = '"+credit.getStatus()+"', ");
        sb.append("`"+COLUMN_CREDITED_DATE+"` = '"+ java.time.LocalDate.now() +"'");
        sb.append("WHERE `"+COLUMN_CREDITED_STATUS+"` = 'PENDING' AND "+COLUMN_CREDITED_ID_NUMBER+" = "+credit.getIdNumber()+" LIMIT 1;");

        try(java.sql.Statement statement = conn.createStatement();){
            statement.executeUpdate(sb.toString());
        }

        return credit;
    }
    //here am trying to add the pending amount plus the constant amount where and its all wrong try to check
    public static pace_Classes.Credited_Accounts CheckAmountOverpayment(pace_Classes.Credited_Accounts credit) throws java.sql.SQLException {
        pace_Classes.Credited_Accounts constantAmount = selectConstantAmount();
        pace_Classes.Credited_Accounts pendingAmount =  PendingStatus(credit.getIdNumber());

        String status = null;
        double balance = 0;
        double answer;


        //trying to add the actual amount plus the initial amount
        answer  = pendingAmount.getCredited_amount() + credit.getCredited_amount();

        if (answer == constantAmount.getContant_amount()){
            status = "CREDITED";
            credit.setStatus(status);
            credit.setBalance(balance);
        }else if (answer < constantAmount.getContant_amount()){
            status = "PENDING";
            balance = constantAmount.getContant_amount() - answer;
            credit.setBalance(balance);
            credit.setStatus(status);
        }else if (answer > constantAmount.getContant_amount())
        {
            status = "OVERPAYED";
            credit.setStatus(status);
            credit.setBalance(balance);
        }

        return credit;
    }
    public static pace_Classes.Credited_Accounts PendingStatus(int idNumber) throws java.sql.SQLException {
        StringBuilder sb1 = new StringBuilder(CHECK_PENDING_ACCOUNTS);
        sb1.append(idNumber);
        sb1.append("\"");

        pace_Classes.Credited_Accounts accounts =  new pace_Classes.Credited_Accounts();

        try (java.sql.Statement statement = conn.createStatement();
             java.sql.ResultSet resultSet = statement.executeQuery(sb1.toString())){

            while (resultSet.next()){
                accounts.setCredited_amount(resultSet.getDouble(COLUMN_CREDITED_BALANCE));
            }

        }catch (java.sql.SQLException e){
            throw e;
        }
        return accounts;
    }
    public static pace_Classes.Credited_Accounts CurrentTotalAmount()throws java.sql.SQLException{
        StringBuilder sb = new StringBuilder(CURRENT_MONTH_TOTAL_AMOUNT);
        pace_Classes.Credited_Accounts credit = new pace_Classes.Credited_Accounts();

        try (java.sql.Statement statement = conn.createStatement();
            java.sql.ResultSet resultset = statement.executeQuery(sb.toString())){

            while (resultset.next()){
                credit.setTotalAmount(resultset.getDouble(COLUMN_CREDITED_AMOUNT));
            }

        }
        return credit;
    }
    public static pace_Classes.Credited_Accounts AddToAccount(pace_Classes.Credited_Accounts accounts)throws java.sql.SQLException {
        StringBuilder sb = new StringBuilder(ADD_AMOUNT_TO_ACCOUNT);
        sb.append(accounts.getCredited_amount());
        sb.append(" WHERE "+COLUMN_TRANSACTION_ID_NUMBER+" = "+accounts.getIdNumber()+" ;");

        try (java.sql.Statement statement = conn.createStatement()){
            statement.executeUpdate(sb.toString());
        }
        return accounts;
    }
    public static pace_Classes.Credited_Accounts currentMonthContribution(pace_Classes.Credited_Accounts accounts) throws java.sql.SQLException {
        StringBuilder sb = new StringBuilder(CURRENT_MONTH_CONTRIBUTION);
        sb.append(accounts.getIdNumber());
        try(java.sql.Statement statement = conn.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery(sb.toString())){
            while (resultSet.next()){
                accounts.setCredited_amount(resultSet.getDouble(COLUMN_CREDITED_AMOUNT));
            }
        }
        return accounts;
    }
    public static pace_Classes.Credited_Accounts individual_Total_amount(pace_Classes.Credited_Accounts cred)throws java.sql.SQLException {
        StringBuilder sb = new StringBuilder(INDIVIDUAL_TOTAL_AMOUNT);
        sb.append(cred.getIdNumber());
        try (java.sql.Statement statement = conn.createStatement();
             java.sql.ResultSet resultSet = statement.executeQuery(sb.toString())){
            while (resultSet.next()){
                cred.setIndividualtotalAmount(resultSet.getDouble(COLUMN_TRANSACTION_AMOUNT));
            }
        }
        return cred;
    }
    public static pace_Classes.Credited_Accounts selectConstantAmount() throws java.sql.SQLException {
        StringBuilder sb = new StringBuilder(SET_CONSTANT_AMOUNT);
        //sb.append(rate);
        sb.append(" limit 1;");

        try(java.sql.Statement statement = conn.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery(sb.toString())) {

            pace_Classes.Credited_Accounts loan = resultsetConAmount(resultSet);

            return loan;
        }catch (java.sql.SQLException e) {
            System.out.println(e);
            throw e;
        }


    }
    private static pace_Classes.Credited_Accounts resultsetConAmount(java.sql.ResultSet resultSet) throws java.sql.SQLException {
        pace_Classes.Credited_Accounts credit = null;

        while (resultSet.next()){
            credit =  new pace_Classes.Credited_Accounts();
            credit.setContant_amount(resultSet.getDouble(COLUMN_CONSTANT_AMOUNT));
        }
        return credit;
    }
    public static javafx.collections.ObservableList<javafx.scene.chart.XYChart.Data<String, Double>> loadBarchat() throws java.sql.SQLException {
        javafx.collections.ObservableList<javafx.scene.chart.XYChart.Data<String, Double>> data =
                javafx.collections.FXCollections.observableArrayList();

        StringBuilder sb = new StringBuilder(MONTHLY_GRAPH);
        try(java.sql.Statement statement = conn.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery(sb.toString())) {

            while (resultSet.next()){
                data.add(new javafx.scene.chart.XYChart.Data<>(resultSet.getString("monthname("+COLUMN_GRAPHMONTH_DATE+")"), resultSet.getDouble(COLUMN_GRAPHMONTH_AMOUNT)));
            }

        }catch (java.sql.SQLException e) {
            System.out.println(e);
            throw e;
        }
        return data;
    }
    public static pace_Classes.Credited_Accounts totalAmountContributed() throws java.sql.SQLException {
        StringBuilder sb = new StringBuilder(GET_TOTAL_AMOUNT_CONTRIBUTED);
        //Get ResultSet from dbExecuteQuery method
        pace_Classes.Credited_Accounts accounts = new pace_Classes.Credited_Accounts();
        try(java.sql.Statement statement = conn.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery(sb.toString())) {

            while (resultSet.next()){
                accounts.setTotalAmount(resultSet.getDouble(COLUMN_TRANSACTION_AMOUNT));
            }

        }catch (java.sql.SQLException e) {
            System.out.println(e);
            throw e;
        }
        return accounts;
    }
    public static pace_Classes.Accounts searchDetails(int idNumber) throws java.sql.SQLException, ClassNotFoundException {
        StringBuilder sb = new StringBuilder(SELECT_DETAILS);
        sb.append(idNumber);
        sb.append("\"");
        open();

        //Get ResultSet from dbExecuteQuery method
        try(java.sql.Statement statement = conn.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery(sb.toString())) {

            pace_Classes.Accounts accounts = getEmployeeDetails(resultSet);

            return accounts;

        }catch (java.sql.SQLException e) {
            System.out.println(e);
            throw e;
        }

    }
    private static pace_Classes.Accounts getEmployeeDetails(java.sql.ResultSet rs) throws java.sql.SQLException {
        pace_Classes.Accounts accounts = null;

        if (rs.next()){
            accounts = new pace_Classes.Accounts();
            accounts.setSirName(rs.getString(COLUMN_ACCOUNTS_SIRNAME));
            accounts.setMiddleName(rs.getString(COLUMN_ACCOUNTS_MIDDLENAME));
            accounts.setLastName(rs.getString(COLUMN_ACCOUNTS_LASTNAME));
        }
        return accounts;
    }
    private static void displaystatus(int idNumber, double amount, double defaultAmount, String date) throws java.sql.SQLException, java.text.ParseException {

        String status = null;
        double balance = 0;
        if (amount >= defaultAmount) {
            status = "CREDITED";
            balance = 0;

            //insert query
        } else if (amount < defaultAmount && defaultAmount > 0) {
            status = "PENDING";
            //insert query
            balance = amount - defaultAmount;
        }
        insertDatabase(idNumber, defaultAmount, balance, status, date);

    }
    public static void calculateMonthlyDate(int idNumber, int month, double amount, double defaultAmount, java.time.LocalDate pickdate)
            throws java.sql.SQLException, java.text.ParseException {
        java.time.LocalDate localDate = java.time.LocalDate.of(pickdate.getYear(),
                                                        pickdate.getMonthValue(),
                                                                pickdate.getDayOfMonth());

        java.time.LocalDate checkdate = java.time.LocalDate.now();

        if (localDate.getYear() == checkdate.getYear()  && localDate.getYear() < checkdate.getYear()){
            if (localDate.getMonthValue() < checkdate.getMonthValue()){
                fineCalculation(localDate, amount);
            }

        }else {
            //java.text.SimpleDateFormat getDate = pickdate.
            java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
            java.util.Calendar cal = java.util.Calendar.getInstance();
            cal.clear();
            //assumming start of day
            cal.set(
                    pickdate.getYear(),
                    pickdate.getMonthValue()-1,
                    pickdate.getDayOfMonth()
            );

            java.util.Calendar calNext = java.util.Calendar.getInstance();
            calNext.set(java.util.Calendar.YEAR, cal.get(java.util.Calendar.YEAR));
            calNext.set(java.util.Calendar.MONTH, cal.get(java.util.Calendar.MONTH));
            calNext.set(java.util.Calendar.DAY_OF_MONTH, cal.get(java.util.Calendar.DAY_OF_MONTH));


            String date;
            date = simpleDateFormat.format(calNext.getTime());
            totalMonthlyDeposit(amount, date);
            for (int i = 0; i < month ; i++) {
                date = simpleDateFormat.format(calNext.getTime());
                //give out the fine before inserting the details
                FineGivenPerson(idNumber ,amount, date);
                displaystatus(idNumber ,amount, defaultAmount, date);
                calNext.set(java.util.Calendar.DAY_OF_MONTH, 1);
                calNext.add(java.util.Calendar.MONTH, 1);
                amount = amount - defaultAmount;

            }
        }

    }

    private static void FineGivenPerson(int idNumber, double amount, String date) throws java.sql.SQLException {
        pace_Classes.Fine fine = new pace_Classes.Fine();
        fine.setIdNumber(idNumber);
        fine.setCredited_amount(amount);
        fine.setCredit_date(date);
        DAO.FineDAO.FineCredited(fine);
    }

    public static void insertCreditedAccount(int idNumber, double amount, java.time.LocalDate date) throws java.sql.SQLException, java.text.ParseException {
        pace_Classes.Credited_Accounts credit = selectConstantAmount();
        double remaider;
        int answer;
        answer = (int) (amount / credit.getContant_amount());

        remaider = amount % credit.getContant_amount();

        if (remaider > 0){
            answer = answer + 1;
            calculateMonthlyDate(idNumber, answer, amount, credit.getContant_amount(), date);
        }else {
            calculateMonthlyDate(idNumber, answer, amount, credit.getContant_amount(), date);
        }

    }
    public static void insertDatabase(int idNumber, double amount, double balance, String status, String date) throws java.sql.SQLException, java.text.ParseException {
        StringBuilder sb = new StringBuilder(INSERT_QUERY_INTO_CREDITED_ACCOUNT);
        sb.append(idNumber);
        sb.append(", ");
        sb.append(amount);
        sb.append(", ");
        sb.append(balance);
        sb.append(", ");
        sb.append("'"+status+"'");
        sb.append(", ");
        //changing the string to dateFormat
        java.sql.Date date1 = java.sql.Date.valueOf(date);
        sb.append("'"+date1+"'");
        sb.append(" );");
        //checking if there is a pending transaction
          boolean check = CheckPendingAccount(idNumber);


        try(java.sql.Statement statement = conn.createStatement();) {
            if (check){
                statement.executeUpdate(sb.toString());
            }

        }catch (java.sql.SQLException e) {
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
            alert.setContentText(String.valueOf(e));
            alert.show();
        }



    }
    public static Boolean CheckPendingAccount(int idNumber) throws java.sql.SQLException{

        StringBuilder sb1 = new StringBuilder(CHECK_PENDING_ACCOUNTS);
        sb1.append(idNumber);
        sb1.append("\"");

        try (java.sql.Statement statement = conn.createStatement();
             java.sql.ResultSet resultSet = statement.executeQuery(sb1.toString())){

            if (resultSet.next()){
                javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
                alert.setContentText("ID: " + idNumber + " has pending transaction kindly top up to credit your account");
                alert.show();

                return false;
            }
        }catch (java.sql.SQLException e){
            throw e;
        }
        return true;
    }
    public static void insertOverpayement(int idNumber, double amount, java.time.LocalDate date) throws java.sql.SQLException, java.text.ParseException {
        final Double balance = 0.0;
        final String status = "OVERPAYED";
        pace_Classes.Credited_Accounts credit = selectConstantAmount();
        if (credit.getContant_amount() > amount){
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
            alert.setContentText("Sorry amount Should be more than " + credit.getContant_amount() + " \n" +
                            " if not, you have to select the checkbox");
            alert.show();
        }else {
            StringBuilder sb = new StringBuilder(CREDIT_SINGLE_MONTH);
            sb.append(idNumber);
            sb.append(", ");
            sb.append(amount);
            sb.append(", ");
            sb.append(balance);
            sb.append(", ");
            sb.append("'"+status+"'");
            sb.append(", ");
            //changing the string to dateFormat
            java.sql.Date date1 = java.sql.Date.valueOf(date);
            sb.append("'"+date1+"'");
            sb.append(" );");
            //checking if there is a pending transaction
            StringBuilder sb1 = new StringBuilder(CHECK_PENDING_ACCOUNTS);
            sb1.append(idNumber);
            sb1.append("\"");

            try(java.sql.Statement statement = conn.createStatement();
                java.sql.ResultSet resultSet = statement.executeQuery(sb1.toString())) {

                boolean check = CheckPendingAccount(idNumber);

                if (check){
                    //changing the date to string so that i can be able to pass it
                    java.text.SimpleDateFormat simpleDateFormat =  new java.text.SimpleDateFormat("yyyy-MM-dd");
                    String setDate = simpleDateFormat.format(date1);
                    totalMonthlyDeposit(amount,  setDate);
                    //fining the amount if the person did not pay
                    FineGivenPerson(idNumber ,amount, setDate);
                    //then quickly insert the reoords for overpayment
                    statement.executeUpdate(sb.toString());

                }

            }catch (java.sql.SQLException e) {
                javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
                alert.setContentText(String.valueOf(e));
                alert.show();
            }

        }
    }
    public static void totalMonthlyDeposit(Double amount, String amtdate) throws java.sql.SQLException {
        StringBuilder sb = new StringBuilder(MONTHLY_TOTAL_DEPOSIT);
        sb.append(amount);
        sb.append(", ");
        //changing the string to dateFormat
        java.sql.Date date1 = java.sql.Date.valueOf(amtdate);
        sb.append("'"+date1+"'");
        sb.append(" );");

        try(java.sql.Statement statement = conn.createStatement();) {
            statement.executeUpdate(sb.toString());


        }catch (java.sql.SQLException e) {
            System.out.println(e);
            throw e;
        }
    }
    public static javafx.collections.ObservableList<pace_Classes.Credited_Accounts> searchEmployees() throws java.sql.SQLException {
        open();
        StringBuilder sb = new StringBuilder(TABLE_QUERY);
        //Get ResultSet from dbExecuteQuery method
        try(java.sql.Statement statement = conn.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery(sb.toString())) {
            //Send ResultSet to the getEmployeeList method and get employee object
            javafx.collections.ObservableList<pace_Classes.Credited_Accounts> empList = getDataUser(resultSet);
            //return employee object
            return empList;
        }catch (java.sql.SQLException e) {
            System.out.println(e);
            throw e;
        }
    }
    public static javafx.collections.ObservableList<pace_Classes.Credited_Accounts> getDataUser(java.sql.ResultSet resultSet) throws java.sql.SQLException {
        //Declare a observable List which comprises of Employee objects
        javafx.collections.ObservableList<pace_Classes.Credited_Accounts> list = javafx.collections.FXCollections.observableArrayList();
        while (resultSet.next()){
            //creating an object of Credited accounts
            pace_Classes.Credited_Accounts acc = new pace_Classes.Credited_Accounts();

            acc.setMiddleName(resultSet.getString(COLUMN_ACCOUNTS_MIDDLENAME));
            acc.setLastName(resultSet.getString(COLUMN_ACCOUNTS_LASTNAME));
            acc.setIdNumber(resultSet.getInt(COLUMN_ACCOUNTS_IDNUMBER));
            acc.setCredited_amount(resultSet.getDouble(COLUMN_CREDITED_AMOUNT));
            acc.setBalance(resultSet.getDouble(COLUMN_CREDITED_BALANCE));
            acc.setStatus(resultSet.getString(COLUMN_CREDITED_STATUS));
            acc.setDate(resultSet.getDate(COLUMN_CREDITED_DATE));

            //add employee to the observable list
            list.add(acc);
        }
        return list;
    }
    public static void fineCalculation(java.time.LocalDate dateset, double amountset){
        java.time.LocalDate localDate = java.time.LocalDate.of(2021, 8, 2);


        java.time.LocalDate bday = java.time.LocalDate.of(
                localDate.getYear(),
                localDate.getMonthValue(),
                localDate.getDayOfMonth());
        java.time.LocalDate today = java.time.LocalDate.now();


        System.out.println(bday);


        if (bday.getYear() == today.getYear()  || bday.getYear() < today.getYear()) {
            if (bday.getMonthValue() < today.getMonthValue()) {
                int add = -1;
                for (java.time.LocalDate checkdate = bday; checkdate.isBefore(today); checkdate = checkdate.plusMonths(1)) {
                    //System.out.println(checkdate);
                    add++;

                }
                double fineset = 100;
                for (int i = 0; i < add; i++) {
                    fineset = getFineForMonthlyContribution(amountset, fineset);
                }
                System.out.println(fineset);
                System.out.println("Total Months = "+add);
            }
        }
    }
    public static double getFineForMonthlyContribution(double amount, double fine){
        fine = amount + fine;
        return fine;
    }
    public  static boolean updateContant(pace_Classes.Credited_Accounts credit){
        StringBuilder sb = new StringBuilder(UPDATE_CONSTANT_AMOUNT);
        sb.append(credit.getContant_amount());
        sb.append(" WHERE col_conAMOUNT = 1 LIMIT 1;");

        javafx.scene.control.Alert alert1 = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
        alert1.setContentText("You are about to update the Percentage rate to " + credit.getContant_amount() + " are you sure you want to update");

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
    public static pace_Classes.Fine FinefromDatabase() throws java.sql.SQLException {
        StringBuilder sb = new StringBuilder(GET_CONSTANT_FINE);

        try (java.sql.Statement statement = conn.createStatement();
             java.sql.ResultSet resultSet = statement.executeQuery(sb.toString())){

            pace_Classes.Fine fine = resultSetFine(resultSet);

            return fine;

        } catch (java.sql.SQLException e ) {
            System.out.println(e);
            throw e;
        }
    }

    private static pace_Classes.Fine resultSetFine(java.sql.ResultSet resultSet) throws java.sql.SQLException {
        pace_Classes.Fine fine = null;

        while (resultSet.next()){
            fine =  new pace_Classes.Fine();
            fine.setFine_constant(resultSet.getDouble(1));
        }
        return fine;
    }

    public  static boolean updateFine(Double fine_constant){
        StringBuilder sb = new StringBuilder(UPDATE_FINE_CONSTANT);
        sb.append(fine_constant);
        sb.append(" WHERE COL_RATE = 1 LIMIT 1;");

        javafx.scene.control.Alert alert1 = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
        alert1.setContentText("You are about to update the Constant Fine to " + fine_constant + "; Are you sure you want to update");

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
    public static javafx.collections.ObservableList<pace_Classes.Credited_Accounts> searchIndividualID(int idNumber) throws java.sql.SQLException {
        open();
        StringBuilder sb = new StringBuilder(MONTHLY_RECORDS);
        sb.append(idNumber);
        sb.append(" and   DATE > now() - interval 12 month  group by month(DATE)  order by DATE, ID_NUMBER;");
        //Get ResultSet from dbExecuteQuery method
        try(java.sql.Statement statement = conn.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery(sb.toString())) {
            //Send ResultSet to the getEmployeeList method and get employee object
            javafx.collections.ObservableList<pace_Classes.Credited_Accounts> empList = getIndividualID(resultSet);
            //return employee object
            return empList;
        }catch (java.sql.SQLException e) {
            System.out.println(e);
            throw e;
        }
    }
    public static javafx.collections.ObservableList<pace_Classes.Credited_Accounts> getIndividualID(java.sql.ResultSet resultSet) throws java.sql.SQLException {
        //Declare a observable List which comprises of Employee objects
        javafx.collections.ObservableList<pace_Classes.Credited_Accounts> list = javafx.collections.FXCollections.observableArrayList();
        while (resultSet.next()){
            //creating an object of Credited accounts
            pace_Classes.Credited_Accounts acc = new pace_Classes.Credited_Accounts();

            acc.setCredit_date(resultSet.getString("date_format("+COLUMN_CREDITED_DATE+", '%M')"));
            acc.setCredited_amount(resultSet.getDouble("sum("+COLUMN_CREDITED_AMOUNT+")"));
            acc.setBalance(resultSet.getDouble(COLUMN_CREDITED_BALANCE));
            acc.setStatus(resultSet.getString(COLUMN_CREDITED_STATUS));

            //add employee to the observable list
            list.add(acc);
        }
        return list;
    }
    public static pace_Classes.Credited_Accounts TotalAmountMonthly(int idNumber) throws java.sql.SQLException {
        StringBuilder sb = new StringBuilder(GET_TOTAL_MONTHLY_CONTRIBUTION);
        sb.append(idNumber);
        sb.append(" and DATE > now() - interval 12 month;");

        try(java.sql.Statement statement = conn.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery(sb.toString())) {

            pace_Classes.Credited_Accounts credit = resultSetTotalMonthly(resultSet);

            return credit;

        }catch (java.sql.SQLException e) {
            System.out.println(e);
            throw e;
        }
    }

    private static pace_Classes.Credited_Accounts resultSetTotalMonthly(java.sql.ResultSet resultSet) throws java.sql.SQLException {
        pace_Classes.Credited_Accounts credit = null;

        while (resultSet.next()){
            credit = new pace_Classes.Credited_Accounts();
            credit.setTotalAmount(resultSet.getDouble("sum("+COLUMN_CREDITED_AMOUNT+")"));
        }
        return credit;
    }
    public static pace_Classes.Credited_Accounts displayBarGraphh() throws java.sql.SQLException {
        StringBuilder sb = new StringBuilder(DISPLAY_BARGRAPH_CREDITED_ACCOUNT);

        try(java.sql.Statement statement = conn.createStatement();
        java.sql.ResultSet resultSet = statement.executeQuery(sb.toString())) {

            pace_Classes.Credited_Accounts credit = resultSetBarGraphCreditAcount(resultSet);

            return credit;
        }catch (java.sql.SQLException e) {
            System.out.println(e);
            throw e;
        }
    }

    private static pace_Classes.Credited_Accounts resultSetBarGraphCreditAcount(java.sql.ResultSet resultSet) throws java.sql.SQLException {
        pace_Classes.Credited_Accounts credit = null;

        while (resultSet.next()){
            credit = new pace_Classes.Credited_Accounts();
            credit.setTotalAmount(resultSet.getDouble("sum("+COLUMN_GRAPHMONTH_AMOUNT+")"));
        }
        return credit;
    }
    public static pace_Classes.Credited_Accounts displayLineGraph() throws java.sql.SQLException {
        StringBuilder sb = new StringBuilder(DISPLAY_BARGRAPH_CREDITED_ACCOUNT);

        try(java.sql.Statement statement = conn.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery(sb.toString())) {

            pace_Classes.Credited_Accounts credit = resultSetLineGraph(resultSet);

            return credit;
        }catch (java.sql.SQLException e) {
            System.out.println(e);
            throw e;
        }
    }

    private static pace_Classes.Credited_Accounts resultSetLineGraph(java.sql.ResultSet resultSet) throws java.sql.SQLException {
        pace_Classes.Credited_Accounts credit = null;

        while (resultSet.next()){
            credit = new pace_Classes.Credited_Accounts();
            credit.setTotalAmount(resultSet.getDouble("sum("+COLUMN_GRAPHMONTH_AMOUNT+")"));
        }
        return credit;
    }


    public static javafx.collections.ObservableList<javafx.scene.chart.XYChart.Data> loadLineChart() throws java.sql.SQLException {
        javafx.collections.ObservableList<javafx.scene.chart.XYChart.Data> data =
                javafx.collections.FXCollections.observableArrayList();

        StringBuilder sb = new StringBuilder(YEAR_GRAPH);
        try(java.sql.Statement statement = conn.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery(sb.toString())) {

            while (resultSet.next()){
                data.add(new javafx.scene.chart.XYChart.Data<>(resultSet.getString("DATE"), resultSet.getDouble(COLUMN_GRAPHMONTH_AMOUNT)));
            }

        }catch (java.sql.SQLException e) {
            System.out.println(e);
            throw e;
        }
        return data;
    }
}
