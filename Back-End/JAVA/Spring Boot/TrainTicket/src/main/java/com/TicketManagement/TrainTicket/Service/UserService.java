package com.TicketManagement.TrainTicket.service;

import com.TicketManagement.TrainTicket.dto.UserDTO;
import com.TicketManagement.TrainTicket.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;


    public void saveUser(UserDTO user) {
        userMapper.toDTO();
        new UserDTO(user.getUserId(), user.getName(), user.getAddress(), user.getPhoneNumber(), user.getStatus());
    }

    public UserDTO getUserById(Long id) {
        userMapper.toDTO();
        for (UserDTO userDTO : userMapper.getUserList()) {
            if (userDTO.getUserId().equals(id)) {
                return userDTO;
            }
        }
        return null;
    }

    public List<UserDTO> getAllUsers() {
        userMapper.toDTO();
        return new ArrayList<>(userMapper.getUserList());
    }

    public String deleteUser(Long userId) {
        final String[] str = {""};
        userMapper.toDTO();
        userMapper.getUserList().forEach(user -> {
            if (user.getUserId().equals(userId)) {
                user.setStatus("De-Active");
                userMapper.toEntity(user);
                str[0] = "Deleted";
            } else {
                str[0] = "No Id Matched";
            }
        });
        return str[0];
    }
}

