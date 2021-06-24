package com.people.chat.View;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.people.chat.Model.Conversation;

import org.apache.commons.dbcp.BasicDataSource;

public class ConversationView {
    public static Conversation getExistingConvoBetweenPids(Integer firstPid, Integer secondPid, Integer pendingPid)
            throws SQLException {
        List<Integer> pidList = new ArrayList<>();
        pidList.add(firstPid);
        pidList.add(secondPid);
        Collections.sort(pidList);

        String query = "Select * from conversation where pidlist = '{" + pidList.get(0) + "," + pidList.get(1) + "}' ";

        // System.out.println("Existing conv query = " + query);
        ResultSet results = null;
        Connection conn = null;
        PreparedStatement ptstmnt = null;

        try {
            BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
            conn = dataSource.getConnection();
            ptstmnt = conn.prepareStatement(query);
            results = ptstmnt.executeQuery();
            while (results != null && results.next()) {
                // System.out.println("SOUMIK == GETTING OBJ FROM SQL");
                return Conversation.getConversationFromSQL(results);
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
        // return createConversation(new Conversation(-1, true, pidList, pendingPid));
    }

    public static Conversation createConversation(Conversation conversation) {
        String query = "INSERT INTO public.conversation(isopen, pidlist, pendingpid, lastupdated) VALUES (true, '{"
                + conversation.getPidList().get(0) + "," + conversation.getPidList().get(1) + "}', "
                + conversation.getPendingPid() + "," + new Date().getTime() + ");";

        Connection conn = null;
        PreparedStatement ptstmnt = null;
        ResultSet results = null;

        // System.out.println("SOUMIK ---- CONVO CREATE QUERY == " + query);

        try {
            BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
            conn = dataSource.getConnection();
            ptstmnt = conn.prepareStatement(query);
            ptstmnt.executeUpdate();
            // System.out.println("SOUMIK AFTERCREATE QUERY EXEC");
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

        query = "select max(id) from conversation";
        try {
            BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
            conn = dataSource.getConnection();
            ptstmnt = conn.prepareStatement(query);
            results = ptstmnt.executeQuery();
            while (results != null && results.next()) {
                // System.out.println("SOUMIK == GETTING OBJ FROM SQL");
                conversation.setId(results.getInt(1));
                return conversation;
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

    public static List<Conversation> getAllOpenConversations() {
        String query = "select * from conversation where isopen = true";
        Connection conn = null;
        PreparedStatement ptstmnt = null;
        ResultSet results = null;
        List<Conversation> convList = new ArrayList<>();

        try {
            BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
            conn = dataSource.getConnection();
            ptstmnt = conn.prepareStatement(query);
            results = ptstmnt.executeQuery();
            while (results != null && results.next()) {
                convList.add(Conversation.getConversationFromSQL(results));
            }

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
        return convList;
    }

    public static Conversation save(Integer id, Conversation conversation) {
        String query = "Update conversation set isopen = " + conversation.getIsOpen() + ", pidList = '{"
                + conversation.getPidList().get(0) + "," + conversation.getPidList().get(1) + "}'," + " pendingpid =  "
                + conversation.getPendingPid() + ", lastupdated = " + new Date().getTime() + " where id = " + id;

        Connection conn = null;
        PreparedStatement ptstmnt = null;

        // System.out.println("Conv Save Query = " + query);

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
        return getConversation(id);
    }

    public static Conversation getConversation(Integer id) {
        String query = "select * from conversation where id = " + id;
        ResultSet results = null;
        Connection conn = null;
        PreparedStatement ptstmnt = null;

        try {
            BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
            conn = dataSource.getConnection();
            ptstmnt = conn.prepareStatement(query);
            results = ptstmnt.executeQuery();
            while (results != null && results.next()) {
                return Conversation.getConversationFromSQL(results);
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

    public static List<Conversation> getAllConvosForUserId(Integer userId) {
        List<Conversation> convList = new ArrayList<>();
        String query = "SELECT * FROM conversation WHERE " + userId + " = ANY(pidlist)";

        ResultSet results = null;
        Connection conn = null;
        PreparedStatement ptstmnt = null;

        try {
            BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
            conn = dataSource.getConnection();
            ptstmnt = conn.prepareStatement(query);
            results = ptstmnt.executeQuery();
            while (results.next()) {
                convList.add(Conversation.getConversationFromSQL(results));
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
        return convList;
    }

    // TODO Reuse GetExisitngConvoBetween PIDS and do away with this fn
    public static boolean getExistingConvoBetweenPids(Integer firstPid, Integer secondPid) throws SQLException {
        List<Integer> pidList = new ArrayList<>();
        pidList.add(firstPid);
        pidList.add(secondPid);
        Collections.sort(pidList);

        String query = "Select * from conversation where pidlist = '{" + pidList.get(0) + "," + pidList.get(1)
                + "}' and isopen = true";

        // Start
        ResultSet results = null;
        Connection conn = null;
        PreparedStatement ptstmnt = null;
        try {
            BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
            conn = dataSource.getConnection();
            ptstmnt = conn.prepareStatement(query);
            results = ptstmnt.executeQuery();
            while (results != null && results.next()) {
                return true;
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
        return false;
    }

    public static void main(String[] args) throws SQLException {
        getExistingConvoBetweenPids(1, 2, 2);
    }
}