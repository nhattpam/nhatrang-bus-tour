package com.grouppro.nhatrangbustour.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "buses")
@RequestMapping("api/buses")
public class BusController {

    @Operation(summary = "Get all buses")
    @GetMapping("/")
    public ResponseEntity<?> getBuses(@RequestParam(defaultValue = "0") Integer input) {
        if (input == 0) {
            return ResponseEntity.ok("default value here:" + input);
        } else {
            return ResponseEntity.ok("value:" + input);
        }
    }
    @Operation(summary = "Get a bus by its ID")
    @GetMapping("/{busId}")
    public ResponseEntity<?> getBusByID(@RequestParam(defaultValue = "0") Integer input,
                                        @PathVariable("busId") Long busId) {
        if (input == 0) {
            return ResponseEntity.ok("default value here:" + input);
        } else {
            return ResponseEntity.ok("value:" + input);
        }
    }
    @Operation(summary = "Delete a bus by its ID")
    @DeleteMapping("/{busID}")
    public ResponseEntity<?> deleteBusById(@RequestParam(defaultValue = "0") Integer input,
                                           @PathVariable("busId") Long busId) {
        if (input == 0) {
            return ResponseEntity.ok("default value here:" + input);
        } else {
            return ResponseEntity.ok("value:" + input);
        }
    }
    @Operation(summary = "Create a new bus")
    @PostMapping("/")
    public ResponseEntity<?> addBus(@RequestParam(defaultValue = "0") Integer input) {
        if (input == 0) {
            return ResponseEntity.ok("default value here:" + input);
        } else {
            return ResponseEntity.ok("value:" + input);
        }
    }
    
}
