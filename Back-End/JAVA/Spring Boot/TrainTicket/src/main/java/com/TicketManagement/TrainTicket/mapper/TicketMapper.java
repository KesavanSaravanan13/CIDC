package com.TicketManagement.TrainTicket.mapper;

import com.TicketManagement.TrainTicket.dto.TicketDTO;
import com.TicketManagement.TrainTicket.entity.TicketDetails;
import com.TicketManagement.TrainTicket.entity.User;

public class TicketMapper {
    public static TicketDTO toDTO(TicketDetails ticketDetails) {
        TicketDTO ticketDetailsDTO = new TicketDTO();
        ticketDetailsDTO.setTicketId(ticketDetails.getTicket_id());
        ticketDetailsDTO.setTicketNumber(ticketDetails.getTicketNumber());
        ticketDetailsDTO.setDateOfBooking(ticketDetails.getDateOfBooking());
        ticketDetailsDTO.setDateOfTravel(ticketDetails.getDateOfTravel());
        ticketDetailsDTO.setTravelTiming(ticketDetails.getTravelTiming());
        ticketDetailsDTO.setTravelFrom(ticketDetails.getTravelFrom());
        ticketDetailsDTO.setTravelTo(ticketDetails.getTravelTo());
        ticketDetailsDTO.setNoOfDaysTravel(ticketDetails.getNoOfDaysTravel());
        ticketDetailsDTO.setPrebookFood(ticketDetails.getPrebookFood());
        ticketDetailsDTO.setUserId(ticketDetails.getUser().getUserId());
        return ticketDetailsDTO;
    }

    public static TicketDetails toEntity(TicketDTO ticketDetailsDTO) {
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
        user.setUserId(ticketDetailsDTO.getUserId());
        ticketDetails.setUser(user);

        return ticketDetails;
    }
}
