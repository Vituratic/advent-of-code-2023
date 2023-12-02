package org.example.day02;

import lombok.extern.slf4j.Slf4j;
import org.example.util.ResourceReader;

import java.util.ArrayList;
import java.util.List;

@Slf4j
class Main {

    public static void main(String[] args) {
        List<String> gameLines = ResourceReader.readAllLinesFromResource("org/example/day02/input.txt");
        CubeGameEvaluator cubeGameEvaluator = new CubeGameEvaluator(12, 13, 14);
        List<Integer> idsOfPossibleGames = new ArrayList<>();
        for (String game : gameLines) {
            boolean isPossible = cubeGameEvaluator.evalutePossibility(game);
            if (isPossible) {
                int gameId = Integer.parseInt(game.split(":")[0].trim().split("\\s")[1]);
                idsOfPossibleGames.add(gameId);
            }
        }
        int sumOfIdsOfPossibleGames = idsOfPossibleGames.stream().mapToInt(Integer::intValue).sum();
        log.info("Sum of Ids of possible games: {}", sumOfIdsOfPossibleGames);
    }
}
