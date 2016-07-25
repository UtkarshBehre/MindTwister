package com.mindtwister.mindtwister.memorymatrix;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.mindtwister.mindtwister.R;
import com.mindtwister.mindtwister.SettingsActivity;
import com.mindtwister.mindtwister.generallayouts.ProfileActivity;
import com.mindtwister.mindtwister.managers.SessionManager;

public class Memorymatrix_instructionActivity extends AppCompatActivity {
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memorymatrix_instruction);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.memory_matrix_menu, menu);
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
