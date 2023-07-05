package app.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBManager{
    private static final String DB_URL = "jdbc:mysql://localhost/cap07_bd";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";
    public static Connection getCon(){
        Connection con = null;
        try{
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch(Exception e){
            e.printStackTrace();
        }
        return con;
    }
} //DBManager{}
