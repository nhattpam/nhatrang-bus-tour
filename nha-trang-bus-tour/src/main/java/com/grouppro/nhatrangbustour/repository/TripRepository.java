package com.grouppro.nhatrangbustour.repository;

import com.grouppro.nhatrangbustour.Entity.*;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findAllByRoute(Route route);
    List<Trip> findAllByBus(Bus bus);
    List<Trip> findAllByDriver(Driver driver);
    List<Trip> findAllByPriceFrame(PriceFrame priceFrame);
}
