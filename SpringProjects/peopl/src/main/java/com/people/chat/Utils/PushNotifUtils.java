package com.people.chat.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

public class PushNotifUtils {
    public final static String AUTH_KEY_FCM = "AAAAbmk5KSM:APA91bFFgQoXEcjo5d7FIMoxMWkSn_ZW_8Waa0XCpHIpig979oA2kzhWn5wgRUIW1rHWp-B9kxnvJ-ltqs7SFfpE_HJbY4KYIqB4EN9tBZCn9xkVIuM7UPw3bZiXpI35-gyQtbQhT-CC";
    public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";

    public static String sendPushNotification(String deviceToken, String title, String body) throws IOException, JSONException {
        String result = "";
        URL url = new URL(API_URL_FCM);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        if(deviceToken == "-1") return "Failure";

        conn.setUseCaches(false);
        conn.setDoInput(true);
        conn.setDoOutput(true);

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "key=" + AUTH_KEY_FCM);
        // conn.setRequestProperty("Authorization", "registration_token=" + AUTH_KEY_FCM);
        conn.setRequestProperty("Content-Type", "application/json");

        JSONObject json = new JSONObject();

        json.put("to", deviceToken.trim());
        JSONObject info = new JSONObject();

        info.put("title", title); // Notification title
        info.put("body", body); // Notification

                                                                // body
        json.put("notification", info);
        try {
            OutputStreamWriter wr = new OutputStreamWriter(
                    conn.getOutputStream());
            wr.write(json.toString());
            wr.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            // System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            result = "Success";
        } catch (Exception e) {
            e.printStackTrace();
            result = "Failure";
        }
        // System.out.println("GCM Notification is sent successfully");

        return result;
    }

    public static void main(String[] args) throws IOException, JSONException {
        // sendPushNotification("fgDQ8S9NSj-dpUDiHPZoAo:APA91bEVvuKox_6-ZhYU6NKxD_v4c_8_2qO5fjozz8LynXBIZlGIjtr_QENtuhigQkWZhow6feQ-Htueqa_l6dIPnuTnJFmhLS2hXn6G-qMZ-1sUfVvgedKV4ZCKPRk-Xrh0FDxUvK6k");
    }
}