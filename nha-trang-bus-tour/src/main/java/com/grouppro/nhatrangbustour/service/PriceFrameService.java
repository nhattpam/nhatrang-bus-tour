package com.grouppro.nhatrangbustour.service;

import com.grouppro.nhatrangbustour.repository.PriceFrameRepository;
import com.grouppro.nhatrangbustour.service.interfaces.IPriceFrameService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class PriceFrameService implements IPriceFrameService {
    private final PriceFrameRepository priceFrameRepository;
}
