package com.endava.ap.lotery.service.impl;

import com.endava.ap.lotery.dao.TicketDao;
import com.endava.ap.lotery.service.Cashier;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

public class StatisticsGeneratorImpl {

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
