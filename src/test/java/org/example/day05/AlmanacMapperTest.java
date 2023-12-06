package org.example.day05;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlmanacMapperTest {

    private final AlmanacMapper almanacMapper = new AlmanacMapper();

    @Test
    void testGetValuesForKeysWithinRange() {
        List<Long> keys = List.of(79L, 14L, 55L, 13L);
        List<Long> expectedValues = List.of(81L, 14L, 57L, 13L);
        List<String> inputs = List.of("50 98 2", "52 50 48");
        Map<Long, Long> actualValues = almanacMapper.getValuesForKeysFromInput(keys, inputs);
        assertEquals(expectedValues.size(), actualValues.size());
        for (int i = 0; i < keys.size(); i++) {
            assertEquals(expectedValues.get(i), actualValues.get(keys.get(i)));
        }
    }
}
