package com.people.chat.View;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.people.chat.Model.Branch;
import com.people.chat.Model.Degree;

import org.apache.commons.dbcp.BasicDataSource;

public class EducationView {

    public static void createEducationMetric() {
        
    }

    public static Degree createDegree(Degree deg) {
        String query = "INSERT INTO public.degree(degreename) VALUES ('" + deg.getName().trim() + "');";

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
            System.out.print("Error in Deg creation = " + e.getMessage());
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

        query = "select max(id) from degree";

        try {
            conn = dataSource.getConnection();
            ptstmnt = conn.prepareStatement(query);
            results = ptstmnt.executeQuery();
            while (results.next()) {
                deg.setId(results.getInt(1));
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

        return deg;
    }

    public static Branch createBranch(Branch br) {
        String query = "INSERT INTO public.branch(name) VALUES ('" + br.getName().trim() + "');";

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
            System.out.print("Error in Branch creation = " + e.getMessage());
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

        query = "select max(id) from branch";

        try {
            conn = dataSource.getConnection();
            ptstmnt = conn.prepareStatement(query);
            results = ptstmnt.executeQuery();
            while (results.next()) {
                br.setId(results.getInt(1));
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

        return br;
    }

    public static Integer getEduMetricId(Integer degId, Integer branchId) {

        String query = "select * from educationmetric where degreeid = " + degId + " and branchid = " + branchId;
        ResultSet results = null;
        Connection conn = null;
        PreparedStatement ptstmnt = null;
        BasicDataSource dataSource = null;

        try {
            dataSource = DatabaseHandler.getHandler().getDataSource();
            conn = dataSource.getConnection();
            ptstmnt = conn.prepareStatement(query);
            results = ptstmnt.executeQuery();
            while (results.next()) {
                return results.getInt(1);
            }
            return -1;

        } catch (SQLException e) {
            System.out.print("Error in Branch creation = " + e.getMessage());
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
        return -1;
    }

    public static Boolean doesDegreeExist(Degree degree) {
        String query = "select * from public.degree where degreename = '" + degree.getName() + "'";
        ResultSet results = null;
        Connection conn = null;
        PreparedStatement ptstmnt = null;
        BasicDataSource dataSource = null;

        try {
            dataSource = DatabaseHandler.getHandler().getDataSource();
            conn = dataSource.getConnection();
            ptstmnt = conn.prepareStatement(query);
            results = ptstmnt.executeQuery();
            while (results.next()) {
                return true;
            }

        } catch (SQLException e) {
            System.out.print("Error in Branch creation = " + e.getMessage());
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
        return false;
    }

    public static Boolean doesBranchExist(Branch br) {
        String query = "select * from public.branch where name = '" + br.getName() + "'";
        ResultSet results = null;
        Connection conn = null;
        PreparedStatement ptstmnt = null;
        BasicDataSource dataSource = null;

        try {
            dataSource = DatabaseHandler.getHandler().getDataSource();
            conn = dataSource.getConnection();
            ptstmnt = conn.prepareStatement(query);
            results = ptstmnt.executeQuery();
            while (results.next()) {
                return true;
            }

        } catch (SQLException e) {
            System.out.print("Error in Branch creation = " + e.getMessage());
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
        return false;
    }

    public static void main(String[] args) {
        // createDegree(new Degree(-1, "Bachelor of Engineering"));
        // createBranch(new Branch(-1, "Civil Engineering"));

        Boolean t = doesBranchExist(new Branch(-1, "Civil Engineering"));
        System.out.println(t);
    }
}