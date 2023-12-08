package org.example.day08;

import org.example.util.Pair;

import java.util.Map;

public class NetworkNodeTraverser {

    private static final char LEFT = 'L';

    public int getAmountOfRequiredStepsToReachKeyPattern(String startKey, String keyPattern, Pair<String, String> startNode, String instructions, Map<String, Pair<String, String>> networkNodes) {
        var node = startNode;
        String currentKey = startKey;
        int amountOfRequiredSteps = 0;
        for (char direction : instructions.toCharArray()) {
            if (direction == LEFT) {
                currentKey = node.a();
            } else {
                currentKey = node.b();
            }
            node = networkNodes.get(currentKey);
            amountOfRequiredSteps++;
        }
        if (!currentKey.matches(keyPattern)) {
            return amountOfRequiredSteps + getAmountOfRequiredStepsToReachKeyPattern(currentKey, keyPattern, node, instructions, networkNodes);
        }
        return amountOfRequiredSteps;
    }
}
