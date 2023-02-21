package com.easygo.david.easygotest.services;

import com.easygo.david.easygotest.models.InfectedRegister;
import com.easygo.david.easygotest.models.Survivor;
import com.easygo.david.easygotest.repositories.InfectedRegisterRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public InfectedRegister findBySurvivorId(String id) {
        if (id == null) throw new IllegalStateException("id can't be null");
        try {
            UUID uuid = UUID.fromString(id);
            var svr = infectedRegisterRepository.findById(uuid);
            if (svr.isPresent()) return svr.get();
            else throw new IllegalStateException("User with ID " + id + "not exits");
        } catch (Exception e) {
            throw new IllegalStateException("ID format error");
        }
    }

    public InfectedRegister updaterUserInfectedAlerts(String id) {
        if (id == null) throw new IllegalStateException("id can't be null");
        try {
            UUID uuid = UUID.fromString(id);
            var found = infectedRegisterRepository.findById(uuid);
            if (found.isPresent()) {
                InfectedRegister register = found.get();
                register.setInfected_alerts(register.getInfected_alerts() + 1);

                if (register.getInfected_alerts() >= MAX_INFECTED_ALERTS) register.setInfected(true);
                
                infectedRegisterRepository.save(register);

                return register;
            } else throw new IllegalStateException("User with ID " + id + "not exits");
        } catch (Exception e) {
            throw new IllegalStateException("ID format error");
        }
    }

    public InfectedRegister createInfectedRegister(Survivor survivor) {
        if (survivor == null) throw new IllegalStateException("Survivor can't be null");
        InfectedRegister register = new InfectedRegister(survivor);
        register.setSurvivor_id(survivor.getId());
        return infectedRegisterRepository.save(register);
    }

    public void deleteInfectedRegister(String id) {
        if (id == null) throw new IllegalStateException("ID can't be null");
        try {
            UUID uuid = UUID.fromString(id);
            infectedRegisterRepository.deleteById(uuid);
        } catch (Exception e) {
            throw new IllegalStateException("ID format error");
        }
    }
}
