package com.TicketManagement.TrainTicket.service;

import com.TicketManagement.TrainTicket.controller.UserController;
import com.TicketManagement.TrainTicket.dto.Response;
import com.TicketManagement.TrainTicket.entity.Error;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ErrorService {

    private final UserController userController;

    public Error getErrorCode(String message, HttpStatus code) {
        String arr[] = code.toString().split(" ");
        Long errorCode = Long.valueOf(arr[0]);
        String errorStatus = arr[1];
        return setErrors(errorCode, errorStatus, message);
    }

    public Error setErrors(Long errorCode, String errorStatus, String message) {
        Error error = new Error();
        error.setCode(errorCode);
        error.setMessage(message);
        error.setStatus("error");
        error.setRequestedTime(Instant.now().toEpochMilli());
        error.setValidationErrors(errorStatus);
        Response response = new Response();
        response.setUser(userController.getAllUsers());
        error.setResponse(response);

        return error;
    }
}
