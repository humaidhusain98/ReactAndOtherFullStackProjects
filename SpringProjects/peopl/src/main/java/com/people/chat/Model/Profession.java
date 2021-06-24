package com.people.chat.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Profession {
    Integer id;
    String name;
    Boolean isHot;

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

    public Boolean isIsHot() {
        return this.isHot;
    }

    public Boolean getIsHot() {
        return this.isHot;
    }

    public void setIsHot(Boolean isHot) {
        this.isHot = isHot;
    }

    public static Profession getProfessionFromSQL(ResultSet result) {
        try {
            return new Profession(result.getInt(1), result.getString(2), result.getBoolean(3));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public Profession(Integer id, String name, Boolean isHot) {
        this.id = id;
        this.name = name;
        this.isHot = isHot;
    }

}