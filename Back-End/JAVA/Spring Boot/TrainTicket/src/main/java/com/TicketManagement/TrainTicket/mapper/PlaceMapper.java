package com.TicketManagement.TrainTicket.mapper;

import com.TicketManagement.TrainTicket.dto.PlaceDTO;
import com.TicketManagement.TrainTicket.entity.Place;
import com.TicketManagement.TrainTicket.repository.PlaceRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Component
@RequiredArgsConstructor
public class PlaceMapper {

    private final PlaceRepository placeRepo;
    private List<PlaceDTO> placeList = new ArrayList<>();

    public void toDTO() {
        for (Place place : placeRepo.findAll()) {
            if (place.getStatus() != null && place.getStatus().equalsIgnoreCase("Available")) {
                placeList.add(new PlaceDTO(place.getPlace_id(), place.getPlaceName(), place.getNoOfStations(), place.getStatus()));
            }
        }
    }

    public void toEntity(PlaceDTO placeDTO) {
        Place place = new Place();
        place.setPlace_id(placeDTO.getPlaceId());
        place.setPlaceName(placeDTO.getPlaceName());
        place.setNoOfStations(placeDTO.getNoOfStations());
        placeRepo.save(place);
    }
}
