package com.TicketManagement.TrainTicket.Table;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Entity
@Table(name = "place")
@Component
@Getter
@Setter
public class PlaceDetails {

    @Id
    @Column(name = "place_name", nullable = false, length = 100)
    private String placeName;

    @Column(name = "no_of_stations", nullable = false)
    private int noOfStations;

}
