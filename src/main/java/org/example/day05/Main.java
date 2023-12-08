package org.example.day05;

import lombok.extern.slf4j.Slf4j;
import org.example.util.Pair;
import org.example.util.ResourceReader;

import java.util.*;

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
        List<SeedMapper> seedMappers = new ArrayList<>();
        for (int i = 1; i < inputs.size(); i++) {
            String line = inputs.get(i);
            if (line.isBlank()) continue;
            if (line.contains("map")) {
                int mapStart = i + 1;
                int mapEnd = mapStart;
                while (mapEnd < inputs.size() && !inputs.get(mapEnd).isBlank()) {
                    mapEnd++;
                }
                seedMappers.add(new SeedMapper(inputs.subList(mapStart, mapEnd)));
            }
        }
        List<Long> appliedSeeds = new ArrayList<>();
        for (long seed : seeds) {
            long x = seed;
            for (SeedMapper mapper : seedMappers) {
                x = mapper.applyAsLong(x);
            }
            appliedSeeds.add(x);
        }
        log.info("Lowest location number part one: {}", appliedSeeds.stream().min(Long::compare).get());

        List<Pair<Long, Long>> startsAndSizes = new ArrayList<>();
        for (int i = 0; i < seeds.size(); i += 2) {
            startsAndSizes.add(new Pair<>(seeds.get(i), seeds.get(i + 1)));
        }
        appliedSeeds.clear();
        for (var startAndSize : startsAndSizes) {
            List<Pair<Long, Long>> ranges = new ArrayList<>();
            ranges.add(new Pair<>(startAndSize.a(), startAndSize.a() + startAndSize.b()));
            for (SeedMapper mapper : seedMappers) {
                ranges = mapper.apply(ranges);
            }
            appliedSeeds.add(ranges.stream().map(Pair::a).min(Long::compare).orElse(Long.MAX_VALUE));
        }
        log.info("Lowest location number part two: {}", appliedSeeds.stream().min(Long::compare).get());
    }
}
