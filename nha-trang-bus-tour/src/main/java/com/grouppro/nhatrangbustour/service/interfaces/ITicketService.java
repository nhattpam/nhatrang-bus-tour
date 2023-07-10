package com.grouppro.nhatrangbustour.service.interfaces;

import com.grouppro.nhatrangbustour.Entity.Order;
import com.grouppro.nhatrangbustour.Entity.Ticket;
import com.grouppro.nhatrangbustour.Entity.User;

import java.util.List;

public interface ITicketService {
    List<Ticket> getTickets();
    List<Ticket> getTicketsByOrder(User user);
    Long saveTicket(Ticket ticket, Long tid, Long oid, Long sid, Long ttid);
}
