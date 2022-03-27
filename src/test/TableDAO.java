package test;

public class TableDAO extends DBclass.DataBase_connection {

    public static javafx.collections.ObservableList<Table> searchEmployees() throws java.sql.SQLException {
        open();
        StringBuilder sb = new StringBuilder("SELECT a.middle_name, a.last_name, a.id_number, c.amount, c.balance, c.status, c.date\n" +
                "from pacesetterfx.accounts as a\n" +
                "join pacesetterfx.credited_accounts as c on c.id_number = a.id_number;");
        //Get ResultSet from dbExecuteQuery method
        try(java.sql.Statement statement = conn.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery(sb.toString())) {
            //Send ResultSet to the getEmployeeList method and get employee object
            javafx.collections.ObservableList<Table> empList = getDataUser(resultSet);
            //return employee object
            return empList;
        }catch (java.sql.SQLException e) {
            System.out.println(e);
            throw e;
        }
    }

    private static javafx.collections.ObservableList<Table> getDataUser(java.sql.ResultSet resultSet) throws java.sql.SQLException {
        //Declare a observable List which comprises of Employee objects
        javafx.collections.ObservableList<Table> list = javafx.collections.FXCollections.observableArrayList();
        while (resultSet.next()){
            //creating an object of Credited accounts
            test.Table hb = new Table();
            hb.setFname(resultSet.getString("middle_name"));
            hb.setLname(resultSet.getString("last_name"));
            hb.setIdnumber(resultSet.getInt("id_number"));
            hb.setAmount(resultSet.getDouble("amount"));
            hb.setBalance(resultSet.getDouble("balance"));
            hb.setStatus(resultSet.getString("status"));
            hb.setDate(resultSet.getDate("Date"));


            //add employee to the observable list
            list.add(hb);
        }
        return list;
    }
}
