package com.TicketManagement.TrainTicket.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaceDTO {

    private Long placeId;
    private String placeName;
    private int noOfStations;
    private String status;

    public PlaceDTO(Long placeId, String placeName, int noOfStations, String status) {
        this.placeId = placeId;
        this.placeName = placeName;
        this.noOfStations = noOfStations;
        this.status = status;
    }
}
