package com.endava.ap.lotery.service.impl;

import com.endava.ap.lotery.dao.ParticipantDao;
import com.endava.ap.lotery.model.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DatabaseInitServiceImpl implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    public ParticipantDao participantDao;

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

        participantDao.save(participant);
    }
}
