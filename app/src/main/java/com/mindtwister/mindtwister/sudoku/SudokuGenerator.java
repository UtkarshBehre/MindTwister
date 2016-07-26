package com.mindtwister.mindtwister.sudoku;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by SomyaMittal on 7/17/2016.
 */
public class SudokuGenerator {
    public static int numbersToRemove = 0;
    private static SudokuGenerator ourInstance = new SudokuGenerator();
    private ArrayList<ArrayList<Integer>> Availabe = new ArrayList<ArrayList<Integer>>();
    private Random rand = new Random();

    private SudokuGenerator() {
    }

    public static SudokuGenerator getInstance() {
        if (ourInstance == null) {
            ourInstance = new SudokuGenerator();
        }

        return ourInstance;
    }

    public int[][] generatorGrid() {
        int[][] Sudoku = new int[9][9];
        int currentPos = 0;
        clearGrid(Sudoku);
        while (currentPos < 81) {
            if (Availabe.get(currentPos).size() != 0) {
                int i = rand.nextInt(Availabe.get(currentPos).size());
                int number = Availabe.get(currentPos).get(i);

                if (!checkConflict(Sudoku, currentPos, number)) {
                    int xPos = currentPos % 9;
                    int yPos = currentPos / 9;
                    Sudoku[xPos][yPos] = number;
                    Availabe.get(currentPos).remove(i);
                    currentPos++;
                } else {
                    Availabe.get(currentPos).remove(i);
                }
            } else {
                for (int i = 1; i <= 9; i++) {
                    Availabe.get(currentPos).add(i);
                }
                currentPos--;
            }
        }
        return Sudoku;
    }

    public int[][] removeElement(int[][] Sudoku) {
        int i = 0;
        while (i < numbersToRemove) {
            int x = rand.nextInt(9);
            int y = rand.nextInt(9);

            if (Sudoku[x][y] != 0) {
                Sudoku[x][y] = 0;
                i++;
            }
        }
        return Sudoku;
    }

    private void clearGrid(int[][] Sudoku) {
        Availabe.clear();
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                Sudoku[x][y] = -1;
            }
        }
        for (int x = 0; x < 81; x++) {
            Availabe.add(new ArrayList<Integer>());
            for (int i = 1; i <= 9; i++) {
                Availabe.get(x).add(i);
            }
        }
    }

    private boolean checkConflict(int[][] Sudoku, int currentPos, final int number) {
        int xPos = currentPos % 9;
        int yPos = currentPos / 9;


        if (checkHorizontalConflict(Sudoku, xPos, yPos, number) || checkVerticalConflict(Sudoku, xPos, yPos, number) || checkRegionConflict(Sudoku, xPos, yPos, number)) {
            return true;
        }
        return false;
    }

    private boolean checkHorizontalConflict(final int[][] Sudoku, final int xPos, final int yPos, final int number) {
        for (int x = xPos - 1; x >= 0; x--) {
            if (number == Sudoku[x][yPos]) {
                return true;
            }
        }
        return false;
    }

    private boolean checkVerticalConflict(final int[][] Sudoku, final int xPos, final int yPos, final int number) {
        for (int y = yPos - 1; y >= 0; y--) {
            if (number == Sudoku[xPos][y]) {
                return true;
            }
        }
        return false;
    }

    private boolean checkRegionConflict(final int[][] Sudoku, final int xPos, final int yPos, final int number) {
        int xRegion = xPos / 3;
        int yRegion = yPos / 3;

        for (int x = xRegion * 3; x < xRegion * 3 + 3; x++) {
            for (int y = yRegion * 3; y < yRegion * 3 + 3; y++) {
                if ((x != xPos || y != yPos) && number == Sudoku[x][y]) {
                    return true;
                }
            }
        }
        return false;
    }

}
