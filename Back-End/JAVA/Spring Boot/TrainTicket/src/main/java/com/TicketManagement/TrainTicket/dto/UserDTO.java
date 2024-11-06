package com.TicketManagement.TrainTicket.dto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class UserDTO {

    private Long userId;
    private String userName;
    private String address;
    private String phoneNumber;
    private boolean status;
    private String email;

    public UserDTO() {
    }

    public UserDTO(Long userId, String userName, String address, String phoneNumber, boolean status, String email) {
        this.userId = userId;
        this.userName = userName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.email = email;
    }

    public boolean getStatus() {
        return this.status;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", status=" + status +
                '}';
    }
}
