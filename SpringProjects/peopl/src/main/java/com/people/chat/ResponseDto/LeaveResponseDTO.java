package com.people.chat.ResponseDto;

public class LeaveResponseDTO {
    Integer pauseDuration;
    String initiatedOn;
    String reason;
    String reactiveOn;

    Integer allottedTime;
    Integer pauseBalance;

    public LeaveResponseDTO(Integer pauseDuration, String initiatedOn, String reason, String reactiveOn,
            Integer pauseBalance, Integer allottedTime) {
        this.pauseDuration = pauseDuration;
        this.initiatedOn = initiatedOn;
        this.reason = reason;
        this.reactiveOn = reactiveOn;
        this.pauseBalance = pauseBalance;
        this.allottedTime = allottedTime;
    }

}