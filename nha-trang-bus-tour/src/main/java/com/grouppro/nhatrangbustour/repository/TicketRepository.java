package com.grouppro.nhatrangbustour.repository;

import com.grouppro.nhatrangbustour.Entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
