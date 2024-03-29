package com.grouppro.nhatrangbustour.controller;

import com.grouppro.nhatrangbustour.Entity.Driver;
import com.grouppro.nhatrangbustour.Entity.Order;
import com.grouppro.nhatrangbustour.Entity.Ticket;
import com.grouppro.nhatrangbustour.Entity.TicketType;
import com.grouppro.nhatrangbustour.service.interfaces.ITicketTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Ticket-Type-API")
@RequestMapping("api/tickettypes")
@SecurityRequirement(name = "Authorization")
public class TicketTypeController {
    private final ITicketTypeService ticketTypeService;
    private static final String ADMIN="ROLE_Admin";
    private static final String CUSTOMER="ROLE_Customer";
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "When don't have any Ticket Type"),
            @ApiResponse( content = @Content(schema = @Schema(implementation = Driver.class)))
    })
    @Operation(summary = "Get all Ticket Types")
    @GetMapping("/")
    @Secured({ADMIN,CUSTOMER})
    public ResponseEntity<?> getTicketTypes() {
        List<TicketType> ticketTypes = ticketTypeService.getTicketTypes();
        if (!ticketTypes.isEmpty()) {
            return ResponseEntity.ok(ticketTypes);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no ticket type");
        }
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "When Ticket Type created successfully!"),
            @ApiResponse(responseCode = "400", description = "When Ticket Type can't be created - Object is not valid!")
    })
    @Operation(summary = "Create a new ticket type ")
    @PostMapping("/")
    @Secured(ADMIN)
    public ResponseEntity<?> addTicketType(@RequestParam("tickettypename")String name, @RequestParam("route") Long rid) {
        TicketType ticketType = new TicketType();
        ticketType.setTicketTypeName(name);
        Long id = ticketTypeService.saveTicketType(ticketType, rid);
        if (id == null) {
            return new ResponseEntity<>("Can't create Ticket Type", HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(id,HttpStatus.CREATED);
        }
    }
}
