package com.grouppro.nhatrangbustour.controller;

import com.grouppro.nhatrangbustour.Entity.PriceFrame;
import com.grouppro.nhatrangbustour.Entity.Route;
import com.grouppro.nhatrangbustour.Entity.Station;
import com.grouppro.nhatrangbustour.service.interfaces.IPriceFrameService;
import com.grouppro.nhatrangbustour.service.interfaces.IRouteService;
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

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Route-API")
@RequestMapping("api/routes")
public class RouteController {
    private final IRouteService routeService;
    private final IPriceFrameService priceFrameService;
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "When don't have any Station"),
            @ApiResponse(content = @Content(schema = @Schema(implementation = Route.class)))
    })
    @Operation(summary = "Get all routes")
    @GetMapping("/")
    public ResponseEntity<?> getRoutes() {
        List<Route> routes = routeService.getRoutes();
        if (!routes.isEmpty()) {
            return (ResponseEntity<?>) ResponseEntity.ok(routes);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no route");
        }
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "When Route created successfully!"),
            @ApiResponse(responseCode = "400", description = "When Route can't be created - Object is not valid!")
    })
    @Operation(summary = "Create a new Route ")
    @PostMapping("/")
    public ResponseEntity<?> addRoute(@RequestParam("routename")String name, @RequestParam("parentid") Long parentid) {
        Route route = new Route();
        route.setRouteName(name);
        route.setParentRouteID(parentid);
        Long id = routeService.saveRoute(route);
        if (id == null) {
            return new ResponseEntity<>("Can't create Station", HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(id,HttpStatus.CREATED);
        }
    }@Operation(summary = "Update a Route ")
    @PostMapping("/update")
    public ResponseEntity<?> updateRoute(@RequestParam("routeid")Long rid, @RequestParam("routename") String name, @RequestParam("parentid") Long parentid) {
        Route route = routeService.getRouteByID(rid);
        route.setRouteName(name);
        route.setParentRouteID(parentid);
        List<PriceFrame> priceFrames = priceFrameService.getPriceFramesbyRoute(route);
        route.setPriceFrame(priceFrames);
        Long id = routeService.saveRoute(route);
        if (id == null) {
            return new ResponseEntity<>("Can't create Station", HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(id,HttpStatus.CREATED);
        }
    }
}
