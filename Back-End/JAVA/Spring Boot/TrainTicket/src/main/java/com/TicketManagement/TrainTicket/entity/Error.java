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
//
//    @Override
//    public String toString() {
//        return "{" +
//                "\"code\": " + code + ",\n" +
//                "\"status\": \"" + status + "\",\n" +
//                "\"message\": \"" + message + "\",\n" +
//                "\"validationErrors\": \"" + validationErrors + "\",\n" +
//                "\"requestedTime\": " + requestedTime + ",\n" +
//                "\"response\": " + response + "\n" +
//                "}";
//    }
//

    private Response response;

}