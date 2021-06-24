package com.people.chat.View;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.people.chat.Model.Company;

import org.apache.commons.dbcp.BasicDataSource;

public class CompanyView {

    public static Company getCompanyFromName(String name) {
        String query = "select * from company where LOWER(name) ilike '%" + name + "%'";
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
                return Company.getCompanyFromSQL(results);
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

    public static Company getCompanyObj(Integer id) {
        String query = "select * from company where id = " + id;

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
                return Company.getCompanyFromSQL(results);
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

    public static Company createCompany(Company comp) {
        String query = "INSERT INTO public.company(name) VALUES ('" + comp.getName().trim() + "');";
        ResultSet results = null;
        Connection conn = null;
        PreparedStatement ptstmnt = null;
        BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();

        try {
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

        query = "select max(id) from company";

        try {
            conn = dataSource.getConnection();
            ptstmnt = conn.prepareStatement(query);
            results = ptstmnt.executeQuery();
            while (results != null && results.next()) {
                comp.setId(results.getInt(1));
                return comp;
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
}