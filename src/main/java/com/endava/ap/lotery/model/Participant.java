package com.endava.ap.lotery.model;

import java.util.ArrayList;
import java.util.List;

public class Participant {

    private List<Integer> ticket = new ArrayList<>();

    public Participant() {
    }

    public void addNumber(Integer number){
        this.ticket.add(number);
    }

    public Integer getNumber(Integer index){
        return this.ticket.get(index);
    }

    public List<Integer> getTicket(){
        return this.ticket;
    }
}
