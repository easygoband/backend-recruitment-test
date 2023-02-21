package com.easygo.david.easygotest.services;

import com.easygo.david.easygotest.controllers.request.NewSurvivorRequest;
import com.easygo.david.easygotest.controllers.request.UpdateSurvivorRequest;
import com.easygo.david.easygotest.models.Survivor;
import org.springframework.stereotype.Service;

@Service
public class SurvivorsService {
    public Survivor findSurvivorById(String id) {
        return new Survivor();
    }

    public Survivor registrateSurvivor(NewSurvivorRequest requestBody) {
        return new Survivor();
    }

    public Survivor updateSurvivorData(String id, UpdateSurvivorRequest request) {
        return new Survivor();
    }

    public void deleteSurvivor(String id) {
    }
}
