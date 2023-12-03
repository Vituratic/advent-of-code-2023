package org.example.day03;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EngineSchematicEvaluatorTest {

    private final EngineSchematicEvaluator engineSchematicEvaluator = new EngineSchematicEvaluator();

    private static final List<String> TEST_INPUTS = new ArrayList<>(10);

    @BeforeAll
    static void setupTestInputs() {
        TEST_INPUTS.add("467..114..");
        TEST_INPUTS.add("...*......");
        TEST_INPUTS.add("..35..633.");
        TEST_INPUTS.add("......#...");
        TEST_INPUTS.add("617*......");
        TEST_INPUTS.add(".....+.58.");
        TEST_INPUTS.add("..592.....");
        TEST_INPUTS.add("......755.");
        TEST_INPUTS.add("...$.*....");
        TEST_INPUTS.add(".664.598..");
    }

    @Test
    void testGetSumOfAllPartNumbers() {
        int actualSum = engineSchematicEvaluator.getSumOfAllPartNumbers(TEST_INPUTS);
        assertEquals(4361, actualSum);
    }
}
