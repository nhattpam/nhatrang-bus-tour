package com.grouppro.nhatrangbustour.controller;

import com.grouppro.nhatrangbustour.Entity.Driver;
import com.grouppro.nhatrangbustour.Entity.Payment;
import com.grouppro.nhatrangbustour.service.PaymentService;
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

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Payments-API")
@RequestMapping("api/payments")
public class PaymentController {
    private final PaymentService paymentService;
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "When don't have any Payment"),
            @ApiResponse( content = @Content(schema = @Schema(implementation = Payment.class)))
    })
    @Operation(summary = "Get all payments")
    @GetMapping("/")
    public ResponseEntity<?> getPayments() {
        List<Payment> payments = paymentService.getPayments();
        if (!payments.isEmpty()) {
            return ResponseEntity.ok(payments);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no payment");
        }
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "When Payment created successfully!"),
            @ApiResponse(responseCode = "400", description = "When Payment can't be created - Object is not valid!")
    })
    @Operation(summary = "Create a new Payment")
    @PostMapping("/")
    public ResponseEntity<?> addDriver(@RequestBody Payment payment) {
        LocalDate date = LocalDate.now();
        payment.setPaymentDate(date);
        Long id = paymentService.savePayment(payment);
        if (id == null) {
            return new ResponseEntity<>("Can't create Payment", HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(id,HttpStatus.CREATED);
        }
    }
}
