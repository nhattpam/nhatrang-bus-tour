package com.grouppro.nhatrangbustour.controller;

import com.grouppro.nhatrangbustour.Entity.Bus;
import com.grouppro.nhatrangbustour.dto.BusDTO;
import com.grouppro.nhatrangbustour.service.interfaces.IBusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "buses")
@RequestMapping("api/buses")
@Slf4j
@SecurityRequirement(name = "Authorization")
public class BusController {
    private final IBusService busService;
    private static final String ADMIN="ROLE_Admin";
    private static final String CUSTOMER="ROLE_Customer";
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "When don't have any Bus"),
            @ApiResponse( content = @Content(schema = @Schema(implementation = Bus.class)))
    })
    @Operation(summary = "Get all buses, or a specific bus by id")
    @GetMapping("/")
    @Secured({ADMIN,CUSTOMER})
    public ResponseEntity<?> getBuses(@RequestParam(required = false,name = "busid") Long busid) {
        List<Bus> buses;
        if (busid==null){
            buses =busService.getBuses();
        }
        else {
            buses =busService.getBusById(busid);
        }
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
    @Secured(ADMIN)
    public ResponseEntity<?> testMethod2(@RequestParam("busnumber") String busnumber,@RequestParam("seat") int seat) {
        Long id = busService.save(busnumber,seat);
        if (id == null) {
            return new ResponseEntity<>("Can't create bus", HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(id,HttpStatus.CREATED);
        }
    }
    
}
