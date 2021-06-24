package com.people.chat.View;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.people.chat.Model.Message;

import org.apache.commons.dbcp.BasicDataSource;

public class MessageView {
    public static Message createMsg(Message msg) {
        // System.out.println("WTF!!!!!!!!!!!!!!!!!!!!");
        msg.setData(msg.getData().replace("'", "''"));
        String query = "INSERT INTO public.message("
                + "data, conversationid,  delivered, read, postedat, frompid, topid) " + "VALUES ('" + msg.getData()
                + "', " + msg.getConversationId() + "," + false + ", " + false + ", " + new Date().getTime() + ", "
                + msg.getFromPid() + ", " + msg.getToPid() + ");";

        // System.out.println("Query for msg creation " + query);
        ResultSet results = null;
        Connection conn = null;
        PreparedStatement ptstmnt = null;
        BasicDataSource dataSource = null;

        try {
            dataSource = DatabaseHandler.getHandler().getDataSource();
            conn = dataSource.getConnection();
            ptstmnt = conn.prepareStatement(query);
            ptstmnt.executeUpdate();

        } catch (SQLException e) {
            System.out.print("Error is MSG RESP = " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    if (results != null)
                        results.close();
                    ptstmnt.close();
                } catch (SQLException e) { // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        query = "select max(id) from message";

        try {
            conn = dataSource.getConnection();
            ptstmnt = conn.prepareStatement(query);
            results = ptstmnt.executeQuery();
            while (results.next()) {
                msg.setId(results.getInt(1));
                // System.out.println("New Msg Id for msg = " + msg.getData() + " is " +
                // msg.getId());
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
        return msg;
    }

    public static Integer markMsgAsRead(Integer msgId) {
        String query = "Update message set read = true where id = " + msgId;
        Connection conn = null;
        PreparedStatement ptstmnt = null;

        try {
            BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
            conn = dataSource.getConnection();
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

        return msgId;
    }

    public static List<Message> getMsgsForConvo(Integer convId, Integer userId, Boolean isClear) {
        List<Message> msgList = new ArrayList<>();
        String query = null;
        if (isClear) {
            query = "select * from message where conversationid = " + convId;
        } else {
            query = "select * from message where conversationid = " + convId + " and read = false and topid = "
                    + userId;// + timestamp;
        }

        // System.out.println(query);
        ResultSet results = null;
        Connection conn = null;
        PreparedStatement ptstmnt = null;

        try {
            BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
            conn = dataSource.getConnection();
            ptstmnt = conn.prepareStatement(query);
            results = ptstmnt.executeQuery();
            while (results != null && results.next()) {
                msgList.add(Message.getMessageFromSQL(results));
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
        return msgList;
    }

    // public static void main(String[] args) {
    // List<Message> m = getAllMsgsForConvoId(10);
    // System.out.println(m.get(0).getData());
    // }
}