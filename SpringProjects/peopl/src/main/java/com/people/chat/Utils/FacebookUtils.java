package com.people.chat.Utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.JsonObject;

import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.social.ExpiredAuthorizationException;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.web.client.RestTemplate;

public class FacebookUtils {

    public static String GRAPH_API_URL = "https://graph.facebook.com/";

    /** The Constant LOGGED_USER_ID. */
    public static final String LOGGED_USER_ID = "me";

    /** The Constant USER_FIELD_ID. */
    public static final String USER_FIELD_ID = "id";

    /** The Constant USER_FIELD_EMAIL. */
    public static final String USER_FIELD_EMAIL = "email";

    /** The Constant USER_FIELD_FIRST_NAME. */
    public static final String USER_FIELD_FIRST_NAME = "first_name";

    /** The Constant USER_FIELD_LAST_NAME. */
    public static final String USER_FIELD_LAST_NAME = "last_name";

    public static Object fetchUserDataFromAccessToken(String fbAccessToken) {
        Facebook faceBook = new FacebookTemplate(fbAccessToken);
        JsonObject obj = new JsonObject();
        User fbUser = null;
        try {
            fbUser = faceBook.fetchObject(FacebookUtils.LOGGED_USER_ID, User.class, FacebookUtils.USER_FIELD_ID,
                    FacebookUtils.USER_FIELD_EMAIL, FacebookUtils.USER_FIELD_FIRST_NAME,
                    FacebookUtils.USER_FIELD_LAST_NAME);
            // faceBook.userOperations().getUserProfile();
        } catch (ExpiredAuthorizationException e) {
            obj.addProperty("error", "Auth Token Expired");
            return obj;
        } catch (Exception e) {
            obj.addProperty("error", e.getMessage());
            return obj;
        }
        return fbUser;
    }

    public static void main(String[] args) throws MalformedURLException {
        // System.out.println("URL === " + getProfilePicUrl("1340691946140444"));
        // System.out.print("DAWW = " + getAppAccessToken() + "\n");
    }

    public static void getProfilePicUrl(String profileId) throws MalformedURLException {
        String imgUrl = GRAPH_API_URL + profileId + "/picture?type=small&redirect=false";
        // URL url = new URL(
        // "https://platform-lookaside.fbsbx.com/platform/profilepic/?asid=1340691946140444&height=50&width=50&ext=1597613863&hash=AeSkIjYUrhgB44xr");

        return;
    }

    // Almost always returns a constant
    private static String getAppAccessToken() {
        String url = "https://graph.facebook.com/oauth/access_token?client_id=842409916236345&client_secret=558a7b517ed94e8183430876961eb214&grant_type=client_credentials";
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);

        RestTemplate template = new RestTemplate();
        template.setMessageConverters(messageConverters);

        JsonNode res = template.getForObject(url, JsonNode.class);
        return res.get("access_token").asText();
        // return appAccessToken;
    }

    public static void downloadProfilePic(String imgUrl, String profileId) throws IOException {
        String app_access_token = getAppAccessToken();
        imgUrl = imgUrl + "&access_token=" + app_access_token;

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        // Add the Jackson Message converter
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

        // Note: here we are making this converter to process any kind of response,
        // not only application/*json, which is the default behaviour
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        // converter.setDefaultCharset(Charset.forName("UTF-8"));
        messageConverters.add(converter);

        RestTemplate template = new RestTemplate();
        template.setMessageConverters(messageConverters);

        JsonNode res = template.getForObject(imgUrl, JsonNode.class);
        String actualUrl = res.get("data").get("url").asText();
        
        AwsUtils.uploadProfilePic(actualUrl, profileId);
        return;
    }
}