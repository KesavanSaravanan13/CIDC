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
    private List<UserDTO> userList;

    public void toDTO() {
        userList = new ArrayList<>();
        for (User user : userRepo.findAll()) {
            if (user.getStatus() != null && user.getStatus().equalsIgnoreCase("Active")) {
                userList.add(new UserDTO(user.getUserId(), user.getName(), user.getAddress(), user.getPhoneNumber(), user.getStatus()));
            }
        }
    }

    public void toEntity(UserDTO userDTO) {
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setName(userDTO.getName());
        user.setAddress(userDTO.getAddress());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setStatus(userDTO.getStatus());
        userRepo.save(user);
    }


    public void saveUser(UserDTO user) {
        toDTO();
        new UserDTO(user.getUserId(), user.getName(), user.getAddress(), user.getPhoneNumber(), user.getStatus());
    }

    public UserDTO getUserById(Long id) {
        toDTO();
        for (UserDTO userDTO : userList) {
            if (userDTO.getUserId().equals(id)) {
                return userDTO;
            }
        }
        return null;
    }

    public List<UserDTO> getAllUsers() {
        toDTO();
        return new ArrayList<>(userList);
    }

    public String deleteUser(Long userId) {
        final String[] str = {""};
        toDTO();
        userList.forEach(user -> {
            if (user.getUserId().equals(userId)) {
                user.setStatus("De-Active");
                toEntity(user);
                str[0] = "Deleted";
            } else {
                str[0] = "No Id Matched";
            }
        });
        return str[0];
    }
}

