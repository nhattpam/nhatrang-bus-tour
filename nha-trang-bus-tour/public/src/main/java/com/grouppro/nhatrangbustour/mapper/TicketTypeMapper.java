package com.grouppro.nhatrangbustour.mapper;

import com.grouppro.nhatrangbustour.Entity.TicketType;
import com.grouppro.nhatrangbustour.dto.TicketTypeDTO;
import org.mapstruct.factory.Mappers;

public interface TicketTypeMapper {
    TicketTypeMapper INSTANCE = Mappers.getMapper(TicketTypeMapper.class);
    TicketTypeDTO toDTO(TicketType ticketType);
    TicketType toEntity(TicketTypeDTO ticketTypeDTO);
}
