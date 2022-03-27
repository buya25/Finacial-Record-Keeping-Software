package test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
    public class DBConnect {

        private static java.sql.Connection conn;
        private static String url = "jdbc:mysql://localhost:3306/test";
        private static String user = "root";
        private static String pass = "Yabs2030";

        public static java.sql.Connection connect() throws java.sql.SQLException{

            conn = java.sql.DriverManager.getConnection(url,user,pass);
            return conn;
        }
        public static java.sql.Connection getConnection() throws java.sql.SQLException, ClassNotFoundException{
            if(conn !=null && !conn.isClosed())
                return conn;
            connect();
            return conn;

        }
    }
