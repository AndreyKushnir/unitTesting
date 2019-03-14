package com.endava.ap.lotery.service.impl;

import com.endava.ap.lotery.model.Ticket;
import com.endava.ap.lotery.service.Cashier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CashierImpl implements Cashier {

    public Ticket buyTicket(List<Integer> integerList) {
        return new Ticket();
    }
}
