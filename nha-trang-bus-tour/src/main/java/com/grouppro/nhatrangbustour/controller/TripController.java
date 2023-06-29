package com.grouppro.nhatrangbustour.controller;

import com.grouppro.nhatrangbustour.Entity.Driver;
import com.grouppro.nhatrangbustour.Entity.Trip;
import com.grouppro.nhatrangbustour.service.interfaces.ITripService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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
    public ResponseEntity<?> getTrips() {
        List<Trip> trips = tripService.getTrips();
        if (!trips.isEmpty()) {
            return ResponseEntity.ok(trips);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no trip");
        }
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "When Trip created successfully!"),
            @ApiResponse(responseCode = "400", description = "When Trip can't be created - Object is not valid!")
    })
    @Operation(summary = "Create a new trip ")
    @PostMapping("/{rid}/{did}/{bid}/{pfid}")
    public ResponseEntity<?> addTrio(@RequestBody Trip trip,
                                     @PathVariable Long rid, @PathVariable Long did, @PathVariable Long bid,
                                     @PathVariable Long pfid) {
        Long id = tripService.saveTrip(trip,bid,did,rid,pfid);
        if (id == null) {
            return new ResponseEntity<>("Can't create trip", HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(id,HttpStatus.CREATED);
        }
    }
}
