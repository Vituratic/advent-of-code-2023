package org.example.day06;

import lombok.extern.slf4j.Slf4j;
import org.example.util.ResourceReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

@Slf4j
class Main {

    public static void main(String[] args) {
        List<String> inputs = ResourceReader.readAllLinesFromResource("org/example/day06/input.txt");
        String[] times = inputs.get(0).substring(inputs.get(0).indexOf(":") + 1).trim().split("\\s+");
        String[] distances = inputs.get(1).substring(inputs.get(1).indexOf(":") + 1).trim().split("\\s++");
        RaceEvaluator raceEvaluator = new RaceEvaluator();
        List<Integer> numbersOfWaysToWinTheRaces = new ArrayList<>();
        for (int i = 0; i < times.length; i++) {
            Race input = new Race(parseInt(times[i]), parseLong(distances[i]));
            numbersOfWaysToWinTheRaces.add(raceEvaluator.getNumberOfWaysToWinTheRace(input));
        }
        int productOfNumbersOfWaysToWinTheRaces = numbersOfWaysToWinTheRaces.stream().reduce((a, b) -> a * b).get();
        log.info("Product of numbers of ways to win the races: {}", productOfNumbersOfWaysToWinTheRaces);

        String time = Arrays.stream(times).reduce((a, b) -> a + b).get().trim();
        String distance = Arrays.stream(distances).reduce((a, b) -> a + b).get().trim();
        int numberOfWaysToWinTheRace = raceEvaluator.getNumberOfWaysToWinTheRace(new Race(parseInt(time), parseLong(distance)));
        log.info("Number of ways to win the long single race: {}", numberOfWaysToWinTheRace);
    }
}
