package com.TicketManagement.TrainTicket.Controller;

import com.TicketManagement.TrainTicket.Service.TrainStationService;
import com.TicketManagement.TrainTicket.Table.TrainStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TrainStationController {

    @Autowired
    private TrainStationService trainStationService;

    @GetMapping("/train-station")
    public List<TrainStation> getAllTrainStations() {
        return trainStationService.findAll();
    }

    @GetMapping("/train-station/id/{id}")
    public TrainStation getTrainStationById(@PathVariable int id) {
        return trainStationService.findById(id);
    }

    @DeleteMapping("/train-station/delete/{id}")
    public void deleteTrainStation(@PathVariable int id) {
        trainStationService.deleteById(id);
    }
}

