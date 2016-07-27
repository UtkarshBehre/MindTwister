package com.mindtwister.mindtwister;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.mindtwister.mindtwister.generallayouts.AboutActivity;
import com.mindtwister.mindtwister.generallayouts.ProfileActivity;
import com.mindtwister.mindtwister.managers.SessionManager;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    static MediaPlayer mp;
    //for debugging
    private final String TAG = "in MainActivity.java";
    //public MediaPlayer mediaPlayer;
    SessionManager session;

    //Start Music method
    public static void startMusic(Context context, boolean musicStatus) {
        if (musicStatus) {
            mp = MediaPlayer.create(context, R.raw.play_background_music);
            mp.setLooping(true);
            mp.start();
        }
    }

    //Stop Music method
    public static void stopMusic(Context context) {
        if (mp != null) {
            if (mp.isPlaying()) {
                mp.stop();
            }
        }
    }

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
        session.checkLogin(this);

        //play music unless its off in settings
        MainActivity.startMusic(this, session.getMusicStatus());

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mp != null) {
            if (mp.isPlaying()) {
                mp.stop();
            }
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

    public void viewHighScoresOnClick(View view) {
        Intent intent = new Intent(this, viewHighScoresSelector.class);
        startActivity(intent);
    }
}
