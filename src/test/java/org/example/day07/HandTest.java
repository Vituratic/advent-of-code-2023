package org.example.day07;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.example.day07.Card.*;
import static org.example.day07.HandType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HandTest {

    @ParameterizedTest
    @MethodSource("provideHandsForHandTypeTest")
    void testHandForHandType(Hand input, HandType expected) {
        assertEquals(expected, input.getHandType());
    }

    private static Stream<Arguments> provideHandsForHandTypeTest() {
        return Stream.of(
                Arguments.of(new Hand(List.of(TWO, THREE, FOUR, FIVE, SIX), 111, false), HIGH_CARD),
                Arguments.of(new Hand(List.of(ACE, TWO, THREE, ACE, FOUR), 222, false), ONE_PAIR),
                Arguments.of(new Hand(List.of(TWO, THREE, FOUR, THREE, TWO), 333, false), TWO_PAIR),
                Arguments.of(new Hand(List.of(TEN, TEN, TEN, NINE, EIGHT), 444, false), THREE_OF_A_KIND),
                Arguments.of(new Hand(List.of(TWO, THREE, THREE, THREE, TWO), 555, false), FULL_HOUSE),
                Arguments.of(new Hand(List.of(ACE, ACE, EIGHT, ACE, ACE), 666, false), FOUR_OF_A_KIND),
                Arguments.of(new Hand(List.of(ACE, ACE, ACE, ACE, ACE), 777, false), FIVE_OF_A_KIND),
                // Joker as Wildcard
                Arguments.of(new Hand(List.of(QUEEN, JOKER, JOKER, QUEEN, TWO), 777, true), FOUR_OF_A_KIND),
                Arguments.of(new Hand(List.of(JOKER, TWO, TEN, TWO, TEN), 777, true), FULL_HOUSE),
                Arguments.of(new Hand(List.of(KING, KING, JOKER, ACE, ACE), 777, true), FULL_HOUSE),
                Arguments.of(new Hand(List.of(JOKER, KING, KING, ACE, QUEEN), 777, true), THREE_OF_A_KIND),
                Arguments.of(new Hand(List.of(JOKER, JOKER, KING, ACE, QUEEN), 777, true), THREE_OF_A_KIND)
        );
    }

    @ParameterizedTest
    @MethodSource("provideHandsForStrengthTest")
    void testCompareTo(Hand stronger, Hand weaker) {
        assertTrue(stronger.compareTo(weaker) > 0);
    }

    private static Stream<Arguments> provideHandsForStrengthTest() {
        return Stream.of(
                // Stronger Hand, Weaker Hand
                Arguments.of(new Hand(List.of(THREE, THREE, THREE, THREE, TWO), 0, false), new Hand(List.of(TWO, ACE, ACE, ACE, ACE), 0, false)),
                Arguments.of(new Hand(List.of(SEVEN, SEVEN, EIGHT, EIGHT, EIGHT), 0, false), new Hand(List.of(SEVEN, SEVEN, SEVEN, EIGHT, EIGHT), 0, false)),
                // Joker as Wildcard
                Arguments.of(new Hand(List.of(QUEEN, QUEEN, QUEEN, QUEEN, TWO), 0, true), new Hand(List.of(JOKER, KING, KING, KING, TWO), 0, true)),
                Arguments.of(new Hand(List.of(THREE, THREE, THREE, THREE, TWO), 0, true), new Hand(List.of(JOKER, KING, KING, KING, TWO), 0, true)),
                Arguments.of(new Hand(List.of(JOKER, JOKER, JOKER, JOKER, TWO), 0, true), new Hand(List.of(JOKER, JOKER, JOKER, JOKER, JOKER), 0, true))
        );
    }

    @Test
    void testCompareToBySortingNoWildcard() {
        Hand rankOne = new Hand(List.of(THREE, TWO, TEN, THREE, KING), 765, false);
        Hand rankFour = new Hand(List.of(TEN, FIVE, FIVE, JOKER, FIVE), 684, false);
        Hand rankThree = new Hand(List.of(KING, KING, SIX, SEVEN, SEVEN), 28, false);
        Hand rankTwo = new Hand(List.of(KING, TEN, JOKER, JOKER, TEN), 220, false);
        Hand rankFive = new Hand(List.of(QUEEN, QUEEN, QUEEN, JOKER, ACE), 483, false);
        List<Hand> hands = new ArrayList<>(List.of(rankOne, rankTwo, rankThree, rankFour, rankFive));
        hands.sort(Hand::compareTo);
        assertEquals(rankOne, hands.get(0));
        assertEquals(rankTwo, hands.get(1));
        assertEquals(rankThree, hands.get(2));
        assertEquals(rankFour, hands.get(3));
        assertEquals(rankFive, hands.get(4));
    }

    @Test
    void testCompareToBySortingWildcard() {
        Hand rankOne = new Hand(List.of(THREE, TWO, TEN, THREE, KING), 765, true);
        Hand rankThree = new Hand(List.of(TEN, FIVE, FIVE, JOKER, FIVE), 684, true);
        Hand rankTwo = new Hand(List.of(KING, KING, SIX, SEVEN, SEVEN), 28, true);
        Hand rankFive = new Hand(List.of(KING, TEN, JOKER, JOKER, TEN), 220, true);
        Hand rankFour = new Hand(List.of(QUEEN, QUEEN, QUEEN, JOKER, ACE), 483, true);
        List<Hand> hands = new ArrayList<>(List.of(rankOne, rankTwo, rankThree, rankFour, rankFive));
        hands.sort(Hand::compareTo);
        assertEquals(rankOne, hands.get(0));
        assertEquals(rankTwo, hands.get(1));
        assertEquals(rankThree, hands.get(2));
        assertEquals(rankFour, hands.get(3));
        assertEquals(rankFive, hands.get(4));
    }
}
