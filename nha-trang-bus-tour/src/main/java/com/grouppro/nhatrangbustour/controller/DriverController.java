package com.grouppro.nhatrangbustour.controller;

import com.grouppro.nhatrangbustour.Entity.Bus;
import com.grouppro.nhatrangbustour.Entity.Driver;
import com.grouppro.nhatrangbustour.service.interfaces.IDriverService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;

import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
@Tag(name = "Driver-API")
@RequestMapping("api/drivers")
@SecurityRequirement(name = "Authorization")
public class DriverController {
    private final IDriverService driverService;
    private static final String ADMIN="ROLE_Admin";
    private static final String CUSTOMER="ROLE_Customer";
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "When don't have any Driver"),
            @ApiResponse( content = @Content(schema = @Schema(implementation = Driver.class)))
    })
    @Operation(summary = "Get all drivers")
    @GetMapping("/")
    @Secured({ADMIN,CUSTOMER})
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
    @Operation(summary = "Create a new driver ")
    @PostMapping("/")
    @Secured({ADMIN})
    public ResponseEntity<?> addDriver(@RequestBody Driver driver) {
        Long id = driverService.saveDriver(driver);
        if (id == null) {
            return new ResponseEntity<>("Can't create Driver", HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(id,HttpStatus.CREATED);
        }
    }
    @Operation(summary = "Get a driver by its ID")
    @GetMapping("/{driverId}")
    @Secured({ADMIN,CUSTOMER})
    public ResponseEntity<?> getDriverByID(@PathVariable("driverId") Long driverId) {
        Driver driver = driverService.getDriverById(driverId);
        if (driver != null) {
            return ResponseEntity.ok(driver);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Driver not found");
        }
    }
    @Operation(summary = "Update a driver by its ID")
    @PutMapping("/{driverID}")
    @Secured({ADMIN})
    public ResponseEntity<?> updateDriver(@PathVariable("driverID") Long driverId, @RequestBody Driver updateDriver) {
        Driver existingdriver = driverService.getDriverById(driverId);
        if (existingdriver == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Driver not found");
        }

        existingdriver.setDriverName(updateDriver.getDriverName());
        existingdriver.setDriverPhone(updateDriver.getDriverPhone());
        // Update any other properties you need to modify

        Long id = driverService.saveDriver(existingdriver);
        if (id == null) {
            return new ResponseEntity<>("Can't update driver", HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(id,HttpStatus.CREATED);
        }
    }
}
