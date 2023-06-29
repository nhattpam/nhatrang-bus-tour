package com.grouppro.nhatrangbustour.service.interfaces;

import com.grouppro.nhatrangbustour.Entity.TicketType;

import java.util.List;

public interface ITicketTypeService {
    List<TicketType> getTicketTypes();
    Long saveTicketType(TicketType ticketType, Long rid);
    TicketType getTicketTypeById(Long ttid);
    Long editTicketType(TicketType ticketType);
}
