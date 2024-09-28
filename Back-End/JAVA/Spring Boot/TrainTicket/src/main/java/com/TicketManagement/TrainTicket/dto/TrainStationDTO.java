package com.TicketManagement.TrainTicket.dto;

import com.TicketManagement.TrainTicket.entity.Place;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainStationDTO {
    private Long stationId;
    private String stationName;
    private Place place;
    private boolean status;

    public TrainStationDTO(Long stationId, String stationName, Place place, boolean status) {
        this.stationId = stationId;
        this.stationName = stationName;
        this.place = place;
        this.status = status;
    }

    public boolean getStatus() {
        return this.status;
    }
}
