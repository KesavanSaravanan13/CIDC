package com.TicketManagement.TrainTicket.controller;

import com.TicketManagement.TrainTicket.dto.TicketDTO;
import com.TicketManagement.TrainTicket.repository.UserRepository;
import com.TicketManagement.TrainTicket.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketDetailsController {

    private final TicketService ticketDetailsService;

    @Autowired
    private UserRepository userDetailsRepository;

    @GetMapping
    public List<TicketDTO> getAllTickets() {
        return ticketDetailsService.getAllTickets();
    }

    @GetMapping("/{ticketNum}")
    public TicketDTO getTicketById(@PathVariable Long ticketNum) {
        return ticketDetailsService.getTicketById(ticketNum);
    }

    @PostMapping
    public void createTicket(@RequestBody TicketDTO TicketDTO) {
        ticketDetailsService.saveTicket(TicketDTO);
    }

    @DeleteMapping("/{ticketNum}")
    public String deleteTicket(@PathVariable Long ticketNum) {
        return ticketDetailsService.deleteTicket(ticketNum);
    }
}

