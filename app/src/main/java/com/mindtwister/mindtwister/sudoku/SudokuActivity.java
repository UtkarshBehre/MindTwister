package com.mindtwister.mindtwister.sudoku;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import com.mindtwister.mindtwister.R;
import com.mindtwister.mindtwister.managers.SessionManager;
import com.mindtwister.mindtwister.rainbowmatrix.RainbowMatrixGameDifficultyParameters;
import com.mindtwister.mindtwister.sudoku.sudokugrid.GameGrid;

public class SudokuActivity extends AppCompatActivity {
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sudoku);
        session = new SessionManager(this);
        switch (session.getDifficultyLevel()) {
            case SessionManager.EASY:

                SudokuGenerator.numbersToRemove = SudokuDifficultyParameters.REMOVENUMBERSEASY;
                GameGrid.sudokuDifficultyMultiplier = SudokuDifficultyParameters.DIFFICULTYMULTIPLIEREASY;

                break;
            case SessionManager.MEDIUM:

                SudokuGenerator.numbersToRemove = SudokuDifficultyParameters.DIFFICULTYMULTIPLIERMEDIUM;
                GameGrid.sudokuDifficultyMultiplier = SudokuDifficultyParameters.DIFFICULTYMULTIPLIERMEDIUM;

                break;
            case SessionManager.HARD:
                SudokuGenerator.numbersToRemove = SudokuDifficultyParameters.REMOVENUMBERSHARD;
                GameGrid.sudokuDifficultyMultiplier = SudokuDifficultyParameters.DIFFICULTYMULTIPLIERHARD;
                break;
            case SessionManager.EXTREME:

                SudokuGenerator.numbersToRemove = SudokuDifficultyParameters.REMOVENUMBERSEREXTREME;
                GameGrid.sudokuDifficultyMultiplier = SudokuDifficultyParameters.DIFFICULTYMULTIPLIEREXTREME;
                break;
            case SessionManager.IMBALANCED:

                SudokuGenerator.numbersToRemove = SudokuDifficultyParameters.REMOVENUMBERSIMBALANCED;
                GameGrid.sudokuDifficultyMultiplier = SudokuDifficultyParameters.DIFFICULTYMULTIPLIERIMBALANCED;
                break;
            default:
        }
        GameEngine.getInstance().createGrid(this);

    }

}
