package com.endava.ap.lotery.service.impl;

import com.endava.ap.lotery.dao.ParticipantDao;
import com.endava.ap.lotery.dao.TicketDao;
import com.endava.ap.lotery.model.Participant;
import com.endava.ap.lotery.service.Cashier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DatabaseInitServiceImpl implements ApplicationListener<ContextRefreshedEvent> {

    private ParticipantDao participantDao;
    private Environment environment;
    private Cashier cashier;
    private TicketDao ticketDao;

    @Autowired
    public DatabaseInitServiceImpl(Environment environment,
                                   Cashier cashier,
                                   TicketDao ticketDao,
                                   ParticipantDao participantDao){
        this.cashier = new CashierImpl();
        this.ticketDao = ticketDao;
        this.participantDao = participantDao;
        this.environment = environment;
    }

    @Transactional
    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        persistWinner();
    }

    private void persistWinner() {
        Participant participant = new Participant();
        participant.setEmail("lotery@endava.com");
        participant.setFirstName("Lucky");
        participant.setLastName("Winner");

        participantDao.saveAndFlush(participant);
        //generate and save participants and tickets
    }

    private void generateParticipants(){
        //write implementation for Participant population
        //use Cashier.registerParticipant
    }

    private void buyTicket(Participant participant){
        //write implementation for buying Tickets
        //use Cashier.buyTicket
    }
}
