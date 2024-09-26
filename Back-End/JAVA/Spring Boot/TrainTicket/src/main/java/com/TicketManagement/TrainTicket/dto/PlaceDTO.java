package com.TicketManagement.TrainTicket.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
public class PlaceDTO {

    private Long placeId;
    private String placeName;
    private int noOfStations;

    public PlaceDTO(Long placeId, String placeName, int noOfStations) {
        this.placeId = placeId;
        this.placeName = placeName;
        this.noOfStations = noOfStations;
    }
}
