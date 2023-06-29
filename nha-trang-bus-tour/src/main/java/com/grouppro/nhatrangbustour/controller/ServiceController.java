package com.grouppro.nhatrangbustour.controller;

import com.grouppro.nhatrangbustour.Entity.Service;
import com.grouppro.nhatrangbustour.service.interfaces.IServiceService;
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
@Tag(name = "Service-API")
@RequestMapping("api/services")
public class ServiceController {
    private final IServiceService serviceService;
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "When don't have any Driver"),
            @ApiResponse( content = @Content(schema = @Schema(implementation = Service.class)))
    })
    @Operation(summary = "Get all services")
    @GetMapping("/")
    public ResponseEntity<?> getServices() {
        List<Service> services = serviceService.getServices();
        if (!services.isEmpty()) {
            return ResponseEntity.ok(services);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no driver");
        }
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "When Service created successfully!"),
            @ApiResponse(responseCode = "400", description = "When Service can't be created - Object is not valid!")
    })
    @Operation(summary = "Create a new service ")
    @PostMapping("/")
    public ResponseEntity<?> addService(@RequestParam("servicenumber")String number, @RequestParam("servicename") String name) {
        Service service = new Service();
        service.setServiceNumber(number);
        service.setServiceName(name);
        Long id = serviceService.saveService(service);
        if (id == null) {
            return new ResponseEntity<>("Can't create Service", HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(id,HttpStatus.CREATED);
        }
    }
}
