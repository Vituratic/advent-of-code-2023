package org.example.day09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class OASISExtrapolator {


    public List<String> makePredictions(String input) {
        List<String> predictions = new ArrayList<>();
        predictions.add(input);
        StringBuilder stringBuilder = new StringBuilder();
        String working = input;
        while (!containsOnlyZeroes(working)) {
            List<Long> inputs = Arrays.stream(working.trim().split("\\s+"))
                    .map(Long::parseLong)
                    .toList();
            for (int i = 1; i < inputs.size(); i++) {
                stringBuilder.append(inputs.get(i) - inputs.get(i - 1));
                stringBuilder.append(" ");
            }
            working = stringBuilder.toString().trim();
            predictions.add(working);
            stringBuilder.delete(0, stringBuilder.length());
        }

        return predictions;
    }

    private boolean containsOnlyZeroes(String input) {
        return input.replace("0", "").isBlank();
    }

    public long extrapolate(List<String> input) {
        long result = 0;
        for (int i = input.size() - 2; i >= 0; i--) {
            List<Long> values = Arrays.stream(input.get(i).split("\\s+"))
                    .map(Long::parseLong)
                    .toList();
            result += values.get(values.size() - 1);
        }
        return result;
    }
}
