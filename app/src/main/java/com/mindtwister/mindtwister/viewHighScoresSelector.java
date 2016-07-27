package com.mindtwister.mindtwister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class viewHighScoresSelector extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_high_scores_selector);
    }

    public void viewMemoryMatrixHighScoresOnClick(View view) {
        Intent intent = new Intent(this, ListViewActivity.class);
        intent.putExtra("game", 1);
        startActivity(intent);

    }

    public void viewRainbowMatrixHighScoresOnClick(View view) {
        Intent intent = new Intent(this, ListViewActivity.class);
        intent.putExtra("game", 2);
        startActivity(intent);
    }

    public void viewSudokuHighScoresOnClick(View view) {
        Intent intent = new Intent(this, ListViewActivity.class);
        intent.putExtra("game", 3);
        startActivity(intent);
    }
}
