package com.TicketManagement.TrainTicket.exception;


import com.TicketManagement.TrainTicket.entity.ResponseMessage;
import com.TicketManagement.TrainTicket.service.ErrorService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalException {

    private final ErrorService errorService;

    @ExceptionHandler({
            ExpiredJwtException.class,
            UserNotFoundException.class,
            NotAuthenticatedException.class,
            NotAnActiveUserException.class,
            NoIdMatchedException.class,
            InputMantatoryException.class,
            PasswordNotMatchException.class,
            InvalidUserException.class,
            NoDataFoundException.class
    })
    public ResponseEntity<?> handleTokenNotFoundException(Exception ex) {
        ResponseMessage errorMessage = errorService.getErrorCode(ex.getMessage(),HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
