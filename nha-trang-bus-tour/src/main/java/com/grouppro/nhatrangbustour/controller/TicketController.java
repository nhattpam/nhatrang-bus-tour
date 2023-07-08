package com.grouppro.nhatrangbustour.controller;

import com.grouppro.nhatrangbustour.Entity.Ticket;
import com.grouppro.nhatrangbustour.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Ticket-API")
@RequestMapping("api/tickets")
@SecurityRequirement(name = "Authorization")
public class TicketController {
    private final TicketService ticketService;
    private static final String ADMIN="ROLE_Admin";
    private static final String CUSTOMER="ROLE_Customer";
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "When don't have any Ticket"),
            @ApiResponse( content = @Content(schema = @Schema(implementation = Ticket.class)))
    })
    @Operation(summary = "Get all tickets")
    @GetMapping("/")
    @Secured({ADMIN,CUSTOMER})
    public ResponseEntity<?> getTickets() {
        List<Ticket> tickets = ticketService.getTickets();
        if (!tickets.isEmpty()) {
            return ResponseEntity.ok(tickets);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no ticket");
        }
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "When Ticket created successfully!"),
            @ApiResponse(responseCode = "400", description = "When Ticket can't be created - Object is not valid!")
    })
    @Operation(summary = "Create a new ticket")
    @PostMapping("/")
    @Secured(ADMIN)
    public ResponseEntity<?> addTicket(@RequestParam("passengername") String name, @RequestParam("passengerphone")String phone,
                                       @RequestParam("passengeremail")String email, @RequestParam("feedback") String feedback,
                                       @RequestParam("trip") Long tid, @RequestParam("order")Long oid,
                                       @RequestParam("service")Long sid, @RequestParam("tickettype")Long ttid) {
        Ticket ticket = new Ticket();
        ticket.setPassengerName(name);
        ticket.setPassengerPhone(phone);
        ticket.setPassengerEmail(email);
        ticket.setFeedback(feedback);
        Long id = ticketService.saveTicket(ticket,tid,oid,sid,ttid);
        if (id == null) {
            return new ResponseEntity<>("Can't create Ticket", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(id,HttpStatus.CREATED);
        }
    }
}
