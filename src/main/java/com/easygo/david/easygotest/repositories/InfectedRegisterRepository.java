package com.easygo.david.easygotest.repositories;

import com.easygo.david.easygotest.models.InfectedRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InfectedRegisterRepository extends JpaRepository<InfectedRegister, UUID> {
}
