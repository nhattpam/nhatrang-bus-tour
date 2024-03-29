package com.grouppro.nhatrangbustour.controller;

import com.grouppro.nhatrangbustour.Entity.PriceFrame;
import com.grouppro.nhatrangbustour.Entity.PriceFrameTicket;
import com.grouppro.nhatrangbustour.service.PriceFrameTicketService;
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
@Tag(name = "Price-Frame-Ticket-API")
@RequestMapping("api/priceframetickets")
@SecurityRequirement(name = "Authorization")
public class PriceFrameTicketController {
    private static final String ADMIN="ROLE_Admin";
    private static final String CUSTOMER="ROLE_Customer";
    private final PriceFrameTicketService priceFrameTicketService;
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "When don't have any Price Frame Ticket"),
            @ApiResponse( content = @Content(schema = @Schema(implementation = PriceFrameTicket.class)))
    })
    @Operation(summary = "Get all price frame tickets")
    @GetMapping("/")
    @Secured({ADMIN,CUSTOMER})
    public ResponseEntity<?> getPriceFrameTickets() {
        List<PriceFrameTicket> priceFrameTickets = priceFrameTicketService.getPriceFrameTickets();
        if (!priceFrameTickets.isEmpty()) {
            return ResponseEntity.ok(priceFrameTickets);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no price frame ticket");
        }
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "When Price Frame Ticket created successfully!"),
            @ApiResponse(responseCode = "400", description = "When Price Frame Ticket can't be created - Object is not valid!")
    })
    @Operation(summary = "Create a new price frame ticket ")
    @PostMapping("/")
    @Secured({ADMIN})
    public ResponseEntity<?> addPriceFrameTicket(@RequestParam("price")Double price, @RequestParam("priceframe") Long pfid,
                                                 @RequestParam("tickettype") Long ttid) {
        PriceFrameTicket priceFrameTicket = new PriceFrameTicket();
        priceFrameTicket.setPrice(price);
        Long id = priceFrameTicketService.savePriceFrameTicket(priceFrameTicket, pfid,ttid);
        if (id == null) {
            return new ResponseEntity<>("Can't create Price Frame Ticket", HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(id,HttpStatus.CREATED);
        }

    }
    @Operation(summary = "Get a price frame ticket by its ID")
    @GetMapping("/{priceFrameTicketId}")
    @Secured({ADMIN,CUSTOMER})
    public ResponseEntity<?> getPriceFrameTicketByID(@PathVariable("priceFrameTicketId") Long priceFrameTicketId) {
        PriceFrameTicket priceFrameTicket = priceFrameTicketService.getPriceFrameTicketById(priceFrameTicketId);
        if (priceFrameTicket != null) {
            return ResponseEntity.ok(priceFrameTicket);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Price Frame Ticket not found");
        }
    }
    @Operation(summary = "Update a price frame ticket by its ID")
    @PutMapping("/{priceFrameTicketID}/{price}")
    @Secured({ADMIN})
    public ResponseEntity<?> updatePriceFrameTicket(@PathVariable("priceFrameTicketID") Long priceFrameTicketID, @PathVariable("price")Double price) {
        PriceFrameTicket existingpriceframeticket = priceFrameTicketService.getPriceFrameTicketById(priceFrameTicketID);
        if (existingpriceframeticket == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Price Frame Ticket not found");
        }

        existingpriceframeticket.setPrice(price);
        // Update any other properties you need to modify

        Long id = priceFrameTicketService.savePriceFrameTicket(existingpriceframeticket,existingpriceframeticket.getPriceFrame().getPriceFrameId(),
                existingpriceframeticket.getTicketType().getTicketTypeId());
        if (id == null) {
            return new ResponseEntity<>("Can't update price frame ticket", HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(id,HttpStatus.CREATED);
        }
    }
}
