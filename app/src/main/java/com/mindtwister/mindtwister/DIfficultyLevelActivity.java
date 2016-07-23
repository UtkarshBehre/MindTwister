package com.mindtwister.mindtwister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mindtwister.mindtwister.managers.SessionManager;
import com.mindtwister.mindtwister.memorymatrix.MemoryMatrix33Activity;
import com.mindtwister.mindtwister.rainbowmatrix.RainbowMatrixActivity;

public class DIfficultyLevelActivity extends AppCompatActivity {
    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memorymatrix__difficultylevel);
        session = new SessionManager(this);
    }

    public void mmEasyOnClick(View view) {
        Intent intent;
        switch (getIntent().getIntExtra("gameSelector", 0)) {

            case 1:
                session.setDifficultyLevel(SessionManager.EASY);
                intent = new Intent(this, MemoryMatrix33Activity.class);
                startActivity(intent);
                break;
            case 2:
                session.setDifficultyLevel(SessionManager.EASY);
                intent = new Intent(this, RainbowMatrixActivity.class);
                startActivity(intent);
                break;
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
                break;
            case 2:
                session.setDifficultyLevel(SessionManager.MEDIUM);
                intent = new Intent(this, RainbowMatrixActivity.class);
                startActivity(intent);
                break;
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
                break;
            case 2:
                session.setDifficultyLevel(SessionManager.HARD);
                intent = new Intent(this, RainbowMatrixActivity.class);
                startActivity(intent);
                break;
            default:

        }
    }
}