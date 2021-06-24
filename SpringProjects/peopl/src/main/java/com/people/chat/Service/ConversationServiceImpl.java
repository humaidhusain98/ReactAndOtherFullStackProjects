package com.people.chat.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.people.chat.Common.Constants;
import com.people.chat.Model.Conversation;
import com.people.chat.Model.Message;
import com.people.chat.Model.User;
import com.people.chat.ResponseDto.ConversationDTO;
import com.people.chat.ResponseDto.MessageDTO;
import com.people.chat.View.ConversationView;
import com.people.chat.View.MessageView;
import com.people.chat.View.UserView;

import org.apache.commons.lang3.StringEscapeUtils;

public class ConversationServiceImpl implements ConversationService {

    // @Override
    // public void createConversation(Integer pid1, Integer pid2, Integer
    // initiatorPid) {

    // }

    @Override
    public void closeConversation(Integer conversationId) {
        Conversation conv = ConversationView.getConversation(conversationId);
        conv.setIsOpen(false);
        ConversationView.save(conversationId, conv);
    }

    @Override
    public Long getLastMsgTimeStamp(Integer convId) {
        return null;
    }

    @Override
    public List<ConversationDTO> getALLConvForUserId(User user, Boolean isClear) {
        List<ConversationDTO> convDTOList = new ArrayList<>();
        List<Conversation> convList = ConversationView.getAllConvosForUserId(user.getId());
        System.out.println("SOUMIK " + convList.size());
        // convList.stream().sorted((Conversation c1, conversation c2)->c1.get)
        List<User> fromUser = null;
        List<User> toUser = null;
        for (Conversation conv : convList) {
            Integer convId = conv.getId();
            MessageService msgService = new MessageServiceImpl();
            List<Message> msgList = msgService.getSortedMsgListForConv(convId, user.getId(), isClear);

            List<MessageDTO> msgDTOList = new ArrayList<>();
            if (msgList == null || msgList.size() == 0) {
                continue;
            }

            // System.out.println(msgList.size() + " ----- convo Id = " + convId);
            Long lastMsgPostedTimeStamp = msgList.get(msgList.size() - 1).getpostedAt();
            Long currTime = new Date().getTime();
            Long timeMillsRemaining = currTime - lastMsgPostedTimeStamp;
            if (timeMillsRemaining > TimeUnit.HOURS.toMillis(Constants.OPEN_HOURS_FOR_CONV)) {
                if (conv.getIsOpen()) {
                    MessageSenderService mSenderService = new MessageSenderServiceImpl<>();
                    Integer toPid = conv.getLastSentPID();
                    try {
                        mSenderService.sendTextMsg(toPid, conv.getPendingPid(),
                                "No response has been recorded in the last 36 hours, thus closing the conversation. Please reopen the conversation if you want to converse further. Thanks",
                                0, user.getAccessToken());
                        msgList = msgService.getSortedMsgListForConv(convId, user.getId(), isClear);
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    this.closeConversation(conv.getId());
                }
                timeMillsRemaining = Long.valueOf(0);
            } else {
                timeMillsRemaining = lastMsgPostedTimeStamp + TimeUnit.HOURS.toMillis(Constants.OPEN_HOURS_FOR_CONV)
                        - currTime;
            }

            for (Message msg : msgList) {
                String fromName = "", toName = "", fromPicUrl = "", toPicUrl = "";
                Boolean isFromUserOnLeave = false;
                String otherUserLeaveDetails = "";
                try {
                    fromUser = UserView.getUserFromColValue(msg.getFromPid(), "id");
                    toUser = UserView.getUserFromColValue(msg.getToPid(), "id");
                    if (fromUser.size() > 0) {
                        fromName = fromUser.get(0).getName();
                        fromPicUrl = fromUser.get(0).getProfilePicUrl();
                    }
                    if (toUser.size() > 0) { // Not needed
                        toName = toUser.get(0).getName();
                        toPicUrl = toUser.get(0).getProfilePicUrl();
                    }

                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                msg.setData(StringEscapeUtils.unescapeJava(msg.getData()));

                MessageDTO msgDTO = new MessageDTO(msg.getData(), msg.getFromPid(), msg.getToPid(), msg.getId(),
                        msg.getpostedAt(), msg.getRead(), fromName, toName, fromPicUrl, toPicUrl);
                msgDTOList.add(msgDTO);
            }
            // System.out.println("Conv Id = " + conv.getId());
            // System.out.println("SOUMIK CONV ID UFF = " + conv.getId());
            User userObject = (fromUser.get(0).getId() == user.getId() ? toUser.get(0) : fromUser.get(0)); // The Other
                                                                                                           // Guy

            convDTOList.add(new ConversationDTO(msgDTOList, convId, timeMillsRemaining, conv.getIsOpen(),
                    conv.getPendingPid(), lastMsgPostedTimeStamp, conv.getPidList(), userObject, user.getId()));
        }
        return convDTOList;
    }

    @Override
    public void markConvRead(Integer userId, Integer convId) {
        List<Message> msgList = MessageView.getMsgsForConvo(convId, userId, false);
        for (Message msg : msgList) {
            if (msg.getToPid() == userId)
                MessageView.markMsgAsRead(msg.getId());
        }
    }

    @Override
    public boolean isConnected(Integer pid1, Integer pid2) {
        try {
            return ConversationView.getExistingConvoBetweenPids(pid1, pid2);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    // public static void main(String[] args) {
    // ConversationService c = new ConversationServiceImpl();
    // System.out.println(c.getALLConvForUserId(1).toString());
    // }

}