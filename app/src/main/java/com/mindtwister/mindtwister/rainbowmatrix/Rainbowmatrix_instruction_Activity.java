package com.mindtwister.mindtwister.rainbowmatrix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.mindtwister.mindtwister.R;
import com.mindtwister.mindtwister.SettingsActivity;
import com.mindtwister.mindtwister.generallayouts.ProfileActivity;
import com.mindtwister.mindtwister.managers.SessionManager;

public class Rainbowmatrix_instruction_Activity extends AppCompatActivity {
SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rainbowmatrix_instruction_);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.raibow_matrix_instructions_menu, menu);
        return true;
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
