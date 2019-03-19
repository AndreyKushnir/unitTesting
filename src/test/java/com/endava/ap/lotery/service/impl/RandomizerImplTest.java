package com.endava.ap.lotery.service.impl;

import com.endava.ap.lotery.dao.ParticipantDao;
import com.endava.ap.lotery.model.Participant;
import com.endava.ap.lotery.model.Ticket;
import com.endava.ap.lotery.service.Randomizer;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.sql.ResultSet;
import java.util.List;

import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

@SpringBootTest
public class RandomizerImplTest extends AbstractTestDatabaseInit {

    @Autowired
    ParticipantDao participantDao;

    @Autowired
    Randomizer randomizer;

    @PersistenceContext
    EntityManager entityManager;

    @Test
    void whenGetWinner() {
        //Given

        //When
//        Ticket participant = randomizer.getWinner();
//
//        //Then
//        Assert.assertEquals("Winning ticket should have 6 numbers", 6, participant.getNumbers().size());
//        participant.getNumbers().forEach(number ->
//                Assert.assertThat("All numbers should be between 1 and 50",
//                        number,
//                        is(both(greaterThan(0)).and(lessThan(51)))));

    }

    @Test
    void whenGetWinnerWithComments() {
        /* Given
        Nothing to prepare here since the method does not accept any data in entry and there shouldn't be any specific state
        for it to be processed */

        /* Call the tested method */
        //when
        Ticket winner = randomizer.getWinner();

        /* Do all the assertions on the expected results and behaviours */
        //then
        Assert.assertNotNull("Winning ticket should have 6 numbers", winner.getNumber1());
        Assert.assertNotNull("Winning ticket should have 6 numbers", winner.getNumber2());
        Assert.assertNotNull("Winning ticket should have 6 numbers", winner.getNumber3());
        Assert.assertNotNull("Winning ticket should have 6 numbers", winner.getNumber4());
        Assert.assertNotNull("Winning ticket should have 6 numbers", winner.getNumber5());
        Assert.assertNotNull("Winning ticket should have 6 numbers", winner.getNumber6());
        Assert.assertThat("All numbers should be between 1 and 50",
                winner.getNumber1(),
                is(both(greaterThan(0)).and(lessThan(51))));
        Assert.assertThat("All numbers should be between 1 and 50",
                winner.getNumber2(),
                is(both(greaterThan(0)).and(lessThan(51))));
        Assert.assertThat("All numbers should be between 1 and 50",
                winner.getNumber3(),
                is(both(greaterThan(0)).and(lessThan(51))));
        Assert.assertThat("All numbers should be between 1 and 50",
                winner.getNumber4(),
                is(both(greaterThan(0)).and(lessThan(51))));
        Assert.assertThat("All numbers should be between 1 and 50",
                winner.getNumber5(),
                is(both(greaterThan(0)).and(lessThan(51))));
        Assert.assertThat("All numbers should be between 1 and 50",
                winner.getNumber6(),
                is(both(greaterThan(0)).and(lessThan(51))));


        /* Show the actual result */
        System.out.println(winner.getNumber1());
        System.out.println(winner.getNumber2());
        System.out.println(winner.getNumber3());
        System.out.println(winner.getNumber4());
        System.out.println(winner.getNumber5());
        System.out.println(winner.getNumber6());

        JdbcTemplate jdbcTemplate = extractJdbcTemplate();
        Long ticketId = jdbcTemplate.queryForObject("Select id From t_ticket where number1 = " + winner.getNumber1().toString(), Long.class);

        System.out.println(ticketId);
    }

}
