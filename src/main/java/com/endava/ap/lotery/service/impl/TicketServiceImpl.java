package com.endava.ap.lotery.service.impl;

import com.endava.ap.lotery.TicketType;
import com.endava.ap.lotery.model.Ticket;
import com.endava.ap.lotery.service.TicketService;

public class TicketServiceImpl implements TicketService {

    //Task
    // generate numbers for every type of ticket

    //INSTANT_GAMES  : 10 numbers
    //LOTTO : 15 numbers
    //DAILY : 7 numbers
    //range 0 - 100

    //every ticket should have unique list of numbers

    //write unit tests
    //use repeated test to run the test al least 10 times to validate the uniqueness
    //use parameterized tests to validate the number of numbers for each type
    //use junit5
    //use mockito to mock the ticket Repo
    //all methods should be covered 100%


    /*
    *  Example of how and when to use @Spy
    *
    *  link : https://akcasoy.wordpress.com/2015/02/13/when-to-use-mockito-spy/

    * */

    @Override
    public Ticket generateTicket(TicketType ticketType) {
        //FIXME
        return null;
    }
}
