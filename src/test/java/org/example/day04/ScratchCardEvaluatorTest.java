package org.example.day04;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScratchCardEvaluatorTest {

    private final ScratchCardEvaluator scratchCardEvaluator = new ScratchCardEvaluator();

    @ParameterizedTest
    @MethodSource("provideScratchCards")
    void testEvaluateScratchCardValue(ScratchCard input, int expectedWorthOfScratchCard, int expectedAmountOfWinningCards) {
        int actualWorthOfScratchCard = scratchCardEvaluator.evaluateScratchCardValue(input);
        assertEquals(expectedWorthOfScratchCard, actualWorthOfScratchCard);
    }

    private static Stream<Arguments> provideScratchCards() {
        return Stream.of(
                Arguments.of(ScratchCard.fromString("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53"), 8, 4),
                Arguments.of(ScratchCard.fromString("Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19"), 2, 2),
                Arguments.of(ScratchCard.fromString("Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1"), 2, 2),
                Arguments.of(ScratchCard.fromString("Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83"), 1, 1),
                Arguments.of(ScratchCard.fromString("Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36"), 0, 0),
                Arguments.of(ScratchCard.fromString("Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11"), 0, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("provideScratchCards")
    void testGetAmountOfWinningNumbers(ScratchCard input,int expectedWorthOfScratchCard, int expectedAmountOfWinningNumbers) {
        int actualAmountOfWinningNumbers = scratchCardEvaluator.getAmountOfWinningNumbers(input);
        assertEquals(expectedAmountOfWinningNumbers, actualAmountOfWinningNumbers);
    }
}
