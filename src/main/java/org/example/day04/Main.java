package org.example.day04;

import lombok.extern.slf4j.Slf4j;
import org.example.util.ResourceReader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
class Main {
    public static void main(String[] args) {
        Stream<ScratchCard> cards = ResourceReader.readAllLinesFromResource("org/example/day04/input.txt")
                .stream()
                .map(ScratchCard::fromString);
        ScratchCardEvaluator scratchCardEvaluator = new ScratchCardEvaluator();
        List<Integer> cardPoints = new ArrayList<>();
        cards.forEach(scratchCard -> cardPoints.add(scratchCardEvaluator.evaluateScratchCard(scratchCard)));
        int pointWorthOfCardPile = cardPoints.stream().mapToInt(Integer::intValue).sum();
        log.info("The point worth of the given pile of cards is {}", pointWorthOfCardPile);
    }
}
