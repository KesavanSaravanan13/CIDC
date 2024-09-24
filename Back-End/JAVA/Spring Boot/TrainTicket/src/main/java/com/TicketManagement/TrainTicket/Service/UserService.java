package com.TicketManagement.TrainTicket.Service;

import com.TicketManagement.TrainTicket.Repository.UserRepository;
import com.TicketManagement.TrainTicket.Table.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveUser(UserDetails user) {
         userRepository.save(user);
    }

    public Optional<UserDetails> getUserById(int id) {
        return userRepository.findById(id);
    }

    public List<UserDetails> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}

