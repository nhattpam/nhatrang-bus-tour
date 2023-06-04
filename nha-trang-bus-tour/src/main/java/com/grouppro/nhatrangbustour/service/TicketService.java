package com.grouppro.nhatrangbustour.service;

import com.grouppro.nhatrangbustour.repository.TicketRepository;
import com.grouppro.nhatrangbustour.service.interfaces.ITicketService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class TicketService implements ITicketService {
    private final TicketRepository ticketRepository;
}
