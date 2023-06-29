package com.grouppro.nhatrangbustour.controller;

import com.grouppro.nhatrangbustour.Entity.Driver;
import com.grouppro.nhatrangbustour.Entity.Station;
import com.grouppro.nhatrangbustour.service.interfaces.IStationService;
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

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "stations")
@RequestMapping("api/stations")
public class StationController {
    private final IStationService stationService;
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "When don't have any Station"),
            @ApiResponse( content = @Content(schema = @Schema(implementation = Station.class)))
    })
    @Operation(summary = "Get all stations")
    @GetMapping("/")
    public ResponseEntity<?> getStations() {
        List<Station> stations = stationService.getStations();
        if (!stations.isEmpty()) {
            return ResponseEntity.ok(stations);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no station");
        }
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "When Station created successfully!"),
            @ApiResponse(responseCode = "400", description = "When Staion can't be created - Object is not valid!")
    })
    @Operation(summary = "Create a new station ")
    @PostMapping("/")
    public ResponseEntity<?> addStation(@RequestBody Station station) {
        Long id = stationService.saveStation(station);
        if (id == null) {
            return new ResponseEntity<>("Can't create Station", HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(id,HttpStatus.CREATED);
        }
    }
}
