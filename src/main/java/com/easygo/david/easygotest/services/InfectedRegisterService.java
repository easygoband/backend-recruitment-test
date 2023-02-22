package com.easygo.david.easygotest.services;

import com.easygo.david.easygotest.exceptions.ApiRequestException;
import com.easygo.david.easygotest.exceptions.NotFoundException;
import com.easygo.david.easygotest.models.InfectedRegister;
import com.easygo.david.easygotest.models.Survivor;
import com.easygo.david.easygotest.repositories.InfectedRegisterRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class InfectedRegisterService {
    private static final Integer MAX_INFECTED_ALERTS = 3;

    @Autowired
    private final InfectedRegisterRepository infectedRegisterRepository;

    public List<InfectedRegister> findAll() {
        return infectedRegisterRepository.findAll();
    }

    public List<InfectedRegister> findCustom(Boolean infected) {
        var total = infectedRegisterRepository.findAll();
        return total.stream().filter(register -> infected == register.getInfected()).toList();
    }

    public InfectedRegister findBySurvivorId(UUID id) {
        try {
            var svr = infectedRegisterRepository.findById(id);
            return svr.get();
        } catch (NoSuchElementException e) {
            throw new NotFoundException("User with ID " + id + "not exits");
        } catch (Exception e) {
            throw new ApiRequestException("ID request error. ");
        }

    }

    public InfectedRegister updaterUserInfectedAlerts(UUID id) {
        try {
            var found = infectedRegisterRepository.findById(id);

            InfectedRegister register = found.get();
            register.setInfected_alerts(register.getInfected_alerts() + 1);

            if (register.getInfected_alerts() >= MAX_INFECTED_ALERTS) register.setInfected(true);

            infectedRegisterRepository.save(register);

            return register;
        } catch (NoSuchElementException e) {
            throw new NotFoundException("User Location with ID " + id + "not exits");
        } catch (Exception e) {
            throw new ApiRequestException("ID format error");
        }
    }

    public InfectedRegister createInfectedRegister(Survivor survivor) {
        try {
            if (survivor == null) throw new IllegalStateException("Survivor can't be null");
            InfectedRegister register = new InfectedRegister(survivor);
            register.setSurvivor_id(survivor.getId());
            return infectedRegisterRepository.save(register);
        } catch (Exception e){
            throw new ApiRequestException("Apir request error");
        }
    }

    public void deleteInfectedRegister(UUID id) {
        try {
            infectedRegisterRepository.deleteById(id);
        } catch (Exception e) {
            throw new ApiRequestException("ID format error");
        }
    }
}
