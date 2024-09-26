package com.TicketManagement.TrainTicket.dto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
public class UserDTO {

    private Long userId;
    private String name;
    private String address;
    private String phoneNumber;
    private String status;

    public UserDTO(Long userId, String name, String address, String phoneNumber,String status) {
        this.userId = userId;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.status=status;
    }


}
