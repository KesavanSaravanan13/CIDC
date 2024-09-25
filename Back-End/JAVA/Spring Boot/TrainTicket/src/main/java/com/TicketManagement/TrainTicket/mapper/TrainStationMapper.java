package com.TicketManagement.TrainTicket.mapper;

import com.TicketManagement.TrainTicket.dto.TrainStationDTO;
import com.TicketManagement.TrainTicket.entity.Place;
import com.TicketManagement.TrainTicket.entity.TrainStation;

public class TrainStationMapper {

    public static TrainStationDTO toDTO(TrainStation trainStation) {
        TrainStationDTO trainStationDTO = new TrainStationDTO();
        trainStationDTO.setStationId(trainStation.getStationId());
        trainStationDTO.setStationName(trainStation.getStationName());
        trainStationDTO.setPlaceId(trainStation.getPlace().getPlace_id());
        return trainStationDTO;
    }

    public static TrainStation toEntity(TrainStationDTO trainStationDTO) {
        TrainStation trainStation = new TrainStation();
        trainStation.setStationId(trainStationDTO.getStationId());
        trainStation.setStationName(trainStationDTO.getStationName());

        Place place = new Place();
        place.setPlace_id(trainStationDTO.getPlaceId());
        trainStation.setPlace(place);

        return trainStation;
    }
}
