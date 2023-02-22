package com.easygo.david.easygotest.services;

import com.easygo.david.easygotest.controllers.request.NewSurvivorRequest;
import com.easygo.david.easygotest.controllers.request.UpdateSurvivorRequest;
import com.easygo.david.easygotest.exceptions.ApiRequestException;
import com.easygo.david.easygotest.exceptions.NotFoundException;
import com.easygo.david.easygotest.models.Survivor;
import com.easygo.david.easygotest.repositories.SurvivorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class SurvivorsService {
    @Autowired
    private final SurvivorRepository survivorsRepository;

    public List<Survivor> findAll() {
        return survivorsRepository.findAll();
    }

    public Survivor findSurvivorById(UUID id) {
        var found = survivorsRepository.findById(id);
        if (found.isPresent()) return found.get();
        else throw new NotFoundException("User with ID " + id + "not exits");
    }

    public Survivor registrateSurvivor(NewSurvivorRequest request) {
        if (request == null) throw new IllegalStateException("Survivor can't be null");

        Survivor survivor = new Survivor(request.getFirst_name(), request.getSecond_name(), request.getAge(), request.getGender());
        return survivorsRepository.save(survivor);
    }

    public Survivor updateSurvivorData(UUID id, UpdateSurvivorRequest request) {
        try {
            var svr = survivorsRepository.findById(id);
            var toUpdate = svr.get();
            if (request.getFirst_name() != null) toUpdate.setFirst_name(request.getFirst_name());
            if (request.getSecond_name() != null) toUpdate.setSecond_name(request.getSecond_name());
            if (request.getAge() != null) toUpdate.setAge(request.getAge());
            return survivorsRepository.save(toUpdate);
        } catch (NoSuchElementException e) {
            throw new NotFoundException("User with ID " + id + "not exits");
        } catch (Exception e) {
            throw new ApiRequestException("ID format error");
        }
    }

    public void deleteSurvivor(UUID id) {
        try {
            survivorsRepository.deleteById(id);
        } catch (Exception e) {
            throw new ApiRequestException("ID format error");
        }
    }
}
