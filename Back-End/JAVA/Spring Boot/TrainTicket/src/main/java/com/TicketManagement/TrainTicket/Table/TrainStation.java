package com.TicketManagement.TrainTicket.Table;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Entity
@Table(name = "train_station")
@Component
@Getter
@Setter
public class TrainStation implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "station_id", nullable = false)
        private int stationId;

        @Column(name = "station_name", nullable = false, unique = true)
        private String stationName;

        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "place_name",nullable = false,referencedColumnName = "place_name")
        private PlaceDetails place;
}
