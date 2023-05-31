package com.grouppro.nhatrangbustour.mapper;

import com.grouppro.nhatrangbustour.Entity.Station;
import com.grouppro.nhatrangbustour.dto.StationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StationMapper {
    StationMapper INSTANCE = Mappers.getMapper(StationMapper.class);
    StationDTO toDTO(Station station);
    Station toEntity(StationDTO stationDTO);
}
