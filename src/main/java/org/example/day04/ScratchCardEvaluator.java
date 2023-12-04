package org.example.day04;

class ScratchCardEvaluator {

    public int evaluateScratchCard(ScratchCard input) {
        int amountOfWinningCards = (int) input.ownedNumbers().stream()
                .filter(input.winningNumbers()::contains)
                .count();
        if (amountOfWinningCards == 1 || amountOfWinningCards == 2) return amountOfWinningCards;
        return (int) Math.pow(2, amountOfWinningCards - 1.0);
    }
}
