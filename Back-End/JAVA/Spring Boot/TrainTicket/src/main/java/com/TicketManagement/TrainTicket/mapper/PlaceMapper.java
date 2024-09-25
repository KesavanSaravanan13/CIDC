package com.TicketManagement.TrainTicket.mapper;

import com.TicketManagement.TrainTicket.dto.PlaceDTO;
import com.TicketManagement.TrainTicket.entity.Place;

public class PlaceMapper {
    public static PlaceDTO toDTO(Place place) {
        PlaceDTO placeDTO = new PlaceDTO();
        placeDTO.setPlaceId(place.getPlace_id());
        placeDTO.setPlaceName(place.getPlaceName());
        placeDTO.setNoOfStations(place.getNoOfStations());
        return placeDTO;
    }

    public static Place toEntity(PlaceDTO placeDTO) {
        Place place = new Place();
        place.setPlace_id(placeDTO.getPlaceId());
        place.setPlaceName(placeDTO.getPlaceName());
        place.setNoOfStations(placeDTO.getNoOfStations());
        return place;
    }
}
