package org.example.day05;

import lombok.extern.slf4j.Slf4j;
import org.example.util.ResourceReader;

import java.util.*;

@Slf4j
class Main {

    public static void main(String[] args) throws Exception {
        List<String> inputs = ResourceReader.readAllLinesFromResource("org/example/day05/input.txt");
        String seedsLine = inputs.get(0);
        List<Long> seeds = Arrays.stream(seedsLine.substring(seedsLine.indexOf(":") + 1)
                .trim()
                .split("\\s+"))
                .map(String::trim)
                .map(Long::parseLong)
                .toList();
        List<OneToOneMapper> oneToOneMappers = new ArrayList<>();
        for (int i = 1; i < inputs.size(); i++) {
            String line = inputs.get(i);
            if (line.isBlank()) continue;
            if (line.contains("map")) {
                int mapStart = i + 1;
                int mapEnd = mapStart;
                while (mapEnd < inputs.size() && !inputs.get(mapEnd).isBlank()) {
                    mapEnd++;
                }
                oneToOneMappers.add(new OneToOneMapper(inputs.subList(mapStart, mapEnd)));
            }
        }
        List<Long> appliedSeeds = new ArrayList<>();
        for (long seed : seeds) {
            long x = seed;
            for (OneToOneMapper mapper : oneToOneMappers) {
                x = mapper.applyAsLong(x);
            }
            appliedSeeds.add(x);
        }
        log.info("Lowest location number: {}", appliedSeeds.stream().min(Long::compare).get());
    }
}
