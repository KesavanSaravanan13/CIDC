package com.TicketManagement.TrainTicket.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Table(name = "train")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trainId;

    @Column(nullable = false)
    private int trainNumber;

    @Column(nullable = false)
    private String fromPlace;

    @Column(nullable = false)
    private String toPlace;

    @Column(nullable = false)
    private LocalTime arriveAt;

    @Column(nullable = false)
    private LocalTime reachOn;

    @Column(nullable = false)
    private String reachingTime;
}
