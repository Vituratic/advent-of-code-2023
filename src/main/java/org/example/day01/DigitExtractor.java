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
        return inputString.replaceAll("[A-Za-z]", "");
    }
}
