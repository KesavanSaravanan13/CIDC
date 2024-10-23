package com.TicketManagement.TrainTicket.dto;

import com.TicketManagement.TrainTicket.entity.Place;
import com.TicketManagement.TrainTicket.repository.PlaceRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainStationDTO {
    private Long stationId;
    private String stationName;
    private Place place;
    private boolean status;

    @Autowired
    private PlaceRepository placeRepository;

    public TrainStationDTO(Long stationId, String stationName, Place place, boolean status) {
        this.stationId = stationId;
        this.stationName = stationName;
        this.place = place;
        this.status = status;
    }
    public Optional<Place> getPlaceByName(String name) {
        return placeRepository.findByName(name);
    }
    public boolean getStatus() {
        return this.status;
    }
}
