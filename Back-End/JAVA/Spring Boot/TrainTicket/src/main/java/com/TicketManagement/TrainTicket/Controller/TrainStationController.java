package com.TicketManagement.TrainTicket.controller;

import com.TicketManagement.TrainTicket.dto.TrainStationDTO;
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
    public List<TrainStationDTO> getAllTrainStations() {
        return trainStationService.getAllTrainStations();
    }

    @GetMapping("/{id}")
    public TrainStationDTO getTrainStationById(@PathVariable long id) {
        return trainStationService.getTrainStationById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTrainStation(@PathVariable long id) {
        trainStationService.deleteTrainStation(id);
    }
}

