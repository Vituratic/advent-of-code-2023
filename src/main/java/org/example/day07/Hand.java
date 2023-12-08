package org.example.day07;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static org.example.day07.HandType.*;

class Hand {

    private final List<Card> cards;

    @Getter
    private final int bid;

    @Getter
    private final HandType handType;

    public Hand(List<Card> cards, int bid) {
        this.cards = cards;
        this.bid = bid;
        this.handType = evaluateHandType();
    }

    private HandType evaluateHandType() {
        List<Card> workingCards = new ArrayList<>(cards);
        workingCards.sort(Enum::compareTo);
        int maxAmountOfSameCards = 1;
        int amountOfSameCards = 1;
        for (int i = 1; i < 5; i++) {
            if (workingCards.get(i - 1) == workingCards.get(i)) {
                amountOfSameCards++;
                maxAmountOfSameCards = Math.max(maxAmountOfSameCards, amountOfSameCards);
            } else {
                amountOfSameCards = 1;
            }
        }
        return switch (maxAmountOfSameCards) {
            case 5 -> FIVE_OF_A_KIND;
            case 4 -> FOUR_OF_A_KIND;
            case 3 -> workingCards.stream().distinct().count() == 2 ? FULL_HOUSE : THREE_OF_A_KIND;
            case 2 -> workingCards.stream().distinct().count() == 3 ? TWO_PAIR : ONE_PAIR;
            case 1 -> HIGH_CARD;
            default -> throw new IllegalArgumentException("Unexpected amount of same cards: " + maxAmountOfSameCards);
        };
    }

    public int compareTo(Hand other) {
        if (this.handType != other.handType) {
            return this.handType.compareTo(other.handType);
        }
        for (int i = 0; i < 5; i++) {
            if (cards.get(i) != other.cards.get(i)) {
                return cards.get(i).compareTo(other.cards.get(i));
            }
        }
        throw new IllegalArgumentException("Identic hands? " + cards + " " + other.cards);
    }
}
