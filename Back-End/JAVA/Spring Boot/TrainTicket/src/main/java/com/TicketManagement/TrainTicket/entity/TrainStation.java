package com.TicketManagement.TrainTicket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "train_station")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "station_id")
    private Long stationId;

    @Column(name = "station_name", nullable = false, unique = true)
    private String stationName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "place_id", referencedColumnName = "place_id")
    private Place place;

    @Column(name = "status")
    private boolean status;

    public boolean getStatus() {
        return this.status;
    }
}
