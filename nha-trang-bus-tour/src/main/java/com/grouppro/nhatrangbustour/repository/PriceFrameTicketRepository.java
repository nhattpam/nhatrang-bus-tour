package com.grouppro.nhatrangbustour.repository;

import com.grouppro.nhatrangbustour.Entity.PriceFrame;
import com.grouppro.nhatrangbustour.Entity.PriceFrameTicket;
import com.grouppro.nhatrangbustour.Entity.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceFrameTicketRepository extends JpaRepository<PriceFrameTicket, Long> {
    List<PriceFrameTicket> findAllByPriceFrame(PriceFrame priceFrame);
    List<PriceFrameTicket> findAllByTicketType(TicketType ticketType);
}
