package com.TicketManagement.TrainTicket.dto;

import com.TicketManagement.TrainTicket.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    private boolean status;

    public boolean getStatus() {
        return this.status;
    }


}
