package com.grouppro.nhatrangbustour.service.interfaces;

import com.grouppro.nhatrangbustour.Entity.Route;
import com.grouppro.nhatrangbustour.Entity.Station;
import com.grouppro.nhatrangbustour.Entity.StationRoute;

import java.util.List;

public interface IStationRouteService {
    List<StationRoute> getStationRoute();
    Long saveStationRoute(StationRoute stationRoute);
    List<StationRoute> getStationRouteByStation(Station station);
    List<StationRoute> getStationRouteByRoute(Route route);

}
