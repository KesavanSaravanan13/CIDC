package com.TicketManagement.TrainTicket.service;

import com.TicketManagement.TrainTicket.entity.TicketDetails;
import com.TicketManagement.TrainTicket.repository.TicketDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketDetailsRepository ticketDetailsRepository;

    public List<TicketDetails> getAllTickets() {
        return ticketDetailsRepository.findAll();
    }

    public Optional<TicketDetails> getTicketById(int ticketNum) {
        return ticketDetailsRepository.findById(ticketNum);
    }

    public void saveTicket(TicketDetails ticketDetails) {
        ticketDetailsRepository.save(ticketDetails);
    }

    public void updateTicket(int id, TicketDetails ticketDetails) {
        ticketDetailsRepository.findById(id)
                .map(ticketDetails1 -> {
                    ticketDetails1.setUser(ticketDetails.getUser());
                    ticketDetails1.setDateOfBooking(ticketDetails.getDateOfBooking());
                    ticketDetails1.setPrebookFood(ticketDetails.getPrebookFood());
                    ticketDetails1.setTravelFrom(ticketDetails.getTravelFrom());
                    ticketDetails1.setTravelTo(ticketDetails.getTravelTo());
                    ticketDetails1.setDateOfTravel(ticketDetails.getDateOfTravel());
                    ticketDetails1.setTravelTiming(ticketDetails.getTravelTiming());
                    ticketDetails1.setNoOfDaysTravel(ticketDetails.getNoOfDaysTravel());

                    return ticketDetailsRepository.save(ticketDetails1);
                });
    }

    public void deleteTicket(int ticketNum) {
        ticketDetailsRepository.deleteById(ticketNum);
    }
}

