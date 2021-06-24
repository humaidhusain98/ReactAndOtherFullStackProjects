package com.people.chat.Service;

import java.util.List;

import com.people.chat.Model.User;
import com.people.chat.ResponseDto.ConversationDTO;

public interface ConversationService {
    // public void createConversation(Integer pid1, Integer pid2, Integer
    // initiatorPid);
    public void closeConversation(Integer conversationId);

    public Long getLastMsgTimeStamp(Integer convId);

    public List<ConversationDTO> getALLConvForUserId(User user, Boolean isClear);

    public void markConvRead(Integer userId, Integer convId);

    public boolean isConnected(Integer pid1, Integer pid2);

}