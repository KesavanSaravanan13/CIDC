package com.TicketManagement.TrainTicket.dto;

import com.TicketManagement.TrainTicket.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class TicketDTO {

    private Long ticketId;
    private Long ticketNumber;
    private LocalDate dateOfBooking;
    private LocalDate dateOfTravel;
    private LocalTime travelTiming;
    private String travelFrom;
    private String travelTo;
    private Integer noOfDaysTravel;
    private Boolean prebookFood;
    private User user;

    public TicketDTO(Long ticketId, Long ticketNumber,
                     LocalDate dateOfBooking, LocalDate dateOfTravel,
                     LocalTime travelTiming, String travelFrom,
                     String travelTo, Integer noOfDaysTravel,
                     Boolean prebookFood, User userId) {
        this.ticketId = ticketId;
        this.ticketNumber = ticketNumber;
        this.dateOfBooking = dateOfBooking;
        this.dateOfTravel = dateOfTravel;
        this.travelTiming = travelTiming;
        this.travelFrom = travelFrom;
        this.travelTo = travelTo;
        this.noOfDaysTravel = noOfDaysTravel;
        this.prebookFood = prebookFood;
        this.user = userId;
    }
}
