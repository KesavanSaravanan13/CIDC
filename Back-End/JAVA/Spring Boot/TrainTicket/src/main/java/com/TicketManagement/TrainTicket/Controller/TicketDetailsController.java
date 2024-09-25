package com.TicketManagement.TrainTicket.controller;

import com.TicketManagement.TrainTicket.entity.TicketDetails;
import com.TicketManagement.TrainTicket.repository.UserRepository;
import com.TicketManagement.TrainTicket.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketDetailsController {

    private final TicketService ticketDetailsService;

    @Autowired
    private UserRepository userDetailsRepository;

    @GetMapping
    public List<TicketDetails> getAllTickets() {
        return ticketDetailsService.getAllTickets();
    }

    @GetMapping("/{ticketNum}")
    public Optional<TicketDetails> getTicketById(@PathVariable int ticketNum) {
        return ticketDetailsService.getTicketById(ticketNum);
    }

    @PostMapping
    public void createTicket(@RequestBody TicketDetails ticketDetails) {
        ticketDetailsService.saveTicket(ticketDetails);
    }

    @PutMapping("/{ticketNum}")
    public void updateTicket(@PathVariable int ticketNum, @RequestBody TicketDetails ticketDetails) {
        ticketDetailsService.updateTicket(ticketNum, ticketDetails);
    }

    @DeleteMapping("/{ticketNum}")
    public void deleteTicket(@PathVariable int ticketNum) {
        ticketDetailsService.deleteTicket(ticketNum);
    }
}

