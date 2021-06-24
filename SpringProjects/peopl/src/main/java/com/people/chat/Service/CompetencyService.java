package com.people.chat.Service;

import java.util.List;
import java.util.Map;

import com.google.gson.JsonArray;
import com.people.chat.Model.Competency;

public interface CompetencyService {
    public List<Competency> createCompetenciesIfNeeded(JsonArray competencyList, Integer userId);

    void deleteExistingCompetencies(Integer userId);

    public Map<Competency, Integer> getCompListForUserId(Integer userId);

}