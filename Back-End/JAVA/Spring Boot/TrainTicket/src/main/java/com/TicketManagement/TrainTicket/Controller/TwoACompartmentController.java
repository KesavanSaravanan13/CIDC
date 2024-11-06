package com.TicketManagement.TrainTicket.controller;

import com.TicketManagement.TrainTicket.entity.TwoACompartment;
import com.TicketManagement.TrainTicket.repository.TwoACompartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/2a-compartments")
public class TwoACompartmentController {

    @Autowired
    private TwoACompartmentRepository twoACompartmentRepository;

    @GetMapping
    public List<TwoACompartment> getAllTwoACompartments() {
        return twoACompartmentRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TwoACompartment> getTwoACompartmentById(@PathVariable Long id) {
        Optional<TwoACompartment> twoACompartment = twoACompartmentRepository.findById(id);
        return twoACompartment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public TwoACompartment createTwoACompartment(@RequestBody TwoACompartment twoACompartment) {
        return twoACompartmentRepository.save(twoACompartment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TwoACompartment> updateTwoACompartment(@PathVariable Long id, @RequestBody TwoACompartment twoACompartmentDetails) {
        return twoACompartmentRepository.findById(id)
                .map(twoACompartment -> {
                    twoACompartment.setTrain(twoACompartmentDetails.getTrain());
                    twoACompartmentRepository.save(twoACompartment);
                    return ResponseEntity.ok(twoACompartment);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteTwoACompartment(@PathVariable Long id) {
//        return twoACompartmentRepository.findById(id)
//                .map(twoACompartment -> {
//                    twoACompartmentRepository.delete(twoACompartment);
//                    return ResponseEntity.noContent().build();
//                })
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
}

