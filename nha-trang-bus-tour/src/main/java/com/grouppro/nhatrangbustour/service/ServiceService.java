package com.grouppro.nhatrangbustour.service;

import com.grouppro.nhatrangbustour.repository.ServiceRepository;
import com.grouppro.nhatrangbustour.service.interfaces.IServiceService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ServiceService implements IServiceService {
    private final ServiceRepository serviceRepository;
}
