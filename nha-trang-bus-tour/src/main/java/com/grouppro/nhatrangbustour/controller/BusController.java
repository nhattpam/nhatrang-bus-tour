package com.grouppro.nhatrangbustour.controller;

import com.grouppro.nhatrangbustour.Entity.Bus;
import com.grouppro.nhatrangbustour.service.interfaces.IBusService;
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
@Tag(name = "Buses-API")
@RequestMapping("api/buses")
public class BusController {
private final IBusService busService;
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "When don't have any Bus"),
            @ApiResponse( content = @Content(schema = @Schema(implementation = Bus.class)))
    })
    @Operation(summary = "Get all buses")
    @GetMapping("/")
    public ResponseEntity<?> getBuses() {
        List<Bus> buses =busService.getBuses();
        if (!buses.isEmpty()) {
            return ResponseEntity.ok(buses);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no bus");
        }
    }
    @Operation(summary = "Get a bus by its ID")
    @GetMapping("/{busId}")
    public ResponseEntity<?> getBusByID(@PathVariable("busId") Long busId) {
        Bus bus = busService.getBusById(busId);
        if (bus != null) {
            return ResponseEntity.ok(bus);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bus not found");
        }
    }

    @Operation(summary = "Delete a bus by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBus(@PathVariable Long id) {
        try {
            busService.deleteBusById(id);
            return ResponseEntity.ok("Bus deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete bus: " + e.getMessage());
        }
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "When Bus created successfully!"),
            @ApiResponse(responseCode = "400", description = "When Bus can't be created - Object is not valid!")
    })
    @Operation(summary = "Create a new bus")
    @PostMapping("/")
    public ResponseEntity<?> addBus(@RequestBody Bus bus) {

        Long id = busService.save(bus);
        if (id == null) {
            return new ResponseEntity<>("Can't create bus", HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(id,HttpStatus.CREATED);
        }
    }
    
    @Operation(summary = "Update a bus by its ID")
    @PutMapping("/{busID}")
    public ResponseEntity<?> updateBus(@PathVariable("busID") Long busId, @RequestBody Bus updatedBus) {
        Bus existingBus = busService.getBusById(busId);
        if (existingBus == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bus not found");
        }

        existingBus.setBusNumber(updatedBus.getBusNumber());
        existingBus.setSeat(updatedBus.getSeat());
        // Update any other properties you need to modify

        Long id = busService.save(existingBus);
        if (id == null) {
            return new ResponseEntity<>("Can't update bus", HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(id,HttpStatus.CREATED);
        }
    }

    
}
