package com.TicketManagement.TrainTicket.controller;

import com.TicketManagement.TrainTicket.entity.Seat;
import com.TicketManagement.TrainTicket.repository.SeatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        return seatsRepository.findById(id)
                .map(seat -> {
                    seat.setSeatOneA(seatDetails.isSeatOneA());
                    seat.setSeatTwoA(seatDetails.isSeatTwoA());
                    seat.setSeatThreeA(seatDetails.isSeatThreeA());
                    seat.setSeatFourA(seatDetails.isSeatFourA());
                    seat.setSeatFiveA(seatDetails.isSeatFiveA());
                    seat.setSeatSixA(seatDetails.isSeatSixA());
                    seat.setSeatOneB(seatDetails.isSeatOneB());
                    seat.setSeatTwoB(seatDetails.isSeatTwoB());
                    seat.setSeatThreeB(seatDetails.isSeatThreeB());
                    seat.setSeatFourB(seatDetails.isSeatFourB());
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
