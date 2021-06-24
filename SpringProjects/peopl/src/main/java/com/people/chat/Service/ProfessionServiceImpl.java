package com.people.chat.Service;

import com.google.gson.JsonObject;
import com.people.chat.Model.Profession;
import com.people.chat.View.ProfessionView;

public class ProfessionServiceImpl implements ProfessionService {

    @Override
    public Profession createProfIfNeeded(JsonObject profObj) {
        Profession prof = ProfessionView.getProfessionFromId(profObj.get("id").getAsInt());
        if (prof == null) {
            prof = new Profession(-1, profObj.get("name").getAsString(), false);
            prof =  ProfessionView.createProf(prof);
        }
        return prof;
    }

}