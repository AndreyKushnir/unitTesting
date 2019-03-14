package com.endava.ap.lotery.service;

import com.endava.ap.lotery.model.Ticket;

import java.util.List;

public interface Cashier {

    Ticket buyTicket(List<Integer> integerList);
}
