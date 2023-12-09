package org.example.day09;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OASISExtrapolatorTest {

    private final OASISExtrapolator oasisExtrapolator = new OASISExtrapolator();

    @ParameterizedTest
    @MethodSource("providePredictions")
    void testMakePrediction(String input, List<String> expected) {
        List<String> actual = oasisExtrapolator.makePredictions(input);
        assertEquals(expected.size(), actual.size());
        assertTrue(expected.containsAll(actual));
    }

    private static Stream<Arguments> providePredictions() {
        return Stream.of(
                Arguments.of("0 3 6 9 12 15", listOne()),
                Arguments.of("1 3 6 10 15 21", listTwo()),
                Arguments.of("10 13 16 21 30 45", listThree())
        );
    }

    @ParameterizedTest
    @MethodSource("provideExtrapolations")
    void testExtrapolate(List<String> input, long expected) {
        long actual = oasisExtrapolator.extrapolate(input);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> provideExtrapolations() {
        return Stream.of(
                Arguments.of(listOne(), 18L),
                Arguments.of(listTwo(), 28L),
                Arguments.of(listThree(), 68L)
        );
    }

    private static List<String> listOne() {
        return List.of(
                "0 3 6 9 12 15",
                "3 3 3 3 3",
                "0 0 0 0");
    }

    private static List<String> listTwo() {
        return List.of(
                "1 3 6 10 15 21",
                "2 3 4 5 6",
                "1 1 1 1",
                "0 0 0");
    }

    private static List<String> listThree() {
        return List.of(
                "10 13 16 21 30 45",
                "3 3 5 9 15",
                "0 2 4 6",
                "2 2 2",
                "0 0");
    }

}
