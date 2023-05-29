package com.grouppro.nhatrangbustour.mapper;

import com.grouppro.nhatrangbustour.Entity.PriceFrame;
import com.grouppro.nhatrangbustour.dto.PriceFrameDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PriceFrameMapper {
    PriceFrameMapper INSTANCE = Mappers.getMapper(PriceFrameMapper.class);
    PriceFrameDTO toDTO(PriceFrame priceFrame);
    PriceFrame toEntity(PriceFrameDTO priceFrameDTO);
}
