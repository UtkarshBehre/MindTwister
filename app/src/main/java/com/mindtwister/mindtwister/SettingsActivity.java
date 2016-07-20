package com.mindtwister.mindtwister;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Switch;

/**
 * Created by Utkarsh on 07-07-2016.
 */
public class SettingsActivity extends AppCompatActivity {
    SessionManager session;
    Switch musicSwitch;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        session = new SessionManager(this);
        musicSwitch = (Switch) findViewById(R.id.musicSwitch);
        if (session.getMusicStatus())
            musicSwitch.setChecked(true);
    }

    public void profileOnClick(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void creditsOnClick(View view) {
        Intent intent = new Intent(this, CreditsActivity.class);
        startActivity(intent);
    }

    public void privacyPolicyOnClick(View view) {
        Intent intent = new Intent(this, PrivacyPolicyActivity.class);
        startActivity(intent);
    }

    public void feedbackOnClick(View view) {
        Intent intent = new Intent(this, FeedbackActivity.class);
        startActivity(intent);
    }

    public void musicSwitchButton(View view) {
        if (musicSwitch.isChecked()) {
            session.setMusicStatus(true);
            MainActivity.stopMusic(this);
            MainActivity.startMusic(this, session.getMusicStatus());
        } else {
            session.setMusicStatus(false);
            MainActivity.stopMusic(this);
        }
    }
}
