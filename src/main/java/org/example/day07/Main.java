package org.example.day07;

import lombok.extern.slf4j.Slf4j;
import org.example.util.ResourceReader;

import java.util.ArrayList;
import java.util.List;

@Slf4j
class Main {

    public static void main(String[] args) {
        List<String> lines = ResourceReader.readAllLinesFromResource("org/example/day07/input.txt");
        List<Hand> handsNoWildcard = new ArrayList<>();
        List<Hand> handsWildcard = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split("\\s+");
            List<Card> cards = new ArrayList<>();
            for (char cardIdentifier : parts[0].toCharArray()) {
                cards.add(Card.fromIdentifier(cardIdentifier));
            }
            handsNoWildcard.add(new Hand(cards, Integer.parseInt(parts[1]), false));
            handsWildcard.add(new Hand(cards, Integer.parseInt(parts[1]), true));
        }
        handsNoWildcard.sort(Hand::compareTo);
        handsWildcard.sort(Hand::compareTo);
        long totalWinningsNoWildcard = 0L;
        long totalWinningsWildcard = 0L;
        for (int i = 0; i < handsNoWildcard.size(); i++) {
            totalWinningsNoWildcard += handsNoWildcard.get(i).getBid() * (i + 1L);
            totalWinningsWildcard += handsWildcard.get(i).getBid() * (i + 1L);
        }
        log.info("Total winnings without joker as wildcard: {}", totalWinningsNoWildcard);
        log.info("Total winnings with joker as wildcard: {}", totalWinningsWildcard);
    }
}
