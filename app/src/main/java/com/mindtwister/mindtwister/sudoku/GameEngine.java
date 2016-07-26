package com.mindtwister.mindtwister.sudoku;

import android.content.Context;

import com.mindtwister.mindtwister.sudoku.sudokugrid.GameGrid;

/**
 * Created by SomyaMittal on 7/18/2016.
 */
public class GameEngine {
    private static GameEngine ourInstance;
    private GameGrid grid = null;
    private int selectedPosX = -1, selectedPosY = -1;

    private GameEngine() {
    }

    public static GameEngine getInstance() {
        if (ourInstance == null) {
            ourInstance = new GameEngine();
        }
        return ourInstance;
    }

    public void createGrid(Context context) {
        int[][] Sudoku = SudokuGenerator.getInstance().generatorGrid();
        Sudoku = SudokuGenerator.getInstance().removeElement(Sudoku);
        grid = new GameGrid(context);
        grid.setGrid(Sudoku);
    }

    public GameGrid getGrid() {
        return grid;
    }

    public void setSelectedPostion(int x, int y) {
        selectedPosX = x;
        selectedPosY = y;
    }

    public void setNumber(int number) {
        if (selectedPosX != -1 && selectedPosY != -1) {
            grid.setItem(selectedPosX, selectedPosY, number);
        }
        grid.checkGame();
    }


}
