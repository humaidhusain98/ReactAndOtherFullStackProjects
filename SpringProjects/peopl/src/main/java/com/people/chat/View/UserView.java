package com.people.chat.View;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.people.chat.Model.User;

import org.apache.commons.dbcp.BasicDataSource;

public class UserView {
    public static Boolean isAccessTokenPresent(String accessCode) {
        String query = "select * from users where accesstoken = '" + accessCode.toString() + "'";
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

    public static List<User> getUserFromColValue(String colValue, String colName, boolean isSubSeq)
            throws SQLException {

        List<User> userList = new ArrayList<>();
        String query = "select * from users where " + colName + " ilike '";//
        if (isSubSeq) {
            query = query.concat("%" + colValue + "%';");
        } else
            query = query.concat(colValue + "';");

        ResultSet results = null;
        Connection conn = null;
        PreparedStatement ptstmnt = null;

        try {
            BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
            conn = dataSource.getConnection();
            ptstmnt = conn.prepareStatement(query);
            results = ptstmnt.executeQuery();
            while (results != null && results.next()) {
                userList.add(User.getUserFromSql(results));
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

        return userList;
    }

    // TODO - MERGE THIS AND THE ABOVE FUNCTION INTO ONE, AND USE OBJECT TO TYPECAST
    public static List<User> getUserFromColValue(Integer colValue, String colName) throws SQLException {
        String query = "select * from users where " + colName + " = " + colValue;
        List<User> userList = new ArrayList<>();

        ResultSet results = null;
        Connection conn = null;
        PreparedStatement ptstmnt = null;

        try {
            BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
            conn = dataSource.getConnection();
            ptstmnt = conn.prepareStatement(query);
            results = ptstmnt.executeQuery();
            while (results != null && results.next()) {
                userList.add(User.getUserFromSql(results));
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
        return userList;
    }

    public static List<User> getUsersFromProfAndExp(Integer profId, Integer exp) throws SQLException {
        String query = "select * from users where currentprofessionid = " + profId + " and totalyearsofexp >=" + exp;
        // System.out.println(query);
        List<User> userList = new ArrayList<>();
        ResultSet results = null;
        Connection conn = null;
        PreparedStatement ptstmnt = null;
        try {
            BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
            conn = dataSource.getConnection();
            System.out.println("GOT CONNECTION FROM USER VIEW");
            ptstmnt = conn.prepareStatement(query);
            results = ptstmnt.executeQuery();
            while (results != null && results.next()) {
                userList.add(User.getUserFromSql(results));
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

        // if (result != null)
        // result.close();
        // System.out.println(userList.size());
        return userList;
    }

    public static Integer createUser(User user) {

        String query = "INSERT INTO public.users("
                + "name, email, socialid, source, freemsgcount, timeoffdays, totalyearsofexp, "
                + "currentcompanyid, currentprofessionid, createdat, points, lastlogin, accesstoken, profilepicurl, city, country, onleave) "
                + "VALUES ('" + user.getName() + "', '" + user.getEmailAddress().replaceAll("^\"|\"$", "") + "', '"
                + user.getSocialId() + "', " + user.getSource() + "," + user.getFreeMsgCount() + ","
                + user.getTimeOffDays() + "," + user.getTotalYearsOfExp() + "," + user.getCurrentCompanyId() + ","
                + user.getCurrentProfessionId() + "," + new Date().getTime() + "," + user.getPoints() + ","
                + user.getLastLogin() + ",'" + user.getAccessToken() + "','" + user.getProfilePicUrl() + "','"
                + user.getCity() + "','" + user.getCountry().trim() + "'," + user.getOnLeave() + ");";

        ResultSet results = null;
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
                    if (results != null)
                        results.close();
                    ptstmnt.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        query = "select max(id) from public.users";
        try {
            BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
            conn = dataSource.getConnection();
            ptstmnt = conn.prepareStatement(query);
            results = ptstmnt.executeQuery();
            while (results != null && results.next()) {
                user.setId(results.getInt(1));
                return user.getId();
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

    public static void updateAccessToken(Integer userId, String accessToken) {
        String query = "update public.users set accesstoken = '" + accessToken + "' where id = " + userId;
        // System.out.println(query);
        try {

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
            // if (result != null)
            // result.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static User saveUser(User user) {
        // String query = "UPDATE public.users " + "SET name='" + user.getName() + "', email='" + user.getEmailAddress()
        //         + "', socialid='" + user.getSocialId() + "', source=" + user.getSource() + ", freemsgcount="
        //         + user.getFreeMsgCount() + ", timeoffdays=" + user.getTimeOffDays() + ", totalyearsofexp="
        //         + user.getTotalYearsOfExp() + ", currentcompanyid=" + user.getCurrentCompanyId()
        //         + ", currentprofessionid=" + user.getCurrentProfessionId() + ", createdat=" + user.getCreatedAt()
        //         + ", points=" + user.getPoints() + ", lastlogin=" + user.getLastLogin() + ", accesstoken='"
        //         + user.getAccessToken() + "', profilepicurl='" + user.getProfilePicUrl() + "', city='" + user.getCity()
        //         + "', country='" + user.getCountry() + "', onleave=" + user.getOnLeave() + " WHERE id = "
        //         + user.getId();

        String query = "UPDATE public.users " + "SET name='" + user.getName() + "', email='" + user.getEmailAddress()
                + "', socialid='" + user.getSocialId() + "', source=" + user.getSource() + ", freemsgcount="
                + user.getFreeMsgCount() + ", timeoffdays=" + user.getTimeOffDays() + ", totalyearsofexp="
                + user.getTotalYearsOfExp() + ", currentcompanyid=" + user.getCurrentCompanyId()
                + ", currentprofessionid=" + user.getCurrentProfessionId() + ", createdat=" + user.getCreatedAt()
                + ", points=" + user.getPoints() + ", lastlogin=" + user.getLastLogin() + ", accesstoken='"
                + user.getAccessToken() + "', profilepicurl='" + user.getProfilePicUrl() + "', city='" + user.getCity()
                + "', country='" + user.getCountry() + "', onleave=" + user.getOnLeave() + ", devicetoken = '" + user.getDeviceToken() + "' WHERE id = "
                + user.getId();

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
        return null;
    }

    public static void main(String[] args) throws SQLException {
        System.out.println(getUsersFromProfAndExp(2, 12).size());
    }
}