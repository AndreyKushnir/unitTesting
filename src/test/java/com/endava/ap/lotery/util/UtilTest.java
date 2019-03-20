package com.endava.ap.lotery.util;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import com.endava.ap.lotery.model.User;

import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumingThat;
import static org.springframework.test.util.AssertionErrors.assertTrue;

class UtilTest {

    private List<String> testList = new ArrayList<>();

    // test lifecycle
    @BeforeAll
    static void initAll() {
        System.out.println("Before All");
    }

    @BeforeEach
    void init() {
        System.out.println("Before Each");
    }

    @Test
    @DisplayName("This test add a string to the list")
    void shouldAddToTheList() {
        //give
        final String junit = "JUNIT";

        //when
        final boolean isAdded = testList.add(junit);

        //then
        assertTrue("list should contain one element ", isAdded);
    }

    @AfterEach
    void tearDown() {
        System.out.println("After Each");
        testList.clear();
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("After All");
    }

    @Test
    void shouldDeleteFromTheList() {
        //given
        testList.add("John");
        testList.add("Doe");

        //when
        testList.remove(0);

        //then
        assertEquals(1, testList.size());
    }

    //annotations

    //Dependency Injection for Constructors and Methods
    @RepeatedTest(10)
    @DisplayName("This test add a string to the list")
    void repeatedTest(RepetitionInfo repetitionInfo, TestInfo testInfo) {
        System.out.println("repeat :" + repetitionInfo.getTotalRepetitions());
        System.out.println("name :" + testInfo.getDisplayName());
    }

    @Test
    @Disabled("for demonstration purposes")
    void skippedTest() {
        // not executed
    }

    //assertions

    @Test
    @DisplayName("This test will fail")
    void failingTest() {
        fail("story is not implemented");
    }

    @Test
    void shouldCreateUser() {
        //prepare
        String name = "Jane";
        String email = "jane@doe@gmail.com";

        //act
        User user = new User(name, email);

        //assert
        assertAll("user",
                () -> assertEquals(name, user.getName()),
                () -> assertEquals(email, user.getEmail()),
                () -> assertTrue("List is empty", user.getTickets().isEmpty())
        );

        assertNotNull(user);
    }

    @Test
    void testTimoutCase() {

        //This will pass
        Assertions.assertTimeout(Duration.ofMinutes(1), () -> {
            return "result";
        });

        //This will fail
        Assertions.assertTimeout(Duration.ofMillis(100), () -> {
            Thread.sleep(200);
            return "result";
        });

        //This will fail
        Assertions.assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            Thread.sleep(200);
            return "result";
        });
    }
    //    assumptions
    @Test
    void assumeENV() {
        System.setProperty("ENV", "DEV");
        Assumptions.assumeTrue("DEV".equals(System.getProperty("ENV")));
    }

    @Test
    void testInAllEnvironments() {
        ArrayList<Integer> listOne = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        Iterable<Integer> listTwo = new ArrayList<>(Arrays.asList(1, 2, 3, 4));

        assumingThat("CI".equals(System.getenv("ENV")),
                () -> {
                    // perform these assertions only on the CI server
                    assertIterableEquals(listOne, listTwo);
                });

        // perform these assertions in all environments
        assertTrue("list should contain this item", listOne.contains(1));
    }

    //ParameterizedTest

    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 3 })
    void testWithValueSource(int argument) {
        assertTrue("argument should be in this range", argument > 0 && argument < 4);
    }

    @ParameterizedTest
    @MethodSource("emailProvider")
    void testWithExplicitLocalMethodSource(String email) {
        assertTrue("email should follow this pattern characters@characters.domain", Util.validateEmail(email));

    }

    @ParameterizedTest
    @CsvSource({ "test,TEST", "tEst,TEST", "Java,JAVA" })
    void toUpperCase_ShouldGenerateTheExpectedUppercaseValue(String input, String expected) {
        String actualValue = input.toUpperCase();
        assertEquals(expected, actualValue);
    }

    @ParameterizedTest(name = "{index} {0} is String")
    @ArgumentsSource(BlankStringsArgumentsProvider.class)
    void isBlank_ShouldReturnTrueForNullOrBlankStringsArgProvider(String input) {
        assertTrue("shoud be blank", Strings.isBlank(input));
    }

    @ParameterizedTest
    @CsvSource({
            "Jane, doe@gmail.com",
            "John, john@mail.com"
    })
    void testWithArgumentsAccessor(ArgumentsAccessor arguments) {
        User user = new User(arguments.getString(0),
                arguments.getString(1));

        assertTrue("email should follow this pattern characters@characters.domain", Util.validateEmail(user.getEmail()));
        assertNotNull(user.getTickets());
    }

    @ParameterizedTest
    @CsvSource({
            "Jane, Doe@email.tt",
            "John, Doe@ttmm"
    })
    void testWithArgumentsAggregator(@AggregateWith(PersonAggregator.class) User user) {
        assertTrue("email should follow this pattern characters@characters.domain", Util.validateEmail(user.getEmail()));
        assertNotNull(user.getTickets());
    }


    static Stream<String> emailProvider() {
        return Stream.of("apple@gmail.com", "banana@email.md");
    }

    static class PersonAggregator implements ArgumentsAggregator {

        @Override
        public User aggregateArguments(ArgumentsAccessor arguments, ParameterContext context) {
            return new User(arguments.getString(0),
                    arguments.getString(1));
        }
    }

    static class BlankStringsArgumentsProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of((String) null),
                    Arguments.of(""),
                    Arguments.of("   ")
            );
        }
    }
}