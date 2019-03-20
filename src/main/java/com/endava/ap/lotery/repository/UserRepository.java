package com.endava.ap.lotery.repository;

import com.endava.ap.lotery.model.User;

public interface UserRepository {

    void addUser(final User user);

    User getUser(final String email);
}
