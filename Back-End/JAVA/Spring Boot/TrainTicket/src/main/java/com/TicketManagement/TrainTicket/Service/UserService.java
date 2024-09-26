package com.TicketManagement.TrainTicket.service;

import com.TicketManagement.TrainTicket.dto.UserDTO;
import com.TicketManagement.TrainTicket.entity.User;
import com.TicketManagement.TrainTicket.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final List<UserDTO> userList = new ArrayList<>();

    public void saveUser(UserDTO user) {
        userMapper.toDTO();
        new UserDTO(user.getUserId(), user.getName(), user.getAddress(), user.getPhoneNumber());
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

    public void deleteUser(Long userId) {
        userMapper.toDTO();
        userList.removeIf(userDTO -> userDTO.getUserId().equals(userId));
    }
}

