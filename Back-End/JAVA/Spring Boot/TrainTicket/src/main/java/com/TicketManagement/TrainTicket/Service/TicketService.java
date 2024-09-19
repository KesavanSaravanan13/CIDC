package com.TicketManagement.TrainTicket.Service;

import com.TicketManagement.TrainTicket.Repository.TicketDetailsRepository;
import com.TicketManagement.TrainTicket.Table.TicketDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketDetailsRepository ticketDetailsRepository;

    public List<TicketDetails> getAllTickets() {
        return ticketDetailsRepository.findAll();
    }

    public Optional<TicketDetails> getTicketById(int ticketNum) {
        return ticketDetailsRepository.findById(ticketNum);
    }

    public void saveTicket(TicketDetails ticketDetails) {
        ticketDetailsRepository.save(ticketDetails);
    }

    public void updateTicket(TicketDetails ticketDetails) {
        ticketDetailsRepository.save(ticketDetails);
    }

    public void deleteTicket(int ticketNum) {
        ticketDetailsRepository.deleteById(ticketNum);
    }
}

