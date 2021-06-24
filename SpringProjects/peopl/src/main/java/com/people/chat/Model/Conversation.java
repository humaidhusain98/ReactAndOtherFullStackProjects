package com.people.chat.Model;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Conversation {
    Integer id;
    Boolean isOpen;
    List<Integer> pidList;
    Integer pendingPid;;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean isIsOpen() {
        return this.isOpen;
    }

    public Boolean getIsOpen() {
        return this.isOpen;
    }

    public void setIsOpen(Boolean isOpen) {
        this.isOpen = isOpen;
    }

    // public Long getCreatedOn() {
    // return this.createdOn;
    // }

    // public void setCreatedOn(Long createdOn) {
    // this.createdOn = createdOn;
    // }

    public List<Integer> getPidList() {
        return this.pidList;
    }

    public void setPidList(List<Integer> pidList) {
        Collections.sort(pidList);
        this.pidList = pidList;
    }

    public Integer getPendingPid() {
        return this.pendingPid;
    }

    public void setPendingPid(Integer pendingPid) {
        this.pendingPid = pendingPid;
    }

    public Conversation(Integer id, Boolean isOpen, List<Integer> pidList, Integer pendingPid) {
        this.id = id;
        this.isOpen = isOpen;
        this.pidList = pidList;
        Collections.sort(this.pidList);
        this.pendingPid = pendingPid;
    }

    public static Conversation getConversationFromSQL(ResultSet result) {
        try {
            if (result == null) {
                // System.out.println("SOUMIK ==== FUCK YOU !!!!");
            }
            //while (result.next()) {
                Array list = result.getArray(3);
                // System.out.println("SOUMIK == ABOUT TO TYPECAST PIDLIST!!!");
                Integer[] pList =  (Integer[])list.getArray();
                return new Conversation(result.getInt(1), result.getBoolean(2), Arrays.asList(pList), result.getInt(4));
           // }
        } catch (SQLException e) {
            // System.out.println("SOUMIK === errorMsg = " + e.getMessage());
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public Integer getLastSentPID() {
        List<Integer>pidList = this.getPidList();
        for(Integer pid : pidList) {
            if(pid != this.pendingPid) return pid;
        }
        return null;
    }

}