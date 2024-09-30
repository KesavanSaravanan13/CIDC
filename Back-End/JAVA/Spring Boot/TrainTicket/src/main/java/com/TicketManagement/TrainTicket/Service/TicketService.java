package com.TicketManagement.TrainTicket.service;

import com.TicketManagement.TrainTicket.dto.TicketDTO;
import com.TicketManagement.TrainTicket.entity.TicketDetails;
import com.TicketManagement.TrainTicket.repository.TicketDetailsRepository;
import com.TicketManagement.TrainTicket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final UserRepository userRepository;
    private final TicketDetailsRepository ticketRepo;

    public List<TicketDTO> getTicketDetails() {
        List<TicketDTO> ticketList = new ArrayList<>();
        this.ticketRepo.findAll().forEach(ticket -> {
            if (ticket.getStatus()) {
                TicketDTO ticketDTO = new TicketDTO();
                ticketDTO.setTicketId(ticket.getTicketId());
                ticketDTO.setTicketNumber(ticket.getTicketNumber());
                ticketDTO.setDateOfBooking(ticket.getDateOfBooking());
                ticketDTO.setPrebookFood(ticket.getPrebookFood());
                ticketDTO.setDateOfTravel(ticket.getDateOfTravel());
                ticketDTO.setNoOfDaysTravel(ticket.getNoOfDaysTravel());
                ticketDTO.setTravelFrom(ticket.getTravelFrom());
                ticketDTO.setUser(ticket.getUser());
                ticketDTO.setTravelTo(ticket.getTravelTo());
                ticketDTO.setStatus(ticket.getStatus());
                ticketDTO.setTravelTiming(ticket.getTravelTiming());
                ticketList.add(ticketDTO);
            }
        });
        return ticketList;
    }

    public void saveTicket(final TicketDTO ticket) {
        getTicketDetails();
        TicketDetails ticketDetails = new TicketDetails();
        ticketDetails.setTicketId(ticket.getTicketId());
        ticketDetails.setTicketNumber(ticket.getTicketNumber());
        ticketDetails.setDateOfBooking(ticket.getDateOfBooking());
        ticketDetails.setDateOfTravel(ticket.getDateOfTravel());
        ticketDetails.setTravelTiming(ticket.getTravelTiming());
        ticketDetails.setTravelFrom(ticket.getTravelFrom());
        ticketDetails.setTravelTo(ticket.getTravelTo());
        ticketDetails.setNoOfDaysTravel(ticket.getNoOfDaysTravel());
        ticketDetails.setPrebookFood(ticket.getPrebookFood());
        ticketDetails.setStatus(ticket.getStatus());
        ticketDetails.setUser(this.userRepository.findById(ticket.getUser().getUserId())
                .orElseThrow(() -> new RuntimeException("User not found")));
        this.ticketRepo.save(ticketDetails);
    }

    public TicketDTO getTicketById(final Long id) {
        return getTicketDetails().stream().filter(ticketDTO ->
                ticketDTO.getTicketId().equals(id)
        ).findFirst().orElseThrow(()->new RuntimeException("Not Found"));
    }

    public List<TicketDTO> getAllTickets() {
        return new ArrayList<>(getTicketDetails());
    }

    public String deleteTicket(final Long ticketId) {
        final String[] str = {""};
        str[0] = "No Id Matched";
        getTicketDetails().forEach(user -> {
            if (user.getTicketId().equals(ticketId)) {
                user.setStatus(false);
                saveTicket(user);
                str[0] = "Deleted";
            }
        });
        return str[0];
    }
}

