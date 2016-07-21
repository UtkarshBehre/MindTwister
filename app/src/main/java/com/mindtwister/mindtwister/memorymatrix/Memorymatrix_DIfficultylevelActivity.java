package com.mindtwister.mindtwister.memorymatrix;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mindtwister.mindtwister.R;

public class Memorymatrix_DIfficultylevelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memorymatrix__difficultylevel);
    }

    public void mmEasyOnClick(View view) {
        Intent intent = new Intent(this, MemoryMatrix33Activity.class);
        startActivity(intent);
    }
}
