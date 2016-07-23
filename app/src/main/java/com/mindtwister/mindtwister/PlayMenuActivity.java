package com.mindtwister.mindtwister;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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
        Intent i = new Intent(this, DIfficultyLevelActivity.class);
        i.putExtra("gameSelector", 1);
        startActivity(i);
    }

    public void rmPlayOnClick(View view) {
        Intent i = new Intent(this, DIfficultyLevelActivity.class);
        i.putExtra("gameSelector", 2);
        startActivity(i);
    }
}
