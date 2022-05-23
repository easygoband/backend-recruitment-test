package com.easy.zssn.service;

import java.util.List;

import com.easy.zssn.model.Survivor;
import com.easy.zssn.repository.SurvivorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurvivorService implements SurvivorInterface {
    @Autowired
    public SurvivorRepository suvRepository;
    @Override
    public Survivor upsertSurvivor(Survivor newSurvivor) {
        return suvRepository.save(newSurvivor);
    }
    @Override
    public boolean deleteSurvivor(int id) {
        boolean resultado= false;
        try {
            suvRepository.deleteById(id);
            resultado = true;
        } catch (Exception e) {
            resultado = false;
        }
        return resultado;
    }
    @Override
    public Survivor findByIDSurvivor(int id) {
        return suvRepository.findById(id).get();
    }
    @Override
    public List<Survivor> findAllSurvivor() {
        return suvRepository.findAll();
    }
    
}
