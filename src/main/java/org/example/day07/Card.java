package org.example.day07;

import lombok.Getter;

import java.util.Arrays;

enum Card {
    TWO('2', 2),
    THREE('3', 3),
    FOUR('4', 4),
    FIVE('5', 5),
    SIX('6', 6),
    SEVEN('7', 7),
    EIGHT('8', 8),
    NINE('9', 9),
    TEN('T', 10),
    JOKER('J', 1),
    QUEEN('Q', 11),
    KING('K', 12),
    ACE('A', 13);

    private final char identifier;

    @Getter
    private final int value;

    Card(char identifier, int value) {
        this.identifier = identifier;
        this.value = value;
    }

    public static Card fromIdentifier(char identifier) {
        return Arrays.stream(values())
                .filter(card -> card.identifier == identifier)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Identifier not implemented: " + identifier));
    }

    public int compareToWithJokerAsWildcard(Card other) {
        return Integer.compare(value, other.value);
    }
}
