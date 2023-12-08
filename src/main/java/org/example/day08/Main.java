package org.example.day08;

import lombok.extern.slf4j.Slf4j;
import org.example.util.LeastCommonMultiple;
import org.example.util.Pair;
import org.example.util.ResourceReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
class Main {

    public static void main(String[] args) {
        List<String> lines = ResourceReader.readAllLinesFromResource("org/example/day08/input.txt");
        NetworkNodeTraverser networkNodeTraverser = new NetworkNodeTraverser();
        String instructions = lines.get(0);

        var networkNodes = new HashMap<String, Pair<String, String>>();
        for (int i = 2; i < lines.size(); i++) {
            String line = lines.get(i);
            String key = line.substring(0, 3);
            Pair<String, String> leftRight = new Pair<>(line.substring(7, 10), line.substring(12, 15));
            networkNodes.put(key, leftRight);
        }

        long amountOfRequiredStepsToReachZZZ = networkNodeTraverser.getAmountOfRequiredStepsToReachKeyPattern("AAA", "ZZZ", networkNodes.get("AAA"), instructions, networkNodes);
        log.info("Amount of required steps to reach ZZZ: {}", amountOfRequiredStepsToReachZZZ);

        List<Long> stepsRequiredPer = new ArrayList<>();
        for (var entry : networkNodes.entrySet()) {
            if (entry.getKey().matches(".*A")) {
                long stepsRequired = networkNodeTraverser.getAmountOfRequiredStepsToReachKeyPattern(entry.getKey(), ".*Z", networkNodes.get(entry.getKey()), instructions, networkNodes);
                stepsRequiredPer.add(stepsRequired);
            }
        }
        long amountOfRequiredStepsToReachZGhostly = stepsRequiredPer.stream().reduce(LeastCommonMultiple::get).get();
        log.info("Amount of required steps to reach *Z ghostly: {}", amountOfRequiredStepsToReachZGhostly);
    }
}
