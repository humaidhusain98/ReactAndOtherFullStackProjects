package com.people.chat.Service;

import java.sql.SQLException;

import com.people.chat.Common.Constants;
import com.people.chat.Common.Constants.POINT_REWARD_TYPE;
import com.people.chat.Model.User;
import com.people.chat.View.CommonView;
import com.people.chat.View.UserView;

public class PointServiceImpl implements PointService {

    @Override
    public void grantPoints(User user, Integer points, POINT_REWARD_TYPE type, Integer otherPid) {
        user.setPoints(user.getPoints() + points);
        UserView.saveUser(user);

        //Create Point History
        CommonView.createPointHistory(user.getId(), otherPid, points, type.ordinal());
    }
 

}