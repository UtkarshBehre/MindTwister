package com.mindtwister.mindtwister.memorymatrix;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mindtwister.mindtwister.R;
import com.mindtwister.mindtwister.memorymatrix.utility.UtilityMethodsForMemoryMatrix;

public class MemoryMatrix33Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_matrix_33);
        UtilityMethodsForMemoryMatrix.getGridSet(9);
    }
}
