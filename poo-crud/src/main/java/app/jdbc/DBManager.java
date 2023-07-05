package app.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBManager {
    private static final String DB_URL = "jdbc:mysql://localhost/database";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";
    public static Connection getCon(){
        Connection con = null;
        try{
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch(Exception e){
            e.printStackTrace();
        }
        return con;
    }
}
