package com.easygo.david.easygotest.repositories;



import com.easygo.david.easygotest.models.Survivor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SurvivorRepository extends JpaRepository<Survivor, UUID> {
}