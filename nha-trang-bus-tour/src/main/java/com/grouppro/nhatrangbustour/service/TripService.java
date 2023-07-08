package com.grouppro.nhatrangbustour.service;

import com.grouppro.nhatrangbustour.Entity.*;
import com.grouppro.nhatrangbustour.repository.TripRepository;
import com.grouppro.nhatrangbustour.service.interfaces.ITripService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TripService implements ITripService {
    private final TripRepository tripRepository;
    private final DriverService driverService;
    private final BusService busService;
    private final RouteService routeService;
    private final PriceFrameService priceFrameService;

    @Override
    public List<Trip> getTrips() {
        return tripRepository.findAll();
    }

    @Override
    public Long saveTrip(Trip trip, Long bid, Long did, Long rid, Long pfid) {
        Bus bus = busService.getBusById(bid);
        Driver driver = driverService.getDriverById(did);
        Route route = routeService.getRouteByID(rid);
        PriceFrame priceFrame = priceFrameService.getPriceFrameById(pfid);
        trip.setBus(bus);
        trip.setRoute(route);
        trip.setDriver(driver);
        trip.setPriceFrame(priceFrame);
        tripRepository.save(trip);
        if(trip!=null){
            List<Trip> btrips = tripRepository.findAllByBus(bus);
            List<Trip> dtrips = tripRepository.findAllByDriver(driver);
            List<Trip> rtrips = tripRepository.findAllByRoute(route);
            List<Trip> pftrips = tripRepository.findAllByPriceFrame(priceFrame);
            bus.setTrip(btrips);
            driver.setTrip(dtrips);
            route.setTrip(rtrips);
            priceFrame.setTrip(pftrips);
            busService.save(bus);
            driverService.saveDriver(driver);
            routeService.saveRoute(route);
            priceFrameService.savePriceFrame(priceFrame);
            return trip.getTripID();
        }
        return null;
    }

    @Override
    public Long editTrip(Trip trip) {
        if(trip!=null){
            tripRepository.save(trip);
            return trip.getTripID();
        }
        return null;
    }

    @Override
    public Trip getTripByid(Long tid) {
        return tripRepository.getReferenceById(tid);
    }

    @Override
    public List<Trip> searchTrip(String from, String to) {
        List<Trip> trips = new ArrayList<>();
        List<Route> routes = routeService.SearchRoute(from,to);
        if (!routes.isEmpty()){
            for (Route item: routes) {
                System.out.println(item.getRouteId());
                List<Trip> trips1 = tripRepository.findAllByRoute(item);
                trips.addAll(trips1);
            }
        }else {
            throw new RuntimeException("Can't find trip");
        }
        return trips;
    }
}
