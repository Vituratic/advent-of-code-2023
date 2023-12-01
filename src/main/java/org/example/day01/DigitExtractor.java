package org.example.day01;

public class DigitExtractor {
    public String findFirstDigit(String inputString) {
        return "" + removeCharactersFromString(inputString).charAt(0);
    }

    public String findLastDigit(String inputString) {
        String strippedString = removeCharactersFromString(inputString);
        return "" + strippedString.charAt(strippedString.length() - 1);
    }

    private String removeCharactersFromString(String inputString) {
        return replaceAllWrittenNumbersByDigits(inputString).replaceAll("[A-Za-z]", "");
    }

    private String replaceAllWrittenNumbersByDigits(String inputString) {
        return inputString.replace("one", "o1e")
                .replace("two", "t2o")
                .replace("three", "t3e")
                .replace("four", "f4r")
                .replace("five", "f5e")
                .replace("six", "s6x")
                .replace("seven", "s7n")
                .replace("eight", "e8t")
                .replace("nine", "n9e");
    }
}
