package com.grouppro.nhatrangbustour.mapper;

import com.grouppro.nhatrangbustour.Entity.StationRoute;
import com.grouppro.nhatrangbustour.dto.StationRouteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StationRouteMapper {
    StationRouteMapper INSTANCE = Mappers.getMapper(StationRouteMapper.class);
    StationRouteDTO toDTO(StationRoute stationRoute);
    StationRoute toEntity(StationRouteDTO stationRouteDTO);
}
