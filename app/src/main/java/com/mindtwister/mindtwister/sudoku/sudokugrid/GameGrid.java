package com.mindtwister.mindtwister.sudoku.sudokugrid;

import android.content.Context;
import android.widget.Toast;

import com.mindtwister.mindtwister.sudoku.SudokuChecker;

/**
 * Created by SomyaMittal on 7/17/2016.
 */
public class GameGrid {
    public static int sudokuDifficultyMultiplier = 1;
    private SudokuCell[][] Sudoku = new SudokuCell[9][9];
    private Context context;

    public GameGrid(Context context) {
        this.context = context;
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                Sudoku[x][y] = new SudokuCell(context);
            }
        }
    }

    public SudokuCell[][] getGrid() {
        return Sudoku;
    }

    public void setGrid(int[][] grid) {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                Sudoku[x][y].setInitValue(grid[x][y]);
                if (grid[x][y] != 0) {
                    Sudoku[x][y].setNotModifiable();
                }
            }
        }
    }

    public SudokuCell getItem(int x, int y) {
        return Sudoku[x][y];
    }

    public SudokuCell getItem(int postion) {
        int x = postion % 9;
        int y = postion / 9;

        return Sudoku[x][y];

    }

    public void setItem(int x, int y, int number) {
        Sudoku[x][y].setValue(number);
    }

    public void checkGame() {
        int[][] sudGrid = new int[9][9];
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                sudGrid[x][y] = getItem(x, y).getValue();
            }
        }
        if (SudokuChecker.getInstance().checkSudoku(sudGrid)) {

            Toast.makeText(context, "You Solved Sudoku", Toast.LENGTH_LONG).show();
        }
    }
}
