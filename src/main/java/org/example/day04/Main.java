package org.example.day04;

import lombok.extern.slf4j.Slf4j;
import org.example.util.ResourceReader;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Slf4j
class Main {
    public static void main(String[] args) {
        List<ScratchCard> cards = ResourceReader.readAllLinesFromResource("org/example/day04/input.txt")
                .stream()
                .map(ScratchCard::fromString)
                .toList();
        ScratchCardEvaluator scratchCardEvaluator = new ScratchCardEvaluator();
        List<Integer> cardPoints = new ArrayList<>();
        cards.forEach(scratchCard -> cardPoints.add(scratchCardEvaluator.evaluateScratchCardValue(scratchCard)));
        int pointWorthOfCardPile = cardPoints.stream().mapToInt(Integer::intValue).sum();
        log.info("The point worth of the given pile of cards is {}", pointWorthOfCardPile);

        ListIterator<ScratchCard> scratchCardListIterator = new ArrayList<>(cards).listIterator();
        int totalAmountOfScratchCards = 0;
        while (scratchCardListIterator.hasNext()) {
            totalAmountOfScratchCards++;
            ScratchCard card = scratchCardListIterator.next();
            int amountOfWinningNumbers = scratchCardEvaluator.getAmountOfWinningNumbers(card);
            for (int i = 0; i < amountOfWinningNumbers; i++) {
                if (card.cardId() + i >= cards.size()) continue;
                scratchCardListIterator.add(cards.get(card.cardId() + i));
                scratchCardListIterator.previous();
            }
        }
        log.info("You end up with following amount of scratchcards: {}", totalAmountOfScratchCards);
    }
}
