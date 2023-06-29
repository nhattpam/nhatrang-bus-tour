package com.grouppro.nhatrangbustour.service;

import com.grouppro.nhatrangbustour.Entity.PriceFrame;
import com.grouppro.nhatrangbustour.Entity.PriceFrameTicket;
import com.grouppro.nhatrangbustour.Entity.TicketType;
import com.grouppro.nhatrangbustour.repository.PriceFrameTicketRepository;
import com.grouppro.nhatrangbustour.service.interfaces.IPriceFrameService;
import com.grouppro.nhatrangbustour.service.interfaces.IPriceFrameTicketService;
import com.grouppro.nhatrangbustour.service.interfaces.ITicketTypeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PriceFrameTicketService implements IPriceFrameTicketService {
    private final PriceFrameTicketRepository priceFrameTicketRepository;
    private final IPriceFrameService priceFrameService;
    private final ITicketTypeService ticketTypeService;

    @Override
    public List<PriceFrameTicket> getPriceFrameTickets() {
        return priceFrameTicketRepository.findAll();
    }

    @Override
    public Long savePriceFrameTicket(PriceFrameTicket priceFrameTicket, Long prid, Long ttid) {
        PriceFrame priceFrame = priceFrameService.getPriceFrameById(prid);
        TicketType ticketType = ticketTypeService.getTicketTypeById(ttid);
        priceFrameTicket.setPriceFrame(priceFrame);
        priceFrameTicket.setTicketType(ticketType);
        priceFrameTicketRepository.save(priceFrameTicket);
        if(priceFrameTicket!=null){
            List<PriceFrameTicket> priceFrameTickets = priceFrameTicketRepository.findAllByPriceFrame(priceFrame);
            List<PriceFrameTicket> TpriceFrameTickets1 = priceFrameTicketRepository.findAllByTicketType(ticketType);
            priceFrame.setPriceFrameTicket(priceFrameTickets);
            ticketType.setPriceFrameTicket(TpriceFrameTickets1);
            priceFrameService.savePriceFrame(priceFrame);
            ticketTypeService.editTicketType(ticketType);
            return priceFrameTicket.getPriceFrameTicketId();
        }
        return null;
    }

    @Override
    public PriceFrameTicket getPriceFrameTicketById(Long id) {
        return priceFrameTicketRepository.getReferenceById(id);
    }

    @Override
    public List<PriceFrameTicket> getPrice(Long pfid) {
        PriceFrame priceFrame = priceFrameService.getPriceFrameById(pfid);
        List<PriceFrameTicket> priceFrameTickets = priceFrameTicketRepository.findAllByPriceFrame(priceFrame);
        return priceFrameTickets;
    }
}
