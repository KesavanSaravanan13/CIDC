package com.TicketManagement.TrainTicket.service;

import com.TicketManagement.TrainTicket.dto.Response;
import com.TicketManagement.TrainTicket.entity.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ErrorService {

    public ResponseMessage getErrorCode(String message, HttpStatus code) {
        String[] arr = code.toString().split(" ");
        Long errorCode = Long.valueOf(arr[0]);
        String errorStatus = arr[1];
        return setErrors(errorCode, errorStatus, message);
    }

    public ResponseMessage setErrors(Long errorCode, String errorStatus, String message) {
        ResponseMessage ResponseMessage = new ResponseMessage();
        ResponseMessage.setCode(errorCode);
        ResponseMessage.setMessage(message);
        ResponseMessage.setStatus("error");
        ResponseMessage.setRequestedTime(Instant.now().toEpochMilli());
        ResponseMessage.setValidationErrors(errorStatus);
        Response response = new Response();
        ResponseMessage.setResponse(null);

        return ResponseMessage;
    }
}
