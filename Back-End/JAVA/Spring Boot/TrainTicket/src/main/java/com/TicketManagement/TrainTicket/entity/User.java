package com.TicketManagement.TrainTicket.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    private String password;

    @Column(name = "user_name", nullable = false, length = 100)
    private String userName;

    @Column(name = "address", nullable = false, length = 255)
    private String address;

    @Column(name = "ph_no", nullable = false, length = 15)
    private String phoneNumber;

    @Column(name = "status")
    private boolean status;

    @Column(name="email")
    private String email;

    public User(Long userId, String userName, String address, String phoneNumber, boolean status,String email) {
    }


    public boolean getStatus() {
        return this.status;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", status=" + status +
                ", email='" + email + '\'' +
                '}';
    }
}
