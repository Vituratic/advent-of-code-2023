package org.example.day03;

import lombok.extern.slf4j.Slf4j;
import org.example.util.ResourceReader;

import java.util.List;

@Slf4j
class Main {
    public static void main(String[] args) {
        List<String> input = ResourceReader.readAllLinesFromResource("org/example/day03/input.txt");
        EngineSchematicEvaluator engineSchematicEvaluator = new EngineSchematicEvaluator();
        int sumOfAllPartNumbers = engineSchematicEvaluator.getSumOfAllPartNumbers(input);
        log.info("Sum of all part numbers: {}", sumOfAllPartNumbers);
    }
}
