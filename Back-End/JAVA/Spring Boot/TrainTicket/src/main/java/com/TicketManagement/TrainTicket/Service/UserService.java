package com.TicketManagement.TrainTicket.service;

import com.TicketManagement.TrainTicket.dto.UserDTO;
import com.TicketManagement.TrainTicket.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDTO userdto;

    public void saveUser(User user) {
        userdto.save(user);
    }

    public Optional<User> getUserById(int id) {
        return userdto.findById(id);
    }

    public List<User> getAllUsers() {
        return userdto.findAll();
    }

    public void deleteUser(int id) {
        userdto.deleteById(id);
    }
}

