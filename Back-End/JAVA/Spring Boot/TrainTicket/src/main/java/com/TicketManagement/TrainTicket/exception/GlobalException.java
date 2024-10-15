package com.TicketManagement.TrainTicket.exception;


import com.TicketManagement.TrainTicket.entity.Error;
import com.TicketManagement.TrainTicket.service.ErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalException {

    private final ErrorService errorService;

    @ExceptionHandler(TokenNotFoundException.class)
    public ResponseEntity<String> handleTokenNotFoundException(TokenNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleTokenNotFoundException(UserNotFoundException ex) {
        Error errorMessage = errorService.getErrorCode(ex.getMessage(),HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotAuthenticatedException.class)
    public ResponseEntity<?> handleTokenNotFoundException(NotAuthenticatedException ex) {
        Error errorMessage = errorService.getErrorCode(ex.getMessage(),HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotAnActiveUserException.class)
    public ResponseEntity<?> handleTokenNotFoundException(NotAnActiveUserException ex) {
        Error errorMessage = errorService.getErrorCode(ex.getMessage(),HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoIdMatchedException.class)
    public ResponseEntity<?> handleTokenNotFoundException(NoIdMatchedException ex) {
        Error errorMessage = errorService.getErrorCode(ex.getMessage(),HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

}
