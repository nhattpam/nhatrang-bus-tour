package com.grouppro.nhatrangbustour.service;

import com.grouppro.nhatrangbustour.repository.StationRouteRepository;
import com.grouppro.nhatrangbustour.service.interfaces.IStationRouteService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class StationRouteService implements IStationRouteService {
    private final StationRouteRepository stationRouteRepository;
}
