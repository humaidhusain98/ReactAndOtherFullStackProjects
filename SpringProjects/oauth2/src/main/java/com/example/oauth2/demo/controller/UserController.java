//package com.example.oauth2.demo.controller;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import com.maxmind.geoip2.exception.GeoIp2Exception;
//import com.people.chat.Model.Company;
//import com.people.chat.Model.Profession;
//import com.people.chat.Model.User;
//import com.people.chat.ResponseDto.SearchResponseDTO;
//import com.people.chat.ResponseDto.UserResponseDTO;
//import com.people.chat.Service.*;
//import com.people.chat.Utils.CommonUtils;
//import com.people.chat.View.CompanyView;
//import com.people.chat.View.UserView;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.List;
//
//@RestController
//@RequestMapping("/user")
//public class UserController {
//
//    @ResponseBody
//    @RequestMapping(value = "/new/login", method = RequestMethod.POST)
//    public ResponseEntity<?> login(HttpEntity<String> httpEntity) {
//        // System.out.println("IN NEW LOGIN CALL\n");
//        String data = httpEntity.getBody();
//        JsonObject jsonData = new Gson().fromJson(data, JsonObject.class);
//        JsonObject loginInfo = jsonData.getAsJsonObject("loginInfo");
//        Integer loginSource = Integer.parseInt(jsonData.get("loginSource").toString());
//        JsonArray compList = jsonData.getAsJsonArray("competencyList");
//        JsonObject sdkInfo = jsonData.getAsJsonObject("sdkInfo");
//        JsonObject profObj = jsonData.getAsJsonObject("profession");
//        JsonObject companyObj = jsonData.getAsJsonObject("currentCompany");
//
//        UserService userService = new UserServiceImpl();
//        String retData = new Gson()
//                .toJson(userService.setUpAndGetData(loginInfo, loginSource, sdkInfo, compList, companyObj, profObj));
//
//        // System.out.println(compList.toString());
//
//        return new ResponseEntity<Object>(retData, HttpStatus.OK);
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/update/profilePic", method = RequestMethod.POST)
//    public ResponseEntity<?> updateProfilePic(@RequestParam("profilePic") MultipartFile file,
//    @RequestParam("socialId") String socialId,
//    @RequestParam("accessToken") String accessToken) {
//        System.out.println("IN API CALL");
//        UserService userService = new UserServiceImpl();
//        boolean isUpdated = userService.updateProfilePic(file, socialId, accessToken);
//        if(isUpdated) {
//            return new ResponseEntity<Object>("Update Successful", HttpStatus.OK);
//        }
//        return  new ResponseEntity<Object>("Update failed", HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    public ResponseEntity<?> updateUser(HttpEntity<String> httpEntity) throws SQLException {
//
//        String data = httpEntity.getBody();
//        System.out.println("JSON response for update api = " + data);
//        JsonObject jsonData = new Gson().fromJson(data, JsonObject.class);
//        JsonArray objList = jsonData.getAsJsonArray("updateList");
//        // System.out.println(data);
//        Integer userId = jsonData.get("userId").getAsInt();
//        for (int index = 0; index < objList.size(); index++) {
//            JsonObject obj = objList.get(index).getAsJsonObject();
//            String updateObj = obj.get("component").getAsString();
//            if (updateObj.equalsIgnoreCase("competencies")) {
//                CompetencyService compService = new CompetencyServiceImpl();
//                JsonArray competencyList = obj.getAsJsonArray("competencyList");
//                compService.deleteExistingCompetencies(userId);
//                compService.createCompetenciesIfNeeded(competencyList, userId);
//            } else {
//                User user = UserView.getUserFromColValue(userId, "id").get(0);
//                if (updateObj.equalsIgnoreCase("profession")) {
//                    ProfessionService profService = new ProfessionServiceImpl();
//                    JsonObject profObj = obj.get("profession").getAsJsonObject();
//                    Profession prof = profService.createProfIfNeeded(profObj);
//                    user.setCurrentProfessionId(prof.getId());
//                    user.setTotalYearsOfExp(profObj.get("totalExp").getAsInt());
//                    UserView.saveUser(user);
//                } else if (updateObj.equalsIgnoreCase("emailAddress")) {
//                    JsonObject emailObj = obj.getAsJsonObject("email");
//                    String email = emailObj.get("emailAddress").getAsString();
//                    // System.out.println("Emaill Address === " + email);
//                    user.setEmailAddress(email);
//                    UserView.saveUser(user);
//                } else if (updateObj.equalsIgnoreCase("company")) {
//                    CompanyService companyService = new CompanyServiceImpl();
//                    JsonObject companyObj = obj.getAsJsonObject("currentCompany");
//                    Company company = companyService.createIfNotPresent(companyObj);
//                    user.setCurrentCompanyId(company.getId());
//                    UserView.saveUser(user);
//                }
//            }
//        }
//        // // TODO - MOVE ALL THIS CODE TO A SERVICE
//
//        return new ResponseEntity<Object>(null, HttpStatus.OK);
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/get/ip", method = RequestMethod.GET)
//    public ResponseEntity<?> getIp(HttpServletRequest request) throws SQLException {
//
//        return new ResponseEntity<Object>(new Gson().toJson(request.getHeader("X-FORWARDED-FOR")), HttpStatus.OK);
//        // return null;
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/getInfo", method = RequestMethod.POST)
//    public ResponseEntity<?> getUserInfo(HttpEntity<String> httpEntity) throws SQLException {
//        String data = httpEntity.getBody();
//        // System.out.println("IN GETINFO CALL ");
//        JsonObject jsonData = new Gson().fromJson(data, JsonObject.class);
//        String accessToken = jsonData.get("accessToken").getAsString();
//        Boolean isClear = jsonData.get("isClear").getAsBoolean();
//        Integer source = jsonData.get("source").getAsInt();
//        JsonElement deviceToken = jsonData.get("deviceToken");
//        System.out.println("\n\nData for User creation == " + data + "\n\n");
//        // LinkedInUtils.fetchProfileData(accessToken);
//
//        // System.out.println(jsonData + "\n\n");
//        UserService userService = new UserServiceImpl();
//        UserResponseDTO dataObj = userService.processAccessToken(accessToken, source, true, deviceToken);
//
//        String ret = new Gson().toJson(dataObj);
//        // System.out.println("Done Client req");
//
//        return new ResponseEntity<Object>(ret, HttpStatus.OK);
//        // return null;
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/get/location", method = RequestMethod.POST)
//    public ResponseEntity<?> getLocationInfo(HttpEntity<String> httpEntity) throws SQLException {
//        String data = httpEntity.getBody();
//        JsonObject jsonData = new Gson().fromJson(data, JsonObject.class);
//        try {
//            String ip = jsonData.get("ipAddress").getAsString();
//            System.out.println("IP Address = " + ip);
//            return new ResponseEntity<Object>(new Gson().toJson(LocationService.getCity(ip)), HttpStatus.OK);
//        } catch (IOException | GeoIp2Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            System.out.println("Returning default locations");
//            String op =  "India" + ", " + "India";
//            return new ResponseEntity<Object>(new Gson().toJson(op), HttpStatus.OK);
//        }
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/get/all/{object}", method = RequestMethod.GET)
//    public ResponseEntity<?> fetchAllObjects(@PathVariable("object") String dataSet) throws SQLException {
//        String query = "select * from " + dataSet;
//
//        if (!dataSet.equalsIgnoreCase("competency") && !dataSet.equalsIgnoreCase("company")
//                && !dataSet.equalsIgnoreCase("profession")) {
//            return new ResponseEntity<Object>("Fuck You", HttpStatus.OK);
//        }
//        JsonArray json = CommonUtils.getJsonArrayFromSQL(query);
//        // r.close();
//        return new ResponseEntity<Object>(new Gson().toJson(json), HttpStatus.OK);
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/search/", method = RequestMethod.POST)
//    public ResponseEntity<?> getSearchResults(HttpEntity<String> httpEntity) throws SQLException {
//        // System.out.println(new Date().getMinutes() + " : " + new
//        // Date().getSeconds());
//        String data = httpEntity.getBody();
//        JsonObject jsonData = new Gson().fromJson(data, JsonObject.class);
//        String dataSet = jsonData.get("object").getAsString().replace("'", "''");
//        String key = jsonData.get("key").getAsString().replace("'", "''");
//        Integer pid = jsonData.get("pid").getAsInt();
//
//        SearchService search = new SearchServiceImpl();
//        List<SearchResponseDTO> searchResults = null;
//
//        if (dataSet.equalsIgnoreCase("name")) {
//            searchResults = search.getResultsFromName(key, pid);
//            if (searchResults == null || searchResults.size() == 0) {
//                System.out.println("Search key = " + key);
//                Company comp = CompanyView.getCompanyFromName(key.toLowerCase());
//                if (comp != null)
//                    searchResults = search.getResultsFromCurrentCompany(comp.getId(), pid);
//            }
//        } else if (dataSet.equalsIgnoreCase("city")) {
//            searchResults = search.getResultsFromCurrentLocation(key, pid);
//        } else if (dataSet.equalsIgnoreCase("competency")) {
//            Integer years = jsonData.get("exp").getAsInt();
//            searchResults = search.getResultsFromCompetency(Integer.parseInt(key), years, pid);
//        } else if (dataSet.equalsIgnoreCase("profession")) {
//            Integer years = jsonData.get("exp").getAsInt();
//            searchResults = search.getResultsFromCurrentProf(Integer.parseInt(key), years, pid);
//        } else if (dataSet.equalsIgnoreCase("company")) {
//            searchResults = search.getResultsFromCurrentCompany(Integer.parseInt(key), pid);
//        }
//        // System.out.println(new Date().getMinutes() + " : " + new
//        // Date().getSeconds());
//        return new ResponseEntity<Object>(new Gson().toJson(searchResults), HttpStatus.OK);
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/timeOff", method = RequestMethod.POST)
//    public ResponseEntity<?> updateTimeOffDays(HttpEntity<String> httpEntity) throws SQLException {
//        String data = httpEntity.getBody();
//        JsonObject jsonData = new Gson().fromJson(data, JsonObject.class);
//        Integer userId = jsonData.get("userId").getAsInt();
//        String fromDate = jsonData.get("fromDate").getAsString();
//        String toDate = jsonData.get("toDate").getAsString();
//        Integer numberOfDays = CommonUtils.getDays(fromDate, toDate);
//        String reason = jsonData.get("reason").getAsString().trim();
//
//        UserService userService = new UserServiceImpl();
//        Integer leaveGranted = userService.updateTimeOffDays(userId, numberOfDays, fromDate, toDate, reason);
//        JsonObject response = new JsonObject();
//        if (leaveGranted != null && leaveGranted > 0) {
//            response.addProperty("grantedDays", leaveGranted);
//            response.addProperty("success", true);
//            response.addProperty("msg", "Your vacation is being processed");
//        } else
//            response.addProperty("msg",
//                    "You're out of pause balance. Please wait for retrieval of pause balance at start of next year");
//        return new ResponseEntity<Object>(new Gson().toJson(response), HttpStatus.OK);
//    }
//}