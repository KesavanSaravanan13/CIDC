package com.TicketManagement.TrainTicket.controller;

import com.TicketManagement.TrainTicket.dto.PlaceDTO;
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
    public void createPlace(@RequestBody PlaceDTO place) {
        placeService.savePlace(place);
    }

    @GetMapping
    public List<PlaceDTO> getAllPlaces() {
        return placeService.getAllPlaces();
    }

    @GetMapping("/{placeId}")
    public PlaceDTO getPlaceByName(@PathVariable Long placeId) {
        return placeService.getPlaceById(placeId);
    }

    @DeleteMapping("/{placeId}")
    public void deletePlace(@PathVariable Long placeId) {
         placeService.deletePlace(placeId);
    }

}

