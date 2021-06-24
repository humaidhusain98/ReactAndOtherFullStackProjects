package com.example.oauth2.demo.utils;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;

public class LinkedInUtils {

    private static final String CLIENT_SECRET = "Rewt6iLh2xN32aWv";
    private static final String REDIRECT_URI = "https://www.linkedin.com/developer/apps";
    private static final String CLIENT_ID = "77c9akuzveztxv";

    private static final String PROFILE_ENDPOINT = "https://api.linkedin.com/v2/me";
    private static final String IMAGE_ENDPOINT = "https://api.linkedin.com/v2/me?projection="
            + "(id,profilePicture(displayImage~:playableStreams))&oauth2_access_token=";
    private static final String EMAIL_ENDPOINT = "https://api.linkedin.com/v2/emailAddress?q=members&projection=(elements*(handle~))";

    private static String getAccessToken(String accessTokenUri, Integer retries) {
        RestTemplate restTemplate = new RestTemplate();
        if(retries >= 3) return "";
        try {
            String accessTokenRequest = restTemplate.getForObject(accessTokenUri, String.class);
            JSONObject jsonObjOfAccessToken = new JSONObject(accessTokenRequest);

            return jsonObjOfAccessToken.get("access_token").toString();
        } catch (HttpClientErrorException e) {
            System.out.println("LinkedIn Error in process accessToken = " + accessTokenUri);
            return getAccessToken(accessTokenUri, retries + 1);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            System.out.println("LinkedIn Error in process accessToken = " + accessTokenUri);
            return getAccessToken(accessTokenUri, retries + 1);
        }
    }

    private static String getEmail(String accessToken) throws Exception {
        String response = sendGetWithAuthorizationHeader(EMAIL_ENDPOINT, accessToken);

        JSONObject responseAsJSON = new JSONObject(response);

        return parseEmailApiResponse(responseAsJSON);
    }

    private static String parseEmailApiResponse(JSONObject responseAsJSON) throws JSONException {

        return responseAsJSON.getJSONArray("elements").getJSONObject(0).getJSONObject("handle~")
                .getString("emailAddress");
    }

    public static String fetchProfileData(String authCode) {
        String accessTokenUri = "https://www.linkedin.com/oauth/v2/accessToken?grant_type=authorization_code&code="
                + authCode + "&redirect_uri=" + REDIRECT_URI + "&client_id=" + CLIENT_ID + "&client_secret="
                + CLIENT_SECRET + "";
        String accessToken = "";
        try {
            accessToken = getAccessToken(accessTokenUri, 0);
            if(accessToken == "") return null;
            StringBuilder socialId = new StringBuilder();
            String name = getName(accessToken, socialId);
            String profilePic = getProfilePhotoUrl(accessToken);
            String emailAddress = getEmail(accessToken);
            // getProfileData(accessToken);
            String profileData = name + "|" + profilePic + "|" + emailAddress + "|" + socialId.toString();
            // System.out.println(name + "\n" + profilePic + "\n" + socialId);
            return profileData;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;

    }

    private static HttpsURLConnection getHttpsURLConnectionWithAccessTokenConcatenized(String url, String accessToken)
            throws IOException {

        URL urlWithToken = new URL(url + accessToken);
        return (HttpsURLConnection) urlWithToken.openConnection();
    }

    public static String getProfilePhotoUrl(String accessToken) throws Exception {
        HttpsURLConnection connection = getHttpsURLConnectionWithAccessTokenConcatenized(IMAGE_ENDPOINT, accessToken);

        System.out.println(connection);
        StringBuilder jsonString = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String line;
        while ((line = br.readLine()) != null) {
            jsonString.append(line);
        }

        br.close();
        // System.out.println(jsonString);
        return parseProfilePhotoApiResponse(new JSONObject(jsonString.toString()));
    }

    private static String parseProfilePhotoApiResponse(JSONObject profilePhotoJson){

        try {
            return profilePhotoJson.getJSONObject("profilePicture").getJSONObject("displayImage~")
                    .getJSONArray("elements").getJSONObject(3).getJSONArray("identifiers").getJSONObject(0)
                    .getString("identifier");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            System.out.println("Error in parsing Json for profile Pic Link = " + profilePhotoJson);
            return "https://profileimg.s3.ap-south-1.amazonaws.com/blank/blank-profile-picture-973460_640.png";
        }
    }

    private static String getName(String accessToken, StringBuilder socialId) throws Exception {
        String jsonString = sendGetWithAuthorizationHeader(PROFILE_ENDPOINT, accessToken);

        JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonObject = jsonReader.readObject();
        // System.out.println(jsonObject);
        // getProfilePhotoUrl(accessToken);
        socialId.append(jsonObject.getString("id"));
        // System.out.println("ID FROM JSON === " + socialId);
        // Remove extra double quotes in JSONObject
        String localizedFirstName = jsonObject.get("localizedFirstName").toString().replaceAll("^\"|\"$", "")
                .replaceAll("[ğ]", "g").replaceAll("[İ]", "I");
        String localizedLastName = jsonObject.get("localizedLastName").toString().replaceAll("^\"|\"$", "")
                .replaceAll("[ğ]", "g").replaceAll("[İ]", "I");

        return localizedFirstName + " " + localizedLastName;
    }

    private static String sendGetWithAuthorizationHeader(String resourceUrl, String accessToken) throws IOException {
        HttpsURLConnection con = openAuthorizedConnectionTo(resourceUrl, accessToken);

        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder jsonString = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null) {
            jsonString.append(line);
        }

        br.close();

        return jsonString.toString();
    }

    private static HttpsURLConnection openAuthorizedConnectionTo(String resourceUrl, String accessToken)
            throws IOException {
        URL url = new URL(resourceUrl);
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("Authorization", "Bearer " + accessToken);
        con.setRequestProperty("cache-control", "no-cache");
        con.setRequestProperty("X-Restli-Protocol-Version", "2.0.0");

        return con;
    }
}