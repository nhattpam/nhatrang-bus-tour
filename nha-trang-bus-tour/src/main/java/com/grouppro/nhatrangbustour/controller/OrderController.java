package com.grouppro.nhatrangbustour.controller;

import com.grouppro.nhatrangbustour.Entity.Order;
import com.grouppro.nhatrangbustour.Entity.Ticket;
import com.grouppro.nhatrangbustour.Entity.User;
import com.grouppro.nhatrangbustour.response.OrderResponse;
import com.grouppro.nhatrangbustour.service.interfaces.IOrderService;
import com.grouppro.nhatrangbustour.service.interfaces.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.aspectj.weaver.ast.Or;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@Tag(name = "Order-API")
@RequestMapping("api/orders")
@SecurityRequirement(name = "Authorization")
public class OrderController {
    private static final String ADMIN="ROLE_Admin";
    private static final String CUSTOMER="ROLE_Customer";
    private final IOrderService orderService;
    private final IUserService userService;
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "When don't have any Order"),
            @ApiResponse( content = @Content(schema = @Schema(implementation = Order.class)))
    })
    @Operation(summary = "Get all orders")
    @GetMapping("/")
    @Secured({ADMIN,CUSTOMER})
    public ResponseEntity<List<OrderResponse>> getOrders() {
        List<Order> orders = orderService.getOrders();
        List<OrderResponse> orderResponses = orders.stream()
                .map(order -> new OrderResponse(order.getOrderId(), order.getOrderDate(), order.getPayment(), order.getTotalPrice(), order.getUserId()))
                .collect(Collectors.toList());

        if (!orderResponses.isEmpty()) {
            return ResponseEntity.ok(orderResponses);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "When Order created successfully!"),
            @ApiResponse(responseCode = "400", description = "When Order can't be created - Object is not valid!")
    })
    @Operation(summary = "Create an order")
    @PostMapping("/{uid}/{pid}/{totalPrice}")
    @Secured({ADMIN,CUSTOMER})
    public ResponseEntity<?> addOrder(@PathVariable Long uid, @PathVariable Long pid, @PathVariable Double totalPrice) {

        Order order = new Order();
        LocalDate date = LocalDate.now();
        order.setOrderDate(date);
        order.setTotalPrice(totalPrice);
        Long id = orderService.saveOrder(order, pid, uid);
        if (id == null) {
            return new ResponseEntity<>("Can't create Order", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(id,HttpStatus.CREATED);
        }
    }
    @Operation(summary = "Get orders by userEmail")
    @GetMapping("/{userEmail}")
    @Secured({ADMIN,CUSTOMER})
    public ResponseEntity<?> getOrdersByUserEmail(@PathVariable("userEmail") String userEmail) {
        User user = userService.getUserByEmail(userEmail);
        if(user==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        List<Order> orders = orderService.getOrdersByUser(user);
        if (orders != null) {
            return ResponseEntity.ok(orders);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order is empty");
        }
    }
}
