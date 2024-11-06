package com.TicketManagement.TrainTicket.controller;

import com.TicketManagement.TrainTicket.entity.SlCompartment;
import com.TicketManagement.TrainTicket.repository.SlCompartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sl-compartments")
public class SlCompartmentController {

    @Autowired
    private SlCompartmentRepository slCompartmentRepository;

    @GetMapping
    public List<SlCompartment> getAllSLCompartments() {
        return slCompartmentRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SlCompartment> getSLCompartmentById(@PathVariable Long id) {
        Optional<SlCompartment> SlCompartment = slCompartmentRepository.findById(id);
        return SlCompartment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public SlCompartment createSLCompartment(@RequestBody SlCompartment SlCompartment) {
        return slCompartmentRepository.save(SlCompartment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SlCompartment> updateSLCompartment(@PathVariable Long id, @RequestBody SlCompartment slCompartmentDetails) {
        return slCompartmentRepository.findById(id)
                .map(SlCompartment -> {
                    SlCompartment.setTrain(slCompartmentDetails.getTrain());
                    slCompartmentRepository.save(SlCompartment);
                    return ResponseEntity.ok(SlCompartment);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteSLCompartment(@PathVariable Long id) {
//        return slCompartmentRepository.findById(id)
//                .map(SlCompartment -> {
//                    slCompartmentRepository.delete(SlCompartment);
//                    return ResponseEntity.noContent().build();
//                })
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
}

