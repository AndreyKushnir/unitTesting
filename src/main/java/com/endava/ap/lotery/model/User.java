package com.endava.ap.lotery.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    public User(final String name, final String email) {
        this.name = name;
        this.email = email;
    }

    private String name;

    private String email;

    private List<Ticket> tickets =  new ArrayList<>();

}
