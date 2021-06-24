package com.people.chat.Service;

import java.sql.SQLException;

import com.google.gson.JsonObject;

public interface MessageSenderService {
    public JsonObject sendTextMsg(Integer fromPid, Integer toPid, String msg, Integer points, String accessToken) throws SQLException;

    public void sendHeartBeat(String conId);
}