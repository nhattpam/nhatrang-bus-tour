package com.grouppro.nhatrangbustour.controller;

import com.grouppro.nhatrangbustour.Entity.*;
import com.grouppro.nhatrangbustour.service.OrderService;
import com.grouppro.nhatrangbustour.service.TicketService;
import com.grouppro.nhatrangbustour.service.UserService;
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
    private final OrderService orderService;
    private static final String ADMIN="ROLE_Admin";
    private static final String CUSTOMER="ROLE_Customer";
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "When don't have any Ticket"),
            @ApiResponse( content = @Content(schema = @Schema(implementation = Ticket.class)))
    })
    @Operation(summary = "Get all tickets")
    @GetMapping("/")
    @Secured({ADMIN})
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
    @Secured({ADMIN, CUSTOMER})
    public ResponseEntity<?> addTicket(@RequestParam("passengername") String name, @RequestParam("passengerphone")String phone,
                                       @RequestParam("passengeremail")String email,
                                       @RequestParam("trip") Long tid, @RequestParam("order")Long oid,
                                       @RequestParam("service")Long sid, @RequestParam("tickettype")Long ttid) {
        Ticket ticket = new Ticket();
        ticket.setPassengerName(name);
        ticket.setPassengerPhone(phone);
        ticket.setPassengerEmail(email);
        ticket.setFeedback(" ");
        Long id = ticketService.saveTicket(ticket,tid,oid,sid,ttid);
        if (id == null) {
            return new ResponseEntity<>("Can't create Ticket", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(id,HttpStatus.CREATED);
        }
    }@ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "When Ticket updated successfully!"),
            @ApiResponse(responseCode = "400", description = "When Ticket can't be updated - Object is not valid!")
    })
    @Operation(summary = "Update a ticket by ticketId")
    @PostMapping("/{ticketId}/{passengername}/{passengerphone}/{passengeremail}/{feedback}")
    @Secured({ADMIN, CUSTOMER})
    public ResponseEntity<?> updateTicket(@PathVariable("ticketId") Long tid,@PathVariable("passengername") String name,
                                          @PathVariable("passengerphone")String phone, @PathVariable("passengeremail")String email,
                                          @PathVariable(value = "feedback") String feedback) {
        Ticket ticket = ticketService.getTicketByTicketId(tid);
        if (ticket == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no ticket");
        }
        ticket.setPassengerName(name);
        ticket.setPassengerPhone(phone);
        ticket.setPassengerEmail(email);
        ticket.setFeedback(feedback);
        Long id = ticketService.updateTicket(ticket);
        if (id == null) {
            return new ResponseEntity<>("Can't create Ticket", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(id,HttpStatus.CREATED);
        }
    }
//    @Operation(summary = "Get tickets by userId")
//    @GetMapping("/{userId}")
//    @Secured({ADMIN,CUSTOMER})
//    public ResponseEntity<?> getTicketsByUserID(@PathVariable("userId") Long userId) {
//        User user = userService.getUserById(userId);
//        if(user==null){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
//        }
//        List<Ticket> tickets = ticketService.getTicketsByOrder(user);
//        if (tickets != null) {
//            return ResponseEntity.ok(tickets);
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tickets is empty");
//        }
//    }
    @Operation(summary = "Get tickets by order id")
    @GetMapping("/{orderId}")
    @Secured({ADMIN,CUSTOMER})
    public ResponseEntity<?> getTicketsByOrderId(@PathVariable("orderId") Long orderId) {
        Order order = orderService.getOrderById(orderId);
        if(order==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
        }
        List<Ticket> tickets = ticketService.getTicketsByOrderId(order);
        if (tickets != null) {
            return ResponseEntity.ok(tickets);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tickets is empty");
        }
    }
    @Operation(summary = "Get ticket type by ticket id")
    @GetMapping("/ticket-type/{ticketId}")
    @Secured({ADMIN,CUSTOMER})
    public ResponseEntity<?> getTicketTypeByticketId(@PathVariable("ticketId") Long ticketId) {
        String ticketType = ticketService.getTicketTypeByTicketId(ticketId);
        if (ticketType != null) {
            return ResponseEntity.ok(ticketType);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ticket type is not found");
        }
    }
}
