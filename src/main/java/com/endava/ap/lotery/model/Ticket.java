package com.endava.ap.lotery.model;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.endava.ap.lotery.TicketType;

public class Ticket {

    private final static LocalDateTime XPRY_DATE = LocalDateTime.now().with(DayOfWeek.SATURDAY).toLocalDate().atStartOfDay();

    private final static LocalDateTime BUY_TIME = LocalDateTime.now();

    private List<Integer> numbers = new ArrayList<>();

    private TicketType ticketType;

    public Ticket() {
    }

    public Ticket(TicketType ticketType,Integer... numbers) {
        this.numbers = Arrays.asList(numbers);
    }

    public void addNumber(Integer number) {
        this.numbers.add(number);
    }

    public Integer getNumber(Integer index) {
        return this.numbers.get(index);
    }

    public List<Integer> getNumbers() {
        return this.numbers;
    }

    public boolean isValid() {
        return LocalDateTime.now().compareTo(XPRY_DATE) > 0;
    }

    public static LocalDateTime getXpryDate() {
        return XPRY_DATE;
    }

    public static LocalDateTime getBuyTime() {
        return BUY_TIME;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(final TicketType ticketType) {
        this.ticketType = ticketType;
    }
}
