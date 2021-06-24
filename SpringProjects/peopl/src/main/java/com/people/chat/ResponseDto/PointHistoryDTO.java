package com.people.chat.ResponseDto;

public class PointHistoryDTO{
    String userName;
    Integer points;
    String sourceType;
    String date;


    public PointHistoryDTO(String userName, Integer points, Integer type, String date) {
        this.userName = userName;
        this.points = points;
        this.sourceType = (type == 1 ? "Conversation Initiated" : "Replied within 36 hours");
        this.date = date;
    }


    public String getUserName() {
        return this.userName;
    }

    public Integer getPoints() {
        return this.points;
    }

    public String getSourceType() {
        return this.sourceType;
    }

    public String getDate() {
        return this.date;
    }


}