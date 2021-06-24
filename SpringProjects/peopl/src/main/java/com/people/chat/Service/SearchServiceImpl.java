package com.people.chat.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.route53.model.ReusableDelegationSetLimit;
import com.google.gson.JsonObject;
import com.people.chat.Model.Competency;
import com.people.chat.Model.User;
import com.people.chat.ResponseDto.SearchResponseDTO;
import com.people.chat.View.CompanyView;
import com.people.chat.View.CompetencyView;
import com.people.chat.View.ProfessionView;
import com.people.chat.View.UserView;

public class SearchServiceImpl implements SearchService {

    @Override
    public List<SearchResponseDTO> getResultsFromCurrentCompany(Integer companyId, Integer pid) {
        List<User> userList = new ArrayList<>();
        try {
            userList = UserView.getUserFromColValue(companyId, "currentcompanyid");
            // System.out.println("YEARS EXP === " +userList.size());
            return getSearchDetails(userList, pid);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    //THIS SHOULD NOT BE IN SEARCH - RENAME TO GETUSER DETAILS OR SOMETHING LIKE THAT AND PLACE IN USER CLASS
    public List<SearchResponseDTO> getSearchDetails(List<User> userList, Integer pid) {
        CompetencyService compService = new CompetencyServiceImpl();
        ConversationService convService = new ConversationServiceImpl();
        List<SearchResponseDTO> searchResults = new ArrayList<>();
        for (User user : userList) {
            if (user == null)
                continue;

            Map<Competency, Integer> compMap = compService.getCompListForUserId(user.getId());
            List<JsonObject> compData = new ArrayList<>();
            compMap.entrySet().stream().forEach(e -> {
                Competency competency = e.getKey();
                Integer exp = e.getValue();
                compData.add(competency.toJsonObj(exp));
            });

            Boolean isConnected = convService.isConnected(user.getId(), pid);
            searchResults.add(new SearchResponseDTO(user.getName(), compData, user.getTotalYearsOfExp(),
                    CompanyView.getCompanyObj(user.getCurrentCompanyId()), isConnected, user.getId(),
                    ProfessionView.getProfessionFromId(user.getCurrentProfessionId()), user.getProfilePicUrl(),
                    user.getOnLeave(), user.getCity(), user.getCountry()));
        }

        return searchResults;
    }

    @Override
    public List<SearchResponseDTO> getResultsFromCompetency(Integer compId, Integer exp, Integer pid) {
        List<User> userList = new ArrayList<>();
        userList = CompetencyView.getUsersFromCompMetric(compId, exp);
        return getSearchDetails(userList, pid);
    }

    @Override
    public List<SearchResponseDTO> getResultsFromCurrentProf(Integer profId, Integer years, Integer pid) {
        List<User> userList = new ArrayList<>();
        try {
            userList = UserView.getUsersFromProfAndExp(profId, years);
            return getSearchDetails(userList, pid);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<SearchResponseDTO> getResultsFromCurrentLocation(String cityName, Integer pid) {
        try {
            List<User> users = UserView.getUserFromColValue(cityName, "city", true);
            return getSearchDetails(users, pid);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    public List<SearchResponseDTO> getResultsFromName(String name, Integer pid) {
        try {
            List<User> users = UserView.getUserFromColValue(name, "name", true);
            return getSearchDetails(users, pid);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        SearchService s = new SearchServiceImpl();
        List<SearchResponseDTO> slist = s.getResultsFromCurrentCompany(2, 1234);
        System.out.println(slist.size());
    }

}