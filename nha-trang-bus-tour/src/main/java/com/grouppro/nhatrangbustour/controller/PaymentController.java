package com.grouppro.nhatrangbustour.controller;

import com.grouppro.nhatrangbustour.Entity.Payment;
import com.grouppro.nhatrangbustour.service.PaymentService;
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

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Payment-API")
@RequestMapping("api/payments")
@SecurityRequirement(name = "Authorization")
public class PaymentController {
    private final PaymentService paymentService;
    private static final String ADMIN="ROLE_Admin";
    private static final String CUSTOMER="ROLE_Customer";
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "When don't have any Payment"),
            @ApiResponse( content = @Content(schema = @Schema(implementation = Payment.class)))
    })
    @Operation(summary = "Get all payments")
    @GetMapping("/")
    @Secured({ADMIN,CUSTOMER})
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
    @Secured(ADMIN)
    public ResponseEntity<?> addPayment(@RequestParam("method") String method) {
        Payment payment = new Payment();
        LocalDate date = LocalDate.now();
        payment.setPaymentDate(date);
        payment.setPaymentMethod(method);
        Long id = paymentService.savePayment(payment);
        if (id == null) {
            return new ResponseEntity<>("Can't create Payment", HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(id,HttpStatus.CREATED);
        }
    }
}
