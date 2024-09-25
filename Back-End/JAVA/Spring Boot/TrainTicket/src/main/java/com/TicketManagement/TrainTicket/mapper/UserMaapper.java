package com.TicketManagement.TrainTicket.mapper;

import com.TicketManagement.TrainTicket.dto.UserDTO;
import com.TicketManagement.TrainTicket.entity.User;

public class UserMaapper {

    public static UserDTO toDTO(User user) {
        return new UserDTO(user.getUserId(), user.getName(), user.getAddress(), user.getPhoneNumber());
    }

    public static User toEntity(UserDTO userDTO) {
        User user = new User();

        user.setUserId(userDTO.getUserId());
        user.setName(userDTO.getName());
        user.setAddress(userDTO.getAddress());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        return user;
    }
}

