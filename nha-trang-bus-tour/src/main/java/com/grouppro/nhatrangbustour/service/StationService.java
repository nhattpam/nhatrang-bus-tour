package com.grouppro.nhatrangbustour.service;

import com.grouppro.nhatrangbustour.Entity.Station;
import com.grouppro.nhatrangbustour.repository.StationRepository;
import com.grouppro.nhatrangbustour.service.interfaces.IStationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StationService implements IStationService {
    private final StationRepository stationRepository;

    @Override
    public List<Station> getStations() {
        return stationRepository.findAll();
    }

    @Override
    public Long saveStation(Station station) {
        stationRepository.save(station);
        if(station!=null){
            return station.getStationId();
        }
        return null;
    }

    @Override
    public Station getStationById(Long sid) {
        return stationRepository.getReferenceById(sid);
    }
}
