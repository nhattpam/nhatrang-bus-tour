package com.grouppro.nhatrangbustour.controller;

import com.grouppro.nhatrangbustour.Entity.Driver;
import com.grouppro.nhatrangbustour.Entity.PriceFrame;
import com.grouppro.nhatrangbustour.Entity.Route;
import com.grouppro.nhatrangbustour.service.interfaces.IPriceFrameService;
import com.grouppro.nhatrangbustour.service.interfaces.IRouteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Route-API")
@RequestMapping("api/routes")
@SecurityRequirement(name = "Authorization")
public class RouteController {
    private final IRouteService routeService;
    private static final String ADMIN="ROLE_Admin";
    private static final String CUSTOMER="ROLE_Customer";
    private final IPriceFrameService priceFrameService;
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "When don't have any Station"),
            @ApiResponse(content = @Content(schema = @Schema(implementation = Route.class)))
    })
    @Operation(summary = "Get all routes")
    @GetMapping("/")
    @Secured({ADMIN,CUSTOMER})
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
    @Operation(summary = "requestbodt ")
    @PostMapping("/")
    @Secured(ADMIN)
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
    }
    @Operation(summary = "Get a route by its ID")
    @GetMapping("/{routeId}")
    @Secured({ADMIN,CUSTOMER})
    public ResponseEntity<?> getRouteByID(@PathVariable("routeId") Long routeId) {
        Route route = routeService.getRouteByID(routeId);
        if (route != null) {
            return ResponseEntity.ok(route);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Route not found");
        }
    }
    @Operation(summary = "Update a route by its ID")
    @PutMapping("/{routeId}/{routeName}/{parentRouteId}")
    @Secured({ADMIN})
    public ResponseEntity<?> updateRoute(@PathVariable("routeId") Long routeId, @PathVariable("routeName")String name,
                                          @PathVariable("parentRouteId")Long parent) {
        Route existingroute = routeService.getRouteByID(routeId);
        if (existingroute == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Route not found");
        }

        existingroute.setRouteName(name);
        existingroute.setParentRouteID(parent);
        // Update any other properties you need to modify

        Long id = routeService.saveRoute(existingroute);
        if (id == null) {
            return new ResponseEntity<>("Can't update route", HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(id,HttpStatus.CREATED);
        }
    }
}
