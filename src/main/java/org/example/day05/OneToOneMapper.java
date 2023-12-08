package org.example.day05;

import lombok.extern.slf4j.Slf4j;
import org.example.util.Triplet;

import java.util.ArrayList;
import java.util.List;
import java.util.function.LongUnaryOperator;

@Slf4j
class OneToOneMapper implements LongUnaryOperator {

    private final List<Triplet<Long, Long, Long>> destinationToSourceToSize;

    public OneToOneMapper(List<String> lines) {
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
}
