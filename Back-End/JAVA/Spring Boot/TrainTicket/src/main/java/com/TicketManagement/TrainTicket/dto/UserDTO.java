package com.TicketManagement.TrainTicket.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {

    private Long userId;
    @Getter
    private String userName;
    private String address;
    private String phoneNumber;
    private boolean status;

//    @Override
//    public String toString() {
//        return "{\n" +
//                "  \"userId\": " + userId + ",\n" +
//                "  \"userName\": \"" + userName + "\",\n" +
//                "  \"address\": \"" + address + "\",\n" +
//                "  \"phoneNumber\": \"" + phoneNumber + "\",\n" +
//                "  \"status\": " + status + "\n" +
//                "}";
//    }


    public UserDTO(Long userId, String name, String address, String phoneNumber, boolean status) {
        this.userId = userId;
        this.userName = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }

    public UserDTO() {

    }

    public boolean getStatus() {
        return this.status;
    }

}
