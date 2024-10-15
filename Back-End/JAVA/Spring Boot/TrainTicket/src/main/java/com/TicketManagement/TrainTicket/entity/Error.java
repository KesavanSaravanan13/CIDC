package com.TicketManagement.TrainTicket.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class Error {
    private Long code;
    private String status;
    private String message;
    private String validationErrors;
    private long requestedTime;

}