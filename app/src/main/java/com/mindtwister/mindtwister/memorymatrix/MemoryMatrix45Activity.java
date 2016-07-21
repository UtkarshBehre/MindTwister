package com.mindtwister.mindtwister.memorymatrix;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;
import com.mindtwister.mindtwister.R;

public class MemoryMatrix45Activity extends AppCompatActivity {
    public ArrayList<Button> buttonsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_matrix_45);

        buttonsList = new ArrayList<>();
        Button b1 = (Button) findViewById(R.id.tiles_11_btn);
        buttonsList.add(0, b1);
        Button b2 = (Button) findViewById(R.id.tiles_12_btn);
        buttonsList.add(1, b2);
        Button b3 = (Button) findViewById(R.id.tiles_13_btn);
        buttonsList.add(2, b3);
        Button b4 = (Button) findViewById(R.id.tiles_14_btn);
        buttonsList.add(3, b4);
        Button b5 = (Button) findViewById(R.id.tiles_15_btn);
        buttonsList.add(4, b5);


        Button b6 = (Button) findViewById(R.id.tiles_21_btn);
        buttonsList.add(5, b6);
        Button b7 = (Button) findViewById(R.id.tiles_22_btn);
        buttonsList.add(6, b7);
        Button b8 = (Button) findViewById(R.id.tiles_23_btn);
        buttonsList.add(7, b8);
        Button b9 = (Button) findViewById(R.id.tiles_24_btn);
        buttonsList.add(8, b9);
        Button b10 = (Button) findViewById(R.id.tiles_25_btn);
        buttonsList.add(9, b10);


        Button b11 = (Button) findViewById(R.id.tiles_31_btn);
        buttonsList.add(10, b11);
        Button b12 = (Button) findViewById(R.id.tiles_32_btn);
        buttonsList.add(11, b12);
        Button b13 = (Button) findViewById(R.id.tiles_33_btn);
        buttonsList.add(12, b13);
        Button b14 = (Button) findViewById(R.id.tiles_34_btn);
        buttonsList.add(13, b14);
        Button b15 = (Button) findViewById(R.id.tiles_35_btn);
        buttonsList.add(14, b15);


        Button b16 = (Button) findViewById(R.id.tiles_41_btn);
        buttonsList.add(15, b16);
        Button b17 = (Button) findViewById(R.id.tiles_42_btn);
        buttonsList.add(16, b17);
        Button b18 = (Button) findViewById(R.id.tiles_43_btn);
        buttonsList.add(17, b18);
        Button b19 = (Button) findViewById(R.id.tiles_44_btn);
        buttonsList.add(18, b19);
        Button b20 = (Button) findViewById(R.id.tiles_45_btn);
        buttonsList.add(19, b20);

    }
}
