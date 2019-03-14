package com.endava.ap.lotery.service.impl;

import com.endava.ap.lotery.model.Ticket;
import com.endava.ap.lotery.service.Randomizer;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

public class RandomizerImplTest {

    private final Randomizer randomizer = new RandomizerImpl();

    @Test
    void whenGetWinner() {
        //Given

        //When
        Ticket participant = randomizer.getWinner();

        //Then
        Assert.assertEquals("Winning ticket should have 6 numbers", 6, participant.getNumbers().size());
        participant.getNumbers().forEach(number ->
                Assert.assertThat("All numbers should be between 1 and 50",
                        number,
                        is(both(greaterThan(0)).and(lessThan(51)))));

    }

    @Test
    void whenGetWinnerWithComments() {
        /* Arrange
        Nothing to prepare here since the method does not accept any data in entry and there shouldn't be any specific state
        for it to be processed */

        /* Execute the tested method */
        //when
        Ticket participant = randomizer.getWinner();

        /* Do all the assertions on the expected results and behaviours */
        //then
        Assert.assertEquals("Winning ticket should have 6 numbers", 6, participant.getNumbers().size());
        participant.getNumbers().forEach(number ->
                Assert.assertThat("All numbers should be between 1 and 50",
                        number,
                        is(both(greaterThan(0)).and(lessThan(51)))));


        /* Show the actual result */
        System.out.println(participant.getNumbers());
    }

}
