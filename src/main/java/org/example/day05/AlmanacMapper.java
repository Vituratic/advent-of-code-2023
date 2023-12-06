package org.example.day05;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
class AlmanacMapper {

    //FIXME: Die Methode scheint zu funktionieren. Seed 79 -> Soil 81, 14 -> 14, 55 -> 57, 13 -> 13
    public Map<Long, Long> getValuesForKeysFromInput(List<Long> keys, List<String> inputs) {
        Map<Long, Long> values = new HashMap<>();
        List<Long> foundKeys = new ArrayList<>();
        for (String line : inputs) {
            if (line.isBlank()) continue;
            String[] numbers = line.split("\\s+");
            long destinationRangeStart = Long.parseLong(numbers[0].trim());
            long sourceRangeStart = Long.parseLong(numbers[1].trim());
            long rangeLength = Long.parseLong(numbers[2].trim());
            for (long key : keys) {
                if (sourceRangeStart <= key && key <= sourceRangeStart + rangeLength) {
                    long value = destinationRangeStart + key - sourceRangeStart;
                    values.put(key, value);
                    foundKeys.add(key);
                }
            }
        }
        for (long key : keys) {
            if (!foundKeys.contains(key)) {
                values.put(key, key);
            }
        }
        return values;
    }
}
