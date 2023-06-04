package com.grouppro.nhatrangbustour.service;

import com.grouppro.nhatrangbustour.repository.BusRepostory;
import com.grouppro.nhatrangbustour.service.interfaces.IBusService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class BusService implements IBusService {
    private final BusRepostory busRepostory;
}
