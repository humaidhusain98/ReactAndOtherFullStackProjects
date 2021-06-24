package com.people.chat.Model;

public class PopupObj {
    String msg;
    String buttonText;
    String buttonUrl;
    Boolean isBlockingPopup;


    public PopupObj(String msg, String buttonText, String buttonUrl, Boolean isBlockingPopup) {
        this.msg = msg;
        this.buttonText = buttonText;
        this.buttonUrl = buttonUrl;
        this.isBlockingPopup = isBlockingPopup;
    }
    
}