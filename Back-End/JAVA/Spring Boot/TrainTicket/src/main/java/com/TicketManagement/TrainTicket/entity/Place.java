package com.TicketManagement.TrainTicket.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "place")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_id")
    private Long placeId;

    @Column(name = "place_name", nullable = false, length = 100)
    private String placeName;

    @Column(name = "no_of_stations", nullable = false)
    private int noOfStations;

    @Column(name = "status", nullable = false)
    private boolean status;

    public boolean getStatus() {
        return this.status;
    }
}
