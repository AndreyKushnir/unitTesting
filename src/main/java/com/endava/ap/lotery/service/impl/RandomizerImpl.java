package com.endava.ap.lotery.service.impl;

import com.endava.ap.lotery.model.Participant;

import java.util.Random;

public class RandomizerImpl {

    public Participant getWinner() {
        Random rand = new Random();
        Participant winner = new Participant();
        for (int i = 0; i < 6; i++) {
            int value = rand.nextInt(50) + 1;
            winner.addNumber(value);
        }
        return winner;
    }
}
