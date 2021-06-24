package com.people.chat.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.people.chat.Common.Constants.POINT_REWARD_TYPE;

public class PointHistory {
    Integer id;
    Integer pid;
    Integer otherPid;
    Integer point;
    POINT_REWARD_TYPE type;
    Long timestamp;

    public PointHistory(Integer id, Integer pid, Integer point, Integer otherPid, POINT_REWARD_TYPE type,
            Long timestamp) {
        this.id = id;
        this.pid = pid;
        this.otherPid = otherPid;
        this.point = point;
        this.type = type;
        this.timestamp = timestamp;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return this.pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getOtherPid() {
        return this.otherPid;
    }

    public void setOtherPid(Integer otherPid) {
        this.otherPid = otherPid;
    }

    public Integer getPoint() {
        return this.point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public POINT_REWARD_TYPE getType() {
        return this.type;
    }

    public void setType(POINT_REWARD_TYPE type) {
        this.type = type;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }


    public static PointHistory getPointHistoryFromSQL(ResultSet result) {
        try {
            POINT_REWARD_TYPE pointType = POINT_REWARD_TYPE.values()[result.getInt(5)];
            return new PointHistory(result.getInt(1), result.getInt(2), result.getInt(3), result.getInt(4), pointType,
                    result.getLong(6));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}