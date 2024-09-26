package com.TicketManagement.TrainTicket.mapper;

import ch.qos.logback.core.CoreConstants;
import com.TicketManagement.TrainTicket.dto.UserDTO;
import com.TicketManagement.TrainTicket.entity.User;
import com.TicketManagement.TrainTicket.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Component
@RequiredArgsConstructor
public class UserMapper {

    private final UserRepository userRepo;
    private List<UserDTO> userList = new ArrayList<>();

    public void toDTO() {
        userList.clear();
        for (User user : userRepo.findAll()) {
            userList.add(new UserDTO(user.getUserId(), user.getName(), user.getAddress(), user.getPhoneNumber()));
        }
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
