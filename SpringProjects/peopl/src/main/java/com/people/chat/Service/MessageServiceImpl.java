package com.people.chat.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.people.chat.Model.Message;
import com.people.chat.View.MessageView;

public class MessageServiceImpl implements MessageService {

    @Override
    public List<Message> getSortedMsgListForConv(Integer convId, Integer userId, Boolean isClear) {
        List<Message> msgList = MessageView.getMsgsForConvo(convId, userId, isClear);
        // System.out.println("Msg list for convId = " + convId);

        Collections.sort(msgList, new Comparator<Message>() {
            public int compare(Message m1, Message m2) {
                return m1.getpostedAt().compareTo(m2.getpostedAt());
            }
        });

        // for (Message msg : msgList) {
        // System.out.println(msg.getData() + " -- " + msg.getpostedAt());
        // }
        // System.out.println("\n");
        return msgList;
    }

}