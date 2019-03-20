package com.endava.ap.lotery.service;

import java.util.List;

import com.endava.ap.lotery.model.Ticket;

public interface UserService {

    void createUser(String name, String email);

    void addTicket(String email, Ticket ticket);

}
