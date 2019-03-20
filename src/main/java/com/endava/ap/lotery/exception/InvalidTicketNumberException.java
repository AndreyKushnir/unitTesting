package com.endava.ap.lotery.exception;

public class InvalidTicketNumberException extends RuntimeException {
    public InvalidTicketNumberException() {
        super();
    }

    public InvalidTicketNumberException(String s) {
        super(s);
    }
}
