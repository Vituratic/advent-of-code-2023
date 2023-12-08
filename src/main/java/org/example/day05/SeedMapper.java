package org.example.day05;

import lombok.extern.slf4j.Slf4j;
import org.example.util.Pair;
import org.example.util.Triplet;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;
import java.util.function.LongUnaryOperator;

@Slf4j
class SeedMapper implements LongUnaryOperator {

    private final List<Triplet<Long, Long, Long>> destinationToSourceToSize;

    public SeedMapper(List<String> lines) {
        destinationToSourceToSize = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split("\\s+");
            long destination = Long.parseLong(parts[0]);
            long source = Long.parseLong(parts[1]);
            long size = Long.parseLong(parts[2]);
            destinationToSourceToSize.add(new Triplet<>(destination, source, size));
        }
    }

    @Override
    public long applyAsLong(long x) {
        for (Triplet<Long, Long, Long> triplet : destinationToSourceToSize) {
            if (triplet.b() <= x && x < triplet.b() + triplet.c()) {
                return x + triplet.a() - triplet.b();
            }
        }
        return x;
    }

    public List<Pair<Long, Long>> apply(List<Pair<Long, Long>> startsAndSizes) {
        List<Pair<Long, Long>> result = new ArrayList<>();
        List<Pair<Long, Long>> working = new ArrayList<>(startsAndSizes);
        for (var triplet : destinationToSourceToSize) {
            long destination = triplet.a();
            long source = triplet.b();
            long size = triplet.c();
            long sourceEnd = source + size;
            List<Pair<Long, Long>> ranges = new ArrayList<>();
            for (var startAndEnd : working) {
                long start = startAndEnd.a();
                long end = startAndEnd.b();
                var before = new Pair<>(start, Math.min(end, source));
                var intern = new Pair<>(Math.max(start, source), Math.min(sourceEnd, end));
                var after = new Pair<>(Math.max(sourceEnd, start), end);
                if (before.b() > before.a()) {
                    ranges.add(before);
                }
                if (intern.b() > intern.a()) {
                    var toAdd = new Pair<>(intern.a() - source + destination, intern.b() - source + destination);
                    result.add(toAdd);
                }
                if (after.b() > after.a()) {
                    ranges.add(after);
                }
            }
            working.clear();
            working.addAll(ranges);
        }
        result.addAll(working);
        return result;
    }
}
