package com.dev.zssn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.zssn.models.Position;

public interface PositionRepository extends JpaRepository<Position, Long> {

}
