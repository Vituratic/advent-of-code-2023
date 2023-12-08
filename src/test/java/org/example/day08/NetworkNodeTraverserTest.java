package org.example.day08;

import org.example.util.Pair;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NetworkNodeTraverserTest {

    private final NetworkNodeTraverser networkNodeTraverser = new NetworkNodeTraverser();

    @ParameterizedTest
    @MethodSource("provideData")
    void testGetAmountOfRequiredStepsToReachZZZ(String instructions, Map<String, Pair<String, String>> networkNodes, int expected) {
        int actual = networkNodeTraverser.getAmountOfRequiredStepsToReachZZZ("AAA", networkNodes.get("AAA"), instructions, networkNodes);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> provideData() {
        return Stream.of(
                Arguments.of("RL", getTestNodesOne(), 2),
                Arguments.of("LLR", getTestNodesTwo(), 6)
        );
    }

    private static Map<String,Pair<String,String>> getTestNodesOne() {
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

    private static Map<String,Pair<String,String>> getTestNodesTwo() {
        var result = new HashMap<String,Pair<String,String>>();
        result.put("AAA", new Pair<>("BBB", "BBB"));
        result.put("BBB", new Pair<>("AAA", "ZZZ"));
        result.put("ZZZ", new Pair<>("ZZZ", "ZZZ"));
        return result;
    }
}
