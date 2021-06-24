package com.people.chat.Hydrator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.people.chat.Model.Competency;
import com.people.chat.Model.Profession;
import com.people.chat.Model.User;
import com.people.chat.View.CompetencyView;
import com.people.chat.View.DatabaseHandler;
import com.people.chat.View.ProfessionView;
import com.people.chat.View.UserView;

import org.apache.commons.dbcp.BasicDataSource;

public class CommonHydrator {
    static String[] profList = { "UI Engineer", "UI Developer", "Front End Developer", "UI Architect", "UI Lead",
            "Sr. UI Engineer", "Sr. UI Developer", "Backend Developer" };

    static Map<String, Integer> comp = new HashMap<>();

    public static void main(String[] args) {
        // createCompMap();
        // for (String compName : comp.keySet()) {
        // Competency competency = new Competency(-1, compName, comp.get(compName));
        // CompetencyView.save(competency);
        // }

        // removeOldCompetencies();
        System.out.println("test");
    }

    private static void createProf(String[] args) {
        for (int index = 0; index < profList.length; index++) {
            Profession prof = new Profession(-1, profList[index], false);
            ProfessionView.createProf(prof);
        }
    }

    private static void createCompMap() {
        comp.put("React.JS", 16);
        comp.put("Angular.JS", 17);
        comp.put("Angular1", 18);
        comp.put("Angular2", 19);
        comp.put("Angular3", 20);
        comp.put("Angular4", 21);
        comp.put("HTML", 22);
        comp.put("CSS", 22);
        comp.put("Javascript", 22);
        comp.put("Web App Development", 22);
        comp.put("React Native", 22);
        comp.put("Mobile App Development", 22);
        comp.put("Java", 23);
        comp.put("C++", 23);
        comp.put("Node JS", 23);
        comp.put("Python", 23);
        comp.put("Django", 23);
    }

    private static void removeOldCompetencies() {
        Map<String, Boolean> compMap = new HashMap<>();
        List<Competency> compList = new ArrayList<>();
        List<Integer> duplicates = new ArrayList<>();
        List<User> userList = new ArrayList<>();

        // String query = "select * from competency";
        // ResultSet results = null;
        // Connection conn = null;
        // PreparedStatement ptstmnt = null;
        // try {
        // BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
        // conn = dataSource.getConnection();
        // // System.out.println("GOT CONNECTION FROM competency VIEW");
        // ptstmnt = conn.prepareStatement(query);
        // results = ptstmnt.executeQuery();
        // while (results != null && results.next()) {
        // compList.add(Competency.getCompetency(results));
        // }

        // } catch (SQLException e) {
        // System.out.print(e.getMessage());
        // } finally {
        // if (conn != null) {
        // try {
        // conn.close();
        // results.close();
        // ptstmnt.close();
        // } catch (SQLException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        // }
        // }

        // for (Competency comp : compList) {
        // if (compMap.containsKey(comp.getName()) ||
        // comp.getName().equalsIgnoreCase("Lodu")
        // || comp.getName().equalsIgnoreCase("Xyzabc")
        // || comp.getName().equalsIgnoreCase("Xyzha")
        // || comp.getName().equalsIgnoreCase("Byjj")
        // || comp.getName().equalsIgnoreCase("Dhhg")
        // || comp.getName().equalsIgnoreCase("Lodu1")
        // || comp.getName().equalsIgnoreCase("Lodu1")) {
        // duplicates.add(comp.getId());
        // } else {
        // compMap.put(comp.getName(), true);
        // }
        // }

        // for (Integer id : duplicates) {
        // System.out.println(id + ",");
        // userList.addAll(CompetencyView.getUsersFromCompMetric(id, 0));
        // }

        // for (User user : userList) {
        // System.out.println(user.getId() + ",");
        // }

        List<Integer> pidList = Arrays.asList(5, 31, 37, 28, 21, 35, 23, 31, 29, 26, 26, 29);

        List<Integer> onePids = new ArrayList();
        List<Integer> zeroPids = new ArrayList<>();
        List<Integer> twoPids = new ArrayList<>();

        for (Integer pid : pidList) {
            // User user = UserView.getUserFromColValue(pid, "id").get(0);
            Map<Competency, Integer> map = CompetencyView.getCompetencyMapForUserId(pid);
            if (map.size() == 0) {
                System.out.println(pid + " - " + map.size());
                zeroPids.add(pid);
            } else if (map.size() == 1)
                onePids.add(pid);
            else if (map.size() == 2)
                twoPids.add(pid);
        }

    }

}