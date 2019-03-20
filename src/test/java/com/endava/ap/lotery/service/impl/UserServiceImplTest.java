package com.endava.ap.lotery.service.impl;

import java.util.List;

import com.endava.ap.lotery.TicketType;
import com.endava.ap.lotery.model.Ticket;
import com.endava.ap.lotery.model.User;
import com.endava.ap.lotery.repository.UserRepositoryImpl;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.atLeastOnce;

@DisplayName("Test case for User Service")
@SpringBootTest
class UserServiceImplTest {
/*
    @Mock
    UserRepository userRepository;*/

    @Spy
    UserRepositoryImpl userRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    void shouldCreateUser() {
        //prepare
        String name = "Jane";
        String email = "jane@doe@gmail.com";

        doCallRealMethod().when(userRepository).addUser(any(User.class));

        //act
        userService.createUser(name, email);

        verify(userRepository).addUser(any(User.class));
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void shouldAddTicketToTheUser() {
        //prepare
        String name = "Jane";
        String email = "jane@doe@gmail.com";
        Ticket ticket1 = new Ticket(TicketType.DAILY,3, 4, 51, 55);
        Ticket ticket2 = new Ticket(TicketType.DAILY,4, 6, 7, 5, 4);
        User user = new User(name, email);
        List<Ticket> expectedTickets = Lists.list(ticket1, ticket2);

        when(userRepository.getUser(anyString())).thenReturn(user);

        //act
        userService.addTicket(email, ticket1);
        userService.addTicket(email, ticket2);

        //assert
        assertIterableEquals(expectedTickets, user.getTickets());
        verify(userRepository, times(2)).getUser(anyString());
        verifyNoMoreInteractions(userRepository);

        //additional mockito assertions for demo purpose
        verify(userRepository, atLeastOnce()).getUser(anyString());
        verify(userRepository, atLeast(2)).getUser(anyString());
        verify(userRepository, atMost(3)).getUser(anyString());
    }

    @Disabled("Disabled until story is implemented")
    @Test
    void testWillBeSkipped() {
    }

    @Test
    void shouldThrowExceptionWhenNullEmail() {
        //prepare
        String name = "Jane";

        doCallRealMethod().when(userRepository).addUser(any(User.class));

        //act
        assertThrows(NullPointerException.class, () -> {
            userService.createUser(name, "err");
        });
    }
}