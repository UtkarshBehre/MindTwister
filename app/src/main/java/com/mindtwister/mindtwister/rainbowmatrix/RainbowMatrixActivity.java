package com.mindtwister.mindtwister.rainbowmatrix;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import java.util.Collections;
import java.util.ArrayList;

import com.mindtwister.mindtwister.R;
import com.mindtwister.mindtwister.managers.SessionManager;

import java.util.List;


/**
 * Created by Utkarsh on 17-07-2016.
 */
public class RainbowMatrixActivity extends AppCompatActivity {

    private List<Integer> colorSequence;
    private int score;
    private int trials;
    private int counter;
    private long startTime;
    private long finishTime;
    private long totalTime;
    private long timeTaken;
    private long colorFlashTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rainbow_matrix_normal);
        colorSequence = new ArrayList<>();

    }

    public int getRandomColor(int noOfColors) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i <= noOfColors; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        return list.get(0);

    }
    class Counter extends CountDownTimer{
        public int i=0;
        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public Counter(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            /*


                    Implement text view here


             */
        }

        @Override
        public void onTick(long millisUntilFinished) {

            switch(colorSequence.get(i))
            {
                case 1:
                    /*

                        Remove me
                        b1.setBAckground(Red);

                     */
                    break;
                case 2:
                    /*

                        Remove me
                        b1.setBAckground(green);

                     */
                    break;
                case 3:
                    /*

                        Remove me
                        b1.setBAckground(blue);

                     */
                    break;
                case 4:
                    /*

                        Remove me
                        b1.setBAckground(yellow);

                     */
                    break;
                default:
                    i++;
            }


        }

        @Override
        public void onFinish() {

            /*
                     Remove me
                     b1.setBAckground(default);

             */

            counter=0;
            startTime=System.currentTimeMillis();
        }
    }

    /*private void setDifficultyLevelParameters(String currentDifficultyLevel)
    {
        swtich(currentDifficultyLevel)
        {
            case 1:
                SessionManager
        }
    }*/
}


