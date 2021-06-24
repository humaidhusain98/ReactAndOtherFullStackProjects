package com.people.chat.ResponseDto;

import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.people.chat.Model.Company;
import com.people.chat.Model.Profession;
import com.people.chat.View.CommonView;

public class SearchResponseDTO {
    String name, profileImgUrl;
    Integer pid;
    List<JsonObject> compMap;
    Integer totalWorkExp;
    Company company;
    Boolean connected;
    Profession prof;
    String leaveDetails = "";
    String country;
    String city;

    public SearchResponseDTO(String name, List<JsonObject> compMap, Integer totalWorkExp, Company company,
            Boolean connected, Integer pid, Profession prof, String profileImgUrl, Boolean isOnLeave, String city, String country) {
        this.name = name;
        this.compMap = compMap;
        this.totalWorkExp = totalWorkExp;
        this.company = company;
        this.connected = connected;
        this.pid = pid;
        this.prof = prof;
        this.profileImgUrl = profileImgUrl;
        if (isOnLeave) {
            leaveDetails = name + " " + CommonView.getLeaveDetials(pid);
        }
        this.city = city;
        this.country = country;
    }


    public String getName() {
        return this.name;
    }

    public String getProfileImgUrl() {
        return this.profileImgUrl;
    }

    public Integer getPid() {
        return this.pid;
    }

    public List<JsonObject> getCompMap() {
        return this.compMap;
    }

    public Integer getTotalWorkExp() {
        return this.totalWorkExp;
    }

    public Company getCompany() {
        return this.company;
    }

    public Boolean getConnected() {
        return this.connected;
    }

    public Boolean isConnected() {
        return this.connected;
    }

    public Profession getProf() {
        return this.prof;
    }

    public String getLeaveDetails() {
        return this.leaveDetails;
    }

    public String getCountry() {
        return this.country;
    }

    public String getCity() {
        return this.city;
    }
    

}