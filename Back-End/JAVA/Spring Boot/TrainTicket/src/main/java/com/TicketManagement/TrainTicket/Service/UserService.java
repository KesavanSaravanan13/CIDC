package com.TicketManagement.TrainTicket.service;

import com.TicketManagement.TrainTicket.dto.UserDTO;
import com.TicketManagement.TrainTicket.entity.User;
import com.TicketManagement.TrainTicket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;

    public List<UserDTO> getUserDetails() {
        List<UserDTO> userList = new ArrayList<>();
        this.userRepo.findAll().forEach(user -> {
            if (user.getStatus()) {
                userList.add(new UserDTO(user.getUserId(), user.getName(), user.getAddress(), user.getPhoneNumber(), user.getStatus()));
            }
        });
        return userList;
    }

    public void saveUser(final UserDTO userDTO) {
        getUserDetails();
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setName(userDTO.getName());
        user.setAddress(userDTO.getAddress());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setStatus(userDTO.getStatus());
        this.userRepo.save(user);
    }

    public UserDTO getUserById(final Long id) {
        for (UserDTO userDTO : getUserDetails()) {
            if (userDTO.getUserId().equals(id)) {
                return userDTO;
            }
        }
        return null;
    }

    public List<UserDTO> getAllUsers() {
        return new ArrayList<>(getUserDetails());
    }

    public String deleteUser(Long userId) {
        final String[] str = {""};
        str[0]="No Id Matched";
        getUserDetails().forEach(user -> {
            if (user.getUserId().equals(userId)) {
                user.setStatus(false);
                saveUser(user);
                str[0] = "Deleted";
            }
        });
        return str[0];
    }
}

