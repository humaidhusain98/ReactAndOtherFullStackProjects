//package com.example.oauth2.demo.service;
//
//import com.google.gson.JsonArray;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.sql.SQLException;
//
//public interface UserService {
//        public UserSignUpDTO setUpAndGetData(JsonObject loginInfo, Integer source, JsonObject sdkInfo,
//                        JsonArray competencyList, JsonObject companyObj, JsonObject expObj);
//
//        public UserResponseDTO processAccessToken(String fbAccessToken, Integer source, Boolean isClear, JsonElement deviceToken)
//                        throws SQLException;
//
//        public boolean updateProfilePic(MultipartFile profilePic, String socialId, String accessToken);
//
//        public Integer updateTimeOffDays(Integer userId, Integer timeOffDays, String fromDate, String toDate,
//                        String reason);
//
//        public void updateVacation(Integer vacationDays, Integer userId) throws SQLException;
//
//        public boolean validateToken(String token, Constants.TOKEN_TYPE tokenType, User user);
//
//}