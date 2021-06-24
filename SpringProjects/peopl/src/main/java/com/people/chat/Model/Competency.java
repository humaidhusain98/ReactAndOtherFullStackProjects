package com.people.chat.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Competency {
    Integer id;
    String name;
    Integer professionId;

    public Competency(Integer id, String name, Integer professionId) {
        this.id = id;
        this.name = name;
        this.professionId = professionId;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProfessionId() {
        return this.professionId;
    }

    public void setProfessionId(Integer professionId) {
        this.professionId = professionId;
    }

    public static Competency getCompetency(ResultSet result) {
        try {
            Competency compObj = new Competency(result.getInt(1), result.getString(2), result.getInt(3));
            // if(result != null) result.close();
            return compObj;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public JsonObject toJsonObj(Integer y) {
        JsonObject compObj = new JsonObject();
        compObj.addProperty("name", this.getName());
        compObj.addProperty("id", this.getId());
        compObj.addProperty("yearsOfExp", y);
        return compObj;
    }
}