package com.mindtwister.mindtwister.sudoku;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mindtwister.mindtwister.R;
import com.mindtwister.mindtwister.managers.SessionManager;

public class SudokuActivity extends AppCompatActivity {
    public int numbersToRemove;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sudoku);
        GameEngine.getInstance().createGrid(this);

    }

}
