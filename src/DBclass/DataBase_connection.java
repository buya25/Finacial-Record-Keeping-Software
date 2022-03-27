package DBclass;

public class DataBase_connection implements IDB_connection{

    public static java.sql.Connection conn = null;

    public static boolean open(){
        try{
            conn = java.sql.DriverManager.getConnection(CONNECTION_STRING, USER, DATABASE_PASSWORD);
            return true;
        }catch (java.sql.SQLException e){
            System.out.println("could not connect to database: " + e.getMessage());
            return false;
        }
    }

    public void close(){
        try{
            if (conn != null) {
                conn.close();
            }
        }catch (java.sql.SQLException e){
            System.out.println(" Could not close connection: " +e.getMessage());
        }
    }


}
