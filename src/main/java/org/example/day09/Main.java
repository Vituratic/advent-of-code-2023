package org.example.day09;

import lombok.extern.slf4j.Slf4j;
import org.example.util.ResourceReader;

import java.util.List;

@Slf4j
class Main {

    public static void main(String[] args) {
        List<String> inputs = ResourceReader.readAllLinesFromResource("org/example/day09/input.txt");
        OASISExtrapolator oasisExtrapolator = new OASISExtrapolator();
        long extrapolatedSumFuture = 0L;
        long extrapolatedSumHistory = 0L;
        for (String line : inputs) {
            List<String> predictions = oasisExtrapolator.makePredictions(line);
            long extrapolateFuture = oasisExtrapolator.extrapolateFuture(predictions);
            long extrapolateHistory = oasisExtrapolator.extrapolateHistory(predictions);
            extrapolatedSumFuture += extrapolateFuture;
            extrapolatedSumHistory += extrapolateHistory;
        }
        log.info("Sum of extrapolated values for the future: {}", extrapolatedSumFuture);
        log.info("Sum of extrapolated values for the history: {}", extrapolatedSumHistory);
    }
}
