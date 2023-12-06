package org.example.day05;

import lombok.extern.slf4j.Slf4j;
import org.example.util.ResourceReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
class Main {

    public static void main(String[] args) {
        List<String> inputs = ResourceReader.readAllLinesFromResource("org/example/day05/input.txt");
        String seedsLine = inputs.get(0);
        List<Long> seeds = Arrays.stream(seedsLine.substring(seedsLine.indexOf(":") + 1)
                .trim()
                .split("\\s+"))
                .map(String::trim)
                .map(Long::parseLong)
                .toList();

        AlmanacMapper almanacMapper = new AlmanacMapper();
        List<Map<Long, Long>> maps = new ArrayList<>();
        for (int i = 1; i < inputs.size(); i++) {
            String line = inputs.get(i);
            if (line.isBlank()) continue;
            if (line.contains("map")) {
                int mapStart = i + 1;
                int mapEnd = mapStart;
                while (mapEnd < inputs.size() && !inputs.get(mapEnd).isBlank()) {
                    mapEnd++;
                }
                log.info("Creating {} from line {} to {}", line, mapStart, mapEnd);
                if (!maps.isEmpty()) {
                    List<Long> keys = maps.get(maps.size() - 1).values().stream().toList();
                    maps.add(almanacMapper.getValuesForKeysFromInput(keys, inputs.subList(mapStart, mapEnd)));
                } else {
                    maps.add(almanacMapper.getValuesForKeysFromInput(seeds, inputs.subList(mapStart, mapEnd)));
                }
            }
        }
        long lowestLocationNumber = Long.MAX_VALUE;
        for (long seed : seeds) {
            long soil = maps.get(0).get(seed);
            long fertilizer = maps.get(1).get(soil);
            long water = maps.get(2).get(fertilizer);
            long light = maps.get(3).get(water);
            long temperature = maps.get(4).get(light);
            long humidity = maps.get(5).get(temperature);
            long location = maps.get(6).get(humidity);
            lowestLocationNumber = Math.min(lowestLocationNumber, location);
        }

        log.info("Lowest location number: {}", lowestLocationNumber);
    }
}
