package com.example.oauth2.demo.controller;

import com.example.oauth2.demo.utils.LinkedInUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class testcontroller {

    @PostMapping
    public void handleRequest (HttpEntity<String> requestEntity) throws Exception {
        System.out.println("request body: " + requestEntity.getBody());
        HttpHeaders headers = requestEntity.getHeaders();
        String data = requestEntity.getBody();
        JsonObject jsonData = new Gson().fromJson(data, JsonObject.class);
        String accessToken = jsonData.get("accessToken").getAsString();
        System.out.println(accessToken);

//        System.out.println(LinkedInUtils.getProfilePhotoUrl("AQQcsZM9gR-MTNaBuxDky4eqtx67pz-sp6odqgoWjY26mZ7G7kiEDpMHPNwfgP1ASCXgcUjraBOtTnSVymIRGsbnmGvqcYOFEaBh_QyohMJN2VOCbcs6WyaPoYLoGgEzB7EUu4wl1IFMNgt0yRK881jpIIylr5jB0LQ2lI_YMg7N5bwChREMMwaaRP5mGA"));
        System.out.println(LinkedInUtils.getProfilePhotoUrl("AQWSY5eXi5iGB6grfPGbl7Aj2SXU9bGmTBC_fmStV6wJzjGqQ-MvhYDCPHh6GNJOHI1mIY9P8uc8G80k0PGycaZcUE7r17nUxBxaIHJkE8ysKN7Lz8GA2H2VHsPJkJBPXWdPCarVSLeV2B8HK12syAA81bdEiU-64bmsYoFGToWxoIkKCmCSbnJlY0X8DSNVfb1StjhavjAb_dCzjCCAwv4vEjzQ7VIkbmD5SbI7eE4b-pCsE_H5T4Ir3qHCYj5iaWQE8JMrvwrmKiaUH0GlUznSsAgnvUbwBL34L-HAY5vIslzLndSmBu_M_fH5nyTVMWoeMi6eYLtt-rcCkIuJjExOzCF5AQ"));
        //AQUThljZw8rbiJwH45-fLKIJz-JkF6bo5LlcdmxmmL21anwS-1G3AHDPMOahKqHEI9_KEPNnV_Vlo9jvrt2ktCzUekkgWI0Ki58B6wsNlJQFP-79qJevSPFlPrwOGWenWRhsiPd4F91HhJZ-092CIIxehOx4_vK54-Lyg34zJrhJLK0_s1ouXQQpN_ee9RKFwEKRLJLAMKvxv1EUT6bHU6LfN51Ir-gJMDojNtSDUFpUlJLAeEV74UhQGqsndKpdwWEIoXWDLHbszZ2bN7iA4BqIphf7FT7LZuHkfnodMvTPcaX7Xunh1Qv7oGQyAS8c5siC3bN9ZHn_NHhOQ0IPX7u-GXF9ZA
        System.out.println(LinkedInUtils.getProfilePhotoUrl("AQUThljZw8rbiJwH45-fLKIJz-JkF6bo5LlcdmxmmL21anwS-1G3AHDPMOahKqHEI9_KEPNnV_Vlo9jvrt2ktCzUekkgWI0Ki58B6wsNlJQFP-79qJevSPFlPrwOGWenWRhsiPd4F91HhJZ-092CIIxehOx4_vK54-Lyg34zJrhJLK0_s1ouXQQpN_ee9RKFwEKRLJLAMKvxv1EUT6bHU6LfN51Ir-gJMDojNtSDUFpUlJLAeEV74UhQGqsndKpdwWEIoXWDLHbszZ2bN7iA4BqIphf7FT7LZuHkfnodMvTPcaX7Xunh1Qv7oGQyAS8c5siC3bN9ZHn_NHhOQ0IPX7u-GXF9ZA"));

    }
}