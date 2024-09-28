package com.TicketManagement.TrainTicket.controller;

import com.TicketManagement.TrainTicket.dto.TicketDTO;
import com.TicketManagement.TrainTicket.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketDetailsController {

    private final TicketService ticketDetailsService;

    @GetMapping
    public List<TicketDTO> getAllTickets() {
        return this.ticketDetailsService.getAllTickets();
    }

    @GetMapping("/{ticketNum}")
    public TicketDTO getTicketById(@PathVariable final Long ticketNum) {
        return this.ticketDetailsService.getTicketById(ticketNum);
    }

    @PostMapping
    public void createTicket(@RequestBody final TicketDTO TicketDTO) {
        this.ticketDetailsService.saveTicket(TicketDTO);
    }

    @DeleteMapping("/{ticketNum}")
    public String deleteTicket(@PathVariable final Long ticketNum) {
        return this.ticketDetailsService.deleteTicket(ticketNum);
    }
}

