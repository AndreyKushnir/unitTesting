package com.endava.ap.lotery.service.impl;

import com.endava.ap.lotery.model.Ticket;
import com.endava.ap.lotery.service.Cashier;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.List;

public class CashierImplTest {

    private Cashier cashier = new CashierImpl();
    private Ticket ticket;

    @Test
    void buyTicket() {
        //Arrange
        List<Integer> chosenNumbers = new ArrayList<>();
        chosenNumbers.add(4);
        chosenNumbers.add(12);
        chosenNumbers.add(7);
        chosenNumbers.add(33);
        chosenNumbers.add(45);
        chosenNumbers.add(1);

        //Execute
        ticket = cashier.buyTicket(chosenNumbers);

        //Assert
        Assert.assertTrue("Ticket should be valid", ticket.isValid());
        for (int i = 0; i < 6; i++) {
            Assert.assertEquals("Numbers should be the same as in test", chosenNumbers.get(i), ticket.getNumber(i));
        }
    }
}
