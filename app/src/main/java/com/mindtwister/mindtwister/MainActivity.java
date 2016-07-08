package com.mindtwister.mindtwister;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public MediaPlayer mediaPlayer;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(this, R.raw.inception_horn);
        mediaPlayer.start();
    }

    public void quitOnClick(View view) {
        finish();
        System.exit(0);
    }

    public void aboutOnClick(View view) {
        Intent intent = new Intent(this, AboutActivity.class);
        mediaPlayer.start();
        startActivity(intent);
    }

    public void settingsOnClick(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void playOnClick(View view) {
        Intent intent = new Intent(this, PlayMenuActivity.class);
        startActivity(intent);
    }

    public void instructionsOnClick(View view) {
        Intent intent = new Intent(this, InstructionsActivity.class);
        startActivity(intent);
    }
}
