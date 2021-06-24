package com.people.chat.Service;

import java.util.List;

import com.people.chat.Model.Message;

public interface MessageService {
    public List<Message> getSortedMsgListForConv(Integer convId, Integer userId, Boolean isClear);
}