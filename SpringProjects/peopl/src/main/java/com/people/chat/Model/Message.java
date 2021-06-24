package com.people.chat.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Message {
    Integer id;
    String data;
    Integer conversationId;
    Boolean delivered, read;
    Long postedAt;
    Integer fromPid;
    Integer toPid;

    public Integer getFromPid() {
        return this.fromPid;
    }

    public void setFromPid(Integer fromPid) {
        this.fromPid = fromPid;
    }

    public Integer getToPid() {
        return this.toPid;
    }

    public void setToPid(Integer toPid) {
        this.toPid = toPid;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getConversationId() {
        return this.conversationId;
    }

    public void setConversationId(Integer conversationId) {
        this.conversationId = conversationId;
    }

    public Boolean isDelivered() {
        return this.delivered;
    }

    public Boolean getDelivered() {
        return this.delivered;
    }

    public void setDelivered(Boolean delivered) {
        this.delivered = delivered;
    }

    public Boolean isRead() {
        return this.read;
    }

    public Boolean getRead() {
        return this.read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public Long getpostedAt() {
        return this.postedAt;
    }

    public void setpostedAt(Long postedAt) {
        this.postedAt = postedAt;
    }

    public static Message getMessageFromSQL(ResultSet result) {
        try {
            return new Message(result.getInt(5), result.getString(1), result.getInt(2), result.getBoolean(3),
                    result.getBoolean(4), result.getLong(6), result.getInt(7), result.getInt(8));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public Message(Integer id, String data, Integer conversationId, Boolean delivered, Boolean read, Long postedAt,
            Integer fromPid, Integer toPid) {
        this.id = id;
        this.data = data;
        this.conversationId = conversationId;
        this.delivered = delivered;
        this.read = read;
        this.postedAt = postedAt;
        this.fromPid = fromPid;
        this.toPid = toPid;
    }

}