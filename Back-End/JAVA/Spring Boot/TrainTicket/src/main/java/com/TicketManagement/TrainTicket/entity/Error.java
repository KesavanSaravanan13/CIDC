package com.TicketManagement.TrainTicket.entity;


import com.TicketManagement.TrainTicket.controller.UserController;
import com.TicketManagement.TrainTicket.dto.Response;
import com.TicketManagement.TrainTicket.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Component
public class Error {
    private Long code;
    private String status;
    private String message;
    private String validationErrors;
    private long requestedTime;
    private Response response;

}