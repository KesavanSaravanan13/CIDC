package com.TicketManagement.TrainTicket.exception;

public class InputMantatoryException extends RuntimeException {

    public InputMantatoryException() {
    }

    public InputMantatoryException(String message) {
        super(message);
    }

    public InputMantatoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public InputMantatoryException(Throwable cause) {
        super(cause);
    }

    public InputMantatoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}