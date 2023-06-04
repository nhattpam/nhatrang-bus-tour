package com.grouppro.nhatrangbustour.service;

import com.grouppro.nhatrangbustour.repository.TripRepository;
import com.grouppro.nhatrangbustour.service.interfaces.ITripService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class TripService implements ITripService {
    private final TripRepository tripRepository;
}
