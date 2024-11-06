package com.TicketManagement.TrainTicket.controller;

import com.TicketManagement.TrainTicket.dto.PlaceDTO;
import com.TicketManagement.TrainTicket.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/place")
@RequiredArgsConstructor
@CrossOrigin
public class PlaceController {

    private final PlaceService placeService;

    @PostMapping
    public void createPlace(@RequestBody final PlaceDTO place) {
        this.placeService.savePlace(place);
    }

    @GetMapping
    public List<PlaceDTO> getAllPlaces() {
        return this.placeService.getAllPlaces();
    }

    @GetMapping("/{placeId}")
    public PlaceDTO getPlaceByName(@PathVariable final Long placeId) {
        return this.placeService.getPlaceById(placeId);
    }

    @DeleteMapping("/{placeId}")
    public String deletePlace(@PathVariable final Long placeId) {
        return this.placeService.deletePlace(placeId);
    }

}

