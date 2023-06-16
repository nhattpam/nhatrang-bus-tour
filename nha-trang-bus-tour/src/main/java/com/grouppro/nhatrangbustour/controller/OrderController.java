package com.grouppro.nhatrangbustour.controller;

import com.grouppro.nhatrangbustour.Entity.Order;
import com.grouppro.nhatrangbustour.Entity.Payment;
import com.grouppro.nhatrangbustour.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequiredArgsConstructor
@Tag(name = "orders")
@RequestMapping("api/orders")
public class OrderController {
    private final OrderService orderService;
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "When don't have any Order"),
            @ApiResponse( content = @Content(schema = @Schema(implementation = Order.class)))
    })
    @Operation(summary = "Get all orders")
    @GetMapping("/")
    public ResponseEntity<?> getOrders() {
        List<Order> orders = orderService.getOrders();
        if (!orders.isEmpty()) {
            return ResponseEntity.ok(orders);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no order");
        }
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "When Order created successfully!"),
            @ApiResponse(responseCode = "400", description = "When Order can't be created - Object is not valid!")
    })
    @Operation(summary = "Create an order")
    @PostMapping("/")
    public ResponseEntity<?> addOrder(@RequestParam("user")Long uid, @RequestParam("payment")Long pid) {
        Order order = new Order();
        LocalDate date = LocalDate.now();
        order.setOrderDate(date);
        Long id =orderService.saveOrder(order, pid,uid);
        if (id == null) {
            return new ResponseEntity<>("Can't create Order", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(id,HttpStatus.CREATED);
        }
    }
}
