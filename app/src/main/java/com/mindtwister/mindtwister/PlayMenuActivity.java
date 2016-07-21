package com.mindtwister.mindtwister;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mindtwister.mindtwister.memorymatrix.Memorymatrix_DIfficultylevelActivity;

/**
 * Created by Utkarsh on 07-07-2016.
 */
public class PlayMenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_menu);
    }

    public void mmPlayOnClick(View view) {
        Intent i = new Intent(this, Memorymatrix_DIfficultylevelActivity.class);
        startActivity(i);
    }
}
