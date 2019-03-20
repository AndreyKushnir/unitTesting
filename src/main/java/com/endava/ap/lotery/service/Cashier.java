package com.endava.ap.lotery.service;

import com.endava.ap.lotery.exception.InvalidTicketNumberException;
import com.endava.ap.lotery.model.Participant;
import com.endava.ap.lotery.model.Ticket;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Cashier {

    Participant registerParticipant(String firstName, String lastName, String email);

    Ticket buyTicket(List<Integer> integerList, Participant participant) throws InvalidTicketNumberException;
}
