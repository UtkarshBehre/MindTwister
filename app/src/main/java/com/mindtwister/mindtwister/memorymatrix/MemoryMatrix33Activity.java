package com.mindtwister.mindtwister.memorymatrix;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.mindtwister.mindtwister.R;
import com.mindtwister.mindtwister.memorymatrix.utility.UtilityMethodsForMemoryMatrix;

import java.util.ArrayList;
import java.util.HashMap;

public class MemoryMatrix33Activity extends AppCompatActivity {

    public HashMap<Integer, Boolean> gridSet;
    public ArrayList<Button> buttonsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_matrix_33);
        UtilityMethodsForMemoryMatrix utilityMethodsForMemoryMatrix = new UtilityMethodsForMemoryMatrix();
        gridSet = utilityMethodsForMemoryMatrix.RandomTilesSelector(9, 3);
        buttonsList = new ArrayList<>();
        Button b1 = (Button) findViewById(R.id.tiles_11_btn);
        buttonsList.add(0, b1);
        Button b2 = (Button) findViewById(R.id.tiles_12_btn);
        buttonsList.add(1, b2);
        Button b3 = (Button) findViewById(R.id.tiles_13_btn);
        buttonsList.add(2, b3);
        Button b4 = (Button) findViewById(R.id.tiles_21_btn);
        buttonsList.add(3, b4);
        Button b5 = (Button) findViewById(R.id.tiles_22_btn);
        buttonsList.add(4, b5);
        Button b6 = (Button) findViewById(R.id.tiles_23_btn);
        buttonsList.add(5, b6);
        Button b7 = (Button) findViewById(R.id.tiles_31_btn);
        buttonsList.add(6, b7);
        Button b8 = (Button) findViewById(R.id.tiles_32_btn);
        buttonsList.add(7, b8);
        Button b9 = (Button) findViewById(R.id.tiles_33_btn);
        buttonsList.add(8, b9);
        flashTheTiles(gridSet, buttonsList, 6000);
    }

    //this method is used to flash the tiles and is called in onCreate
    private void flashTheTiles(HashMap<Integer, Boolean> gridSet, ArrayList<Button> buttonsList, long tilesFlashingTime) {
        Counter counter = new Counter(tilesFlashingTime, 1000, gridSet, buttonsList);
        counter.start();
    }

    public void b1OnClick(View view) {
        Button b = (Button) findViewById(R.id.tiles_11_btn);
        b.setBackgroundResource(R.color.appAccent400);
    }

    //CounterDownTimer class to flash tiles
    public class Counter extends CountDownTimer {
        HashMap<Integer, Boolean> gridSet;
        ArrayList<Button> buttonsList;

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public Counter(long millisInFuture, long countDownInterval, HashMap<Integer, Boolean> gs, ArrayList<Button> bl) {
            super(millisInFuture, countDownInterval);
            this.gridSet = new HashMap<>();
            this.gridSet = gs;
            this.buttonsList = new ArrayList<>();
            this.buttonsList = bl;
            //this loop loops for all the buttons in the grid
            for (int i = 0; i < buttonsList.size(); i++) {
                Button tile = buttonsList.get(i);
                tile.setEnabled(false);

                if (gridSet.get(i)) {
                    //=====================SETTING COLOR OF TILE TO FLASH HERE====================
                    tile.setBackgroundResource(R.color.appAccent400);
                }

            }
        }

        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            //this loop loops for all the buttons in the grid
            for (int i = 0; i < buttonsList.size(); i++) {
                Button tile = buttonsList.get(i);
                tile.setEnabled(true);

                if (gridSet.get(i)) {
                    //=====================SETTING COLOR OF TILE TO FLASH HERE====================
                    tile.setBackgroundResource(R.color.memory_matrixs_tile_default_color);
                }

            }
        }

    }

}
