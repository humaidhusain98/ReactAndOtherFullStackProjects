package com.people.chat.Service;

import com.google.gson.JsonObject;
import com.people.chat.Model.Company;
import com.people.chat.View.CompanyView;

public class CompanyServiceImpl implements CompanyService {

    @Override
    public Company createIfNotPresent(JsonObject companyObj) {
        Integer id = companyObj.get("id").getAsInt();
        Company company = CompanyView.getCompanyObj(id);
        if (company == null) {
            company = new Company(-1, companyObj.get("name").getAsString());
            company = CompanyView.createCompany(company);
            // return company;
        }
        return company;
    }

    
    public Company createIfNotPresentwith4args(JsonObject companyObj) {
        Integer id = companyObj.get("id").getAsInt();
        Company company = CompanyView.getCompanyObj(id);
        if (company == null) {
            company = new Company(-1, companyObj.get("name").getAsString(),companyObj.get("description").getAsString(),companyObj.get("imageUrl").getAsString());
            company = CompanyView.createCompany(company);
            // return company;
        }
        return company;
    }
    
    
    
}