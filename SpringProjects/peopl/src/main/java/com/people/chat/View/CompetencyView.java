package com.people.chat.View;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.people.chat.Model.Pair;

import com.people.chat.Model.Competency;
import com.people.chat.Model.User;

import org.apache.commons.dbcp.BasicDataSource;

public class CompetencyView {
    // Use this as a getter for Competency Map For an user
    public static Map<Competency, Integer> getCompetencyMapForUserId(Integer id) {
        Map<Competency, Integer> competencyMap = new HashMap<>();
        List<Pair<Integer>> pidList = new ArrayList<>();
        String query = "select competencyid, yearsofexp from usercompetencymetric where userid = " + id;

        ResultSet results = null;
        Connection conn = null;
        PreparedStatement ptstmnt = null;
        try {
            BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
            conn = dataSource.getConnection();
            ptstmnt = conn.prepareStatement(query);
            results = ptstmnt.executeQuery();
            while (results != null && results.next()) {
                if (results.getInt(2) == 0) // HACKY WAY TO NOT GIVE COMPETENCIES WITH 0 EXP
                    continue;
                pidList.add(new Pair<Integer>(results.getInt(1), results.getInt(2)));
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
        for (Pair<Integer> p : pidList) {
            Competency comp = getCompetencyObject(p.p1);
            // System.out.print(comp.getName());
            competencyMap.put(comp, p.p2);
        }
        return competencyMap;
    }

    // TODO Move this to Common View, return a genberic Object
    public static Competency getCompetencyObject(Integer id) {
        String query = "select * from competency where id = " + id;
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
                return Competency.getCompetency(results);
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

    public static List<User> getUsersFromCompMetric(Integer compId, Integer yrs) {
        List<User> users = new ArrayList<>();
        List<Integer> pidList = new ArrayList<>();
        String query = "select userid from usercompetencymetric where competencyid = " + compId + " and yearsofexp >="
                + yrs;

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
                pidList.add(results.getInt(1));
            }
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    results.close();
                    ptstmnt.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        for (Integer pid : pidList) {
            try {
                users.addAll(UserView.getUserFromColValue(pid, "id"));
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return users;
    }

    public static void main(String[] args) {
        System.out.print(getUsersFromCompMetric(2, 2).size());
    }

    private static boolean doesCompMetricExist(Integer compId, Integer userId) {
        String query = "select * from public.usercompetencymetric where userid = " + userId + " and competencyid = "
                + compId;
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
                return true;
            }
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
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

    // Upsert function - update if combination exists. TODO - Modify Query to have
    // this working with the same query
    public static void createEntryForCompetencyMap(Integer userId, Integer competencyId, Integer yearsOfExp) {
        String query;
        if (doesCompMetricExist(competencyId, userId)) {
            query = "Update public.usercompetencymetric set yearsofexp = " + yearsOfExp + " where userid = " + userId
                    + " and competencyid = " + competencyId;
        } else
            query = "INSERT INTO public.usercompetencymetric(" + "userid, competencyid, yearsofexp) " + "VALUES ("
                    + userId + "," + competencyId + "," + yearsOfExp + ");";
        Connection conn = null;
        PreparedStatement ptstmnt = null;
        try {
            BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
            conn = dataSource.getConnection();
            // System.out.println("GOT CONNECTION FROM competency VIEW");
            ptstmnt = conn.prepareStatement(query);
            ptstmnt.executeUpdate();
            System.out.println("ptsmnt executed ----"+ptstmnt);
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

    public static void deleteCompetencyMetrics(Integer userId) {
        String query = "DELETE FROM public.usercompetencymetric WHERE userid = " + userId;
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

    }

    // MAY NEED AN UPDATE
    public static Competency save(Competency competency) {
        String query = "INSERT INTO public.competency(" + "name, professionid) " + "VALUES ('" + competency.getName().trim()
                + "', null);";

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
        query = "select max(id) from competency";
        try {
            BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
            conn = dataSource.getConnection();
            ptstmnt = conn.prepareStatement(query);
            results = ptstmnt.executeQuery();
            while (results != null && results.next()) {
                competency.setId(results.getInt(1));
                return competency;
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