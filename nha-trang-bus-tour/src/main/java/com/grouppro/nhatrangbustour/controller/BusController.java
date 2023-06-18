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
import org.hibernate.PropertyValueException;
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

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "When Bus created successfully!"),
            @ApiResponse(responseCode = "400", description = "When Bus can't be created - Object is not valid!")
    })
    @Operation(summary = "Create a new bus")
    @PostMapping("/")
    public ResponseEntity<?> addBus(@RequestBody Bus bus) {
            Long id = busService.save(bus);
            
            return new ResponseEntity<>(id, HttpStatus.CREATED);
    }
    
}
