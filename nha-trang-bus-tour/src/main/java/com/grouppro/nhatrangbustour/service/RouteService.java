package com.grouppro.nhatrangbustour.service;

import com.grouppro.nhatrangbustour.repository.RouteRepository;
import com.grouppro.nhatrangbustour.service.interfaces.IRouteService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class RouteService implements IRouteService {
    private final RouteRepository routeRepository;
}
