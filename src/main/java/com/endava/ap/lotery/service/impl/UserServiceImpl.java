package com.endava.ap.lotery.service.impl;

import java.util.Objects;

import com.endava.ap.lotery.model.Ticket;
import com.endava.ap.lotery.model.User;
import com.endava.ap.lotery.repository.UserRepository;
import com.endava.ap.lotery.service.UserService;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(final String name, final String email) {
        Objects.requireNonNull(email);
        User user = new User(name, email);
        userRepository.addUser(user);
    }

    public void addTicket(final String email, final Ticket ticket) {
        userRepository.getUser(email).getTickets().add(ticket);
    }

}
