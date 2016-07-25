package com.mindtwister.mindtwister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    int userFinalScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        userFinalScore = getIntent().getIntExtra("score", 0);
        Log.i("GAMEOVER", "onCreate: " + userFinalScore);
        TextView tv = (TextView) findViewById(R.id.scoreText);
        tv.setText(String.valueOf(userFinalScore));
    }

    public void newGameOnClick(View view) {
        Intent intent = new Intent(this, PlayMenuActivity.class);
        startActivity(intent);
        finish();
    }

    public void mainMenuOnClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
