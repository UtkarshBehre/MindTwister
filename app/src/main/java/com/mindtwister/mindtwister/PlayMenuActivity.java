package com.mindtwister.mindtwister;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.mindtwister.mindtwister.generallayouts.ProfileActivity;
import com.mindtwister.mindtwister.managers.SessionManager;

/**
 * Created by Utkarsh on 07-07-2016.
 */
public class PlayMenuActivity extends AppCompatActivity {
    SessionManager session;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.play_menu, menu);
        return true;
    }

    public void mmPlayOnClick(View view) {
        Intent i = new Intent(this, DIfficultyLevelActivity.class);
        i.putExtra("gameSelector", 1);
        startActivity(i);
    }

    public void rmPlayOnClick(View view) {
        Intent i = new Intent(this, DIfficultyLevelActivity.class);
        i.putExtra("gameSelector", 2);
        startActivity(i);
    }

    public void sudokuPlayOnClick(View view) {
        Intent i = new Intent(this, DIfficultyLevelActivity.class);
        i.putExtra("gameSelector", 3);
        startActivity(i);
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

}
