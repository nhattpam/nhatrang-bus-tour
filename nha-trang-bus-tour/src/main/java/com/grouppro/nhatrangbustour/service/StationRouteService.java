package com.grouppro.nhatrangbustour.service;

import com.grouppro.nhatrangbustour.Entity.Route;
import com.grouppro.nhatrangbustour.Entity.Station;
import com.grouppro.nhatrangbustour.Entity.StationRoute;
import com.grouppro.nhatrangbustour.repository.StationRouteRepository;
import com.grouppro.nhatrangbustour.service.interfaces.IStationRouteService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StationRouteService implements IStationRouteService {
    private final StationRouteRepository stationRouteRepository;

    @Override
    public List<StationRoute> getStationRoute() {
        return stationRouteRepository.findAll();
    }

    @Override
    public Long saveStationRoute(StationRoute stationRoute) {
        stationRouteRepository.save(stationRoute);
        if(stationRoute!=null){
            return stationRoute.getStationRouteId();
        }
        return null;
    }

    @Override
    public List<StationRoute> getStationRouteByStation(Station station) {
        return stationRouteRepository.findAllByStation(station);
    }

    @Override
    public List<StationRoute> getStationRouteByRoute(Route route) {
        return stationRouteRepository.findAllByRoute(route);
    }
}
