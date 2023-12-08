package org.example.day07;

import java.util.Arrays;

enum Card {
    TWO('2'),
    THREE('3'),
    FOUR('4'),
    FIVE('5'),
    SIX('6'),
    SEVEN('7'),
    EIGHT('8'),
    NINE('9'),
    TEN('T'),
    JOKER('J'),
    QUEEN('Q'),
    KING('K'),
    ACE('A');

    private final char identifier;

    Card(char identifier) {
        this.identifier = identifier;
    }

    public static Card fromIdentifier(char identifier) {
        return Arrays.stream(values())
                .filter(card -> card.identifier == identifier)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Identifier not implemented: " + identifier));
    }
}
