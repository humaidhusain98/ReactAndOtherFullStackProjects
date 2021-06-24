package com.people.chat.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.people.chat.Common.Constants;
import com.people.chat.Common.Constants.POINT_REWARD_TYPE;
import com.people.chat.Model.Conversation;
import com.people.chat.Model.Message;
import com.people.chat.Model.User;
import com.people.chat.ResponseDto.SearchResponseDTO;
import com.people.chat.Utils.AwsUtils;
import com.people.chat.Utils.PushNotifUtils;
import com.people.chat.View.CommonView;
import com.people.chat.View.ConnectionView;
import com.people.chat.View.ConversationView;
import com.people.chat.View.MessageView;
import com.people.chat.View.UserView;

import org.json.JSONArray;
import org.json.JSONException;

public class MessageSenderServiceImpl<Output> implements MessageSenderService {
    final String POSTURL = "https://vlelrkmn3h.execute-api.us-east-1.amazonaws.com/dev/@connections/";
    ConversationService convService = new ConversationServiceImpl();

    @Override
    public JsonObject sendTextMsg(Integer fromPid, Integer toPid, String msg, Integer points, String accessToken)
            throws SQLException {
        String connectionId = ConnectionView.getConnectionId(toPid);
        JsonObject ret = new JsonObject();
        String result = "Success";
        JsonObject dataObj = new JsonObject();

        System.out.println("\n\nIn Here for msg = " + msg);

        List<User> fromUserList = UserView.getUserFromColValue(fromPid, "id");
        User fromUser = null, toUser = null;
        if (fromUserList != null && fromUserList.size() > 0) {
            fromUser = UserView.getUserFromColValue(fromPid, "id").get(0);
        }

        List<User> toUserList = UserView.getUserFromColValue(toPid, "id");
        if (toUserList != null && toUserList.size() > 0) {
            toUser = toUserList.get(0);
        }
        if (fromUser == null || toUser == null)
            return null;

        UserService userService = new UserServiceImpl();
        if (!userService.validateToken(accessToken, Constants.TOKEN_TYPE.ACCESS_TOKEN, fromUser)) {
            System.out.println("Validation failed");
            ret.addProperty("result", "Validattion failed");
            // System.out.println("Validation failed");
            return ret;
        }
        // System.out.println("ToPid = " + toPid + " From PID = " + fromPid);
        Conversation existingConv = ConversationView.getExistingConvoBetweenPids(fromPid, toPid, toPid);
        PointService pointService = new PointServiceImpl();
        SearchService searchService = new SearchServiceImpl();

        if (existingConv == null) {
            List<Integer> pidList = new ArrayList<>();
            pidList.add(fromPid);
            pidList.add(toPid);
            Collections.sort(pidList);
            existingConv = ConversationView.createConversation(new Conversation(-1, true, pidList, toPid));
            System.out.println("Granting points for initiation");
            pointService.grantPoints(fromUser, Constants.POINTS_CONV_INITIATION, POINT_REWARD_TYPE.CONV_INITIATE,
                    toPid);
            SearchResponseDTO details = searchService.getSearchDetails(fromUserList, toPid).get(0);
            PacketServices.addUserMiscDetailsToPacket(dataObj, details);

            // dataObj.addProperty("city", fromUser.getCity());
            // dataObj.addProperty("country", fromUser.getCountry());

            // JsonArray compList = new JsonArray();
            // List<JsonObject> compMap = details.getCompMap();
            // for (JsonObject jsonObject : compMap) {
            // compList.add((JsonElement) jsonObject);
            // }
            // dataObj.add("compDetails", (JsonElement) compList);
            // dataObj.addProperty("companyName", details.getCompany().getName());
            // dataObj.addProperty("companyId", details.getCompany().getId());
            // dataObj.addProperty("workExp", details.getTotalWorkExp());
            // dataObj.addProperty("professionName", details.getProf().getName());
            // dataObj.addProperty("professionId", details.getProf().getId());
        }

        if (!existingConv.isIsOpen()) {
            existingConv.setIsOpen(true);
        } else {
            if (existingConv.getPendingPid() == fromPid) {
                pointService.grantPoints(fromUser, points, POINT_REWARD_TYPE.CONV_REPLY, toPid);
            }
        }

        Message message = processMsg(msg, existingConv.getId(), fromPid, toPid);
        if (points == 0) {
            dataObj.addProperty("convClose", true);
        }

        if (toUser.getOnLeave()) {
            System.out.println("Through this");
            result = toUser.getName() + " " + CommonView.getLeaveDetials(toPid);
            ret.addProperty("onLeave", true);
        }
        boolean isOnline = true;
        if (connectionId == null) {
            // call FCM
            isOnline = false;
        } else {
            String userName = "";
            String profilePicUrl = "";
            userName = fromUser.getName();
            profilePicUrl = fromUser.getProfilePicUrl();
            ConnectionView.updateConnectionLastUsed(connectionId);

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("ConnectionId", connectionId);

            dataObj.addProperty("msg", msg);
            dataObj.addProperty("msgId", message.getId());
            dataObj.addProperty("fromPid", fromPid);
            dataObj.addProperty("fromUserName", userName);
            dataObj.addProperty("profilePicUrl", profilePicUrl);
            dataObj.addProperty("convId", existingConv.getId());
            dataObj.addProperty("type", Constants.PN_TEXTMSG);
            jsonObject.addProperty("Data", dataObj.toString());

            // This is the only standalone method to send a PN
            AwsUtils.makePostReqAPIGateway(POSTURL + connectionId, jsonObject);
        }
        existingConv.setPendingPid(toPid);
        ConversationView.save(existingConv.getId(), existingConv);
        // System.out.println("ABOUT TO CALL PROCESSMSG");
        ret.addProperty("result", result);
        ret.addProperty("convId", existingConv.getId());
        ret.addProperty("msgId", message.getId());
        System.out.println("Returning for msg = " + msg);
        if (!isOnline) {
            String title = "New message from " + fromUser.getName();
            String body = (message.getData().length() < 17 ? message.getData()
                    : (message.getData().substring(0, 14) + "..."));
            try {
                PushNotifUtils.sendPushNotification(toUser.getDeviceToken(), title, body);
            } catch (IOException | JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return ret;
    }

    // TODO - RETURN MSG ID
    private Message processMsg(String msg, Integer conversationId, Integer fromPid, Integer toPid) {
        Message message = new Message(-1, msg, conversationId, false, false, new Date().getTime(), fromPid, toPid);
        System.out.println("IN PROCESS MSG for msg = " + msg);
        return MessageView.createMsg(message);
    }

    public void sendHeartBeat(String conId) {
        JsonObject jsonObject = new JsonObject();
        JsonObject dataObj = new JsonObject();
        dataObj.addProperty("type", Constants.PN_HEARTBEAT);
        jsonObject.addProperty("Data", dataObj.toString());
        try {
            AwsUtils.makePostReqAPIGateway(POSTURL + conId, jsonObject);
        } catch(Exception e ) {

        }
        
        // ConnectionView.updateCOnnectionLastUsed(conId);
    }

    public static void main(String[] args) throws SQLException {
        MessageSenderService msgSender = new MessageSenderServiceImpl<>();
        // msgSender.sendMsg(123, 1, "Sundor!!");
    }

}