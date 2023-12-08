package org.example.day05;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OneToOneMapperTest {

    private final OneToOneMapper oneToOneMapper = new OneToOneMapper(List.of("50 98 2", "52 50 48"));

    @Test
    void testAppyMapper() {
        List<Long> keys = List.of(79L, 14L, 55L, 13L);
        List<Long> expectedValues = List.of(81L, 14L, 57L, 13L);
        for (int i = 0; i < keys.size(); i++) {
            long actual = oneToOneMapper.applyAsLong(keys.get(i));
            assertEquals(expectedValues.get(i), actual);
        }
    }
}
