package com.people.chat.Controllers;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin 
@RestController
@RequestMapping("/")
public class TestController {

    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "Success";
    }

    // @ResponseBody
    // @RequestMapping(value = "/test1", method = RequestMethod.GET)
    // public String test1(HttpEntity<String> httpEntity) {
    //     String data = httpEntity.getBody();
    //     JsonObject jsonData = new Gson().fromJson(data, JsonObject.class);
    //     String ip = jsonData.get("text").getAsString();
    //     return ip;
    // }

    // @ResponseBody
    // @RequestMapping(value = "/test2", method = RequestMethod.GET)
    // public String test2(HttpServletRequest request) {
    //     String fromPid = request.getParameter("text");
    //     // System.out.println("SOUMIK ==================== SOMETHONG IS WORKN" +
    //     // fromPid);
    //     return fromPid;
    // }
}