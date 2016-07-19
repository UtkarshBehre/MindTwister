package com.mindtwister.mindtwister;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Utkarsh on 07-07-2016.
 */
public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
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
}
