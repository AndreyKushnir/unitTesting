package com.endava.ap.lotery.service.impl;

import com.endava.ap.lotery.dao.ParticipantDao;
import com.endava.ap.lotery.dao.TicketDao;
import com.endava.ap.lotery.exception.InvalidTicketNumberException;
import com.endava.ap.lotery.model.Participant;
import com.endava.ap.lotery.model.Ticket;
import com.endava.ap.lotery.service.Cashier;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CashierImplTest {

    @PersistenceContext
    EntityManager entityManager;

    @InjectMocks
    private Cashier cashier = new CashierImpl();

    @Mock
    public TicketDao ticketDao;

    @Mock
    public ParticipantDao participantDao;

    private static final String FIRSTNAME = "first";
    private static final String LASTNAME = "last";
    private static final String EMAIL = "firstlast@gmail.com";

    class TicketCompare implements Comparator<Ticket> {
        @Override
        public int compare(Ticket comparableTicket, Ticket comparedTicked) {
            int result = 0;

            if (comparableTicket.getNumber1().equals(comparedTicked.getNumber1())) {
                result = 0;
            }
            if (comparableTicket.getNumber2().equals(comparedTicked.getNumber2()) && result == 0) {
                result = 0;
            }
            if (comparableTicket.getNumber3().equals(comparedTicked.getNumber3()) && result == 0) {
                result = 0;
            }
            if (comparableTicket.getNumber4().equals(comparedTicked.getNumber4()) && result == 0) {
                result = 0;
            }
            if (comparableTicket.getNumber5().equals(comparedTicked.getNumber5()) && result == 0) {
                result = 0;
            }
            if (comparableTicket.getNumber6().equals(comparedTicked.getNumber6()) && result == 0) {
                result = 0;
            }

            return result;
        }
    }

    private final TicketCompare ticketCompare = new TicketCompare();

    @Test
    void whenRegisterParticipantBasicHappyPath() {
        //Arrange
        Participant registeredParticipant = new Participant();
        registeredParticipant.setId(1L);
        registeredParticipant.setFirstName(FIRSTNAME);
        registeredParticipant.setLastName(LASTNAME);
        registeredParticipant.setEmail(EMAIL);

        ArgumentCaptor<Participant> argumentCaptor = ArgumentCaptor.forClass(Participant.class);

        when(participantDao.save(argumentCaptor.capture())).thenReturn(registeredParticipant);

        //Act
        registeredParticipant = cashier.registerParticipant(FIRSTNAME, LASTNAME, EMAIL);

        //Assert
        verify(participantDao, times(1)).save(any());

    }

    @Test
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    void buyTicket() {
        //Arrange
        Participant participant = new Participant();
        participant.setFirstName(FIRSTNAME);
        participant.setLastName(LASTNAME);
        participant.setEmail(EMAIL);

        ArgumentCaptor<Participant> participantArgumentCaptor = ArgumentCaptor.forClass(Participant.class);

        when(participantDao.save(participantArgumentCaptor.capture())).thenReturn(participant);
        Participant registeredParticipant = cashier.registerParticipant(FIRSTNAME, LASTNAME, EMAIL);
        participantDao.saveAndFlush(participantArgumentCaptor.capture());

        verify(participantDao, times(1)).save(any());

        List<Integer> chosenNumbers = new ArrayList<>();
        chosenNumbers.add(4);
        chosenNumbers.add(12);
        chosenNumbers.add(7);
        chosenNumbers.add(33);
        chosenNumbers.add(45);
        chosenNumbers.add(1);

        Ticket expectedTicket = new Ticket();
        expectedTicket.setId(1L);
        expectedTicket.setNumber1(4);
        expectedTicket.setNumber2(12);
        expectedTicket.setNumber3(7);
        expectedTicket.setNumber4(33);
        expectedTicket.setNumber5(45);
        expectedTicket.setNumber6(1);

        ArgumentCaptor<Ticket> ticketArgumentCaptor = ArgumentCaptor.forClass(Ticket.class);

        when(ticketDao.save(ticketArgumentCaptor.capture())).thenReturn(expectedTicket);

        //Act
        Ticket boughtTicket = cashier.buyTicket(chosenNumbers, registeredParticipant);

        //Assert
        verify(ticketDao, times(1)).save(ticketArgumentCaptor.capture());

        Assert.assertNotNull("Ticket should not be null", boughtTicket);
        Assert.assertTrue("Ticket should be valid", boughtTicket.isValid());

        Assert.assertEquals("ticket should be as setup", 0, ticketCompare.compare(expectedTicket,boughtTicket));
    }
    
    //    @Test(expected = InvalidTicketNumberException.class)
    @Test
    void whenBuyTicket_WithIncorrectNumber_ExpectException() {
        //Arrange
        Participant participant = new Participant();
        participant.setFirstName(FIRSTNAME);
        participant.setLastName(LASTNAME);
        participant.setEmail(EMAIL);

        ArgumentCaptor<Participant> participantArgumentCaptor = ArgumentCaptor.forClass(Participant.class);

        when(participantDao.save(participantArgumentCaptor.capture())).thenReturn(participant);
        Participant registeredParticipant = cashier.registerParticipant(FIRSTNAME, LASTNAME, EMAIL);
        participantDao.saveAndFlush(participantArgumentCaptor.capture());

        verify(participantDao, times(1)).save(any());

        List<Integer> chosenNumbers = new ArrayList<>();
        chosenNumbers.add(4);
        chosenNumbers.add(12);
        chosenNumbers.add(7);
        chosenNumbers.add(33);
        chosenNumbers.add(55);
        chosenNumbers.add(1);

        Ticket ticket = new Ticket();
        ticket.setId(1L);
        ticket.setNumber1(4);
        ticket.setNumber2(12);
        ticket.setNumber3(7);
        ticket.setNumber4(33);
        ticket.setNumber5(55);
        ticket.setNumber6(1);

        ArgumentCaptor<Ticket> ticketArgumentCaptor = ArgumentCaptor.forClass(Ticket.class);

        Ticket boughtTicket = null;

        when(ticketDao.save(ticketArgumentCaptor.capture())).thenReturn(ticket);

        //Act
        InvalidTicketNumberException thrown = Assertions.assertThrows(InvalidTicketNumberException.class,
                () -> {
                    cashier.buyTicket(chosenNumbers, registeredParticipant);
                }
        );

        //Assert
        verify(ticketDao, times(0)).save(ticketArgumentCaptor.capture());
        Assert.assertNull("Ticket should be null", boughtTicket);
        Assertions.assertTrue(thrown.getMessage().contains("Numbers should be between 1 and 50"));

    }

}
