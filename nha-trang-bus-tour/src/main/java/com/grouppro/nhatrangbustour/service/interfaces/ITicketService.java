package com.grouppro.nhatrangbustour.service.interfaces;

import com.grouppro.nhatrangbustour.Entity.Ticket;

import java.util.List;

public interface ITicketService {
    List<Ticket> getTickets();
    Long saveTicket(Ticket ticket, Long tid, Long oid, Long sid, Long ttid);
}
