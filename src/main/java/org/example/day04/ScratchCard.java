package org.example.day04;

import java.util.Arrays;
import java.util.List;

public record ScratchCard(int cardId, List<Integer> winningNumbers, List<Integer> ownedNumbers) {

    public static ScratchCard fromString(String input) {
        final int indexOfColon = input.indexOf(":");
        final int indexOfPipe = input.indexOf("|");
        int cardId = Integer.parseInt(input.substring(5, indexOfColon).trim());
        List<Integer> winningNumbers = Arrays.stream(input.substring(indexOfColon + 1, indexOfPipe).split("\\s+"))
                .filter(string -> !string.isBlank())
                .map(Integer::parseInt)
                .toList();
        List<Integer> ownedNumbers = Arrays.stream(input.substring(indexOfPipe + 1).split("\\s+"))
                .filter(string -> !string.isBlank())
                .map(Integer::parseInt)
                .toList();
        return new ScratchCard(cardId, winningNumbers, ownedNumbers);
    }
}
