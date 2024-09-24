package com.TicketManagement.TrainTicket.Service;

import com.TicketManagement.TrainTicket.Repository.PlaceRepository;
import com.TicketManagement.TrainTicket.Table.PlaceDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    public void savePlace(PlaceDetails place) {
         placeRepository.save(place);
    }

    public List<PlaceDetails> getAllPlaces() {
        return placeRepository.findAll();
    }

    public PlaceDetails getPlaceByName(String placeName) {
        return placeRepository.findById(placeName).orElse(null);
    }

    public void deletePlace(String placeName) {
        placeRepository.deleteById(placeName);
    }
}
