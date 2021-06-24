package com.people.chat.ResponseDto;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonObject;
import com.people.chat.Common.Constants;
import com.people.chat.Model.Competency;
import com.people.chat.Model.Job;
import com.people.chat.Model.PointHistory;
import com.people.chat.Model.PopupObj;
import com.people.chat.Model.User;
import com.people.chat.Model.applyJob;
import com.people.chat.Service.CompetencyService;
import com.people.chat.Service.CompetencyServiceImpl;
import com.people.chat.Service.PacketServices;
import com.people.chat.Utils.CommonUtils;
import com.people.chat.View.CommonView;
import com.people.chat.View.UserView;

public class UserResponseDTO {
    List<ConversationDTO> conv;
    JsonObject us;
    Boolean newUser;
    String msg;
    Integer timeOffDaysBalance;
    List<JsonObject> compData;
    PopupObj popupObj;
    Map<String, List<PointHistoryDTO>> pointData;
    LeaveResponseDTO leaveDetails;
    List<applyJob> listapplyJob;
    List<Job> jobslist;
    String webSocketUrl;

    public UserResponseDTO(List<ConversationDTO> conv, User us, Boolean newUser, String msg, PopupObj popupObj,List<applyJob> listapplyjob,List<Job> jobslist) {
        this.conv = conv;
        this.us = new JsonObject();
        this.newUser = newUser;
        this.msg = msg;
        this.listapplyJob=listapplyjob;
        this.jobslist=jobslist;
        if (us != null) {
            this.timeOffDaysBalance = Constants.TIME_OFF_DAYS_ALLOWED - us.getTimeOffDays();
        }
        this.popupObj = popupObj;
        this.compData = new ArrayList<>();
        if (us != null) {
            CompetencyService compService = new CompetencyServiceImpl();

            // System.out.println("SOUMIK User Id == " + us.getId());
            Map<Competency, Integer> compMap = compService.getCompListForUserId(us.getId());
            compMap.entrySet().stream().forEach(e -> {
                Competency competency = e.getKey();
                Integer exp = e.getValue();
                if (competency != null)
                    this.compData.add(competency.toJsonObj(exp));
            });
            try {
                this.pointData = getPointresponseDto(us);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (us.getOnLeave()) {
                this.leaveDetails = us.getLeaveResponseDTO();
            }
            PacketServices.deserializeUserData(this.us, us);
            this.webSocketUrl = "wss://vlelrkmn3h.execute-api.us-east-1.amazonaws.com/dev";
        } else
            this.compData = null;

        // System.out.println("SIZE === " + this.compData.size());
    }

    private Map<String, List<PointHistoryDTO>> getPointresponseDto(User user) throws SQLException {
        List<PointHistory> pointHistory = CommonView.getPointHistoryForUser(user.getId());
        Map<String, List<PointHistoryDTO>> pointData = new HashMap<>();
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<User> otherUsers = null;
        User otherUser = null;

        for (PointHistory pHistory : pointHistory) {
            Date date = new Date(pHistory.getTimestamp());
            otherUsers = UserView.getUserFromColValue(pHistory.getOtherPid(), "id");
            if (otherUsers == null || otherUsers.size() == 0)
                continue;

            otherUser = otherUsers.get(0);
            String dateString = myFormat.format(date);
            String[] dates = dateString.split("-");
            String monthName = CommonUtils.getMonthName(Integer.parseInt(dates[1]));
            String key = monthName + " " + dates[0];
            PointHistoryDTO pointHistoryDTO = new PointHistoryDTO(otherUser.getName(), pHistory.getPoint(),
                    pHistory.getType().ordinal(), (monthName + " " + dates[2]));
            List<PointHistoryDTO> pointList = null;
            if (pointData.containsKey(key)) {
                pointList = pointData.get(key);
            } else {
                // System.out.println("New Key!!");
                pointList = new ArrayList<>();
            }
            pointList.add(pointHistoryDTO);
            pointData.put(key, pointList);
        }
        return pointData;
    }

    public static void main(String[] args) throws SQLException {
        // User user = UserView.getUserFromColValue(31, "id").get(0);
        // Map<String, List<PointHistoryDTO>> pointData = getPointresponseDto(user);
        // System.out.println("\n");
        // for (String key : pointData.keySet()) {
        // List<PointHistoryDTO> p = pointData.get(key);
        // System.out.println(key);
        // for (PointHistoryDTO d : p) {
        // System.out.println(
        // d.getDate() + "\n" + d.getSourceType() + "\n" + d.getUserName() + "\n" +
        // d.getPoints() + "\n");
        // }
        // System.out.println("\n=================\n");
        // }
    }

}