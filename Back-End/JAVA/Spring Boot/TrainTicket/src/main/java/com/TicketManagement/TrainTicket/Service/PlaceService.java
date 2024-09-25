package com.TicketManagement.TrainTicket.service;

import com.TicketManagement.TrainTicket.entity.Place;
import com.TicketManagement.TrainTicket.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    public void savePlace(Place place) {
         placeRepository.save(place);
    }

    public List<Place> getAllPlaces() {
        return placeRepository.findAll();
    }

    public Place getPlaceByName(String placeName) {
        return placeRepository.findById(placeName).orElse(null);
    }

    public void deletePlace(String placeName) {
        placeRepository.deleteById(placeName);
    }
}
