package com.people.chat.View;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.people.chat.Model.PointHistory;
import com.people.chat.Utils.CommonUtils;

import org.apache.commons.dbcp.BasicDataSource;

public class CommonView {
    public static void createVacationHistory(Integer userId, String fromDate, String toDate, String reason,
            Boolean isProcessed) {
        String query = "INSERT INTO public.leavehistory(userid, fromdate, todate, reason, isprocessed) " + " VALUES ("
                + userId + ", '" + fromDate + "', '" + toDate + "','" + reason + "'," + isProcessed + ");";

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
    }

    public static String getLeaveDetials(Integer userId) {
        String query = "select * from vacationrecord where userid = " + userId;
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
                String startDate = results.getString(3);
                String endDate = results.getString(4);
                String reason = results.getString(5);
                Integer days = CommonUtils.getDays(startDate, endDate);
                String[] dateDetails = startDate.split("-");
                return "is on leave starting from " + dateDetails[2] + "-"
                        + CommonUtils.getMonthName(Integer.parseInt(dateDetails[1])) + " for " + days
                        + " days. | Reason - " + reason;
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

    public static List<PointHistory> getPointHistoryForUser(Integer userId) {
        String query = "select * from pointhistory where userid = " + userId;

        ResultSet results = null;
        Connection conn = null;
        PreparedStatement ptstmnt = null;
        List<PointHistory> pointHistory = new ArrayList<>();
        try {
            BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
            conn = dataSource.getConnection();
            // System.out.println("GOT CONNECTION FROM competency VIEW");
            ptstmnt = conn.prepareStatement(query);
            results = ptstmnt.executeQuery();
            while (results != null && results.next()) {
                pointHistory.add(PointHistory.getPointHistoryFromSQL(results));
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

        return pointHistory;
    }

    public static void createPointHistory(Integer userId, Integer otherUserId, Integer points, Integer type) {
        String query = "INSERT INTO public.pointhistory(userid, points, otherpid, type, timestamp) " + " VALUES ("
                + userId + ", " + points + ", " + otherUserId + ", " + type + "," + new Date().getTime() + ");";

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

    }

    public static void createVacationRecord(Integer userId, String startDate, String endDate, String reason) {
        String query = "INSERT INTO public.vacationrecord(" + "userid, startdate, enddate, reason) " + "VALUES ("
                + userId + ", '" + startDate + "', '" + endDate + "', '" + reason + "');";
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
    }

    // TODO - USE/EDIT ABOVE FN
    public static List<String> getFullLeaveDetailsList(Integer userId) {
        String query = "select * from vacationrecord where userid = " + userId;
        Connection conn = null;
        PreparedStatement ptstmnt = null;
        ResultSet results = null;
        List<String> leaveDetails = new ArrayList<>();

        try {
            BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
            conn = dataSource.getConnection();
            ptstmnt = conn.prepareStatement(query);
            results = ptstmnt.executeQuery();
            while (results.next()) {
                String startDate = results.getString(3);
                String endDate = results.getString(4);
                String reason = results.getString(5);
                Integer days = CommonUtils.getDays(startDate, endDate);
                String[] dateDetails = startDate.split("-");

                leaveDetails.add(parseStringFromDate(dateDetails, false));
                leaveDetails.add(parseStringFromDate(endDate.split("-"), true));
                leaveDetails.add(reason);
                leaveDetails.add(days.toString());
                leaveDetails.add(CommonUtils.getDays(startDate, endDate).toString());

                return leaveDetails;

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

        return null;
    }

    // EWW HACK
    private static String parseStringFromDate(String[] dateDetails, Boolean addOneToDate) {
        String startMonthName = CommonUtils.getMonthName(Integer.parseInt(dateDetails[1]));
        String startingDate = dateDetails[2];
        String startingYear = dateDetails[0];

        if (addOneToDate) {
            Integer startDate = Integer.parseInt(startingDate) + 1;
            startingDate = (startDate < 10 ? "0" : "") + startDate.toString();
        }

        return startMonthName + " " + startingDate + "," + startingYear;

    }

    public static void removeVacationRecord(Integer userId) {
        String query = "delete from vacationrecord where userid = " + userId;
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
    }

    public static void main(String[] args) {
        List<String> leave = getFullLeaveDetailsList(34);

        for (String l : leave) {
            System.out.println(l);
        }
    }
}