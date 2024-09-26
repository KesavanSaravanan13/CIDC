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
        new PlaceDTO(place.getPlaceId(), place.getPlaceName(), place.getNoOfStations());
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

    public void deletePlace(Long placeId) {
        placeMapper.toDTO();
        placeMapper.getPlaceList().removeIf(placeDTO -> placeDTO.getPlaceId().equals(placeId));
    }
}
