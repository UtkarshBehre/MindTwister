package com.mindtwister.mindtwister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.mindtwister.mindtwister.generallayouts.ProfileActivity;
import com.mindtwister.mindtwister.managers.SessionManager;
import com.mindtwister.mindtwister.memorymatrix.MemoryMatrix33Activity;
//import com.mindtwister.mindtwister.rainbowmatrix.RainbowMatrixActivity;
//import com.mindtwister.mindtwister.sudoku.SudokuActivity;

public class DIfficultyLevelActivity extends AppCompatActivity {
    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty_level_selector);
        session = new SessionManager(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.difficulty_level_menu, menu);
        return true;
    }

    public void mmEasyOnClick(View view) {
        Intent intent;
        switch (getIntent().getIntExtra("gameSelector", 0)) {

            case 1:
                session.setDifficultyLevel(SessionManager.EASY);
                intent = new Intent(this, MemoryMatrix33Activity.class);
                startActivity(intent);
                finish();
                break;
//            case 2:
//                session.setDifficultyLevel(SessionManager.EASY);
//                intent = new Intent(this, RainbowMatrixActivity.class);
//                startActivity(intent);
//                break;
//            case 3:
//                session.setDifficultyLevel(SessionManager.EASY);
//                intent = new Intent(this, SudokuActivity.class);
//                startActivity(intent);
//                break;
            default:

        }


    }

    public void mmMediumOnClick(View view) {
        Intent intent;
        switch (getIntent().getIntExtra("gameSelector", 0)) {

            case 1:
                session.setDifficultyLevel(SessionManager.MEDIUM);
                intent = new Intent(this, MemoryMatrix33Activity.class);
                startActivity(intent);
                finish();
                break;
//            case 2:
//                session.setDifficultyLevel(SessionManager.MEDIUM);
//                intent = new Intent(this, RainbowMatrixActivity.class);
//                startActivity(intent);
//                break;
//            case 3:
//                session.setDifficultyLevel(SessionManager.MEDIUM);
//                intent = new Intent(this, SudokuActivity.class);
//                startActivity(intent);
//                break;
            default:

        }
    }

    public void mmHardOnClick(View view) {
        Intent intent;
        switch (getIntent().getIntExtra("gameSelector", 0)) {

            case 1:
                session.setDifficultyLevel(SessionManager.HARD);
                intent = new Intent(this, MemoryMatrix33Activity.class);
                startActivity(intent);
                finish();
                break;
//            case 2:
//                session.setDifficultyLevel(SessionManager.HARD);
//                intent = new Intent(this, RainbowMatrixActivity.class);
//                startActivity(intent);
//                break;
//            case 3:
//                session.setDifficultyLevel(SessionManager.HARD);
//                intent = new Intent(this, SudokuActivity.class);
//                startActivity(intent);
//                break;
            default:

        }
    }

    public void mmExtremeOnClick(View view) {
        Intent intent;
        switch (getIntent().getIntExtra("gameSelector", 0)) {

            case 1:
                session.setDifficultyLevel(SessionManager.EXTREME);
                intent = new Intent(this, MemoryMatrix33Activity.class);
                startActivity(intent);
                finish();
                break;
//            case 2:
//                session.setDifficultyLevel(SessionManager.EXTREME);
//                intent = new Intent(this, RainbowMatrixActivity.class);
//                startActivity(intent);
//                break;
//            case 3:
//                session.setDifficultyLevel(SessionManager.EXTREME);
//                intent = new Intent(this, SudokuActivity.class);
//                startActivity(intent);
//                break;
            default:

        }
    }

    public void mmImbalancedOnClick(View view) {
        Intent intent;
        switch (getIntent().getIntExtra("gameSelector", 0)) {

            case 1:
                session.setDifficultyLevel(SessionManager.IMBALANCED);
                intent = new Intent(this, MemoryMatrix33Activity.class);
                startActivity(intent);
                finish();
                break;
//            case 2:
//                session.setDifficultyLevel(SessionManager.IMBALANCED);
//                intent = new Intent(this, RainbowMatrixActivity.class);
//                startActivity(intent);
//                break;
//            case 3:
//                session.setDifficultyLevel(SessionManager.IMBALANCED);
//                intent = new Intent(this, SudokuActivity.class);
//                startActivity(intent);
//                break;
            default:

        }
    }

    public void signout(MenuItem item) {
        finish();
        session.logoutUser();
    }

    public void profile(MenuItem item) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }


    public void music(MenuItem item) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void instructions(MenuItem item){
        Intent intent = new Intent(this, InstructionsActivity.class);
        startActivity(intent);
    }
}
