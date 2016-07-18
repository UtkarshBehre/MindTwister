package com.mindtwister.mindtwister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    //for debugging
    private final String TAG = "in MainActivity.java";
    //public MediaPlayer mediaPlayer;
    SessionManager session;

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
        session = new SessionManager(this);
        session.checkLogin();

        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // name
        String name = user.get(SessionManager.KEY_NAME);

        //nickname
        String nickname = user.get(SessionManager.KEY_NICKNAME);

        // email
        String email = user.get(SessionManager.KEY_EMAIL);
        Log.i(TAG, "name: " + name + "nickname = " + nickname + "email = " + email);
    }

    public void quitOnClick(View view) {
        finish();
        System.exit(0);
    }

    public void aboutOnClick(View view) {
        Intent intent = new Intent(this, AboutActivity.class);
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
