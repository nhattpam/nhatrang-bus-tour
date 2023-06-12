package com.grouppro.nhatrangbustour.controller;

import com.grouppro.nhatrangbustour.Entity.Driver;
import com.grouppro.nhatrangbustour.Entity.Order;
import com.grouppro.nhatrangbustour.service.interfaces.IOrderService;
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
@Tag(name = "Order-API")
@RequestMapping("api/order")
public class OrderController {
    private final IOrderService orderService;
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "When don't have any Order"),
            @ApiResponse( content = @Content(schema = @Schema(implementation = Driver.class)))
    })
    @Operation(summary = "Get all orders, or a specific order by id")
    @GetMapping("/Orders")
    public ResponseEntity<?> getOrders(@RequestParam(required = false, name = "orderid") Long orderid) {
        List<Order> orders;
        if (orderid==null){
            orders=orderService.getOrders();
        }
        else {
            orders=orderService.getOrderById(orderid);
        }
        if (!orders.isEmpty()) {
            return ResponseEntity.ok(orders);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no driver");
        }
    }
    @PostMapping("/addOrder")
    public ResponseEntity<?> testMethod2(@RequestParam(defaultValue = "0") Integer input) {
        if (input == 0) {
            return ResponseEntity.ok("default value here:" + input);
        } else {
            return ResponseEntity.ok("value:" + input);
        }
    }
}
