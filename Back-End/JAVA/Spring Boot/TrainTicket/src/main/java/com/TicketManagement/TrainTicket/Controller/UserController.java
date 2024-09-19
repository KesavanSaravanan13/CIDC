package com.TicketManagement.TrainTicket.Controller;


import com.TicketManagement.TrainTicket.Service.UserService;
import com.TicketManagement.TrainTicket.Table.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/create")
    public UserDetails createUser(@RequestBody UserDetails user) {
        return userService.saveUser(user);
    }

    @GetMapping("/user/id/{id}")
    public Optional<UserDetails> getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @GetMapping("/user")
    public List<UserDetails> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/user/delete/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }
}
