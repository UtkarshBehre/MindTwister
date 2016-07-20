package com.mindtwister.mindtwister;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Switch;

import com.mindtwister.mindtwister.generallayouts.CreditsActivity;
import com.mindtwister.mindtwister.generallayouts.FeedbackActivity;
import com.mindtwister.mindtwister.generallayouts.PrivacyPolicyActivity;
import com.mindtwister.mindtwister.generallayouts.ProfileActivity;
import com.mindtwister.mindtwister.managers.SessionManager;

/**
 * Created by Utkarsh on 07-07-2016.
 */
public class SettingsActivity extends AppCompatActivity {
    SessionManager session;
    Switch musicSwitch;
    Switch soundFxSwitch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        session = new SessionManager(this);
        musicSwitch = (Switch) findViewById(R.id.musicSwitch);
        soundFxSwitch = (Switch) findViewById(R.id.soundSwitch);
        if (session.getMusicStatus())
            musicSwitch.setChecked(true);
        if (session.getSoundFxStatus())
            soundFxSwitch.setChecked(true);
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

    public void soundFxSwitchButton(View view) {
        if (soundFxSwitch.isChecked())
            session.setSoundStatus(true);
        else
            session.setSoundStatus(false);
    }
}
