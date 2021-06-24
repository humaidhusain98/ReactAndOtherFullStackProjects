package com.people.chat.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;

import java.util.Date;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.people.chat.Common.Constants;
import com.people.chat.Model.Company;
import com.people.chat.Model.Competency;
import com.people.chat.Model.PopupObj;
import com.people.chat.Model.Profession;
import com.people.chat.Model.User;
import com.people.chat.ResponseDto.ConversationDTO;
import com.people.chat.ResponseDto.SearchResponseDTO;
import com.people.chat.ResponseDto.UserResponseDTO;
import com.people.chat.ResponseDto.UserSignUpDTO;
import com.people.chat.Utils.AwsUtils;

import com.people.chat.Utils.FacebookUtils;
import com.people.chat.Utils.LinkedInUtils;
import com.people.chat.View.CommonView;
import com.people.chat.View.UserView;

import org.springframework.web.multipart.MultipartFile;

public class UserServiceImpl implements UserService {

    @Override
    public UserSignUpDTO setUpAndGetData(JsonObject loginInfo, Integer source, JsonObject sdkInfo,
            JsonArray competencyList, JsonObject companyObj, JsonObject profObj) {
        String socialPid = loginInfo.get("socialPid").getAsString();

        JsonElement deviceTokenElement = loginInfo.get("deviceToken");
        String deviceToken = "-1";
        if (deviceTokenElement != null) {
            deviceToken = deviceTokenElement.getAsString();
        }

        CompetencyService compService = new CompetencyServiceImpl();
        CompanyService companyService = new CompanyServiceImpl();
        ProfessionService profService = new ProfessionServiceImpl();

        // Create Company if needed
        Company comp = companyService.createIfNotPresent(companyObj);
        // Create profession if needed
        Profession prof = profService.createProfIfNeeded(profObj);

        String city = loginInfo.get("city") != null ? loginInfo.get("city").getAsString() : "Bengaluru";
        String country = loginInfo.get("country") != null ? loginInfo.get("country").getAsString() : "India";

        String profilePicUrl = this.getProfilePicUrl(socialPid, source, loginInfo.get("profilePicUrl").getAsString());

        Float val = Float.parseFloat(profObj.get("totalExp").getAsString());
        Integer yearsOfExp = Math.round(val);

        User user = new User(-1, loginInfo.get("name").getAsString().replace("'", "''"),
                loginInfo.get("email").getAsString().replace("'", "''"), socialPid, source, 10, 0, yearsOfExp,
                comp.getId(), prof.getId(), new Date().getTime(), 0, new Date().getTime(),
                sdkInfo.get("accessToken").getAsString(), profilePicUrl.replace("'", "''"), city, country, false,
                deviceToken); // Encryption Needed
        // Create user entry
        UserView.createUser(user);
        System.out.println("Successfully created user");
        // Create Competency if needed
        List<Competency> compList = compService.createCompetenciesIfNeeded(competencyList, user.getId());

        return new UserSignUpDTO(comp, compList, prof);
    }

