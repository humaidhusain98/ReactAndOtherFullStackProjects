package com.people.chat.Service;
import com.people.chat.Common.Constants.POINT_REWARD_TYPE;
import com.people.chat.Model.User;

public interface PointService {
    public void grantPoints(User user, Integer points, POINT_REWARD_TYPE type, Integer otherUserId);
}