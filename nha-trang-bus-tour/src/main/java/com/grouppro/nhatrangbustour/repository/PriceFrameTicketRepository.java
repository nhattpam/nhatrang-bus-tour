package com.grouppro.nhatrangbustour.repository;

import com.grouppro.nhatrangbustour.Entity.PriceFrameTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceFrameTicketRepository extends JpaRepository<PriceFrameTicket, Long> {
}
