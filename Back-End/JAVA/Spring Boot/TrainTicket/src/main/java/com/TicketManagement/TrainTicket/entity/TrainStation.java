package com.TicketManagement.TrainTicket.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "train_station")
@Getter
@Setter
public class TrainStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stationId;

    @Column(name = "station_name", nullable = false, unique = true)
    private String stationName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "place_id", referencedColumnName = "place_id")
    private Place place;

    @Column(name = "status")
    private String status;
}
