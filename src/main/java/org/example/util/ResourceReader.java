package org.example.util;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ResourceReader {

    private static final String RESOURCE_STRING = "org/example/dayXX/input.txt";


    private ResourceReader() {
    }

    public static List<String> readAllLinesForDay(int day) {
        return readAllLinesFromResource(RESOURCE_STRING.replace("XX", String.valueOf(day)));
    }

    public static List<String> readAllLinesFromResource(String resourcePath) {
        try {
            Path path = getPath(resourcePath);
            return Files.readAllLines(path);
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not read resource", e);
        }
    }

    public static String readFromResource(String resourcePath) {
        try {
            Path path = getPath(resourcePath);
            return Files.readString(path);
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not read resource", e);
        }
    }

    private static Path getPath(String resourcePath) throws URISyntaxException {
        URL resourceUrl = ResourceReader.class.getClassLoader().getResource(resourcePath);
        if (resourceUrl == null) {
            throw new IllegalArgumentException("The resourcePath leads to a null URL: " + resourcePath);
        }
        return Paths.get(resourceUrl.toURI());
    }
}
