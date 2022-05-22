package com.easy.zssn.repository;

import org.springframework.stereotype.Repository;

import com.easy.zssn.model.Survivor;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface SurvivorRepository extends JpaRepository<Survivor, Integer> {
    
}
