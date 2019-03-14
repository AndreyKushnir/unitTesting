package com.endava.ap.lotery.model;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Participant {

    private List<Ticket> ticketList = new ArrayList<>();

    public Participant() {
    }

    void addTicket(Ticket ticket) {
        this.ticketList.add(ticket);
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

}
