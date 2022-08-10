package com.dev.zssn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.zssn.models.Asset;

public interface AssetRepository extends JpaRepository<Asset, Long> {

}
