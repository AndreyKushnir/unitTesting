package com.endava.ap.lotery.service.impl;

import com.endava.ap.lotery.dao.TicketDao;
import com.endava.ap.lotery.service.Cashier;
import com.endava.ap.lotery.service.StatisticsGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

//use DatabaseInitServiceImpl to generate participants
//use those Participants to buy Tickets
//do that by wiring the CashierImplService into the DatabaseInitServiceImpl which loads on Context/Application Startup
//write this service to do analysis on which number/participant is winning
//use creativity (Big Data?)

public class StatisticsGeneratorImpl implements StatisticsGenerator {

    @Autowired
    Cashier cashier = new CashierImpl();

    @Autowired
    TicketDao ticketDao;

//    List<LocalDate> showNumberHistory(Integer number){
//
//    }
//
//    List<LocalDate> showWinnerHistoryByFirstName(String firstName){}
//
//    List<LocalDate> showWinnerHistoryByEmail(String email){}

}
