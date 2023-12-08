package org.example.day07;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static org.example.day07.Card.JOKER;
import static org.example.day07.HandType.*;

class Hand {

    private final List<Card> cards;

    @Getter
    private final int bid;

    @Getter
    private final HandType handType;

    @Getter
    private final boolean isJokerWildcard;

    public Hand(List<Card> cards, int bid, boolean isJokerWildcard) {
        this.cards = cards;
        this.bid = bid;
        this.isJokerWildcard = isJokerWildcard;
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
        int amountOfDifferentCards = (int) workingCards.stream().distinct().count();
        if (isJokerWildcard) {
            int amountOfJokers = (int) workingCards.stream().filter(JOKER::equals).count();
            maxAmountOfSameCards = switch (amountOfJokers) {
                case 5, 4 -> 5;
                case 3 -> amountOfDifferentCards == 2 ? 5 : 4;
                case 2 -> {
                    if (amountOfDifferentCards == 4) yield 3;
                    yield amountOfDifferentCards == 3 ? 4 : 5;
                }
                case 1 -> maxAmountOfSameCards + 1;
                case 0 -> maxAmountOfSameCards;
                default -> throw new IllegalArgumentException("Unexpected amount of joker cards: " + amountOfJokers);
            };
        }
        return switch (maxAmountOfSameCards) {
            case 5 -> FIVE_OF_A_KIND;
            case 4 -> FOUR_OF_A_KIND;
            case 3 -> {
                if (isJokerWildcard) {
                    if (workingCards.stream().noneMatch(JOKER::equals)) {
                        yield amountOfDifferentCards == 2 ? FULL_HOUSE : THREE_OF_A_KIND;
                    }
                    yield amountOfDifferentCards == 3 ? FULL_HOUSE : THREE_OF_A_KIND;
                }
                yield amountOfDifferentCards == 2 ? FULL_HOUSE : THREE_OF_A_KIND;
            }
            case 2 -> amountOfDifferentCards == 3 ? TWO_PAIR : ONE_PAIR;
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
                return isJokerWildcard ?
                        cards.get(i).compareToWithJokerAsWildcard(other.cards.get(i))
                        : cards.get(i).compareTo(other.cards.get(i));
            }
        }
        throw new IllegalArgumentException("Identic hands? " + cards + " " + other.cards);
    }
}
