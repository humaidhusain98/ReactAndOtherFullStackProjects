package com.people.chat.ResponseDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;
import com.people.chat.Model.User;
import com.people.chat.Service.PacketServices;
import com.people.chat.Service.SearchService;
import com.people.chat.Service.SearchServiceImpl;
import com.people.chat.View.CommonView;
import com.people.chat.View.UserView;

public class ConversationDTO {
    List<MessageDTO> msgList;
    Integer convoId;
    Long timeMillsRemaining;
    Boolean isOpen;
    Integer pendingPid;
    Long lastUpdated;
    Boolean otherUserOnLeave;
    String otherUserLeaveDetails;
    JsonObject userDetails;

    public ConversationDTO(List<MessageDTO> msgList, Integer id, Long timeMillsRemaining, Boolean isOpen,
            Integer pendingPid, Long lastUpdated, List<Integer> pidList, User user, Integer userId) {
        this.msgList = msgList;
        this.convoId = id;
        this.timeMillsRemaining = timeMillsRemaining;
        this.isOpen = isOpen;
        this.pendingPid = pendingPid;
        this.lastUpdated = lastUpdated;

        if (user.getOnLeave()) {
            this.otherUserOnLeave = true;
            this.otherUserLeaveDetails = user.getName() + " " + CommonView.getLeaveDetials(user.getId());
        }

        List<User> userList = new ArrayList<>();
        userList.add(user);
        SearchService searchService = new SearchServiceImpl();
        SearchResponseDTO details = searchService.getSearchDetails(userList, userId).get(0);
        this.userDetails = new JsonObject();
        PacketServices.addUserMiscDetailsToPacket(this.userDetails, details);
        // this.userDetails.addProperty("city", user.getCity());
        // this.userDetails.addProperty("country", user.getCountry());
    }

}