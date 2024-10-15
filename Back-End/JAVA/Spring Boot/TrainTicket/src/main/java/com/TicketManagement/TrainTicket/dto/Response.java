package com.TicketManagement.TrainTicket.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
public class Response {
    private List<UserDTO> user;
}