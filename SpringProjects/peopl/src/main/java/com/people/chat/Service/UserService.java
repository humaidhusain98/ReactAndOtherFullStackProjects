package com.people.chat.Service;

import java.sql.SQLException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.people.chat.Common.Constants;
import com.people.chat.Model.User;
import com.people.chat.ResponseDto.UserResponseDTO;
import com.people.chat.ResponseDto.UserSignUpDTO;

import org.springframework.web.multipart.MultipartFile;

public interface UserService {
        public UserSignUpDTO setUpAndGetData(JsonObject loginInfo, Integer source, JsonObject sdkInfo,
                        JsonArray competencyList, JsonObject companyObj, JsonObject expObj);

        public UserResponseDTO processAccessToken(String fbAccessToken, Integer source, Boolean isClear, JsonElement deviceToken)
                        throws SQLException;

        public boolean updateProfilePic(MultipartFile profilePic, String socialId, String accessToken);                

        public Integer updateTimeOffDays(Integer userId, Integer timeOffDays, String fromDate, String toDate,
                        String reason);

        public void updateVacation(Integer vacationDays, Integer userId) throws SQLException;

        public boolean validateToken(String token, Constants.TOKEN_TYPE tokenType, User user);

}