package com.endava.ap.lotery.repository;

import java.util.HashMap;
import java.util.Map;

import com.endava.ap.lotery.model.User;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static Map<String, User> users = new HashMap<>();

    public void addUser(final User user) {
        users.put(user.getEmail(), user);
    }

    public User getUser(final String email) {
        return users.get(email);
    }
}
