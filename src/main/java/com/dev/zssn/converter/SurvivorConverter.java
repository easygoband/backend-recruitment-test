package com.dev.zssn.converter;

import java.util.List;
import java.util.stream.Collectors;

import com.dev.zssn.dto.InventoryDto;
import com.dev.zssn.dto.PositionDto;
import com.dev.zssn.dto.SurvivorDto;
import com.dev.zssn.models.Position;
import com.dev.zssn.models.Survivor;
import com.dev.zssn.models.SurvivorAsset;

public class SurvivorConverter {

  public Survivor toModel(final SurvivorDto dto, final Position position, final List<SurvivorAsset> inventory) {
    final Survivor survivor = new Survivor();
    survivor.setName(dto.getName());
    survivor.setAge(dto.getAge());
    survivor.setGender(dto.getGender());
    survivor.setLastPosition(position);
    survivor.setIsInfected(false);
    survivor.setInventory(inventory);
    return survivor;
  }

  public SurvivorDto toDto(final Survivor survivor) {
    final SurvivorDto dto = new SurvivorDto();
    dto.setName(survivor.getName());
    dto.setAge(survivor.getAge());
    dto.setGender(survivor.getGender());
    dto.setIsInfected(survivor.getIsInfected());

    final PositionConverter positionConverter = new PositionConverter();
    final PositionDto positionDto = positionConverter.toDto(survivor.getLastPosition());
    dto.setLastPosition(positionDto);

    final List<SurvivorAsset> inventory = survivor.getInventory();
    if(inventory!=null) {
      final InventoryConverter inventoryConverter = new InventoryConverter();
      final List<InventoryDto> inventoryDto = inventory.stream().map(i -> inventoryConverter.toDto(i)).collect(Collectors.toList());
			dto.setInventory(inventoryDto);
		}
    return dto;
  }

}
