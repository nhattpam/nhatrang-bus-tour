package com.grouppro.nhatrangbustour.controller;

import com.grouppro.nhatrangbustour.Entity.PriceFrame;
import com.grouppro.nhatrangbustour.Entity.Route;
import com.grouppro.nhatrangbustour.dto.PriceFrameDTO;
import com.grouppro.nhatrangbustour.service.interfaces.IPriceFrameService;
import com.grouppro.nhatrangbustour.service.interfaces.IRouteService;
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
@Tag(name = "PriceFrames-API")
@RequestMapping("api/priceframes")
public class PriceFrameController {
    private final IPriceFrameService priceFrameService;
    private final IRouteService routeService;
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "When don't have any Price Frame"),
            @ApiResponse( content = @Content(schema = @Schema(implementation = PriceFrame.class)))
    })
    @Operation(summary = "Get all price frames")
    @GetMapping("/")
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
    @PostMapping("/{rid}")
    public ResponseEntity<?> addPriceFrame(@RequestBody PriceFrame priceFrame, @PathVariable Long routeid) {
        Route route = routeService.getRouteByID(routeid);
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
}
