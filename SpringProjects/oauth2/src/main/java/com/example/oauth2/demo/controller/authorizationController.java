package com.example.oauth2.demo.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import static com.example.oauth2.demo.utils.LinkedInUtils.getProfilePhotoUrl;

@RestController
public class authorizationController {

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getAuthorizationCode")
    public ResponseEntity<String> getAuthorizationCode(@RequestParam String code,@RequestParam String state)
    {

        String accessToken= exchangeAuthorizationCodeToAccessToken(code);
        System.out.println(accessToken);
        return ResponseEntity.ok().body(accessToken);
    }

    private String exchangeAuthorizationCodeToAccessToken(String authorizationCode)
    { String accessToken="";
        String accessTokenUri = "https://www.linkedin.com/oauth/v2/accessToken?grant_type=authorization_code&code="
                + authorizationCode + "&redirect_uri=http://localhost:4200/callback" + "&client_id=77c9akuzveztxv" + "&client_secret=Rewt6iLh2xN32aWv";
        RestTemplate restTemplate = new RestTemplate();
        try {
            String accessTokenRequest = restTemplate.getForObject(accessTokenUri, String.class);;
            JSONObject jsonObjOfAccessToken = new JSONObject(accessTokenRequest);

            accessToken= jsonObjOfAccessToken.get("access_token").toString();
        }
        catch(HttpClientErrorException | JSONException e)
        {
            System.out.println("LinkedIn Error in process accessToken = " + accessTokenUri);
            accessToken="error";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return accessToken;
    }

    private static void getProfileData(String accessToken)
    {
        String profileUri="https://api.linkedin.com/v2/me?oauth2_access_token="+accessToken;

        RestTemplate restTemplate = new RestTemplate();
        String profileDetailsRequest = restTemplate.getForObject(profileUri, String.class);
        System.out.println(profileDetailsRequest);
        try
        {
            JSONObject profileObject = new JSONObject(profileDetailsRequest);
            System.out.println(profileObject);
            String lastname= profileObject.get("localizedLastName").toString();
            String firstname= profileObject.get("localizedFirstName").toString();
            String profilePicture= profileObject.get("profilePicture").toString();
            String LinkedinId = profileObject.get("id").toString();
            JSONObject profilePictureJson = new JSONObject(profilePicture);
            String displayImageURN= profilePictureJson.get("displayImage").toString();
            System.out.println(lastname);
            System.out.println(firstname);
            System.out.println(LinkedinId);
            System.out.println(displayImageURN);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }


    }
    private static final String IMAGE_ENDPOINT = "https://api.linkedin.com/v2/me?projection="
            + "(id,profilePicture(displayImage~:playableStreams))&oauth2_access_token=";


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

    private static final String EMAIL_ENDPOINT = "https://api.linkedin.com/v2/emailAddress?q=members&projection=(elements*(handle~))";





    public static void main(String args[]) throws Exception {
       String profilePhoto=  getProfilePhotoUrl("AQX0giqmg94uxZtaIsgE1oXiYUI_4XQkPKhR5NLtHSKfDfMP4nIzxx2Kn84PoNRpRBAxm1rjuEJRQyabEk3Xx4O6T0s8A-UHcBSnob3kdmKt7873XIsGc39ufElzdDR2XHuFl7ZhplyVCG8PqgQTXegV_MsD51ZRjHcf022rhCeXef4Vag0n-AdvKgD3p9IxQdkClq5fdYt-TS1hB9w2EGkeg5eujMTgZkFtTPAuUiYmZ65DogDIKJK5GlJgzwKkutIQs191-WqSArVbewPoiqNDD5SW2wg_LA5NX8W9GBhTG8oyDXftYCVNqNbH5Zs1P_Qqsdy7oh_B0aK4JDbUPMk6bgaD4A");
        System.out.println(profilePhoto);
       //        getProfileData("AQX0giqmg94uxZtaIsgE1oXiYUI_4XQkPKhR5NLtHSKfDfMP4nIzxx2Kn84PoNRpRBAxm1rjuEJRQyabEk3Xx4O6T0s8A-UHcBSnob3kdmKt7873XIsGc39ufElzdDR2XHuFl7ZhplyVCG8PqgQTXegV_MsD51ZRjHcf022rhCeXef4Vag0n-AdvKgD3p9IxQdkClq5fdYt-TS1hB9w2EGkeg5eujMTgZkFtTPAuUiYmZ65DogDIKJK5GlJgzwKkutIQs191-WqSArVbewPoiqNDD5SW2wg_LA5NX8W9GBhTG8oyDXftYCVNqNbH5Zs1P_Qqsdy7oh_B0aK4JDbUPMk6bgaD4A");
    }




}
