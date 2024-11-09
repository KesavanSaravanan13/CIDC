package com.TicketManagement.TrainTicket.controller;

import com.TicketManagement.TrainTicket.entity.WaitingList;
import com.TicketManagement.TrainTicket.repository.WaitingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/waitingList")
@CrossOrigin
public class WaitingListController {

    @Autowired
    private WaitingListRepository waitingListRepository;

    @GetMapping
    public List<WaitingList> getAllWaitingListEntries() {
        return waitingListRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WaitingList> getWaitingListById(@PathVariable Long id) {
        return waitingListRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<WaitingList> createWaitingListEntry(@RequestBody WaitingList waitingList) {
        WaitingList createdWaitingList = waitingListRepository.save(waitingList);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWaitingList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WaitingList> updateWaitingListEntry(@PathVariable Long id, @RequestBody WaitingList waitingList) {
        if (!waitingListRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        waitingList.setWaitingListId(id);
        WaitingList updatedWaitingList = waitingListRepository.save(waitingList);
        return ResponseEntity.ok(updatedWaitingList);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteWaitingListEntry(@PathVariable Long id) {
//        if (!waitingListRepository.existsById(id)) {
//            return ResponseEntity.notFound().build();
//        }
//        waitingListRepository.deleteById(id);
//        return ResponseEntity.noContent().build();
//    }
}

