package com.TicketManagement.TrainTicket.Controller;

import com.TicketManagement.TrainTicket.Repository.UserRepository;
import com.TicketManagement.TrainTicket.Service.TicketService;
import com.TicketManagement.TrainTicket.Table.TicketDetails;
import com.TicketManagement.TrainTicket.Table.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tickets")
public class TicketDetailsController {

    @Autowired
    private TicketService ticketDetailsService;

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
    public void updateTicket(@RequestBody TicketDetails ticketDetails) {
        ticketDetailsService.updateTicket(ticketDetails);
    }

    @DeleteMapping("/{ticketNum}")
    public void deleteTicket(@PathVariable int ticketNum) {
        ticketDetailsService.deleteTicket(ticketNum);
    }
}

