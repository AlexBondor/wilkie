package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnector {

    /**
     * An instance of this class
     */
    private static MySQLConnector instance = new MySQLConnector();

    /**
     * Constructor
     */
    MySQLConnector() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to create connection to Database
     *
     * @return connection status
     */
    private Connection createConnection() {
        Connection myConn = null;
        try {
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wilkie", "root", "admin");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myConn;
    }

    public static Connection getConnection() {
        return instance.createConnection();
    }
}