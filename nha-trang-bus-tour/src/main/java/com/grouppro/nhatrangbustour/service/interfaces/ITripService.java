package com.grouppro.nhatrangbustour.service.interfaces;

import com.grouppro.nhatrangbustour.Entity.Trip;

import java.util.List;

public interface ITripService {
    List<Trip> getTrips();
    Long saveTrip(Trip trip, Long bid, Long did, Long rid, Long pfid);
    Long editTrip(Trip trip);
    Trip getTripByid(Long tid);
}
