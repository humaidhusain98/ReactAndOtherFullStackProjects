package com.people.chat.ResponseDto;

public class MessageDTO {
    String text;
    Integer fromPid; String fromUser, toUser; String fromUserProfilePicUrl; String toUserProfilePicUrl;
    Integer toPid;
    Integer msgId;
    Long timestamp;
    Boolean isRead;

    public MessageDTO(String text, Integer fromPid, Integer toPid, Integer msgId, Long timestamp, Boolean isRead, String fromUser, String toUser, String fromPic, String toPic) {
        this.text = text;
        this.fromPid = fromPid;
        this.toPid = toPid;
        this.msgId = msgId;
        this.timestamp = timestamp;
        this.isRead = isRead;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.fromUserProfilePicUrl = fromPic;
        this.toUserProfilePicUrl = toPic;
    }

}