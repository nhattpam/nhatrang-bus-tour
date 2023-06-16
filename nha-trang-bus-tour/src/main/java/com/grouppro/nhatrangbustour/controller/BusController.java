package com.grouppro.nhatrangbustour.controller;

import com.grouppro.nhatrangbustour.Entity.Bus;
import com.grouppro.nhatrangbustour.service.interfaces.IBusService;
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
@CrossOrigin(origins = "https://nha-trang-bus-tour.web.app")
@RestController
@RequiredArgsConstructor
@Tag(name = "buses")
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
    public ResponseEntity<?> getBusByID(@RequestParam(defaultValue = "0") Integer input,
                                        @PathVariable("busId") Long busId) {
        if (input == 0) {
            return ResponseEntity.ok("default value here:" + input);
        } else {
            return ResponseEntity.ok("value:" + input);
        }
    }
    @Operation(summary = "Delete a bus by its ID")
    @DeleteMapping("/{busID}")
    public ResponseEntity<?> deleteBusById(@RequestParam(defaultValue = "0") Integer input,
                                           @PathVariable("busId") Long busId) {
        if (input == 0) {
            return ResponseEntity.ok("default value here:" + input);
        } else {
            return ResponseEntity.ok("value:" + input);
        }
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "When Bus created successfully!"),
            @ApiResponse(responseCode = "400", description = "When Bus can't be created - Object is not valid!")
    })
    @Operation(summary = "Create a new bus")
    @PostMapping("/")
    public ResponseEntity<?> addBus(@RequestParam("busnumber") String busnumber,@RequestParam("seat") int seat) {
        Bus bus = new Bus();
        bus.setBusNumber(busnumber);
        bus.setSeat(seat);
        Long id = busService.save(bus);
        if (id == null) {
            return new ResponseEntity<>("Can't create bus", HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(id,HttpStatus.CREATED);
        }
    }
    
}
