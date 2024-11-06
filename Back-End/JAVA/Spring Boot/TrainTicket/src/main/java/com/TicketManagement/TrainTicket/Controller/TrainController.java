package com.TicketManagement.TrainTicket.controller;
import com.TicketManagement.TrainTicket.entity.Train;
import com.TicketManagement.TrainTicket.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/trains")
public class TrainController {

    @Autowired
    private TrainRepository trainRepository;

    @GetMapping
    public List<Train> getAllTrains() {
        return trainRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Train> getTrainById(@PathVariable Long id) {
        Optional<Train> train = trainRepository.findById(id);
        return train.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Train createTrain(@RequestBody Train train) {
        return trainRepository.save(train);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Train> updateTrain(@PathVariable Long id, @RequestBody Train trainDetails) {
        return trainRepository.findById(id)
                .map(train -> {
                    train.setTrainNumber(trainDetails.getTrainNumber());
                    train.setFromPlace(trainDetails.getFromPlace());
                    train.setToPlace(trainDetails.getToPlace());
                    train.setArriveAt(trainDetails.getArriveAt());
                    train.setReachOn(trainDetails.getReachOn());
                    train.setReachingTime(trainDetails.getReachingTime());
                    trainRepository.save(train);
                    return ResponseEntity.ok(train);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteTrain(@PathVariable Long id) {
//        return trainRepository.findById(id)
//                .map(train -> {
//                    trainRepository.delete(train);
//                    return ResponseEntity.noContent().build();
//                })
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
}

