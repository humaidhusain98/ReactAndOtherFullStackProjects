package com.people.chat.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.people.chat.View.DatabaseHandler;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;

public class CommonUtils {
    static SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static Integer isTodayBetweenDates(String d1, String d2) {
        String today = new Date().toGMTString();
        System.out.println(today);
        return d1.compareTo(d2);
    }

    public static Integer getDays(String date1, String date2) {
        try {
            Date dateBefore = myFormat.parse(date1);
            Date dateAfter = myFormat.parse(date2);
            long difference = Math.abs(dateAfter.getTime() - dateBefore.getTime());
            float daysBetween = (difference / (1000 * 60 * 60 * 24));
            /*
             * You can also convert the milliseconds to days using this method float
             * daysBetween = TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS)
             */
            return ((int) daysBetween) + 1; // Both inclusive
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static Boolean isDatePassed(String date) throws ParseException {
        return myFormat.parse(date).before(new Date());
    }

    public static Boolean datesInSameMonth(String d1, String d2) {
        try {
            Date dateBefore = myFormat.parse(d1);
            Date dateAfter = myFormat.parse(d2);

            return dateBefore.getMonth() == dateAfter.getMonth() && dateBefore.getYear() == dateAfter.getYear();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return false;
    }

    public static Integer getDaysLeftInMonth(String dateString) {
        try {
            Date date = myFormat.parse(dateString);
            YearMonth yearMonthObject = YearMonth.of(date.getYear(), date.getMonth() + 1);
            return yearMonthObject.lengthOfMonth() - date.getDate();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return -1;

    }

    public static void makePostRequest(String authToken) throws ClientProtocolException, IOException {
        // Request parameters and other properties.
        System.out.print(authToken);
        Map<String, String> params = new HashMap<>();
        params.put("client_id", "86i1bzb08lo1n9");
        params.put("client_secret", "IuVL9w67ZyGhv3xy");
        params.put("grant-type", "authorization_code");
        params.put("redirect_uri", "https://www.linkedin.com/developer/apps");
        params.put("code", authToken);
        StringJoiner sj = new StringJoiner("&");
        for (Map.Entry<String, String> entry : params.entrySet())
            sj.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "=" + URLEncoder.encode(entry.getValue(), "UTF-8"));
        byte[] out = sj.toString().getBytes(StandardCharsets.UTF_8);
        int length = out.length;
        URL url = new URL("https://www.linkedin.com/oauth/v2/accessToken");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setDoOutput(true);
        con.setFixedLengthStreamingMode(length);
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        con.connect();
        // con.g
        try (OutputStream os = con.getOutputStream()) {
            os.write(out);
        }

        System.out.println("\nMade Post call\n" + con.getResponseMessage() + " --- " + con.getResponseCode());
        // Execute and get the response.
        // HttpResponse response = httpclient.execute(httppost);
        // HttpEntity entity = response.getEntity();

        // if (entity != null) {
        // try (InputStream instream = entity.getContent()) {
        // // do something useful
        // System.out.println("RES ==========\n " + entity + " \n RESS");
        // }
        // }
    }

    public static JsonArray getJsonArrayFromSQL(String query) {
        ResultSet results = null;
        Connection conn = null;
        PreparedStatement ptstmnt = null;
        JsonArray json = new JsonArray();

        try {
            BasicDataSource dataSource = DatabaseHandler.getHandler().getDataSource();
            conn = dataSource.getConnection();
            ptstmnt = conn.prepareStatement(query);
            results = ptstmnt.executeQuery();

            ResultSetMetaData rsmd = results.getMetaData();
            while (results.next()) {
                int numColumns = rsmd.getColumnCount();
                JsonObject obj = new JsonObject();
                for (int i = 1; i <= numColumns; i++) {
                    String column_name = rsmd.getColumnName(i);
                    // System.out.println(column_name);
                    if (results.getObject(column_name) == null)
                        continue;
                    obj.addProperty(column_name, results.getObject(column_name).toString());
                }
                json.add(obj);
            }
            return json;

        } catch (SQLException e) {
            System.out.print(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    if (results != null)
                        results.close();
                    ptstmnt.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    public static Boolean isDaysInCurrentMonth(String d1, String d2) {
        Integer currentMonth = new Date().getMonth();
        Integer currentYear = new Date().getYear();

        try {
            Integer m1 = myFormat.parse(d1).getMonth();
            Integer m2 = myFormat.parse(d2).getMonth();

            Integer y1 = myFormat.parse(d1).getYear();
            Integer y2 = myFormat.parse(d2).getYear();
            return ((currentMonth == m1 && currentMonth == m2) && (currentYear == y1 && currentYear == y2));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return false;
    }

    public static String getLastDateOfMonth(String date) {
        System.out.println(date);
        YearMonth yearMonth;
        try {
            yearMonth = YearMonth.of(myFormat.parse(date).getYear() + 1900, myFormat.parse(date).getMonth() + 1);
            return yearMonth.atEndOfMonth().toString();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static Boolean isToday(String date) {
        return myFormat.format(new Date()).equalsIgnoreCase(date);
    }

    public static String getDateAfterXDays(String date, Integer x) throws ParseException {
        final Date dateObj = myFormat.parse(date);
        Calendar today = Calendar.getInstance();
        today.setTime(dateObj);
        today.add(Calendar.DAY_OF_YEAR, x);
        return myFormat.format(today.getTime()).toString();
    }

    public static String getNextDayDate(String date) throws ParseException {
        final Date dateObj = myFormat.parse(date);
        Calendar today = Calendar.getInstance();
        today.setTime(dateObj);
        today.add(Calendar.DAY_OF_YEAR, 1);
        return myFormat.format(today.getTime()).toString();
    }

    public static String getMonthName(int month) {
        String[] monthNames = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
                "October", "November", "December" };
        return monthNames[month - 1];
    }

    public static String getCurrentMonth() {
        return getMonthName(new Date().getMonth() + 1);
    }

    public static File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    public static void main(String[] args) throws ParseException {
        // System.out.println(getCurrentMonth());
        // Date date = new Date(Long.valueOf("1598969215515"));

        // System.out.println(myFormat.format(date));
        System.out.println(getDateAfterXDays("2020-09-08", 6));
        // try {
        // // makePostRequest();
        // } catch (IOException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        // System.out.println(getDaysLeftInMonth("2019-11-11"));
        // String today = new Date().toGMTString();
        // System.out.println(myFormat.parse("2020-08-05").getMonth());
        // System.out.println(isDaysInCurrentMonth("2020-06-08", "2010-06-28"));
        // System.out.println(new Date().getMonth());
    }

}