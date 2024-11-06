package com.TicketManagement.TrainTicket.controller;

import com.TicketManagement.TrainTicket.entity.ThreeACompartment;
import com.TicketManagement.TrainTicket.repository.ThreeACompartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/3a-compartments")
public class ThreeACompartmentController {

    @Autowired
    private ThreeACompartmentRepository threeACompartmentRepository;

    @GetMapping
    public List<ThreeACompartment> getAllThreeACompartments() {
        return threeACompartmentRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ThreeACompartment> getThreeACompartmentById(@PathVariable Long id) {
        Optional<ThreeACompartment> threeACompartment = threeACompartmentRepository.findById(id);
        return threeACompartment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ThreeACompartment createThreeACompartment(@RequestBody ThreeACompartment threeACompartment) {
        return threeACompartmentRepository.save(threeACompartment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ThreeACompartment> updateThreeACompartment(@PathVariable Long id, @RequestBody ThreeACompartment threeACompartmentDetails) {
        return threeACompartmentRepository.findById(id)
                .map(threeACompartment -> {
                    threeACompartment.setTrain(threeACompartmentDetails.getTrain());
                    threeACompartmentRepository.save(threeACompartment);
                    return ResponseEntity.ok(threeACompartment);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteThreeACompartment(@PathVariable Long id) {
//        return threeACompartmentRepository.findById(id)
//                .map(threeACompartment -> {
//                    threeACompartmentRepository.delete(threeACompartment);
//                    return ResponseEntity.noContent().build();
//                })
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
}

