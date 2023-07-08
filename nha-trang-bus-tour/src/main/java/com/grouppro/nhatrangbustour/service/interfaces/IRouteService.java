package com.grouppro.nhatrangbustour.service.interfaces;

import com.grouppro.nhatrangbustour.Entity.Route;

import java.util.List;

public interface IRouteService {
    List<Route> getRoutes();
    Long saveRoute(Route route);
    Route getRouteByID(Long rid);
    List<Route> SearchRoute(String fromDestination, String toDestination);

}
