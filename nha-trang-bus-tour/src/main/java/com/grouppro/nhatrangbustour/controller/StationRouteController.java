package com.grouppro.nhatrangbustour.controller;

import com.grouppro.nhatrangbustour.Entity.Route;
import com.grouppro.nhatrangbustour.Entity.Station;
import com.grouppro.nhatrangbustour.Entity.StationRoute;
import com.grouppro.nhatrangbustour.service.interfaces.IRouteService;
import com.grouppro.nhatrangbustour.service.interfaces.IStationRouteService;
import com.grouppro.nhatrangbustour.service.interfaces.IStationService;
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

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "stationroute")
@RequestMapping("api/stationroute")
public class StationRouteController {
    private final IStationRouteService stationRouteService;
    private final IRouteService routeService;
    private final IStationService stationService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "When don't have any StationRoute"),
            @ApiResponse( content = @Content(schema = @Schema(implementation = StationRoute.class)))
    })
    @Operation(summary = "Get all stationroutes")
    @GetMapping("/")
    public ResponseEntity<?> getStationRoutes() {
        List<StationRoute> stationRoutes = stationRouteService.getStationRoute();
        if (!stationRoutes.isEmpty()) {
            return ResponseEntity.ok(stationRoutes);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no stationroute");
        }
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "When StationRoute created successfully!"),
            @ApiResponse(responseCode = "400", description = "When StaionRoute can't be created - Object is not valid!")
    })
    @Operation(summary = "Create a new stationroute ")
    @PostMapping("/{sid}/{rid}")
    public ResponseEntity<?> addStationRoute(@PathVariable Long sid, @PathVariable Long rid) {
        StationRoute stationRoute = new StationRoute();
        Route route = routeService.getRouteByID(rid);
        stationRoute.setRoute(route);
        Station station = stationService.getStationById(sid);
        stationRoute.setStation(station);
        List<StationRoute> stationRoutes = new ArrayList<>();
        List<StationRoute> stationRoutes1 = new ArrayList<>();
        Long id = stationRouteService.saveStationRoute(stationRoute);
        if (id == null) {
            return new ResponseEntity<>("Can't create Station", HttpStatus.BAD_REQUEST);
        } else {
            stationRoutes = stationRouteService.getStationRouteByStation(station);
            stationRoutes1 = stationRouteService.getStationRouteByRoute(route);
            route.setStationRoute(stationRoutes1);
            station.setStationRoute(stationRoutes);
            Long routeid = routeService.saveRoute(route);
            Long stationid = stationService.saveStation(station);
            return new ResponseEntity<>(id+", "+routeid+", "+stationid,HttpStatus.CREATED);
        }
    }
}
