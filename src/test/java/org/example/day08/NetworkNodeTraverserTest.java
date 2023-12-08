package org.example.day08;

import org.example.util.LeastCommonMultiple;
import org.example.util.Pair;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NetworkNodeTraverserTest {

    private final NetworkNodeTraverser networkNodeTraverser = new NetworkNodeTraverser();

    @ParameterizedTest
    @MethodSource("provideDataPartOne")
    void testGetAmountOfRequiredStepsToReachZZZ(String instructions, Map<String, Pair<String, String>> networkNodes, long expected) {
        long actual = networkNodeTraverser.getAmountOfRequiredStepsToReachKeyPattern("AAA", "ZZZ", networkNodes.get("AAA"), instructions, networkNodes);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> provideDataPartOne() {
        return Stream.of(
                Arguments.of("RL", getTestNodesOne(), 2L),
                Arguments.of("LLR", getTestNodesTwo(), 6L)
        );
    }

    private static Map<String, Pair<String, String>> getTestNodesOne() {
        var result = new HashMap<String, Pair<String, String>>();
        result.put("AAA", new Pair<>("BBB", "CCC"));
        result.put("BBB", new Pair<>("DDD", "EEE"));
        result.put("CCC", new Pair<>("ZZZ", "GGG"));
        result.put("DDD", new Pair<>("DDD", "DDD"));
        result.put("EEE", new Pair<>("EEE", "EEE"));
        result.put("GGG", new Pair<>("GGG", "GGG"));
        result.put("ZZZ", new Pair<>("ZZZ", "ZZZ"));
        return result;
    }

    private static Map<String, Pair<String, String>> getTestNodesTwo() {
        var result = new HashMap<String, Pair<String, String>>();
        result.put("AAA", new Pair<>("BBB", "BBB"));
        result.put("BBB", new Pair<>("AAA", "ZZZ"));
        result.put("ZZZ", new Pair<>("ZZZ", "ZZZ"));
        return result;
    }

    @ParameterizedTest
    @MethodSource("provideDataPartTwo")
    void testGetAmountOfRequiredStepsToReachZGhostly(String instructions, Map<String, Pair<String, String>> networkNodes, long expected) {
        List<Long> stepsRequiredPer = new ArrayList<>();
        for (var entry : networkNodes.entrySet()) {
            if (entry.getKey().matches(".*A")) {
                long stepsRequired = networkNodeTraverser.getAmountOfRequiredStepsToReachKeyPattern(entry.getKey(), ".*Z", networkNodes.get(entry.getKey()), instructions, networkNodes);
                stepsRequiredPer.add(stepsRequired);
            }
        }
        long actual = stepsRequiredPer.stream().reduce(LeastCommonMultiple::get).get();
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> provideDataPartTwo() {
        return Stream.of(
                Arguments.of("LR", getTestNodesThree(), 6L)
        );
    }

    private static Map<String, Pair<String, String>> getTestNodesThree() {
        var result = new HashMap<String, Pair<String, String>>();
        result.put("11A", new Pair<>("11B", "XXX"));
        result.put("11B", new Pair<>("XXX", "11Z"));
        result.put("11Z", new Pair<>("11B", "XXX"));
        result.put("22A", new Pair<>("22B", "XXX"));
        result.put("22B", new Pair<>("22C", "22C"));
        result.put("22C", new Pair<>("22Z", "22Z"));
        result.put("22Z", new Pair<>("22B", "22B"));
        result.put("XXX", new Pair<>("XXX", "XXX"));
        return result;
    }
}
