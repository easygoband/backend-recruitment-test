package com.easy.zssn.service;

import java.util.List;

import com.easy.zssn.model.Survivor;

public interface SurvivorInterface  {
    public Survivor upsertSurvivor(Survivor newSurvivor);

    public boolean deleteSurvivor(int id);

    public Survivor findByIDSurvivor(int id);

    public List<Survivor> findAllSurvivor();
}
