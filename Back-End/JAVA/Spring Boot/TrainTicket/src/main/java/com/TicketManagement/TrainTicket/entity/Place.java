package com.TicketManagement.TrainTicket.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "place")
@Getter
@Setter
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long place_id;

    @Column(name = "place_name", nullable = false, length = 100)
    private String placeName;

    @Column(name = "no_of_stations", nullable = false)
    private int noOfStations;

}