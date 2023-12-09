package org.example.day09;

import lombok.extern.slf4j.Slf4j;
import org.example.util.ResourceReader;

import java.util.List;

@Slf4j
class Main {

    public static void main(String[] args) {
        List<String> inputs = ResourceReader.readAllLinesFromResource("org/example/day09/input.txt");
        OASISExtrapolator oasisExtrapolator = new OASISExtrapolator();
        long extrapolatedSum = 0L;
        for (String line : inputs) {
            List<String> predictions = oasisExtrapolator.makePredictions(line);
            long extrapolate = oasisExtrapolator.extrapolate(predictions);
            extrapolatedSum += extrapolate;
        }
        log.info("Sum of extrapolated values: {}", extrapolatedSum);
    }
}
