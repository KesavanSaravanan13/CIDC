package com.TicketManagement.TrainTicket.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    private String password;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "address", nullable = false, length = 255)
    private String address;

    @Column(name = "ph_no", nullable = false, length = 15)
    private String phoneNumber;

    @Column(name = "status")
    private boolean status;

    public boolean getStatus() {
        return this.status;
    }
}
