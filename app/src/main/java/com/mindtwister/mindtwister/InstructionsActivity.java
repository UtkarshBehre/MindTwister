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
import com.mindtwister.mindtwister.memorymatrix.Memorymatrix_instructionActivity;
import com.mindtwister.mindtwister.rainbowmatrix.Rainbowmatrix_instruction_Activity;
import com.mindtwister.mindtwister.sudoku.Sudoku_instructionActivity;

/**
 * Created by Utkarsh on 07-07-2016.
 */
public class InstructionsActivity extends AppCompatActivity {

    SessionManager session;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.memory_matrix_menu, menu);
        return true;
    }

    public void mmInstructionsOnClick(View view) {
        Intent i = new Intent(this, Memorymatrix_instructionActivity.class);
        startActivity(i);
    }

    public void rmInstructionsOnClick(View view) {
        Intent i = new Intent(this, Rainbowmatrix_instruction_Activity.class);
        startActivity(i);
    }

    public void sudokuInstructionsOnClick(View view) {
        Intent i = new Intent(this, Sudoku_instructionActivity.class);
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

    public void instructions(MenuItem item){
        Intent intent = new Intent(this, InstructionsActivity.class);
        startActivity(intent);
    }


}

