package com.TicketManagement.TrainTicket.Controller;

import com.TicketManagement.TrainTicket.Service.PlaceService;
import com.TicketManagement.TrainTicket.Table.PlaceDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @PostMapping("/place/create")
    public PlaceDetails createPlace(@RequestBody PlaceDetails place) {
        return placeService.savePlace(place);
    }

    @GetMapping("/place")
    public List<PlaceDetails> getAllPlaces() {
        return placeService.getAllPlaces();
    }

    @GetMapping("/place/name/{placeName}")
    public PlaceDetails getPlaceByName(@PathVariable String placeName) {
        return placeService.getPlaceByName(placeName);
    }

}

