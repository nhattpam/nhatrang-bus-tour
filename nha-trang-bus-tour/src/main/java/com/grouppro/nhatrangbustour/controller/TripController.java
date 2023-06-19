package com.grouppro.nhatrangbustour.controller;

import com.grouppro.nhatrangbustour.Entity.Driver;
import com.grouppro.nhatrangbustour.Entity.Trip;
import com.grouppro.nhatrangbustour.response.TripResponse;
import com.grouppro.nhatrangbustour.service.interfaces.ITripService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Tag(name = "trips")
@RequestMapping("api/trips")
public class TripController {
    private final ITripService tripService;
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "When don't have any trip"),
            @ApiResponse( content = @Content(schema = @Schema(implementation = Trip.class)))
    })
    @Operation(summary = "Get all trips")
    @GetMapping("/")
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
    public ResponseEntity<?> addTrio(@RequestParam("departureTime")LocalDate depart, @RequestParam("arrivalTime") LocalDate arrival,
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
}
