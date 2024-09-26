package com.TicketManagement.TrainTicket.mapper;

import com.TicketManagement.TrainTicket.dto.PlaceDTO;
import com.TicketManagement.TrainTicket.dto.TrainStationDTO;
import com.TicketManagement.TrainTicket.entity.Place;
import com.TicketManagement.TrainTicket.entity.TrainStation;
import com.TicketManagement.TrainTicket.repository.PlaceRepository;
import com.TicketManagement.TrainTicket.repository.TrainStationRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Component
@RequiredArgsConstructor
public class TrainStationMapper {

    private final TrainStationRepository trainStationRepo;
    private List<TrainStationDTO> trainStationList = new ArrayList<>();

    public void toDTO() {
        trainStationList.clear();
        for (TrainStation trainStation : trainStationRepo.findAll()) {
            trainStationList.add(new TrainStationDTO(trainStation.getStationId(),trainStation.getStationName(),trainStation.getPlace()));
        }
    }

//    public static TrainStation toEntity(TrainStationDTO trainStationDTO) {
//        TrainStation trainStation = new TrainStation();
//        trainStation.setStationId(trainStationDTO.getStationId());
//        trainStation.setStationName(trainStationDTO.getStationName());
//
//        Place place = new Place();
//        place.setPlace_id(trainStationDTO.getPlace().getPlace_id());
//        trainStation.setPlace(place);
//
//        return trainStation;
//    }
}
