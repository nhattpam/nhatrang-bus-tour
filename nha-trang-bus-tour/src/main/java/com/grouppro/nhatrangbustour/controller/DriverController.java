package com.grouppro.nhatrangbustour.controller;

import com.grouppro.nhatrangbustour.Entity.Driver;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Tag(name = "drivers")
@RequestMapping("api/drivers")
public class DriverController {
    private final IDriverService driverService;
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "When don't have any Driver"),
            @ApiResponse( content = @Content(schema = @Schema(implementation = Driver.class)))
    })
    @Operation(summary = "Get all drivers")
    @GetMapping("/")
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
    public ResponseEntity<?> addDriver(@RequestParam("drivername")String name, @RequestParam("driverphone") String phone) {
        Driver driver = new Driver();
        driver.setDriverName(name);
        driver.setDriverPhone(phone);
        Long id = driverService.saveDriver(driver);
        if (id == null) {
            return new ResponseEntity<>("Can't create Driver", HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(id,HttpStatus.CREATED);
        }
    }
}
