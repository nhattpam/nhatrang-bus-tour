package com.grouppro.nhatrangbustour.service;

import com.grouppro.nhatrangbustour.Entity.Route;
import com.grouppro.nhatrangbustour.Entity.TicketType;
import com.grouppro.nhatrangbustour.repository.TicketTypeRepository;
import com.grouppro.nhatrangbustour.service.interfaces.ITicketTypeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TicketTypeService implements ITicketTypeService {
    private final TicketTypeRepository ticketTypeRepository;
    private final RouteService routeService;

    @Override
    public List<TicketType> getTicketTypes() {
        return ticketTypeRepository.findAll();
    }

    @Override
    public Long saveTicketType(TicketType ticketType, Long rid) {
        Route route = routeService.getRouteByID(rid);
        ticketType.setRoute(route);
        ticketTypeRepository.save(ticketType);
        if(ticketType!=null){
            List<TicketType> ticketTypes = ticketTypeRepository.findAllByRoute(route);
            route.setTicketType(ticketTypes);
            routeService.saveRoute(route);
            return ticketType.getTicketTypeId();
        }
        return null;
    }
    @Override
    public TicketType getTicketTypeById(Long ttid) {
        return ticketTypeRepository.getReferenceById(ttid);
    }

    @Override
    public Long editTicketType(TicketType ticketType) {
        if(ticketType!=null){
            ticketTypeRepository.save(ticketType);
            return ticketType.getTicketTypeId();
        }
        return null;
    }
}
