package com.easy.zssn.service;

import com.easy.zssn.model.Survivor;
import com.easy.zssn.repository.SurvivorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurvivorService implements SurvivorInterfase {
    @Autowired
    public SurvivorRepository suvRepository;
    @Override
    public Survivor addSurvivor(Survivor newSurvivor) {
        return suvRepository.save(newSurvivor);
    }
    
}
