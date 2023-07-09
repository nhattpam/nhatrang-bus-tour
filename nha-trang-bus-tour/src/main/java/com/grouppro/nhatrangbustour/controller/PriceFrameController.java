package com.grouppro.nhatrangbustour.controller;

import com.grouppro.nhatrangbustour.Entity.Driver;
import com.grouppro.nhatrangbustour.Entity.PriceFrame;
import com.grouppro.nhatrangbustour.Entity.Route;
import com.grouppro.nhatrangbustour.service.interfaces.IPriceFrameService;
import com.grouppro.nhatrangbustour.service.interfaces.IRouteService;
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

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Price-Frame-API")
@RequestMapping("api/priceframes")
@SecurityRequirement(name = "Authorization")
public class PriceFrameController {
    private static final String ADMIN="ROLE_Admin";
    private static final String CUSTOMER="ROLE_Customer";
    private final IPriceFrameService priceFrameService;
    private final IRouteService routeService;
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "When don't have any Price Frame"),
            @ApiResponse( content = @Content(schema = @Schema(implementation = PriceFrame.class)))
    })
    @Operation(summary = "Get all price frames")
    @GetMapping("/")
    @Secured({ADMIN,CUSTOMER})
    public ResponseEntity<?> getPriceFrames() {
        List<PriceFrame> priceFrames = priceFrameService.getPriceFrames();
        if (!priceFrames.isEmpty()) {
            return ResponseEntity.ok(priceFrames);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no price frame");
        }
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "When Price Frame created successfully!"),
            @ApiResponse(responseCode = "400", description = "When Price Frame can't be created - Object is not valid!")
    })
    @Operation(summary = "Create a new Price Frame ")
    @PostMapping("/")
    @Secured({ADMIN})
    public ResponseEntity<?> addPriceFrame(@RequestParam("priceframe")String name, @RequestParam("routeid") Long routeid) {
        Route route = routeService.getRouteByID(routeid);
        PriceFrame priceFrame = new PriceFrame();
        priceFrame.setPriceFrameName(name);
        priceFrame.setRoute(route);
        Long id = priceFrameService.savePriceFrame(priceFrame);
        if (id == null) {
            return new ResponseEntity<>("Can't create Price Frame", HttpStatus.BAD_REQUEST);
        } else {
            List<PriceFrame> priceFrames = priceFrameService.getPriceFramesbyRoute(route);
            route.setPriceFrame(priceFrames);
            Long rid = routeService.saveRoute(route);
            return new ResponseEntity<>(id+", "+rid ,HttpStatus.CREATED);
        }
    }
    @Operation(summary = "Get a price frame by its ID")
    @GetMapping("/{priceFrameId}")
    @Secured({ADMIN,CUSTOMER})
    public ResponseEntity<?> getPriceFrameByID(@PathVariable("priceFrameId") Long priceFrameId) {
        PriceFrame priceFrame = priceFrameService.getPriceFrameById(priceFrameId);
        if (priceFrame != null) {
            return ResponseEntity.ok(priceFrame);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Price Frame not found");
        }
    }
    @Operation(summary = "Update a price frame by its ID")
    @PutMapping("/{priceFrameID}/{priceFrameName}")
    @Secured({ADMIN})
    public ResponseEntity<?> updatePriceFrame(@PathVariable("priceFrameID") Long priceFrameID, @PathVariable("priceFrameName")String name) {
        PriceFrame existingpriceframe = priceFrameService.getPriceFrameById(priceFrameID);
        if (existingpriceframe == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Price Frame not found");
        }

        existingpriceframe.setPriceFrameName(name);
        // Update any other properties you need to modify

        Long id = priceFrameService.savePriceFrame(existingpriceframe);
        if (id == null) {
            return new ResponseEntity<>("Can't update price frame", HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(id,HttpStatus.CREATED);
        }
    }
}
