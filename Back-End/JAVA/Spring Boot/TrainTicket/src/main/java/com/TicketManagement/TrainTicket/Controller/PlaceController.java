package com.TicketManagement.TrainTicket.controller;

import com.TicketManagement.TrainTicket.entity.Place;
import com.TicketManagement.TrainTicket.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/place")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    @PostMapping
    public void createPlace(@RequestBody Place place) {
        placeService.savePlace(place);
    }

    @GetMapping
    public List<Place> getAllPlaces() {
        return placeService.getAllPlaces();
    }

    @GetMapping("/{placeName}")
    public Place getPlaceByName(@PathVariable String placeName) {
        return placeService.getPlaceByName(placeName);
    }

}

