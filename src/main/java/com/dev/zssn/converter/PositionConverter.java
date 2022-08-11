package com.dev.zssn.converter;

import com.dev.zssn.dto.PositionDto;
import com.dev.zssn.models.Position;

public class PositionConverter {
  
  public Position toModel(final PositionDto dto) {
    final Position position = new Position();
    position.setLatitude(dto.getLatitude());
    position.setLongitude(dto.getLongitude());
    return position;
  }

  public PositionDto toDto(final Position position) {
    final PositionDto dto = new PositionDto();
    dto.setLatitude(position.getLatitude());
    dto.setLongitude(position.getLongitude());
    return dto;
  }
}
