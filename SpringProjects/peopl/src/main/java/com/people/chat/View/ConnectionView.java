package com.people.chat.View;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.dbcp.BasicDataSource;

public class ConnectionView {
    public static String getConnectionId(Integer pid) {
        String query = "select connectionid from connection where profileid = " + pid;
        ResultSet results = null;
        Connection conn = null;
        PreparedStatement ptstmnt = null;

        try {
            BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
            conn = dataSource.getConnection();
            // System.out.println("GOT CONNECTION FROM competency VIEW");
            ptstmnt = conn.prepareStatement(query);
            results = ptstmnt.executeQuery();
            while (results != null && results.next()) {
                return results.getString(1);
            }
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    if (results != null)
                        results.close();
                    ptstmnt.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static void updateConnectionLastUsed(String conId) {
        String query = "Update connection set lastused = " + new Date().getTime() + " where connectionid = '" + conId
                + "'";
        Connection conn = null;
        PreparedStatement ptstmnt = null;

        try {
            BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
            conn = dataSource.getConnection();
            // System.out.println("GOT CONNECTION FROM competency VIEW");
            ptstmnt = conn.prepareStatement(query);
            ptstmnt.executeUpdate();
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    ptstmnt.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.print(getConnectionId(1234));
    }
}