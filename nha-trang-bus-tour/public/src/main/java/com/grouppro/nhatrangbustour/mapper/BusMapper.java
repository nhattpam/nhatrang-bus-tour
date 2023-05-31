package com.grouppro.nhatrangbustour.mapper;

import com.grouppro.nhatrangbustour.Entity.Bus;
import com.grouppro.nhatrangbustour.dto.BusDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BusMapper {
    BusMapper INSTANCE = Mappers.getMapper(BusMapper.class);
    BusDTO toDTO(Bus bus);
    Bus toEntity(BusDTO busDTO);
}
