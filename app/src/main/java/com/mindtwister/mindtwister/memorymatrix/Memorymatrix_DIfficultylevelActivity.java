package com.mindtwister.mindtwister.memorymatrix;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mindtwister.mindtwister.R;
import com.mindtwister.mindtwister.managers.SessionManager;

public class Memorymatrix_DIfficultylevelActivity extends AppCompatActivity {
    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memorymatrix__difficultylevel);
        session = new SessionManager(this);
    }

    public void mmEasyOnClick(View view) {

        session.setDifficultyLevel(SessionManager.EASY);
        Intent intent = new Intent(this, MemoryMatrix33Activity.class);
        startActivity(intent);
    }

    public void mmMediumOnClick(View view) {
        session.setDifficultyLevel(SessionManager.MEDIUM);
        Intent intent = new Intent(this, MemoryMatrix33Activity.class);
        startActivity(intent);
    }

    public void mmHardOnClick(View view) {
        session.setDifficultyLevel(SessionManager.HARD);
        Intent intent = new Intent(this, MemoryMatrix33Activity.class);
        startActivity(intent);
    }
}
