package com.endava.ap.lotery.service.impl;

import com.endava.ap.lotery.model.Ticket;
import com.endava.ap.lotery.service.Randomizer;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomizerImpl implements Randomizer {

    public Ticket getWinner() {
        Random rand = new Random();
        Ticket winner = new Ticket();
        for (int i = 0; i < 6; i++) {
            int value = rand.nextInt(50) + 1;
            winner.addNumber(value);
        }
        return winner;
    }
}
