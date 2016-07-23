package com.mindtwister.mindtwister;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GameOverActivity extends AppCompatActivity {

    int currentUserScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        currentUserScore = getIntent().getIntExtra("score", 0);

    }
}
