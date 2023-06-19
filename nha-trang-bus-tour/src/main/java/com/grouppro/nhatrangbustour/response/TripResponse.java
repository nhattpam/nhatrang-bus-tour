package com.grouppro.nhatrangbustour.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.grouppro.nhatrangbustour.Entity.Bus;
import com.grouppro.nhatrangbustour.Entity.Driver;
import com.grouppro.nhatrangbustour.Entity.PriceFrame;
import com.grouppro.nhatrangbustour.Entity.Route;

import java.time.LocalDate;

public class TripResponse {
    private Long tripID;
    private LocalDate departureTime;
    private LocalDate arrivalTime;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Bus bus;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Driver driver;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private PriceFrame priceFrame;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Route route;

    public Long getTripID() {
        return tripID;
    }

    public void setTripID(Long tripID) {
        this.tripID = tripID;
    }

    public LocalDate getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDate departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDate getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDate arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public PriceFrame getPriceFrame() {
        return priceFrame;
    }

    public void setPriceFrame(PriceFrame priceFrame) {
        this.priceFrame = priceFrame;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public TripResponse(Long tripID, LocalDate departureTime, LocalDate arrivalTime,
                        Bus bus, Driver driver, PriceFrame priceFrame, Route route) {
        this.tripID = tripID;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.bus = bus;
        this.driver = driver;
        this.priceFrame = priceFrame;
        this.route = route;
    }
}
