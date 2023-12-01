package org.example.day01;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DigitExtractorTest {

    private final DigitExtractor digitExtractor = new DigitExtractor();

    @ParameterizedTest
    @MethodSource("provideFindFirstParameters")
    void testFindFirstDigit(String inputString, String expectedFirstDigit) {
        String actualFirstDigit = digitExtractor.findFirstDigit(inputString);
        assertEquals(expectedFirstDigit, actualFirstDigit);
    }

    private static Stream<Arguments> provideFindFirstParameters() {
        return Stream.of(
                Arguments.of("two1nine", "2"),
                Arguments.of("eightwothree", "8"),
                Arguments.of("abcone2threexyz", "1"),
                Arguments.of("xtwone3four", "2"),
                Arguments.of("4nineeightseven2", "4"),
                Arguments.of("zoneight234", "1"),
                Arguments.of("7pqrstsixteen", "7")
        );
    }

    @ParameterizedTest
    @MethodSource("provideFindLastParameters")
    void testFindLastDigit(String inputString, String expectedLastDigit) {
        String actualLastDigit = digitExtractor.findLastDigit(inputString);
        assertEquals(expectedLastDigit, actualLastDigit);
    }

    private static Stream<Arguments> provideFindLastParameters() {
        return Stream.of(
                Arguments.of("two1nine", "9"),
                Arguments.of("eightwothree", "3"),
                Arguments.of("abcone2threexyz", "3"),
                Arguments.of("xtwone3four", "4"),
                Arguments.of("4nineeightseven2", "2"),
                Arguments.of("zoneight234", "4"),
                Arguments.of("7pqrstsixteen", "6")
        );
    }
}
