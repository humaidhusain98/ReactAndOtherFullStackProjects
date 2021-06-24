package com.people.chat.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.people.chat.Common.Constants;
import com.people.chat.Model.Conversation;
import com.people.chat.Model.Message;
import com.people.chat.Model.User;
import com.people.chat.Utils.CommonUtils;
import com.people.chat.View.CommonView;
import com.people.chat.View.ConversationView;
import com.people.chat.View.DatabaseHandler;
import com.people.chat.View.MessageView;
import com.people.chat.View.UserView;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerServices {
    // @Scheduled(cron = "0 0 0 1 1/1 *")

    // Reference -
    // https://www.freeformatter.com/cron-expression-generator-quartz.html

    // @Scheduled(fixedRate = 180000)
    // public void doSomething() throws SQLException {
    // // System.out.println("WTF!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    // String query = "select pg_terminate_backend(pid) from pg_stat_activity where
    // usename = 'peopl987321' and state = 'idle' and query_start <
    // current_timestamp - interval '3 minutes';";

    // ResultSet results = null;
    // Connection conn = null;
    // PreparedStatement ptstmnt = null;

    // try {
    // BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
    // conn = dataSource.getConnection();
    // ptstmnt = conn.prepareStatement(query);
    // results = ptstmnt.executeQuery();

    // } catch (SQLException e) {
    // System.out.print(e.getMessage());
    // } finally {
    // if (conn != null) {
    // try {
    // conn.close();
    // if (results != null)
    // results.close();
    // ptstmnt.close();
    // } catch (SQLException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    // }
    // }
    // }

    @Scheduled(fixedRate = 150000)
    public void closeConversations() throws SQLException {
        // System.out.println("CLosing Conversations");
        // TODO - COMMON CODE FROM CONV SERVICE - MOVE THIS TO FN
        List<Conversation> convList = ConversationView.getAllOpenConversations();
        for (Conversation conv : convList) {
            Integer convId = conv.getId();
            MessageService msgService = new MessageServiceImpl();
            List<Message> msgList = msgService.getSortedMsgListForConv(convId, 0, true);

            if (msgList == null || msgList.size() == 0) {
                continue;
            }

            Long lastMsgPostedTimeStamp = msgList.get(msgList.size() - 1).getpostedAt();
            Long currTime = new Date().getTime();
            Long timeMillsRemaining = currTime - lastMsgPostedTimeStamp;
            if (timeMillsRemaining > TimeUnit.HOURS.toMillis(Constants.OPEN_HOURS_FOR_CONV)) {
                if (conv.getIsOpen()) {
                    System.out.println("Found an open convo that needs to be closed");
                    MessageSenderService mSenderService = new MessageSenderServiceImpl<>();
                    List<User> toUserList = UserView.getUserFromColValue(conv.getLastSentPID(), "id");
                    if (toUserList.size() == 0) {
                        System.out.println("Validation for access token failed");
                        continue;
                    }

                    User toUser = toUserList.get(0);
                    System.out.println("Pending USER = " + conv.getPendingPid() + " FROM USER = " + toUser.getId());
                    try {
                        if(toUser.getId() == conv.getPendingPid()) continue;
                        mSenderService.sendTextMsg(toUser.getId(), conv.getPendingPid(),
                                "No response has been recorded in the last 36 hours, thus the System has auto-closed the conversation. Please reopen the conversation if you want to converse further. Thanks",
                                0, toUser.getAccessToken());
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    ConversationService convService = new ConversationServiceImpl();
                    convService.closeConversation(conv.getId());
                }
            }
        }
    }

    @Scheduled(fixedRate = 60000)
    public void sendHeartBeat() {
        String query = "select * from connection";
        ResultSet results = null;
        Connection conn = null;
        PreparedStatement ptstmnt = null;
        try {
            BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
            conn = dataSource.getConnection();
            ptstmnt = conn.prepareStatement(query);
            results = ptstmnt.executeQuery();
            MessageSenderService senderService = new MessageSenderServiceImpl();
            while (results != null && results.next()) {
                Integer pid = results.getInt(1);
                if (pid == null)
                    continue;
                String conId = results.getString(2);
                Long lastUsed = results.getLong(4);
                Long timeNow = new Date().getTime();

                Long diffSec = (timeNow - lastUsed) / 1000;
                if (diffSec > 60) {
                    senderService.sendHeartBeat(conId);
                }

            }

        } catch (SQLException e) {
            // System.out.print(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    if (results != null)
                        results.close();
                    ptstmnt.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    // e.printStackTrace();
                }
            }
        }

    }

    // public static void main(String[] args) {
    // Long t1 = 1595108665667L;
    // Long t2 = new Date().getTime();
    // long diffSec = (t2 - t1) / 1000;
    // System.out.println("DIFF === " + diffSec);
    // if (diffSec > 120) {
    // System.out.println("Aiyo");
    // }

    // }

    // TODO - JUST CALL THIS ONCE A DAY
    // @Scheduled(cron = "0 0 2,7,13,17,22 * * ?")
    @Scheduled(cron = "0 1 1 * * ?")
    public static void updateVacations() {
        System.out.println("Updating Vacation Records");
        String query = "select * from leavehistory where isprocessed = false";

        ResultSet results = null;
        Connection conn = null;
        PreparedStatement ptstmnt = null;
        List<Integer> vacationHistIds = new ArrayList<>();
        BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
        UserService userService = new UserServiceImpl();

        try {
            conn = dataSource.getConnection();
            ptstmnt = conn.prepareStatement(query);
            results = ptstmnt.executeQuery();
            while (results.next()) {
                Integer userId = results.getInt(2);
                String fromDate = results.getString(3);
                String toDate = results.getString(4); 

                //TODO - GET THE toDate ACCORDING TO THE VACATION BALANCE 

                String reason = results.getString(6);
                if (CommonUtils.isToday(fromDate)) { 
                    System.out.println("Today's date found");
                    Integer vacationDays = CommonUtils.getDays(fromDate, toDate);
                    userService.updateVacation(vacationDays, userId);
                    CommonView.createVacationRecord(userId, fromDate, toDate, reason);
                } else if (CommonUtils.isDatePassed(toDate)) {
                    userService.updateVacation(-1, userId);
                    CommonView.removeVacationRecord(results.getInt(2)); // Just delete this record
                    vacationHistIds.add(results.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    if (results != null)
                        results.close();
                    ptstmnt.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        try {
            for (Integer id : vacationHistIds) {
                System.out.println("Leave History Id = " + id);
                query = "Update leavehistory set isprocessed = true where id = " + id;
                System.out.println(query);
                conn = dataSource.getConnection();
                ptstmnt = conn.prepareStatement(query);
                ptstmnt.execute();
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    if (results != null)
                        results.close();
                    ptstmnt.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) {
        // updateVacations();
    }

    @Scheduled(cron = "0 0 0 1 1 ?") // something that should execute on 1st day every Jan @ 00:00
    public void resetTimeOff() {
        String query = "Updates users set timeoffdays = 0";
        Connection conn = null;
        PreparedStatement ptstmnt = null;
        try {
            BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
            conn = dataSource.getConnection();
            ptstmnt = conn.prepareStatement(query);
            ptstmnt.executeUpdate();
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    ptstmnt.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}