package org.example.day02;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CubeGameEvaluatorTest {

    private final int AMOUNT_OF_RED_CUBES = 12;
    private final int AMOUNT_OF_GREEN_CUBES = 13;
    private final int AMOUNT_OF_BLUE_CUBES = 14;

    private final CubeGameEvaluator cubeGameEvaluator = new CubeGameEvaluator(AMOUNT_OF_RED_CUBES, AMOUNT_OF_GREEN_CUBES, AMOUNT_OF_BLUE_CUBES);

    @ParameterizedTest
    @MethodSource("provideParametersGamePossibility")
    void testEvaluateGamePossibility(String inputLine, boolean expectedIsPossible) {
        boolean actualIsPossible = cubeGameEvaluator.evalutePossibility(inputLine);
        assertEquals(expectedIsPossible, actualIsPossible);
    }

    private static Stream<Arguments> provideParametersGamePossibility() {
        return Stream.of(
                Arguments.of("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green", true),
                Arguments.of("Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue", true),
                Arguments.of("Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red", false),
                Arguments.of("Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red", false),
                Arguments.of("Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green", true)
        );
    }

    @ParameterizedTest
    @MethodSource("provideParametersMinimumSetPower")
    void testGetMinimumSetPower(String inputLine, int expectedMinimumPower) {
        int actualMinimumPower = cubeGameEvaluator.getMinimumSetPower(inputLine);
        assertEquals(expectedMinimumPower, actualMinimumPower);
    }

    private static Stream<Arguments> provideParametersMinimumSetPower() {
        return Stream.of(
                Arguments.of("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green", 48),
                Arguments.of("Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue", 12),
                Arguments.of("Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red", 1560),
                Arguments.of("Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red", 630),
                Arguments.of("Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green", 36)
        );
    }


}
