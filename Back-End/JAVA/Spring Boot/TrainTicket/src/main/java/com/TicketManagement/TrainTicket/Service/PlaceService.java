package com.TicketManagement.TrainTicket.service;

import com.TicketManagement.TrainTicket.dto.PlaceDTO;
import com.TicketManagement.TrainTicket.entity.Place;
import com.TicketManagement.TrainTicket.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepo;
    private List<PlaceDTO> placeList;

    public void toDTO() {
        placeList = new ArrayList<>();
        for (Place place : placeRepo.findAll()) {
            if (place.getStatus() != null && place.getStatus().equalsIgnoreCase("Available")) {
                placeList.add(new PlaceDTO(place.getPlaceId(), place.getPlaceName(), place.getNoOfStations(), place.getStatus()));
            }
        }
    }

    public void toEntity(final PlaceDTO placeDTO) {
        Place place = new Place();
        place.setPlaceId(placeDTO.getPlaceId());
        place.setPlaceName(placeDTO.getPlaceName());
        place.setNoOfStations(placeDTO.getNoOfStations());
        placeRepo.save(place);
    }

    public void savePlace(final PlaceDTO place) {
        toDTO();
        new PlaceDTO(place.getPlaceId(), place.getPlaceName(), place.getNoOfStations(), place.getStatus());
    }

    public PlaceDTO getPlaceById(Long id) {
        toDTO();
        for (PlaceDTO placeDTO : placeList) {
            if (placeDTO.getPlaceId().equals(id)) {
                return placeDTO;
            }
        }
        return null;
    }

    public List<PlaceDTO> getAllPlaces() {
        toDTO();
        return new ArrayList<>(placeList);
    }

    public String deletePlace(final Long placeId) {
        final String[] str = {""};
        toDTO();
        placeList.forEach(user -> {
            if (user.getPlaceId().equals(placeId)) {
                user.setStatus("Not-Available");
                toEntity(user);
                str[0] = "Deleted";
            } else {
                str[0] = "No Id Matched";
            }
        });
        return str[0];
    }
}
