package com.TicketManagement.TrainTicket.service;

import com.TicketManagement.TrainTicket.dto.TicketDTO;
import com.TicketManagement.TrainTicket.entity.TicketDetails;
import com.TicketManagement.TrainTicket.entity.User;
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
    private List<TicketDTO> ticketList;

    public void toDTO() {
        ticketList = new ArrayList<>();
        for (TicketDetails ticket : ticketRepo.findAll()) {
            if (ticket.getStatus() != null && ticket.getStatus().equalsIgnoreCase("Reserved")) {
                TicketDTO ticketObj = new TicketDTO();
                ticketObj.setTicketId(ticket.getTicketId());
                ticketObj.setTicketNumber(ticket.getTicketNumber());
                ticketObj.setDateOfBooking(ticket.getDateOfBooking());
                ticketObj.setPrebookFood(ticket.getPrebookFood());
                ticketObj.setDateOfTravel(ticket.getDateOfTravel());
                ticketObj.setNoOfDaysTravel(ticket.getNoOfDaysTravel());
                ticketObj.setTravelFrom(ticket.getTravelFrom());
                ticketObj.setUser(ticket.getUser());
                ticketObj.setTravelTo(ticket.getTravelTo());
                ticketObj.setStatus(ticket.getStatus());
                ticketObj.setTravelTiming(ticket.getTravelTiming());
                ticketList.add(ticketObj);
            }
        }
    }

    public void toEntity(final TicketDTO ticket) {
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
        ticketDetails.setUser(userRepository.findById(ticket.getUser().getUserId())
                .orElseThrow(() -> new RuntimeException("User not found")));
        ticketRepo.save(ticketDetails);
    }

    public void saveTicket(final TicketDTO ticket) {
        TicketDetails ticketDetails = new TicketDetails();
        ticketDetails.setTicketNumber(ticket.getTicketNumber());
        ticketDetails.setDateOfBooking(ticket.getDateOfBooking());
        ticketDetails.setDateOfTravel(ticket.getDateOfTravel());
        ticketDetails.setTravelTiming(ticket.getTravelTiming());
        ticketDetails.setTravelFrom(ticket.getTravelFrom());
        ticketDetails.setTravelTo(ticket.getTravelTo());
        ticketDetails.setNoOfDaysTravel(ticket.getNoOfDaysTravel());
        ticketDetails.setPrebookFood(ticket.getPrebookFood());
        ticketDetails.setStatus(ticket.getStatus());
        ticketDetails.setUser(userRepository.findById(ticket.getUser().getUserId())
                .orElseThrow(() -> new RuntimeException("User not found")));
        ticketRepo.save(ticketDetails);
    }

    public TicketDTO getTicketById(final Long id) {
        toDTO();
        for (TicketDTO TicketDTO : ticketList) {
            if (TicketDTO.getTicketId().equals(id)) {
                return TicketDTO;
            }
        }
        return null;
    }

    public List<TicketDTO> getAllTickets() {
        toDTO();
        return new ArrayList<>(ticketList);
    }

    public String deleteTicket(final Long ticketId) {
        final String[] str = {""};
        toDTO();
        ticketList.forEach(user -> {
            if (user.getTicketId().equals(ticketId)) {
                user.setStatus("De-Active");
                toEntity(user);
                str[0] = "Deleted";
            } else {
                str[0] = "No Id Matched";
            }
        });
        return str[0];
    }
}

