package com.TicketManagement.TrainTicket.service;

import com.TicketManagement.TrainTicket.dto.PlaceDTO;
import com.TicketManagement.TrainTicket.mapper.PlaceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceMapper placeMapper;
    private final List<PlaceDTO> placeList = new ArrayList<>();

    public void savePlace(PlaceDTO place) {
        placeMapper.toDTO();
        new PlaceDTO(place.getPlaceId(), place.getPlaceName(), place.getNoOfStations(), place.getStatus());
    }

    public PlaceDTO getPlaceById(Long id) {
        placeMapper.toDTO();
        for (PlaceDTO placeDTO : placeMapper.getPlaceList()) {
            if (placeDTO.getPlaceId().equals(id)) {
                return placeDTO;
            }
        }
        return null;
    }

    public List<PlaceDTO> getAllPlaces() {
        placeMapper.toDTO();
        return new ArrayList<>(placeMapper.getPlaceList());
    }

    public String deletePlace(Long placeId) {
        final String[] str = {""};
        placeMapper.toDTO();
        placeMapper.getPlaceList().forEach(user -> {
            if (user.getPlaceId().equals(placeId)) {
                user.setStatus("Not-Available");
                placeMapper.toEntity(user);
                str[0] = "Deleted";
            } else {
                str[0] = "No Id Matched";
            }
        });
        return str[0];
    }
}
