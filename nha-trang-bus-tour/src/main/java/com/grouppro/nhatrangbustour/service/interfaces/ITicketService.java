package com.grouppro.nhatrangbustour.service.interfaces;

import com.grouppro.nhatrangbustour.Entity.Order;
import com.grouppro.nhatrangbustour.Entity.Ticket;
import com.grouppro.nhatrangbustour.Entity.TicketType;
import com.grouppro.nhatrangbustour.Entity.User;

import java.util.List;

public interface ITicketService {
    List<Ticket> getTickets();
    List<Ticket> getTicketsByOrder(User user);
    List<Ticket> getTicketsByOrderId(Order order);
    Long saveTicket(Ticket ticket, Long tid, Long oid, Long sid, Long ttid);
    Long updateTicket(Ticket ticket);
    Ticket getTicketByTicketId(Long tid);
    String getTicketTypeByTicketId(Long tid);
}
