package com.TicketManagement.TrainTicket.mapper;

import com.TicketManagement.TrainTicket.dto.TicketDTO;
import com.TicketManagement.TrainTicket.entity.TicketDetails;
import com.TicketManagement.TrainTicket.entity.User;
import com.TicketManagement.TrainTicket.repository.TicketDetailsRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Component
@RequiredArgsConstructor
public class TicketMapper {

    private final TicketDetailsRepository ticketRepo;
    private List<TicketDTO> ticketList = new ArrayList<>();

    public void toDTO() {
        for (TicketDetails ticket : ticketRepo.findAll()) {
            if (ticket.getStatus() != null && ticket.getStatus().equalsIgnoreCase("Reserved")) {
                ticketList.add(new TicketDTO(ticket.getTicket_id(), ticket.getTicketNumber(),
                        ticket.getDateOfBooking(), ticket.getDateOfTravel(),
                        ticket.getTravelTiming(), ticket.getTravelFrom(),
                        ticket.getTravelTo(), ticket.getNoOfDaysTravel(),
                        ticket.getPrebookFood(), ticket.getUser(), ticket.getStatus()));
            }
        }
    }

    public void toEntity(TicketDTO ticketDetailsDTO) {
        TicketDetails ticketDetails = new TicketDetails();
        ticketDetails.setTicket_id(ticketDetailsDTO.getTicketId());
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
}
