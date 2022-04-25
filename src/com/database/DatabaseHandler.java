package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class DatabaseHandler {

    private static final Logger LOG = Logger.getLogger(DatabaseHandler.class.getName());

    static String HOST;
    static String CLASSNAME;
    static String DBPORT = "3306";
    public static String DBNAME = "pi";
    public static String USERNAME = "root";
    public static String PASSWORD = "";
    static String TIMEZONE = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static Connection connection = null;
    private static DatabaseHandler handler = null;

    static {
        createConnection();
        if (isConnected()) {
            System.out.println("Database is connected  : " + isConnected());
        } else {
            System.out.println("Database is connected  : " + !isConnected());
        }
    }

    public DatabaseHandler() {
    }

    public static DatabaseHandler getInstance() {
        if (handler == null) {
            handler = new DatabaseHandler();
        }
        return handler;
    }
//
//    public static void main(String[] args) throws Exception {
//        DatabaseHandler.getInstance();
//
//    }

    private static void createConnection() {
        try {
            System.out.println("===============================================");
            
            
            HOST = "jdbc:mysql://localhost:" + DBPORT + "/" + DBNAME;
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
            
            System.out.println("Succesfully connected to mysql database");
            System.out.println("Database name   [" + DBNAME + "]");
            System.out.println("Connection created   ... ");
            System.out.println("===============================================");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error   : " + e.getLocalizedMessage());
            System.out.println("Message : " + e.getMessage());
            // System.exit(0);
        }
    }

    public Connection getConnection() {
        // System.out.println("Creating Connection   ... ");
        if (connection == null) {
            createConnection();
        }
        return connection;

    }

    public String checkDatabase() {
        if (connection != null) {
            return "CONNECTED";
        } else {
            return "NOTCONNECTED";
        }
    }

    public static boolean isConnected() {
        Connection con = null;
        try {
            HOST = "jdbc:mysql://localhost:" + DBPORT + "/" + DBNAME;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error   : " + e.getLocalizedMessage());
            System.out.println("Message : " + e.getMessage());
            // System.exit(0);
        }
        return con != null;
    }

    public Statement getStatement() {
        try {
            if (connection == null) {
                createConnection();
            }
            return connection.createStatement();

        } catch (SQLException ex) {
            return null;
        }
    }

    /**
     *
     * @param query
     * @return
     */
    public ResultSet executeQuery(String query) {
        System.out.println(query);
        PreparedStatement pst;
        try {
            pst = getConnection().prepareStatement(query);
            return pst.executeQuery();
        } catch (SQLException ex) {
            return null;
        }
    }

    /**
     *
     * @param query
     * @return
     */
    public ResultSet execUpdate(String query) {
        System.out.println(query);
        PreparedStatement pst;
        try {
            pst = getConnection().prepareStatement(query);
            return pst.executeQuery();
        } catch (SQLException ex) {
            return null;
        }
    }

    /**
     *
     * @param qu
     * @return
     */
    public boolean execAction(String qu) {
        System.out.println(qu);
        try (PreparedStatement pst = getConnection().prepareStatement(qu)) {
            return pst.executeUpdate() == 1;
        } catch (SQLException ex) {
            return false;
        }
        //Finally ...............
    }

}
