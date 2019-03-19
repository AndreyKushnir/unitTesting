package com.endava.ap.lotery.service.impl;

import com.endava.ap.lotery.dao.ParticipantDao;
import com.endava.ap.lotery.model.Participant;
import com.endava.ap.lotery.model.Ticket;
import com.endava.ap.lotery.service.Randomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Random;

@Service
public class RandomizerImpl implements Randomizer {

    @Autowired
    ParticipantDao participantDao;

    //The winning participant is always the one with ID = 1
    @Transactional
    public Ticket getWinner() {
        Random rand = new Random();
        Participant winningParticipant = participantDao.findById(1L).get();
        Ticket winner = new Ticket();
        int[] winningNumbers = new int[6];
        for (int i = 0; i < 6; i++) {
            int value = rand.nextInt(50) + 1;
            winningNumbers[i] = value;
        }
        winner.setNumber1(winningNumbers[0]);
        winner.setNumber2(winningNumbers[1]);
        winner.setNumber3(winningNumbers[2]);
        winner.setNumber4(winningNumbers[3]);
        winner.setNumber5(winningNumbers[4]);
        winner.setNumber6(winningNumbers[5]);

        winningParticipant.addTicket(winner);
        participantDao.saveAndFlush(winningParticipant);

        return winner;
    }
}
