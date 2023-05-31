package com.grouppro.nhatrangbustour.mapper;

import com.grouppro.nhatrangbustour.Entity.Ticket;
import com.grouppro.nhatrangbustour.dto.TicketDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);
    TicketDTO toDTO(Ticket ticket);
    Ticket toEntity(TicketDTO ticketDTO);
}
