package com.grouppro.nhatrangbustour.service.interfaces;

import com.grouppro.nhatrangbustour.Entity.PriceFrame;
import com.grouppro.nhatrangbustour.Entity.PriceFrameTicket;

import java.util.List;

public interface IPriceFrameTicketService {
    List<PriceFrameTicket> getPriceFrameTickets();
    Long savePriceFrameTicket(PriceFrameTicket priceFrameTicket, Long prid, Long ttid);
    PriceFrameTicket getPriceFrameTicketById(Long id);
    List<PriceFrameTicket> getPrice(Long pfid);
}
