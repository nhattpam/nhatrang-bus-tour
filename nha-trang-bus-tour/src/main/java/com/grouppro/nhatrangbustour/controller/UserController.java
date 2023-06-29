package com.grouppro.nhatrangbustour.controller;

import com.grouppro.nhatrangbustour.Entity.Bus;
import com.grouppro.nhatrangbustour.Entity.CustomUserDetails;
import com.grouppro.nhatrangbustour.Entity.User;
import com.grouppro.nhatrangbustour.config.JwtTokenProvider;
import com.grouppro.nhatrangbustour.dto.LoginFormDTO;
import com.grouppro.nhatrangbustour.service.interfaces.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "users")
@RequestMapping("api/users")
@Slf4j
@SecurityRequirement(name = "Authorization")
public class UserController {
    private static final String ADMIN="ROLE_Admin";
    private static final String CUSTOMER="ROLE_Customer";
    private final IUserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "When don't have any User"),
            @ApiResponse( content = @Content(schema = @Schema(implementation = User.class)))
    })
    @Operation(summary = "Get all users")
    @GetMapping("/")
    public ResponseEntity<?> getUsers() {
        List<User> users =userService.getUsers();
        if (!users.isEmpty()) {
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no user");
        }
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "When User created successfully!"),
            @ApiResponse(responseCode = "400", description = "When User can't be created - Object is not valid!")
    })
    @Operation(summary = "Create a new user")
    @PostMapping("/")
    public ResponseEntity<?> Register(@RequestParam("username") String username, @RequestParam("email") String email) {
        User user = new User();
        user.setUserName(username);
        user.setUserPhone(" ");
        user.setUserEmail(email);
        Long id = userService.Register(user);
        if (id == null) {
            return new ResponseEntity<>("Can't create user", HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(id,HttpStatus.CREATED);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam("email") String email) {
        try {
            if (email!=null ){
                String token = tokenProvider.generateToken(email);
                return ResponseEntity.ok(token);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid email");
        }
        return ResponseEntity.badRequest().body("Invalid email");
    }

}
