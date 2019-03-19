package com.endava.ap.lotery.service.impl;

import com.endava.ap.lotery.dao.ParticipantDao;
import com.endava.ap.lotery.dao.TicketDao;
import com.endava.ap.lotery.model.Participant;
import com.endava.ap.lotery.model.Ticket;
import com.endava.ap.lotery.service.Cashier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CashierImpl implements Cashier {

    @Autowired
    ParticipantDao participantDao;

    @Autowired
    TicketDao ticketDao;

    @Transactional
    public Participant registerParticipant(String firstName, String lastName, String email){
        final Participant participant = new Participant();
        participant.setEmail(email);
        participant.setFirstName(firstName);
        participant.setLastName(lastName);

        participantDao.save(participant);

        return participant;
    }

    @Transactional
    public Ticket buyTicket(List<Integer> integerList, Participant participant) {

        Ticket ticket = new Ticket();
        ticket.setNumber1(integerList.get(0));
        ticket.setNumber2(integerList.get(1));
        ticket.setNumber3(integerList.get(2));
        ticket.setNumber4(integerList.get(3));
        ticket.setNumber5(integerList.get(4));
        ticket.setNumber6(integerList.get(5));

        participant.addTicket(ticket);
        ticketDao.save(ticket);

        return ticket;
    }
}
