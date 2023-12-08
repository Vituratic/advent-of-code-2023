package org.example.day06;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RaceEvaluatorTest {

    private final RaceEvaluator raceEvaluator = new RaceEvaluator();

    @ParameterizedTest
    @MethodSource("provideTestDataPartOne")
    void testGetNumberOfWaysToWinTheRace(Race input, int expected) {
        int actual = raceEvaluator.getNumberOfWaysToWinTheRace(input);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> provideTestDataPartOne() {
        return Stream.of(
                Arguments.of(new Race(7, 9), 4),
                Arguments.of(new Race(15, 40), 8),
                Arguments.of(new Race(30, 200), 9)
        );
    }
}
