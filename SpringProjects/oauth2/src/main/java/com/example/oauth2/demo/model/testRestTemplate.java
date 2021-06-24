package com.example.oauth2.demo.model;

import com.google.gson.Gson;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class testRestTemplate {

    public static final String GET_ALL_REGCOMPANY = "http://localhost:5000/regcompany";

    static RestTemplate restTemplate=new RestTemplate();
    public static void main(String args[]){
        setGetAllRegcompany();
    }

    private static void setGetAllRegcompany(){
        HttpHeaders headers=new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity=new HttpEntity<>("parameters",headers);
        List<RegCompany> regCompanyList=new ArrayList<RegCompany>();
        try
        {
        ResponseEntity<String> result= restTemplate.exchange(GET_ALL_REGCOMPANY, HttpMethod.GET,entity,String.class);
        JSONArray regcompanyJSONList=new JSONArray(result.getBody());
        for(int i=0;i<regcompanyJSONList.length();i++)
        {
            JSONObject regObj= (JSONObject) regcompanyJSONList.get(i);
            RegCompany companyObj=new Gson().fromJson(String.valueOf(regObj),RegCompany.class);
            System.out.println(companyObj);

        }

        }
        catch(Exception e)
        {
            System.out.println("Server Down!!!!");
        }
    }

}
