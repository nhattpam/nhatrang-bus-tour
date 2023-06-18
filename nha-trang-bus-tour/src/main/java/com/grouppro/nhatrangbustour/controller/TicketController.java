package com.grouppro.nhatrangbustour.controller;

import com.grouppro.nhatrangbustour.Entity.Order;
import com.grouppro.nhatrangbustour.Entity.Ticket;
import com.grouppro.nhatrangbustour.service.TicketService;
import com.grouppro.nhatrangbustour.service.TicketTypeService;
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
@Tag(name = "tickets")
@RequestMapping("api/tickets")
public class TicketController {
    private final TicketService ticketService;
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "When don't have any Ticket"),
            @ApiResponse( content = @Content(schema = @Schema(implementation = Ticket.class)))
    })
    @Operation(summary = "Get all tickets")
    @GetMapping("/")
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
    @PostMapping("/{tid}/{oid}/{sid}/{ttid}")
    public ResponseEntity<?> addTicket(@RequestBody Ticket ticket,
                                       @PathVariable Long tid, @PathVariable Long oid,
                                       @PathVariable Long sid, @PathVariable Long ttid) {
        Long id = ticketService.saveTicket(ticket,tid,oid,sid,ttid);
        if (id == null) {
            return new ResponseEntity<>("Can't create Ticket", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(id,HttpStatus.CREATED);
        }
    }
}
