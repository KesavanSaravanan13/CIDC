package com.TicketManagement.TrainTicket.exception;

public class NotAnActiveUserException extends RuntimeException {
    public NotAnActiveUserException() {
    }

    public NotAnActiveUserException(String message) {
        super(message);
    }

    public NotAnActiveUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotAnActiveUserException(Throwable cause) {
        super(cause);
    }

    public NotAnActiveUserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
