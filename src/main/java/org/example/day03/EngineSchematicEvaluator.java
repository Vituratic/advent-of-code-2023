package org.example.day03;

import java.util.ArrayList;
import java.util.List;

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
}
