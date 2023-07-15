package com.grouppro.nhatrangbustour.controller;

import com.grouppro.nhatrangbustour.Entity.Bus;
import com.grouppro.nhatrangbustour.Entity.Driver;
import com.grouppro.nhatrangbustour.Entity.PriceFrame;
import com.grouppro.nhatrangbustour.Entity.Trip;
import com.grouppro.nhatrangbustour.response.TripResponse;
import com.grouppro.nhatrangbustour.service.interfaces.IBusService;
import com.grouppro.nhatrangbustour.service.interfaces.IDriverService;
import com.grouppro.nhatrangbustour.service.interfaces.IPriceFrameService;
import com.grouppro.nhatrangbustour.service.interfaces.ITripService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Tag(name = "Trip-API")
@RequestMapping("api/trips")
@SecurityRequirement(name = "Authorization")
public class TripController {
    private final ITripService tripService;
    private final IBusService busService;
    private final IDriverService driverService;
    private final IPriceFrameService priceFrameService;
    private static final String ADMIN="ROLE_Admin";
    private static final String CUSTOMER="ROLE_Customer";
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "When don't have any trip"),
            @ApiResponse( content = @Content(schema = @Schema(implementation = Trip.class)))
    })
    @Operation(summary = "Get all trips")
    @GetMapping("/")
    @Secured({ADMIN,CUSTOMER})
    public ResponseEntity<List<TripResponse>> getTrips() {
        List<Trip> trips = tripService.getTrips();
        List<TripResponse> tripResponses = trips.stream()
                .map(trip -> new TripResponse(
                        trip.getTripID(),
                        trip.getDepartureTime(),
                        trip.getArrivalTime(),
                        trip.getBus(),
                        trip.getDriver(),
                        trip.getPriceFrame(),
                        trip.getRoute()
                ))
                .collect(Collectors.toList());

        if (!tripResponses.isEmpty()) {
            return ResponseEntity.ok(tripResponses);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "When Trip created successfully!"),
            @ApiResponse(responseCode = "400", description = "When Trip can't be created - Object is not valid!")
    })
    @Operation(summary = "Create a new trip ")
    @PostMapping("/")
    @Secured(ADMIN)
    public ResponseEntity<?> addTrip(@RequestParam("departureTime")LocalDate depart, @RequestParam("arrivalTime") LocalDate arrival,
                                     @RequestParam("route")Long rid, @RequestParam("driver")Long did, @RequestParam("bus") Long bid,
                                     @RequestParam("priceframe")Long pfid) {
        Trip trip = new Trip();
        trip.setDepartureTime(depart);
        trip.setArrivalTime(arrival);
        Long id = tripService.saveTrip(trip,bid,did,rid,pfid);
        if (id == null) {
            return new ResponseEntity<>("Can't create trip", HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(id,HttpStatus.CREATED);
        }
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "When don't have any trip"),
            @ApiResponse( content = @Content(schema = @Schema(implementation = Trip.class)))
    })
    @Operation(summary = "Search trip ")
    @GetMapping("/{From}/{To}/{Day}")
    @Secured({ADMIN,CUSTOMER})
    public ResponseEntity<?> searchTrip(@RequestParam("From")String from, @RequestParam("To")String to, @RequestParam("Day") LocalDate date) {
        List<Trip> trips = tripService.searchTrip(from, to, date);
        List<TripResponse> tripResponses = trips.stream()
                .map(trip -> new TripResponse(
                        trip.getTripID(),
                        trip.getDepartureTime(),
                        trip.getArrivalTime(),
                        trip.getBus(),
                        trip.getDriver(),
                        trip.getPriceFrame(),
                        trip.getRoute()
                ))
                .collect(Collectors.toList());

        if (!tripResponses.isEmpty()) {
            return ResponseEntity.ok(tripResponses);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
    }
    @Operation(summary = "Get a trip by its ID")
    @GetMapping("/{tripId}")
    @Secured({ADMIN,CUSTOMER})
    public ResponseEntity<?> getTripByID(@PathVariable("tripId") Long tripId) {
        Trip trip = tripService.getTripByid(tripId);
        if (trip != null) {
            return ResponseEntity.ok(trip);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trip not found");
        }
    }
    @Operation(summary = "Update a trip by its ID")
    @PutMapping("/{tripID}/{departureTime}/{arrivalTime}/{busId}/{driverId}/{priceFrameId}/{routeId}")
    @Secured({ADMIN})
    public ResponseEntity<?> updateTrip(@PathVariable("tripID") Long tripID, @PathVariable("departureTime") LocalDate departureTime,
                                        @PathVariable("arrivalTime")LocalDate arrivalTime, @PathVariable("busId") Long busId,
                                        @PathVariable("driverId") Long driverId, @PathVariable("priceFrameId")Long priceFrameId,
                                        @PathVariable("routeId")Long routeId) {
        Trip existingtrip = tripService.getTripByid(tripID);
        if (existingtrip == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trip not found");
        }
        existingtrip.setArrivalTime(arrivalTime);
        existingtrip.setDepartureTime(departureTime);
        // Update any other properties you need to modify

        Long id = tripService.saveTrip(existingtrip, busId,driverId,routeId,priceFrameId);
        if (id == null) {
            return new ResponseEntity<>("Can't update driver", HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(id,HttpStatus.CREATED);
        }
    }
}
