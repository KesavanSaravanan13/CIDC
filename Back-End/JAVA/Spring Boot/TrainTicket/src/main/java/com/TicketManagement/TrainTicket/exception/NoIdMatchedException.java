package com.TicketManagement.TrainTicket.exception;

public class NoIdMatchedException extends RuntimeException {
    public NoIdMatchedException() {
    }

    public NoIdMatchedException(String message) {
        super(message);
    }

    public NoIdMatchedException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoIdMatchedException(Throwable cause) {
        super(cause);
    }

    public NoIdMatchedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
