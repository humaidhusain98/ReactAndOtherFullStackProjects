package com.people.chat.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.people.chat.Common.Constants;
import com.people.chat.ResponseDto.LeaveResponseDTO;
import com.people.chat.View.CommonView;

public class User {
    Integer id;
    String name;
    String emailAddress;
    String socialId;
    Integer source;
    String profilePicUrl;
    Integer freeMsgCount;
    Integer timeOffDays;
    Integer totalYearsOfExp;
    Integer currentCompanyId;
    Integer currentProfessionId; // currentProf ID : 1 - Software Dev 2 - Frontend Dev 3 - HR
    Map<String, Integer> competencyExpMap;
    Long createdAt;
    Integer points;
    String accessToken;
    Long lastLogin; // Update this from lambda
    String city;
    String country;
    Boolean onLeave;
    String deviceToken;

    /*
     * Profession should have Competencies If I am a software Developer - I maybe
     * having backend dev as a competency, API integration as another competency,
     * Database design as another. Now these competencies should have skills -
     * Frontend Developer => Do I know React etc. Time should be attached to both
     * competencies and skills. If I have 7 years of exp in Backend Dev
     * (Competency), that may be split into 4 years in Java (Skill) + 3 years in
     * Node (Skill). This time should be searchable for both competency and skill
     */

    public static User getUserFromSql(ResultSet result) {
        try {
            // TODO ADD LAST LOGIN AND PROFILE PIC URL
            return new User(result.getInt(1), result.getString(2), result.getString(3), result.getString(4),
                    result.getInt(5), result.getInt(6), result.getInt(7), result.getInt(8), result.getInt(9),
                    result.getInt(10), result.getLong(11), result.getInt(12), result.getLong(13), result.getString(14),
                    result.getString(15), result.getString(16), result.getString(17), result.getBoolean(18), result.getString(19));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public User(Integer id, String name, String emailAddress, String socialId, Integer source, Integer freeMsgCount,
            Integer timeOffDays, Integer totalYearsOfExp, Integer currentCompanyId, Integer currentProfessionId,
            Long createdAt, Integer points, Long lastLogin, String accessToken, String profilePicUrl, String city,
            String country, Boolean onLeave, String deviceToken) {

        // if (id == 31)
        //     // System.out.println("Leave details = " + onLeave);
        this.id = id;
        this.name = name;
        this.emailAddress = emailAddress;
        this.socialId = socialId;
        this.source = source;
        this.freeMsgCount = freeMsgCount;
        this.timeOffDays = timeOffDays;
        this.totalYearsOfExp = totalYearsOfExp;
        this.currentCompanyId = currentCompanyId;
        this.currentProfessionId = currentProfessionId;
        // this.competencyExpMap = competencyExpMap;
        this.createdAt = createdAt;
        this.profilePicUrl = profilePicUrl;
        this.points = points;
        this.lastLogin = lastLogin;
        this.accessToken = accessToken;
        this.city = city;
        this.country = country;
        this.onLeave = onLeave;
        this.deviceToken = deviceToken;
    }


    public String getDeviceToken() {
        return this.deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public Boolean getOnLeave() {
        return this.onLeave;
    }

    public void setOnLeave(Boolean onLeave) {
        this.onLeave = onLeave;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getSocialId() {
        return this.socialId;
    }

    public void setSocialId(String socialId) {
        this.socialId = socialId;
    }

    public Integer getSource() {
        return this.source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getProfilePicUrl() {
        return this.profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    public Integer getFreeMsgCount() {
        return this.freeMsgCount;
    }

    public void setFreeMsgCount(Integer freeMsgCount) {
        this.freeMsgCount = freeMsgCount;
    }

    public Integer getTimeOffDays() {
        return this.timeOffDays;
    }

    public void setTimeOffDays(Integer timeOffDays) {
        this.timeOffDays = timeOffDays;
    }

    public Integer getTotalYearsOfExp() {
        return this.totalYearsOfExp;
    }

    public void setTotalYearsOfExp(Integer totalYearsOfExp) {
        this.totalYearsOfExp = totalYearsOfExp;
    }

    public Integer getCurrentCompanyId() {
        return this.currentCompanyId;
    }

    public void setCurrentCompanyId(Integer currentCompanyId) {
        this.currentCompanyId = currentCompanyId;
    }

    public Integer getCurrentProfessionId() {
        return this.currentProfessionId;
    }

    public void setCurrentProfessionId(Integer currentProfessionId) {
        this.currentProfessionId = currentProfessionId;
    }

    public Map<String, Integer> getCompetencyExpMap() {
        return this.competencyExpMap;
    }

    public void setCompetencyExpMap(Map<String, Integer> competencyExpMap) {
        this.competencyExpMap = competencyExpMap;
    }

    public Long getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getPoints() {
        return this.points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Long getLastLogin() {
        return this.lastLogin;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setLastLogin(Long lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LeaveResponseDTO getLeaveResponseDTO() {
        List<String> leaveDetails = CommonView.getFullLeaveDetailsList(this.id);
        Integer allottedTime = Integer.parseInt(leaveDetails.get(leaveDetails.size()-1));//this.getTimeOffDays(); // SHOWING THE TOTAL ALLOTTED TIME SO FAR
        Integer pauseBalance = Constants.TIME_OFF_DAYS_ALLOWED - allottedTime;

        return new LeaveResponseDTO(Integer.parseInt(leaveDetails.get(3)), leaveDetails.get(0), leaveDetails.get(2),
                leaveDetails.get(1), pauseBalance, allottedTime);
        // return null;
    }

}