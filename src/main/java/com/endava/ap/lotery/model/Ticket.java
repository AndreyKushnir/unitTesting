package com.endava.ap.lotery.model;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Ticket {

    private List<Integer> numbers = new ArrayList<>();
    private final static LocalDateTime XPRY_DATE = LocalDateTime.now().with(DayOfWeek.SATURDAY).toLocalDate().atStartOfDay();
    private final static LocalDateTime BUY_TIME = LocalDateTime.now();

    public Ticket() {
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

    public static LocalDateTime getXpryDate() {
        return XPRY_DATE;
    }

    public static LocalDateTime getBuyTime() {
        return BUY_TIME;
    }

    public boolean isValid() {
        return LocalDateTime.now().compareTo(XPRY_DATE) > 0;
    }
}
