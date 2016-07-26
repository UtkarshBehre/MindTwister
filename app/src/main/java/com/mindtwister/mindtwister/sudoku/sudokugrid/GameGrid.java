package com.mindtwister.mindtwister.sudoku.sudokugrid;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.mindtwister.mindtwister.GameOverActivity;
import com.mindtwister.mindtwister.managers.DBHandler;
import com.mindtwister.mindtwister.managers.SessionManager;
import com.mindtwister.mindtwister.managers.SudokuScores;
import com.mindtwister.mindtwister.sudoku.SudokuChecker;
import com.mindtwister.mindtwister.sudoku.SudokuDifficultyParameters;

import java.util.HashMap;

/**
 * Created by SomyaMittal on 7/17/2016.
 */
public class GameGrid {
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
            SessionManager session = new SessionManager(context);
            long finishTime = System.currentTimeMillis();
            long timeTaken = finishTime - session.getStartTime();
            int score = 200000000 / 600000;
            int difficultyMultiplier = 0;
            switch (session.getDifficultyLevel()) {
                case SessionManager.EASY:
                    difficultyMultiplier = SudokuDifficultyParameters.DIFFICULTYMULTIPLIEREASY;
                    break;
                case SessionManager.MEDIUM:

                    difficultyMultiplier = SudokuDifficultyParameters.DIFFICULTYMULTIPLIERMEDIUM;
                    break;
                case SessionManager.HARD:
                    difficultyMultiplier = SudokuDifficultyParameters.DIFFICULTYMULTIPLIERHARD;
                    break;
                case SessionManager.EXTREME:
                    difficultyMultiplier = SudokuDifficultyParameters.DIFFICULTYMULTIPLIEREXTREME;
                    break;
                case SessionManager.IMBALANCED:

                    difficultyMultiplier = SudokuDifficultyParameters.DIFFICULTYMULTIPLIERIMBALANCED;
                    break;
                default:
            }


            score *= difficultyMultiplier;

            //saving user data in the pojo class to save his/her score in database
            //beans class to store data
            SudokuScores sudokuScores = new SudokuScores();

            //retrieving general user info from session
            HashMap<String, String> userInfo = session.getUserDetails();

            //setting up the beans class
            sudokuScores.setUser_nickname(userInfo.get(SessionManager.KEY_NICKNAME));
            sudokuScores.setUser_email(SessionManager.KEY_EMAIL);
            sudokuScores.setScore(score);
            sudokuScores.setDifficulty_level(session.getDifficultyLevel());
            sudokuScores.setGame_name(SessionManager.SUDOKU);
            DBHandler db = new DBHandler(context);
            db.addSudokuGameScore(sudokuScores);

            //============GAMEOVER HERE============================
            //sending score via intent to the gamevoer screen to be displayed
            Intent gameOver = new Intent(context, GameOverActivity.class);
            Log.i("USERINPUT", "final score: " + score);
            gameOver.putExtra("score", score);
            context.startActivity(gameOver);
            Toast.makeText(context, "You Solved Sudoku", Toast.LENGTH_LONG).show();
        }
    }
}
