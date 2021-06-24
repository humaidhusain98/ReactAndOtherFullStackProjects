package com.people.chat.Service;

import com.google.gson.JsonObject;
import com.people.chat.Model.Profession;

public interface ProfessionService {
    public Profession createProfIfNeeded(JsonObject profObj);
}