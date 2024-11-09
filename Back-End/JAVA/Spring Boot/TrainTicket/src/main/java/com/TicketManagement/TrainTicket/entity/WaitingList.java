package com.TicketManagement.TrainTicket.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "waiting_list")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WaitingList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "waiting_list_id")
    private Long waitingListId;

    @Column(name = "seat_number")
    private String seatNumber;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}

