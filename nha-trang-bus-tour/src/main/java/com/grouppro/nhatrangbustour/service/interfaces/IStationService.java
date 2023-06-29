package com.grouppro.nhatrangbustour.service.interfaces;

import com.grouppro.nhatrangbustour.Entity.Station;

import java.util.List;

public interface IStationService {
    List<Station> getStations();
    Long saveStation(Station station);
    Station getStationById(Long sid);
}
