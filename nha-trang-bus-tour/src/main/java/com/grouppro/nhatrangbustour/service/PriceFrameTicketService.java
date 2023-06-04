package com.grouppro.nhatrangbustour.service;

import com.grouppro.nhatrangbustour.repository.PriceFrameTicketRepository;
import com.grouppro.nhatrangbustour.service.interfaces.IPriceFrameTicketService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class PriceFrameTicketService implements IPriceFrameTicketService {
    private final PriceFrameTicketRepository priceFrameTicketRepository;
}
