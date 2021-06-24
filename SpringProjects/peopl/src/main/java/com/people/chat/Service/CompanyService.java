package com.people.chat.Service;

import com.google.gson.JsonObject;
import com.people.chat.Model.Company;

public interface CompanyService {
    public Company createIfNotPresent(JsonObject companyObj);
}