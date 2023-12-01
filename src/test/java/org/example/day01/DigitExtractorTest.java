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
                Arguments.of("1abc2", "1"),
                Arguments.of("pqr3stu8vwx", "3"),
                Arguments.of("a1b2c3d4e5f", "1"),
                Arguments.of("treb7uchet", "7")
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
                Arguments.of("1abc2", "2"),
                Arguments.of("pqr3stu8vwx", "8"),
                Arguments.of("a1b2c3d4e5f", "5"),
                Arguments.of("treb7uchet", "7")
        );
    }
}
