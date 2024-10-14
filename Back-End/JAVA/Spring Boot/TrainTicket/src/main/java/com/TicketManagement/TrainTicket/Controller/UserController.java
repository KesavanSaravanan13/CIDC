package com.TicketManagement.TrainTicket.controller;


import com.TicketManagement.TrainTicket.config.JwtFilter;
import com.TicketManagement.TrainTicket.dto.UserDTO;
import com.TicketManagement.TrainTicket.entity.User;
import com.TicketManagement.TrainTicket.exception.TokenNotFoundException;
import com.TicketManagement.TrainTicket.service.JWTService;
import com.TicketManagement.TrainTicket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JWTService jwtService;
    private final JwtFilter jwtFilter;

    @PostMapping
    public void createUser(@RequestBody final User user) {
        this.userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public Object getUserById(@PathVariable final Long id) {
        return this.userService.getUserById(id);
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @GetMapping("/error")
    public ResponseEntity<?> getErrors() {
        return ResponseEntity.ok(Optional.ofNullable(userService.getAllUsers()));
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable final Long id) {
        return this.userService.deleteUser(id);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody final User user) {
        return this.userService.verifyUser(user);
    }

}
