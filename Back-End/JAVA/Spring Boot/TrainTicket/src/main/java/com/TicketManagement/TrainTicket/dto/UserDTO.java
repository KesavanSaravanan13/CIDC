package com.TicketManagement.TrainTicket.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private Long userId;
    private String name;
    private String address;
    private String phoneNumber;
    private boolean status;

    public UserDTO(Long userId, String name, String address, String phoneNumber, boolean status) {
        this.userId = userId;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }

    public boolean getStatus() {
        return this.status;
    }

}
