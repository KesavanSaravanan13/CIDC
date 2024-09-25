package com.TicketManagement.TrainTicket.controller;

import com.TicketManagement.TrainTicket.entity.TrainStation;
import com.TicketManagement.TrainTicket.service.TrainStationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/train-station")
@RequiredArgsConstructor
public class TrainStationController {

    private final TrainStationService trainStationService;

    @GetMapping
    public List<TrainStation> getAllTrainStations() {
        return trainStationService.findAll();
    }

    @GetMapping("/{id}")
    public TrainStation getTrainStationById(@PathVariable int id) {
        return trainStationService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTrainStation(@PathVariable int id) {
        trainStationService.deleteById(id);
    }
}

