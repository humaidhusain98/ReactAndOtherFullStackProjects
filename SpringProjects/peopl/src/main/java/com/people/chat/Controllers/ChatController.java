package com.people.chat.Controllers;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.people.chat.Common.Constants;
import com.people.chat.Service.ConversationService;
import com.people.chat.Service.ConversationServiceImpl;
import com.people.chat.Service.MessageSenderService;
import com.people.chat.Service.MessageSenderServiceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ChatController.ENDPOINT)
public class ChatController {

    static final String ENDPOINT = "/chat";

    @ResponseBody
    @RequestMapping(value = "/sendMsg", method = RequestMethod.POST)
    public ResponseEntity<?> saveData(HttpServletRequest request) throws SQLException {
        // System.out.println("1 - " + request.getParameter("toPid") + " 2 - " + request.getParameter("fromPid"));
        Integer fromPid = Integer.parseInt(request.getParameter("fromPid"));
        Integer toPid = Integer.parseInt(request.getParameter("toPid"));
        String msg = request.getParameter("msg");
        MessageSenderService msgService = new MessageSenderServiceImpl();
        Integer points = Constants.POINTS_PER_ACTION;
        String accessToken = request.getParameter("accessToken");
        JsonObject result = msgService.sendTextMsg(fromPid, toPid, msg, points, accessToken);
        return new ResponseEntity<Object>(new Gson().toJson(result), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/conv/read/", method = RequestMethod.POST)
    public ResponseEntity<?> markAsRead(HttpServletRequest request) throws SQLException {
        Integer convId = Integer.parseInt(request.getParameter("convId"));
        Integer userId = Integer.parseInt(request.getParameter("userId"));

        // System.out.println("SOUMIK === " + fromPid + " -- " + toPid);
        ConversationService convService = new ConversationServiceImpl();
        convService.markConvRead(userId, convId);
        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/conv/close/", method = RequestMethod.POST)
    public ResponseEntity<?> closeConv(HttpServletRequest request) throws SQLException {
        Integer convId = Integer.parseInt(request.getParameter("convId"));

        ConversationService convService = new ConversationServiceImpl();
        convService.closeConversation(convId);
        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }

}