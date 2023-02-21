package com.easygo.david.easygotest.services;

import com.easygo.david.easygotest.controllers.request.NewSurvivorRequest;
import com.easygo.david.easygotest.controllers.request.UpdateSurvivorRequest;
import com.easygo.david.easygotest.models.Survivor;
import com.easygo.david.easygotest.repositories.SurvivorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class SurvivorsService {
    @Autowired
    private final SurvivorRepository survivorsRepository;

    public List<Survivor> findAll() {
        return survivorsRepository.findAll();
    }

    public Survivor findSurvivorById(String id) {
        if (id == null) throw new IllegalStateException("ID can't be null");
        try {
            UUID uuid = UUID.fromString(id);
            var svr = survivorsRepository.findById(uuid);
            if (svr.isPresent()) return svr.get();
            else throw new IllegalStateException("User with ID " + id + "not exits");
        } catch (Exception e) {
            throw new IllegalStateException("ID format error");
        }
    }

    public Survivor registrateSurvivor(NewSurvivorRequest request) {
        if (request == null) throw new IllegalStateException("Survivor can't be null");

        Survivor survivor = new Survivor(request.getFirst_name(), request.getSecond_name(), request.getAge(), request.getGender());
        return survivorsRepository.save(survivor);
    }

    public Survivor updateSurvivorData(String id, UpdateSurvivorRequest request) {
        if (id == null) throw new IllegalStateException("ID can't be null");
        try {
            UUID uuid = UUID.fromString(id);
            var svr = survivorsRepository.findById(uuid);
            if (svr.isPresent()) {
                Survivor toUpdate = svr.get();
                if (request.getFirst_name() != null) toUpdate.setFirst_name(request.getFirst_name());
                if (request.getSecond_name() != null) toUpdate.setSecond_name(request.getSecond_name());
                if (request.getAge() != null) toUpdate.setAge(request.getAge());
                return survivorsRepository.save(toUpdate);
            } else throw new IllegalStateException("User with ID " + id + "not exits");
        } catch (Exception e) {
            throw new IllegalStateException("ID format error");
        }
    }

    public void deleteSurvivor(String id) {
        if (id == null) throw new IllegalStateException("ID can't be null");
        try {
            UUID uuid = UUID.fromString(id);
            survivorsRepository.deleteById(uuid);
        } catch (Exception e) {
            throw new IllegalStateException("ID format error");
        }
    }
}
