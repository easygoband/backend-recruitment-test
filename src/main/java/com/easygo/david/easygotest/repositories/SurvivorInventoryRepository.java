package com.easygo.david.easygotest.repositories;

import com.easygo.david.easygotest.models.SurvivorInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SurvivorInventoryRepository extends JpaRepository<SurvivorInventory, UUID> {

    @Query("select s from survivor_inventory s join infected_register i on s.id=i.id where i.infected=:infected")
    List<SurvivorInventory> findByInfectedSurvivor(@Param("infected") Boolean infected);

}
