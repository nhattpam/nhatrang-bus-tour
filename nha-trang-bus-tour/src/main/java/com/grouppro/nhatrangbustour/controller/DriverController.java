package com.grouppro.nhatrangbustour.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Tag(name = "drivers")
@RequestMapping("api/drivers")
public class DriverController {
    @Operation(summary = "Get all drivers")
    @GetMapping("/")
    public ResponseEntity<?> getDrivers(@RequestParam(defaultValue = "0") Integer input) {
        if (input == 0) {
            return ResponseEntity.ok("default value here:" + input);
        } else {
            return ResponseEntity.ok("value:" + input);
        }
    }
    @Operation(summary = "Create a new driver ")
    @PostMapping("/")
    public ResponseEntity<?> addDriver(@RequestParam(defaultValue = "0") Integer input) {
        if (input == 0) {
            return ResponseEntity.ok("default value here:" + input);
        } else {
            return ResponseEntity.ok("value:" + input);
        }
    }
}
