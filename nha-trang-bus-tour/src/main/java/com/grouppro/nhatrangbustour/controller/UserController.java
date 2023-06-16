package com.grouppro.nhatrangbustour.controller;

import com.grouppro.nhatrangbustour.Entity.User;
import com.grouppro.nhatrangbustour.service.interfaces.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "User-API")
@RequestMapping("api/users")
@Slf4j
public class UserController {
    private final IUserService userService;
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
//    @GetMapping("/login/google")
//    public String loginGoogle() {
//        return "redirect:/oauth2/authorization/google";
//    }
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "404", description = "When don't have any User"),
//            @ApiResponse( content = @Content(schema = @Schema(implementation = User.class)))
//    })
//    @Operation(summary = "login by google")
//    @GetMapping("/login/oauth2/code/google")
//    public ResponseEntity<?> LoginByGoogle(@AuthenticationPrincipal OAuth2User oauth2User) {
//        String name = oauth2User.getAttribute("name");
//        String email = oauth2User.getAttribute("email");
//        // Process the user details as needed
//
//        // Return a success response or any desired data
//        return ResponseEntity.ok("Authentication successful");
//    }
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
}
