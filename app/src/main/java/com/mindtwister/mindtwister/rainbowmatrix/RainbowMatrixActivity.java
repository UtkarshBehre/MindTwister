package com.mindtwister.mindtwister.rainbowmatrix;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.mindtwister.mindtwister.GameOverActivity;
import com.mindtwister.mindtwister.R;
import com.mindtwister.mindtwister.managers.RainbowMatrixScore;
import com.mindtwister.mindtwister.managers.SessionManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Utkarsh on 17-07-2016.
 */
public class RainbowMatrixActivity extends AppCompatActivity {
    SessionManager session;
    private List<Integer> colorSequence;
    private int score;
    private int userListCounter;
    private int trials;
    private int noOfColorsUsed;
    private long totalTime;
    private long startTime;
    private long finishTime;
    private long timeTaken;
    private long colorFlashTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rainbow_matrix_normal);
        session = new SessionManager(this);

        //check intent work
        int i = getIntent().getIntExtra("check", 0);
        Log.i("RAINBOW MATRIX", "onCreate: check value is 123?" + i);

        setDifficultyParameters();

        colorSequence = new ArrayList<Integer>();

        totalTime = 0;//initiating both to zero when the application is started
        score = 0;
    }


    //method to be called on a button click passing the color reference number i.e. 1,2,3,4/1,2,3,4,5/1,2,3,4,5,6
    public void colorClickedChecked(int i) {

        if (colorSequence.get(userListCounter) == i) {

            //consider userListCounter as a global looper for listAdapter
            userListCounter++;
        } else {

            //total trials left must be reduced if user does it wrong and patter should repeat itself when he does
            trials--;
            String trial = String.valueOf(trials);
            //tv.setText(trial);

            if (trials > 0) {
                flashListColors();
            } else {
                /*

                    send to game over here!!!!

                 */
            }

            //calculating final score
            finishTime = System.currentTimeMillis();
            timeTaken = finishTime - startTime;
            score += (colorSequence.size() * 1000000) / timeTaken;

            //saving user data in the pojo class to save his/her score in database

            //beans class to store data
            RainbowMatrixScore rms = new RainbowMatrixScore();

            //retrieving general user info from session
            HashMap<String, String> userInfo = session.getUserDetails();

            //setting up the beans class
            rms.setUser_nickname(userInfo.get(SessionManager.KEY_NICKNAME));
            rms.setUser_email(SessionManager.KEY_EMAIL);
            rms.setScore(session.getScore());
            rms.setDifficultylevel(session.getDifficultyLevel());

            /*

                //save score to database
                DBHandler.setRainbowMatrixScore(rms);

             */

            //============GAMEOVER HERE============================
            //sending score via intent to the gamevoer screen to be displayed
            Intent gameOver = new Intent(this, GameOverActivity.class);
            gameOver.putExtra("score", score);
            startActivity(gameOver);
        }

        if (userListCounter == colorSequence.size()) {

            //adding to score if user is success full in identifying a pattern
            finishTime = System.currentTimeMillis();
            totalTime += finishTime - startTime;
            score += (colorSequence.size() * 1000000) / timeTaken;

            //adding to the new color to the list of colors in the current sequence list
            colorSequence.add(getRandomColor(noOfColorsUsed));
            flashListColors();


        }
    }

    //this method is called when we want to flash the initial color sequence or sequences later on.
    public void flashListColors() {
        Counter counter = new Counter(colorSequence.size() * colorFlashTime, colorFlashTime);
        counter.start();

    }

    //gives random color out of given list of color codes
    public int getRandomColor(int noOfColors) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i <= noOfColors; i++) {
            list.add(i);
        }

        Collections.shuffle(list);
        return list.get(0);

    }

    public void setDifficultyParameters() {
        switch (session.getDifficultyLevel()) {
            case SessionManager.EASY:
                noOfColorsUsed = 4;
                colorFlashTime = 2000;
                break;
            case SessionManager.MEDIUM:
                noOfColorsUsed = 4;
                colorFlashTime = 1500;
                break;
            case SessionManager.HARD:
                noOfColorsUsed = 4;
                colorFlashTime = 1000;
                break;
            default:
                noOfColorsUsed = 4;
                colorFlashTime = 1500;
        }
    }

    //this counter class is used to flash the color sequence on our main tile
    class Counter extends CountDownTimer {

        //this variable is created to loop through list colorSequence
        public int i = 0;

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

            switch (colorSequence.get(i)) {
                //1 stands for color red
                case 1:
                    /*

                        Remove me
                        //main display tile color changed
                        b1.setBAckground(Red);

                     */
                    break;
                //2 stands for green
                case 2:
                    /*

                        Remove me
                        b1.setBAckground(green);

                     */
                    break;
                //3 stands for blue
                case 3:
                    /*

                        Remove me
                        b1.setBAckground(blue);

                     */
                    break;
                //4 stands for yellow
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
                     //reset the color of display tile
                     b1.setBAckground(default);

             */

            //this counter is used to check the current number where user is selecting color
            userListCounter = 0;

            //note the current time of the system and set the startTime used later to add up in time left after removing this from finish time
            startTime = System.currentTimeMillis();
        }
    }
}
