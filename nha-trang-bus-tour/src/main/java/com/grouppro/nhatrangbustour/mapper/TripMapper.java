package com.grouppro.nhatrangbustour.mapper;

import com.grouppro.nhatrangbustour.Entity.Trip;
import com.grouppro.nhatrangbustour.dto.TripDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TripMapper {
    TripMapper INSTANCE = Mappers.getMapper(TripMapper.class);
    TripDTO toDTO(Trip trip);
    Trip toEntity(TripDTO tripDTO);
}
