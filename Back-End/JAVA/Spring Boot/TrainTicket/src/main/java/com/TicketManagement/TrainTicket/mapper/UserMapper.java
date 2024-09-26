package com.TicketManagement.TrainTicket.mapper;

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
    private List<UserDTO> userList;

    public void toDTO() {
        userList = new ArrayList<>();
        for (User user : userRepo.findAll()) {
            if(user.getStatus() != null && user.getStatus().equalsIgnoreCase("Active")){
                userList.add(new UserDTO(user.getUserId(), user.getName(), user.getAddress(), user.getPhoneNumber(),user.getStatus()));
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
}
