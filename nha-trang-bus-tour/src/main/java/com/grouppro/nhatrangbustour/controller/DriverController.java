package com.grouppro.nhatrangbustour.controller;

import com.grouppro.nhatrangbustour.Entity.Driver;
import com.grouppro.nhatrangbustour.dto.BusDTO;
import com.grouppro.nhatrangbustour.service.interfaces.IDriverService;
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
@Tag(name = "Driver-API")
@RequestMapping("api/driver")
public class DriverController {
    private final IDriverService driverService;
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "When don't have any Driver"),
            @ApiResponse( content = @Content(schema = @Schema(implementation = Driver.class)))
    })
    @Operation(summary = "Get all drivers")
    @GetMapping("/Drivers")
    public ResponseEntity<?> getDrivers() {
        List<Driver> drivers = driverService.getDrivers();
        if (!drivers.isEmpty()) {
            return ResponseEntity.ok(drivers);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no driver");
        }
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "When Driver created successfully!"),
            @ApiResponse(responseCode = "400", description = "When Driver can't be created - Object is not valid!")
    })
    @Operation(summary = "Create a new Driver")
    @PostMapping("/")
    public ResponseEntity<?> addDriver(@RequestParam("drivername")String name, @RequestParam("driverphone") String phone) {
        Long id = driverService.saveDriver(name, phone);
        if (id == null) {
            return new ResponseEntity<>("Can't create Driver", HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(id,HttpStatus.CREATED);
        }
    }
}
