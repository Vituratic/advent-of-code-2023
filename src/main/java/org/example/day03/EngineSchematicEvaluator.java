package org.example.day03;

import java.util.*;

import static java.lang.Character.isDigit;

class EngineSchematicEvaluator {
    public int getSumOfAllPartNumbers(List<String> input) {
        List<Integer> partNumbers = new ArrayList<>();
        for (int row = 0; row < input.size(); row++) {
            char[] line = input.get(row).toCharArray();
            int startIndex = -1;
            int endIndex = -1;
            for (int column = 0; column < line.length; column++) {
                // current char is a digit, and it is either the first char or the previous char is not a digit
                if (isDigit(line[column]) && (column == 0 || !isDigit(line[column - 1]))) {
                    startIndex = column;
                }
                // current char is a digit, and it is either the last char or the next char is not a digit
                if (isDigit(line[column]) && (column == line.length - 1 || (column != line.length - 1 && !isDigit(line[column + 1])))) {
                    endIndex = column;
                }
                // we found both a start and end index
                if (startIndex != -1 && endIndex != -1) {
                    // check current line
                    if (startIndex - 1 >= 0 && isValidPartNumberSymbol(line[startIndex - 1]) || endIndex + 1 < line.length && isValidPartNumberSymbol(line[endIndex + 1])) {
                        partNumbers.add(Integer.parseInt(input.get(row).substring(startIndex, endIndex + 1)));
                    }
                    // check previous line
                    if (row > 0 && (hasSurroundingLineValidPartNumberSymbolWithinRange(input.get(row - 1), startIndex - 1, endIndex + 1))) {
                        partNumbers.add(Integer.parseInt(input.get(row).substring(startIndex, endIndex + 1)));

                    }
                    // check next line
                    if (row < input.size() - 1 && (hasSurroundingLineValidPartNumberSymbolWithinRange(input.get(row + 1), startIndex - 1, endIndex + 1))) {
                        partNumbers.add(Integer.parseInt(input.get(row).substring(startIndex, endIndex + 1)));

                    }
                    // reset start and end index
                    startIndex = -1;
                    endIndex = -1;
                }
            }
        }
        return partNumbers.stream().mapToInt(Integer::intValue).sum();
    }

    private boolean hasSurroundingLineValidPartNumberSymbolWithinRange(String line, int startIndexOfNumber, int endIndexOfNumber) {
        int startIndexToCheck = Math.max(startIndexOfNumber, 0);
        int endIndexToCheck = Math.min(endIndexOfNumber, line.length() - 1);

        for (int i = startIndexToCheck; i <= endIndexToCheck; i++) {
            if (isValidPartNumberSymbol(line.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidPartNumberSymbol(char c) {
        return !isDigit(c) && c != '.';
    }

    public int getSumOfAllGearRatios(List<String> input) {
        List<Integer> gearRatios = new ArrayList<>();
        for (int row = 0; row < input.size(); row++) {
            char[] line = input.get(row).toCharArray();
            for (int column = 0; column < line.length; column++) {
                if (line[column] == '*' && getAmountOfAdjacentPartNumbers(input, row, column) == 2) {
                    gearRatios.add(getGearRatio(input, row, column));
                }
            }
        }
        return gearRatios.stream().mapToInt(Integer::intValue).sum();
    }

    private int getAmountOfAdjacentPartNumbers(List<String> lines, int row, int column) {
        List<Integer> partNumbers = new ArrayList<>();

        int startingRow = Math.max(0, row - 1);
        int finishingRow = Math.min(row + 1, lines.size() - 1);
        int startingColumn = Math.max(0, column - 1);
        int finishingColumn = Math.min(column + 1, lines.get(0).length() - 1);

        for (int currentRow = startingRow; currentRow <= finishingRow; currentRow++) {
            Set<Integer> rowNumbers = new HashSet<>();
            for (int currentColumn = startingColumn; currentColumn <= finishingColumn; currentColumn++) {
                getPartNumber(lines.get(currentRow), currentColumn).ifPresent(rowNumbers::add);
            }
            partNumbers.addAll(rowNumbers);
        }

        return partNumbers.size();
    }

    private int getGearRatio(List<String> lines, int row, int column) {
        List<Integer> partNumbers = new ArrayList<>();

        int startingRow = Math.max(0, row - 1);
        int finishingRow = Math.min(row + 1, lines.size() - 1);
        int startingColumn = Math.max(0, column - 1);
        int finishingColumn = Math.min(column + 1, lines.get(0).length() - 1);

        for (int currentRow = startingRow; currentRow <= finishingRow; currentRow++) {
            Set<Integer> rowNumbers = new HashSet<>();
            for (int currentColumn = startingColumn; currentColumn <= finishingColumn; currentColumn++) {
                getPartNumber(lines.get(currentRow), currentColumn).ifPresent(rowNumbers::add);
            }
            partNumbers.addAll(rowNumbers);
        }

        return partNumbers.stream().reduce((a, b) -> a * b)
                .orElseThrow(() -> new RuntimeException("Could not find two gear ratios for row " + row + " and column " + column));
    }

    private Optional<Integer> getPartNumber(String line, int providedIndex) {
        if (!isDigit(line.charAt(providedIndex))) {
            return Optional.empty();
        }

        int firstDigitIndex = providedIndex;
        int lastDigitIndex = providedIndex;
        int currentIndex = firstDigitIndex - 1;
        while (currentIndex >= 0 && isDigit(line.charAt(currentIndex))) {
            firstDigitIndex = currentIndex--;
        }
        currentIndex = lastDigitIndex + 1;
        while (currentIndex <= line.length() - 1 && isDigit(line.charAt(currentIndex))) {
            lastDigitIndex = currentIndex++;
        }
        return Optional.of(Integer.parseInt(line.substring(firstDigitIndex, lastDigitIndex + 1)));
    }
}
