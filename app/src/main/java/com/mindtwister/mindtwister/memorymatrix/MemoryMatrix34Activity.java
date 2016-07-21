package com.mindtwister.mindtwister.memorymatrix;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;
import com.mindtwister.mindtwister.R;

public class MemoryMatrix34Activity extends AppCompatActivity {
    public ArrayList<Button> buttonsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_matrix_34);

        buttonsList = new ArrayList<>();
        Button b1 = (Button) findViewById(R.id.tiles_11_btn);
        buttonsList.add(0, b1);
        Button b2 = (Button) findViewById(R.id.tiles_12_btn);
        buttonsList.add(1, b2);
        Button b3 = (Button) findViewById(R.id.tiles_13_btn);
        buttonsList.add(2, b3);
        Button b4 = (Button) findViewById(R.id.tiles_14_btn);
        buttonsList.add(3, b4);


        Button b5 = (Button) findViewById(R.id.tiles_21_btn);
        buttonsList.add(4, b5);
        Button b6 = (Button) findViewById(R.id.tiles_22_btn);
        buttonsList.add(5, b6);
        Button b7 = (Button) findViewById(R.id.tiles_23_btn);
        buttonsList.add(6, b7);
        Button b8 = (Button) findViewById(R.id.tiles_24_btn);
        buttonsList.add(7, b8);


        Button b9 = (Button) findViewById(R.id.tiles_31_btn);
        buttonsList.add(8, b9);
        Button b10 = (Button) findViewById(R.id.tiles_32_btn);
        buttonsList.add(9, b10);
        Button b11 = (Button) findViewById(R.id.tiles_33_btn);
        buttonsList.add(10, b11);
        Button b12 = (Button) findViewById(R.id.tiles_34_btn);
        buttonsList.add(11, b12);

    }
}
