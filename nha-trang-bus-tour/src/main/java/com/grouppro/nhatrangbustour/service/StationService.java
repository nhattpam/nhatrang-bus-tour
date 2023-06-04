package com.grouppro.nhatrangbustour.service;

import com.grouppro.nhatrangbustour.repository.StationRepository;
import com.grouppro.nhatrangbustour.service.interfaces.IStationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class StationService implements IStationService {
    private final StationRepository stationRepository;
}
