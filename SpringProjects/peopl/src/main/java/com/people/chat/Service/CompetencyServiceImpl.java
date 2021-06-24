package com.people.chat.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.people.chat.Model.Competency;
import com.people.chat.View.CompetencyView;

public class CompetencyServiceImpl implements CompetencyService {

    @Override
    public List<Competency> createCompetenciesIfNeeded(JsonArray competencyList, Integer userId) {
        List<Competency> compList = new ArrayList<>();
        for (int index = 0; index < competencyList.size(); index++) {
            JsonObject compObj = competencyList.get(index).getAsJsonObject();
            Integer id = compObj.get("id").getAsInt();
            Integer expInYears = compObj.get("exp").getAsInt();

            Competency competency = CompetencyView.getCompetencyObject(id);
            if (competency == null) {
                competency = new Competency(-1, compObj.get("name").getAsString(), -1);
                competency = CompetencyView.save(competency);
                compList.add(competency);
            }
            CompetencyView.createEntryForCompetencyMap(userId, competency.getId(), expInYears);
            // yearsOfExp);
        }
        if (compList.size() != 0)
            return compList;
        return null;
    }

    @Override
    public Map<Competency, Integer> getCompListForUserId(Integer userId) {
        return CompetencyView.getCompetencyMapForUserId(userId);
    }

    public static void main(String[] args) {
        Map<Competency, Integer> map = new CompetencyServiceImpl().getCompListForUserId(4);
        List<JsonObject> compData = new ArrayList<>();
        map.entrySet().stream().forEach(e -> {
            Competency competency = e.getKey();
            Integer exp = e.getValue();
            if (competency != null)
                compData.add(competency.toJsonObj(exp));
        });
        System.out.println(map.keySet().size());
    }

    @Override
    public void deleteExistingCompetencies(Integer userId) {
        CompetencyView.deleteCompetencyMetrics(userId);
    }

}