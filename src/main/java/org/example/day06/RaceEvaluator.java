package org.example.day06;

class RaceEvaluator {
    public int getNumberOfWaysToWinTheRace(Race input) {
        int amountOfWaysToWinTheRace = 0;
        int raceDuration = input.time();
        int distanceToBeat = input.distance();
        for (int buttonHeldDuration = 1; buttonHeldDuration < raceDuration; buttonHeldDuration++) {
            int timeLeftInRace = raceDuration - buttonHeldDuration;
            int distanceToBeTraveled = timeLeftInRace * buttonHeldDuration;
            if (distanceToBeTraveled > distanceToBeat) {
                amountOfWaysToWinTheRace++;
            }
        }

        return amountOfWaysToWinTheRace;
    }
}
