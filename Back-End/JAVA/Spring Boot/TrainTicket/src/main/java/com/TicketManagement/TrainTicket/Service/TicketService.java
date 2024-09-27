package com.TicketManagement.TrainTicket.service;

import com.TicketManagement.TrainTicket.dto.TicketDTO;
import com.TicketManagement.TrainTicket.entity.TicketDetails;
import com.TicketManagement.TrainTicket.entity.User;
import com.TicketManagement.TrainTicket.repository.TicketDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketDetailsRepository ticketRepo;
    private List<TicketDTO> ticketList;

    public void toDTO() {
        ticketList = new ArrayList<>();
        for (TicketDetails ticket : ticketRepo.findAll()) {
            if (ticket.getStatus() != null && ticket.getStatus().equalsIgnoreCase("Reserved")) {
                ticketList.add(new TicketDTO(ticket.getTicketId(), ticket.getTicketNumber(),
                        ticket.getDateOfBooking(), ticket.getDateOfTravel(),
                        ticket.getTravelTiming(), ticket.getTravelFrom(),
                        ticket.getTravelTo(), ticket.getNoOfDaysTravel(),
                        ticket.getPrebookFood(), ticket.getUser(), ticket.getStatus()));
            }
        }
    }

    public void toEntity(final TicketDTO ticketDetailsDTO) {
        TicketDetails ticketDetails = new TicketDetails();
        ticketDetails.setTicketId(ticketDetailsDTO.getTicketId());
        ticketDetails.setTicketNumber(ticketDetailsDTO.getTicketNumber());
        ticketDetails.setDateOfBooking(ticketDetailsDTO.getDateOfBooking());
        ticketDetails.setDateOfTravel(ticketDetailsDTO.getDateOfTravel());
        ticketDetails.setTravelTiming(ticketDetailsDTO.getTravelTiming());
        ticketDetails.setTravelFrom(ticketDetailsDTO.getTravelFrom());
        ticketDetails.setTravelTo(ticketDetailsDTO.getTravelTo());
        ticketDetails.setNoOfDaysTravel(ticketDetailsDTO.getNoOfDaysTravel());
        ticketDetails.setPrebookFood(ticketDetailsDTO.getPrebookFood());

        User user = new User();
        user.setUserId(ticketDetailsDTO.getUser().getUserId());
        ticketDetails.setUser(user);

        ticketRepo.save(ticketDetails);
    }

    public void saveTicket(final TicketDTO ticket) {
        toDTO();
        new TicketDTO(ticket.getTicketId(), ticket.getTicketNumber(),
                ticket.getDateOfBooking(), ticket.getDateOfTravel(),
                ticket.getTravelTiming(), ticket.getTravelFrom(),
                ticket.getTravelTo(), ticket.getNoOfDaysTravel(),
                ticket.getPrebookFood(), ticket.getUser(), ticket.getStatus());
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