    private String getProfilePicUrl(String socialId, Integer source, String url) {
        try {
            Boolean isLinkedInLogin = false;
            if (source == Constants.SOURCE_LINKEDIN) {
                isLinkedInLogin = true;
                AwsUtils.uploadProfilePic(url, socialId);
            } else {
                // Facebook Login
                String imgUrl = FacebookUtils.GRAPH_API_URL + socialId + "/picture?type=small&redirect=false";
                FacebookUtils.downloadProfilePic(imgUrl, socialId);
            }
            String imgUrl = "https://profileimg.s3.ap-south-1.amazonaws.com/" + socialId + "/profilePic.";
            imgUrl = imgUrl.concat("jpeg");
            return imgUrl;
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

    public String getUserData(Integer socialPid) {
        // fetch all data with respect to user
        return null;
    }

    @Override
    public UserResponseDTO processAccessToken(String accessCode, Integer source, Boolean isClear,
            JsonElement deviceTokenElement) throws SQLException {
        // TODO CLEANUP - THIS CODE IS SHIT
        User user = null;
        String profileData = "";
        String socialId = "";
        Boolean isLinkedInLogin = false;
        Boolean needToSave = false;

        String deviceToken = "-1";
        // if (deviceTokenElement != null) {
            
        // }

        try {
            deviceToken = (deviceTokenElement != JsonNull.INSTANCE ? deviceTokenElement.getAsString() : "-1");
        } catch(Exception e) {
            deviceToken = "-1";
        }
        

        if (source == Constants.SOURCE_LINKEDIN) {
            // TODO - HERE accessCode is the auth code
            // if (!isClear) {
            // accessCode = accessCode.split("\\|")[0];
            // // accessCode = EncryptUtils.decrypt(accessCode);
            // }
            isLinkedInLogin = true;
        }
        Boolean existingToken = UserView.isAccessTokenPresent(accessCode); // Encryption Search Needed
        if (!existingToken) {
            if (isLinkedInLogin) {
                String profileJson = LinkedInUtils.fetchProfileData(accessCode);
                if (profileJson == null) {
                    return new UserResponseDTO(null, null, false,
                            "Unable to Retrieve Profile Data. Please try using FaceBook Login", null,null,null);
                }
                profileData = new String(profileJson);
                socialId = profileData.split("\\|")[3];
            } else {
                // process AccessToken to fetch user profiel ID and assign that to socialId
                org.springframework.social.facebook.api.User fbUser = null;
                Object obj = FacebookUtils.fetchUserDataFromAccessToken(accessCode);
                if (obj.getClass() == JsonObject.class) {
                    JsonObject jsonObj = (JsonObject) obj;
                    return new UserResponseDTO(null, null, false, jsonObj.get("error").getAsString(), null,null,null);
                }
                fbUser = (org.springframework.social.facebook.api.User) obj;
                socialId = fbUser.getId();
                // System.out.println(fbUser.getEmail());
            }
        }

        // Common code
        List<User> userList = UserView.getUserFromColValue(accessCode, "accesstoken", false);
        if (userList.size() > 0) {
            user = userList.get(0);
        }

        // New Access Token
        if (user == null) {
            // Now fetch from Social ID

            userList = UserView.getUserFromColValue(socialId.toString(), "socialid", false);
            if (userList.size() > 0)
                user = userList.get(0);
            if (user == null) {
                // Social id has never logged in :(
                if (!isLinkedInLogin)
                    return new UserResponseDTO(null, null, true, null, null,null,null);
                else {
                    // Long timeStamp = new Date().getTime();
                    // String authCode = accessCode.concat("|").concat(timeStamp.toString());
                    profileData = profileData.concat("|").concat(accessCode);

                    System.out.println("\nprofile data = " + profileData);
                    return new UserResponseDTO(null, null, true, profileData, null,null,null);
                }
            } else {
                // Update Access Token
                // UserView.updateAccessToken(user.getId(), accessCode); // Encryption Needed
                needToSave = true;
                user.setAccessToken(accessCode);
            }
        }

        if (deviceToken != user.getDeviceToken()) {
            needToSave = true;
            user.setDeviceToken(deviceToken);
        }

        if (needToSave) {
            UserView.saveUser(user);
        }
        // Whateever accessToken you send, you get his Data
        /*
         * if (user.getSocialId() != socialId) { // Some one trying to make false calls
         * return null; }
         */
        // Social Id and fb profile Id has matched,

        ConversationService conversationService = new ConversationServiceImpl();
        UserResponseDTO us = null;
        List<ConversationDTO> convDTO = conversationService.getALLConvForUserId(user, isClear);

        PopupObj popupData = this.getPopupDataForUser(user);
        us = new UserResponseDTO(convDTO, user, false, null, popupData,null,null);
        // System.out.println(userData.get("conversationData").getAsString());
        return us;
    }

    private PopupObj getPopupDataForUser(User user) {
        // return new PopupObj(
        // "You are using an old version of the app. Please update to a newer version to
        // access newer features.",
        // "Update", "https://www.facebook.com", false);
        return null;
    }

    @Override
    public Integer updateTimeOffDays(Integer userId, Integer vacationDays, String fromDate, String toDate,
            String reason) {
        List<User> users;
        try {
            users = UserView.getUserFromColValue(userId, "id");
            if (users == null || users.size() == 0)
                return null;
            User user = users.get(0);
            CommonView.createVacationHistory(userId, fromDate, toDate, reason, false);
            if (user.getTimeOffDays() + vacationDays < Constants.TIME_OFF_DAYS_ALLOWED) {
                return vacationDays;
            } else
                return Constants.TIME_OFF_DAYS_ALLOWED - user.getTimeOffDays();
            /*
             * Boolean datesInSameMonth = CommonUtils.datesInSameMonth(fromDate, toDate); if
             * (datesInSameMonth) { // Not needed for now // if
             * (CommonUtils.isToday(fromDate)) { // user.setOnLeave(true); //
             * UserView.saveUser(user); // } CommonView.createVacationHistory(userId,
             * fromDate, toDate, false); return 0; }
             * 
             * String endDate = CommonUtils.getLastDateOfMonth(fromDate);
             * System.out.println("End date == " + endDate);
             * CommonView.createVacationHistory(userId, fromDate, endDate, false);
             * 
             * fromDate = CommonUtils.getNextDayDate(endDate);
             * CommonView.createVacationHistory(userId, fromDate, toDate, false);
             */

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        // new UserServiceImpl().updateTimeOffDays(1, 10, "2020-07-29", "2020-08-09",
        // "Lodu");

        // AwsUtils.uploadProfilePic(
        // "https://media-exp1.licdn.com/dms/image/C5603AQE8MNkfNcKWyQ/profile-displayphoto-shrink_800_800/0?e=1603324800&v=beta&t=kKWPbcZfEWZ3Sl11qC95xqnMnmysJfj3pFsNlH0beiU",
        // "R4-821ThK9");
        // // }
        System.out.println("Scary");

    }

    @Override
    public void updateVacation(Integer vacationDays, Integer userId) throws SQLException {
        List<User> users = UserView.getUserFromColValue(userId, "id");
        if (users == null || users.size() == 0)
            return;

        User user = users.get(0);
        if (vacationDays == -1) {
            user.setOnLeave(false);
            UserView.saveUser(user);
            return;
        }

        Integer timeOffDays = user.getTimeOffDays();
        if (timeOffDays + vacationDays > Constants.TIME_OFF_DAYS_ALLOWED) {
            vacationDays = Constants.TIME_OFF_DAYS_ALLOWED - timeOffDays;
            if (vacationDays < 0) {
                return;
            }
        }
        Integer days = Math.max(vacationDays, 0);
        user.setTimeOffDays(timeOffDays + days);
        user.setOnLeave(true);
        UserView.saveUser(user);
        return;
    }

    @Override
    public boolean validateToken(String token, Constants.TOKEN_TYPE tokenType, User user) {
        // System.out.println(token + "\n\n" + user.getAccessToken());
        if (tokenType == Constants.TOKEN_TYPE.ACCESS_TOKEN) {
            return user.getAccessToken().equals(token);
        }
        System.out.println("Validation failed for token");
        return false;
    }

    @Override
    public boolean updateProfilePic(MultipartFile profilePic, String socialId, String accessToken) {
        User user;
        try {
            user = UserView.getUserFromColValue(socialId, "socialid", false).get(0);
            if(!this.validateToken(accessToken, Constants.TOKEN_TYPE.ACCESS_TOKEN, user)) return false;

            String profilePicUrl = AwsUtils.uploadProfilePic(profilePic, socialId);
            if(profilePic == null) {
                return false;
            }
            profilePicUrl = "https://profileimg.s3.ap-south-1.amazonaws.com/" + socialId + "/" + profilePicUrl;
            user.setProfilePicUrl(profilePicUrl);
            UserView.saveUser(user);
            return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
        
    }

    // public static void main(String[] args) {
    // UserService userService = new UserServiceImpl();
    // try {
    // JsonObject d = userService.processAccessToken(
    // "EAALZBKtfQAjkBAKcrbtZAZBQtJMQWZBYm2KgY5m3ddaCPitKTiFjVUq4n3ypGjZBYfI3XxSvzy3ezAbrFd1a15dUgRsYam5AdJhhpZBtuQJkhvOmYGao4t5CeRigdi8ZCMvRD0eMQVxT0zumruQiLe3Jb9w8xlxy4zJgPayvQga98moefigZARs9dOuM0dNwOMaMZBdyZBteGx6R0VCH5052qmAa7tQ212CKM1v5903kbSCgZDZD",
    // "1340691946140444", true);
    // // System.out.print(d.toString());
    // } catch (SQLException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    // }

}