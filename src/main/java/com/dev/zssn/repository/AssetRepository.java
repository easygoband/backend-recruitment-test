package com.dev.zssn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.zssn.models.Asset;

public interface AssetRepository extends JpaRepository<Asset, Long> {

  List<Asset> findByName(String name);

}
