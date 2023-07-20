package com.grouppro.nhatrangbustour.service;

import com.grouppro.nhatrangbustour.Entity.*;
import com.grouppro.nhatrangbustour.repository.TicketRepository;
import com.grouppro.nhatrangbustour.service.interfaces.ITicketService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TicketService implements ITicketService {
    private final TicketRepository ticketRepository;
    private final TripService tripService;
    private final OrderService orderService;
    private final ServiceService serviceService;
    private final TicketTypeService ticketTypeService;
    @Override
    public List<Ticket> getTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public List<Ticket> getTicketsByOrder(User user) {
        List<Order> orders = orderService.getOrdersByUser(user);
        List<Ticket> tickets = new ArrayList<>();
        for (Order item: orders) {
            List<Ticket> tickets1 = ticketRepository.findAllByOrder(item);
            tickets.addAll(tickets1);
        }
        return tickets;
    }

    @Override
    public List<Ticket> getTicketsByOrderId(Order order) {
        return ticketRepository.findAllByOrder(order);
    }

    @Override
    public Long saveTicket(Ticket ticket, Long tid, Long oid, Long sid, Long ttid) {
        Trip trip = tripService.getTripByid(tid);
        Order order = orderService.getOrderById(oid);
        com.grouppro.nhatrangbustour.Entity.Service service = serviceService.getServiceById(sid);
        TicketType ticketType = ticketTypeService.getTicketTypeById(ttid);
        ticket.setTrip(trip);
        ticket.setOrder(order);
        ticket.setService(service);
        ticket.setTicketType(ticketType);
        if(ticket!=null){
            ticketRepository.save(ticket);
            List<Ticket> Ttickets = ticketRepository.findAllByTrip(trip);
            List<Ticket> Otickets = ticketRepository.findAllByOrder(order);
            List<Ticket> Stickets = ticketRepository.findAllByService(service);
            List<Ticket> TTtickets = ticketRepository.findAllByTicketType(ticketType);
            trip.setTicket(Ttickets);
            order.setTicket(Otickets);
            service.setTicket(Stickets);
            ticketType.setTicket(TTtickets);
            tripService.editTrip(trip);
            orderService.editOrder(order);
            serviceService.saveService(service);
            ticketTypeService.editTicketType(ticketType);
            return ticket.getTicketId();
        }
        return null;
    }
}
