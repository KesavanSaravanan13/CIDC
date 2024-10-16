package com.TicketManagement.TrainTicket.controller;


import com.TicketManagement.TrainTicket.dto.UserDTO;
import com.TicketManagement.TrainTicket.entity.User;
import com.TicketManagement.TrainTicket.exception.InputMantatoryException;
import com.TicketManagement.TrainTicket.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public void createUser(@RequestBody final User user) {
        this.userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable final Long id) {
        return this.userService.getUserById(id);
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return this.userService.getAllUsers();
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
