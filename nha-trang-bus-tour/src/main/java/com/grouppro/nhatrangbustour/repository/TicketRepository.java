package com.grouppro.nhatrangbustour.repository;

import com.grouppro.nhatrangbustour.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findAllByTrip(Trip trip);
    List<Ticket> findAllByOrder(Order order);
    List<Ticket> findAllByService(Service service);
    List<Ticket> findAllByTicketType(TicketType ticketType);
    @Query("SELECT t.ticketType FROM Ticket t WHERE t.ticketId = :ticketId")
    TicketType findTicketTypeByTicketId(@Param("ticketId") Long ticketId);
}
