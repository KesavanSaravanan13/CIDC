package com.TicketManagement.TrainTicket.dto;

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
    private Long userId;


}
