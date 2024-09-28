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

    public List<PlaceDTO> getPlaceDetails() {
        List<PlaceDTO> placeList = new ArrayList<>();
        this.placeRepo.findAll().forEach(place -> {
            if (place.getStatus()) {
                placeList.add(new PlaceDTO(place.getPlaceId(), place.getPlaceName(), place.getNoOfStations(), place.getStatus()));
            }
        });
        return placeList;
    }

    public void savePlace(final PlaceDTO place) {
        getPlaceDetails();
        Place placeObj = new Place();
        placeObj.setPlaceId(place.getPlaceId());
        placeObj.setPlaceName(place.getPlaceName());
        placeObj.setNoOfStations(place.getNoOfStations());
        placeObj.setStatus(place.getStatus());
        this.placeRepo.save(placeObj);
    }

    public PlaceDTO getPlaceById(final Long id) {
        for (PlaceDTO placeDTO : getPlaceDetails()) {
            if (placeDTO.getPlaceId().equals(id)) {
                return placeDTO;
            }
        }
        return null;
    }

    public List<PlaceDTO> getAllPlaces() {
        return new ArrayList<>(getPlaceDetails());
    }

    public String deletePlace(final Long placeId) {
        final String[] str = {""};
        str[0] = "No Id Matched";
        getPlaceDetails().forEach(user -> {
            if (user.getPlaceId().equals(placeId)) {
                user.setStatus(false);
                savePlace(user);
                str[0] = "Deleted";
            }
        });
        return str[0];
    }
}
