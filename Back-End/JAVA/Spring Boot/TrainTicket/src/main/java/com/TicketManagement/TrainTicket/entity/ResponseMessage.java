package com.TicketManagement.TrainTicket.entity;


import com.TicketManagement.TrainTicket.dto.Response;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class ResponseMessage {
    private Long code;
    private String status;
    private String message;
    private String validationErrors;
    private long requestedTime;

    private Response response;

}