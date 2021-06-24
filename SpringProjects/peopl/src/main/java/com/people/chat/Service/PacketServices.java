package com.people.chat.Service;

import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.people.chat.Model.User;
import com.people.chat.ResponseDto.SearchResponseDTO;

public class PacketServices {
    public static void addUserMiscDetailsToPacket(JsonObject dataObj, SearchResponseDTO details) {
        JsonArray compList = new JsonArray();
        List<JsonObject> compMap = details.getCompMap();
        for (JsonObject jsonObject : compMap) {
            compList.add((JsonElement) jsonObject);
        }
        dataObj.add("compDetails", (JsonElement) compList);
        dataObj.addProperty("companyName", details.getCompany().getName());
        dataObj.addProperty("companyId", details.getCompany().getId());
        dataObj.addProperty("workExp", details.getTotalWorkExp());
        dataObj.addProperty("professionName", details.getProf().getName());
        dataObj.addProperty("professionId", details.getProf().getId());
        dataObj.addProperty("city", details.getCity());
        dataObj.addProperty("country", details.getCountry());
    }

    public static void deserializeUserData(JsonObject jsonObj, User user) {
        jsonObj.addProperty("id", user.getId());
        jsonObj.addProperty("name", user.getName());
        jsonObj.addProperty("emailAddress", user.getEmailAddress());
        jsonObj.addProperty("socialId", user.getSocialId());
        jsonObj.addProperty("source", user.getSource());
        jsonObj.addProperty("profilePicUrl", user.getProfilePicUrl());
        jsonObj.addProperty("freeMsgCount", user.getFreeMsgCount());
        jsonObj.addProperty("timeOffDays", user.getTimeOffDays());
        jsonObj.addProperty("totalYearsOfExp", user.getTotalYearsOfExp());
        jsonObj.addProperty("currentCompanyId", user.getCurrentCompanyId());
        jsonObj.addProperty("currentProfessionId", user.getCurrentProfessionId());
        jsonObj.addProperty("points", user.getPoints());
        jsonObj.addProperty("city", user.getCity());
        jsonObj.addProperty("country", user.getCountry());
        jsonObj.addProperty("onLeave", user.getOnLeave());
    }
}