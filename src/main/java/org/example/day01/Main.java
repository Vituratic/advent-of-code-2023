package org.example.day01;

import lombok.extern.slf4j.Slf4j;
import org.example.util.ResourceReader;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Main {
    public static void main(String[] args) {
        List<String> input = ResourceReader.readAllLinesFromResource("org/example/day01/input.txt");
        DigitExtractor digitExtractor = new DigitExtractor();
        List<String> result = new ArrayList<>(1000);
        for (String line : input) {
            String firstDigit = digitExtractor.findFirstDigit(line);
            String lastDigit = digitExtractor.findLastDigit(line);
            result.add(firstDigit + lastDigit);
        }
        int sum = result.stream().mapToInt(Integer::parseInt).sum();
        log.info("Sum of combined digits over all lines: {}", sum);
    }
}