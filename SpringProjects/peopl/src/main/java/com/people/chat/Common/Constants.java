package com.people.chat.Common;

public class Constants {
    public static Integer TIME_OFF_DAYS_ALLOWED = 30;
    public static Integer POINTS_PER_ACTION = 10;
    public static Integer POINTS_CONV_INITIATION = 50;
    static public Integer PN_TEXTMSG = 1;
    static public Integer OPEN_HOURS_FOR_CONV = 36;
    static public Integer PN_HEARTBEAT = 2;
    static public Integer SOURCE_LINKEDIN = 2;

    static public enum TOKEN_TYPE {
        ACCESS_TOKEN,
        RECEIPT_TOKEN
    };

    static public enum POINT_REWARD_TYPE {
        CONV_REPLY,
        CONV_INITIATE
    };
}