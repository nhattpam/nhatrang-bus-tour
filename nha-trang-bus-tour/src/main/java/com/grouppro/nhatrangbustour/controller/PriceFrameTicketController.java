package com.grouppro.nhatrangbustour.controller;

import com.grouppro.nhatrangbustour.Entity.PriceFrameTicket;
import com.grouppro.nhatrangbustour.service.PriceFrameTicketService;
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
@Tag(name = "Price-Frame-Ticket-API")
@RequestMapping("api/priceframetickets")
public class PriceFrameTicketController {
    private final PriceFrameTicketService priceFrameTicketService;
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "When don't have any Price Frame Ticket"),
            @ApiResponse( content = @Content(schema = @Schema(implementation = PriceFrameTicket.class)))
    })
    @Operation(summary = "Get all price frame tickets")
    @GetMapping("/")
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
}
