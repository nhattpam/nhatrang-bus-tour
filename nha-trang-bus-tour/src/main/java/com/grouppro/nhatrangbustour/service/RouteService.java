package com.grouppro.nhatrangbustour.service;

import com.grouppro.nhatrangbustour.Entity.Route;
import com.grouppro.nhatrangbustour.repository.RouteRepository;
import com.grouppro.nhatrangbustour.service.interfaces.IRouteService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.Console;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RouteService implements IRouteService {
    private final RouteRepository routeRepository;

    @Override
    public List<Route> getRoutes() {
        return routeRepository.findAll();
    }

    @Override
    public Long saveRoute(Route route) {
        routeRepository.save(route);
        if(route!=null){
            return route.getRouteId();
        }
        return null;
    }

    @Override
    public Route getRouteByID(Long rid) {
        return routeRepository.getReferenceById(rid);
    }


    @Override
    public List<Route> SearchRoute(String fromDestination, String toDestination) {
        List<Route> routes = new ArrayList<>();
        List<Route> startRoutes = routeRepository.findByRouteName(fromDestination);
        List<Route> endRoutes = routeRepository.findByRouteName(toDestination);
        for (Route fromroute: startRoutes) {
            for (Route toroute: endRoutes) {
                if(fromroute.getParentRouteID()==toroute.getParentRouteID()){
                    List<Route> routeList = routeRepository.findByParentRouteID(fromroute.getParentRouteID());
                    routes.addAll(routeList);
                }
            }
        }
        return routes;
    }


}
