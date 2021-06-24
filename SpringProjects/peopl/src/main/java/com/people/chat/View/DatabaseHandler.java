package com.people.chat.View;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.amazonaws.services.applicationdiscovery.model.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

public class DatabaseHandler {
	
	
	//testing my localhost credentials


    private static DatabaseHandler databaseHandler = new DatabaseHandler();
    private static String userName = "peopl987321";
    private static String password = "C3UvtK6waJdO0oRcREF6";
    private static String port = "5432";
    // private static String hostname =
    // "verticl.crkawyo0x2hb.ap-south-1.rds.amazonaws.com";
    private static String hostname = "peopl.cqqe9bwbt7wx.ap-south-1.rds.amazonaws.com:5432/";
    private static String dbName = "peopl";
    private static BasicDataSource ds = null;


    







    private DatabaseHandler() {

    }

    public static DatabaseHandler getHandler() {
        return databaseHandler;
    }

    public  BasicDataSource getDataSource() {
        if (ds != null) {
            //System.out.println(ds.isClosed());
            return ds;
        }
        String url = "jdbc:postgresql://" + hostname + dbName;
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        // initial number of connection when the pool is created
        dataSource.setInitialSize(0);
        dataSource.setRemoveAbandoned(true); 
        dataSource.setLogAbandoned(true);
        dataSource.setDefaultTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        dataSource.setDefaultAutoCommit(true);
        // maximum number of connection of the pool
        dataSource.setMaxOpenPreparedStatements(35);
        ds = dataSource;
        return dataSource;
    }

    public ResultSet getResultSet(String query) {
        ResultSet results = null;
        Connection conn = null;
        try {
            BasicDataSource dataSource = this.getDataSource();
            conn = dataSource.getConnection();
            PreparedStatement ptstmnt = conn.prepareStatement(query);
            results = ptstmnt.executeQuery();
            return results;
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        } finally {
            // if (conn != null)
            // try {
            // conn.close();
            // } catch (SQLException e) {
            // // TODO Auto-generated catch block
            // e.printStackTrace();
            // }
        }
        System.out.println("ERROR IN QUERY EXE ====== " + query);
        return null;
    }

    public static void main(String[] args) throws SQLException {
        ResultSet result = DatabaseHandler.getHandler().getResultSet("select * from message");
        while (result.next()) {
            // Integer id = result.getInt(1);
            String connectionId = result.getString(1);
            System.out.println(connectionId);
            // System.out.println("\n" + id.toString() + " --- " + connectionId);
        }
        System.out.println("===================================");
        result = DatabaseHandler.getHandler().getResultSet("select * from message");
        while (result.next()) {
            // Integer id = result.getInt(1);
            String connectionId = result.getString(1);
            System.out.println(connectionId);
            // System.out.println("\n" + id.toString() + " --- " + connectionId);
        }
        // String jdbcUrl = "jdbc:postgresql://" + hostname + ":" + port + "/" + dbName
        // + "?user=" + userName
        // + "&password=" + password;
        // try {
        // Class.forName("org.postgresql.Driver");
        // Connection con = DriverManager.getConnection(jdbcUrl);
        // } catch (SQLException | ClassNotFoundException e) {
        // System.out.println("ERROR ==== " + e.toString());
        // e.printStackTrace();
        // }
        // // logger.info("Remote connection successful.");
        // // return con;
        // ResultSet resultSet = null;
        // Connection conn = null;
        // Statement statement = null;
        // try {
        // BasicDataSource dataSource = getDataSource();
        // conn = dataSource.getConnection();
        // statement = conn.createStatement();
        // // resultSet = statement.executeQuery(query);
        // } catch (SQLException e) {
        // System.out.print(e.toString());
        // }
        System.out.print("END");
    }
}
