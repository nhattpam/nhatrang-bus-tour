package com.grouppro.nhatrangbustour.service;

import com.grouppro.nhatrangbustour.repository.TicketTypeRepository;
import com.grouppro.nhatrangbustour.service.interfaces.ITicketTypeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class TicketTypeService implements ITicketTypeService {
    private final TicketTypeRepository ticketTypeRepository;
}
