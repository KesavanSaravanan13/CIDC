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
    private String status;

    public TrainStationDTO(Long stationId, String stationName, Place place, String status) {
        this.stationId = stationId;
        this.stationName = stationName;
        this.place = place;
        this.status = status;
    }
}
