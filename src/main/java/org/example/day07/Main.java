package org.example.day07;

import lombok.extern.slf4j.Slf4j;
import org.example.util.ResourceReader;

import java.util.ArrayList;
import java.util.List;

@Slf4j
class Main {

    public static void main(String[] args) {
        List<String> lines = ResourceReader.readAllLinesFromResource("org/example/day07/input.txt");
        List<Hand> hands = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split("\\s+");
            List<Card> cards = new ArrayList<>();
            for (char cardIdentifier : parts[0].toCharArray()) {
                cards.add(Card.fromIdentifier(cardIdentifier));
            }
            hands.add(new Hand(cards, Integer.parseInt(parts[1])));
        }
        hands.sort(Hand::compareTo);
        long totalWinnings = 0L;
        for (int i = 0; i < hands.size(); i++) {
            totalWinnings += hands.get(i).getBid() * (i + 1L);
        }
        log.info("Total winnings: {}", totalWinnings);
    }
}
