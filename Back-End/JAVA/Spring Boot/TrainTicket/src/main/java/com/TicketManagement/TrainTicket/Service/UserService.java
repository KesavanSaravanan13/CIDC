package com.TicketManagement.TrainTicket.service;

import com.TicketManagement.TrainTicket.dto.UserDTO;
import com.TicketManagement.TrainTicket.entity.User;
import com.TicketManagement.TrainTicket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;
    private BCryptPasswordEncoder encrypt = new BCryptPasswordEncoder();
    private final AuthenticationManager authenticationManager;

    public List<UserDTO> getUserDetails() {
        List<UserDTO> userList = new ArrayList<>();
        this.userRepo.findAll().forEach(user -> {
            if (user.getStatus()) {
                userList.add(new UserDTO(user.getUserId(), user.getName(), user.getAddress(), user.getPhoneNumber(), user.getStatus()));
            }
        });
        return userList;
    }

    public void saveUser(final User user) {
        getUserDetails();
        user.setPassword(encrypt.encode(user.getPassword()));
        this.userRepo.save(user);
    }

    public UserDTO getUserById(final Long id) {
        User user = this.userRepo.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
        return new UserDTO(user.getUserId(), user.getName(), user.getAddress(), user.getPhoneNumber(), user.getStatus());
    }

    public List<UserDTO> getAllUsers() {
        return new ArrayList<>(getUserDetails());
    }

    public String deleteUser(Long userId) {
        final String[] str = {""};
        str[0] = "No Id Matched";
        getUserDetails().forEach(user -> {
            if (user.getUserId().equals(userId)) {
                user.setStatus(false);
                User userDTO = new User();
                userDTO.setUserId(user.getUserId());
                userDTO.setStatus(user.getStatus());
                userDTO.setName(user.getName());
                userDTO.setAddress(user.getAddress());
                saveUser(userDTO);
                str[0] = "Deleted";
            }
        });
        return str[0];
    }


    public String verifyUser(User user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));

        if (authentication.isAuthenticated())
            return JWTService.generateToken(user.getName());
        return "Not Authenticated";
    }
}

