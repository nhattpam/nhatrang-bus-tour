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
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @Operation(summary = "Get all drivers, or a specific driver by id")
    @GetMapping("/")
    public ResponseEntity<?> getDrivers(@RequestParam(required=false, name = "driverid") Long driverid ) {
        List<Driver> drivers;
        if (driverid==null){
            drivers = driverService.getDrivers();
        }else {
            drivers=driverService.getDriverById(driverid);
        }
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
