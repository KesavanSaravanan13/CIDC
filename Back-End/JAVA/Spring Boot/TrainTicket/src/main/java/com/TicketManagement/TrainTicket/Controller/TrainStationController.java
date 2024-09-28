package com.TicketManagement.TrainTicket.controller;

import com.TicketManagement.TrainTicket.dto.TrainStationDTO;
import com.TicketManagement.TrainTicket.service.TrainStationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/train-station")
@RequiredArgsConstructor
public class TrainStationController {

    private final TrainStationService trainStationService;

    @GetMapping
    public List<TrainStationDTO> getAllTrainStations() {
        return this.trainStationService.getAllTrainStations();
    }

    @GetMapping("/{id}")
    public TrainStationDTO getTrainStationById(@PathVariable final long id) {
        return this.trainStationService.getTrainStationById(id);
    }

    @PostMapping
    public void getTrainStationById(@RequestBody final TrainStationDTO train) {
        this.trainStationService.saveTrainStation(train);
    }

    @DeleteMapping("/{id}")
    public String deleteTrainStation(@PathVariable final long id) {
        return this.trainStationService.deleteTrainStation(id);
    }
}

