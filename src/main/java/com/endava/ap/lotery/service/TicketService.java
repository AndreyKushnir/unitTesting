package com.endava.ap.lotery.service;

import com.endava.ap.lotery.TicketType;
import com.endava.ap.lotery.model.Ticket;

public interface TicketService {


    Ticket generateTicket(TicketType ticketType);
}
