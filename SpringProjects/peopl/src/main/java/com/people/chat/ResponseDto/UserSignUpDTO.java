package com.people.chat.ResponseDto;

import java.util.List;

import com.people.chat.Model.Company;
import com.people.chat.Model.Competency;
import com.people.chat.Model.Profession;

public class UserSignUpDTO {
    Company company;
    List<Competency>compList;
    Profession prof;

    public UserSignUpDTO(Company company, List<Competency> compList, Profession prof) {
        this.company = company;
        this.compList = compList;
        this.prof = prof;
    }
    
}