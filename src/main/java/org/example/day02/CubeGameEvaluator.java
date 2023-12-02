package org.example.day02;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CubeGameEvaluator {

    private final int amountOfRedCubes;
    private final int amountOfGreenCubes;
    private final int amountOfBlueCubes;

    public boolean evalutePossibility(String inputLine) {
        String[] split = inputLine.split(":");
        String[] sets = split[1].split(";");
        for (String set : sets) {
            String[] cubes = set.split(",");
            for (String cube : cubes) {
                if (cube.endsWith("red")) {
                    if (getAmountOfCubes(cube) > amountOfRedCubes) return false;
                } else if (cube.endsWith("green")) {
                    if (getAmountOfCubes(cube) > amountOfGreenCubes) return false;
                } else {
                    if (getAmountOfCubes(cube) > amountOfBlueCubes) return false;
                }
            }
        }
        return true;
    }

    private int getAmountOfCubes(String cube) {
        String amountOfCubes = cube.trim().split("\\s")[0];
        return Integer.parseInt(amountOfCubes);
    }

    public int getMinimumSetPower(String inputLine) {
        String[] split = inputLine.split(":");
        String[] sets = split[1].split(";");
        int minimumAmountOfRedCubes = 0;
        int minimumAmountOfGreenCubes = 0;
        int minimumAmountOfBlueCubes = 0;
        for (String set : sets) {
            String[] cubes = set.split(",");
            for (String cube : cubes) {
                if (cube.endsWith("red")) {
                    minimumAmountOfRedCubes = Math.max(minimumAmountOfRedCubes, getAmountOfCubes(cube));
                } else if (cube.endsWith("green")) {
                    minimumAmountOfGreenCubes = Math.max(minimumAmountOfGreenCubes, getAmountOfCubes(cube));
                } else {
                    minimumAmountOfBlueCubes = Math.max(minimumAmountOfBlueCubes, getAmountOfCubes(cube));
                }
            }
        }
        return minimumAmountOfRedCubes * minimumAmountOfGreenCubes * minimumAmountOfBlueCubes;
    }
}
