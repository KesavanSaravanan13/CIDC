package com.TicketManagement.TrainTicket.controller;

import com.TicketManagement.TrainTicket.entity.Seat;
import com.TicketManagement.TrainTicket.repository.SeatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Seat")
public class SeatController {

    @Autowired
    private SeatsRepository seatsRepository;

    @GetMapping
    public List<Seat> getAllSeats() {
        return seatsRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seat> getSeatById(@PathVariable Long id) {
        Optional<Seat> seat = seatsRepository.findById(id);
        return seat.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Seat createSeat(@RequestBody Seat seat) {
        return seatsRepository.save(seat);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Seat> updateSeat(@PathVariable Long id, @RequestBody Seat seatDetails) {
        return seatsRepository.findById(id)
                .map(seat -> {
                    seat.setOneA(seatDetails.isOneA());
                    seat.setTwoA(seatDetails.isTwoA());
                    seat.setThreeA(seatDetails.isThreeA());
                    seat.setFourA(seatDetails.isFourA());
                    seat.setFiveA(seatDetails.isFiveA());
                    seat.setOneB(seatDetails.isOneB());
                    seat.setTwoB(seatDetails.isTwoB());
                    seat.setThreeB(seatDetails.isThreeB());
                    seatsRepository.save(seat);
                    return ResponseEntity.ok(seat);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteSeat(@PathVariable Long id) {
//        return seatsRepository.findById(id)
//                .map(seat -> {
//                    seatsRepository.delete(seat);
//                    return ResponseEntity.noContent().build();
//                })
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
}
