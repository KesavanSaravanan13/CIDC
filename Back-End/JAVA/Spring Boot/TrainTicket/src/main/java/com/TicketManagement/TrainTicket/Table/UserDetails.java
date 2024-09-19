package com.TicketManagement.TrainTicket.Table;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name="user")
@Getter
@Setter
public class UserDetails {

    @Id
    @Column(name = "user_id")
    private int userId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "address", nullable = false, length = 255)
    private String address;

    @Column(name = "ph_no", nullable = false, length = 15)
    private String phoneNumber;
}
