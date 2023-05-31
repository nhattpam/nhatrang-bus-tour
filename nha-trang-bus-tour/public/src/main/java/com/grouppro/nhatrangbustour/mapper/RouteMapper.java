package com.grouppro.nhatrangbustour.mapper;

import com.grouppro.nhatrangbustour.Entity.Route;
import com.grouppro.nhatrangbustour.dto.RouteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RouteMapper {
    RouteMapper INSTANCE = Mappers.getMapper(RouteMapper.class);
    RouteDTO toDTO(Route route);
    Route toEntity(RouteDTO routeDTO);
}
