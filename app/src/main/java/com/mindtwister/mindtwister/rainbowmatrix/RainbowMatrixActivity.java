package com.mindtwister.mindtwister.rainbowmatrix;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mindtwister.mindtwister.GameOverActivity;
import com.mindtwister.mindtwister.R;
import com.mindtwister.mindtwister.SettingsActivity;
import com.mindtwister.mindtwister.generallayouts.ProfileActivity;
import com.mindtwister.mindtwister.managers.DBHandler;
import com.mindtwister.mindtwister.managers.RainbowMatrixScores;
import com.mindtwister.mindtwister.managers.SessionManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Utkarsh on 17-07-2016.
 */
public class RainbowMatrixActivity extends AppCompatActivity {

    public Button displayTile;
    public Button b1, b2, b3, b4;
    TextView difficultyText;
    TextView colorsText;
    TextView trialsText;
    /*
        COLOR CODES USED

        1    -      RED
        2    -      GREEN
        3    -      BLUE
        4    -      YELLOW

    */
    DBHandler db;
    SessionManager session;
    MediaPlayer lvlfx;
    private List<Integer> colorSequence;
    private int score;
    private int userListCounter;
    private int trials;
    private int noOfColorsUsed;
    private int difficultyMultiplier;
    private long totalTime;
    private long startTime;
    private long finishTime;
    private long timeTaken;
    private long colorFlashTime;
    private List<Button> buttonsList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rainbow_matrix_normal);
        db = new DBHandler(this);
        session = new SessionManager(this);
        difficultyText = (TextView) findViewById(R.id.difficultyText);
        colorsText = (TextView) findViewById(R.id.colorsText);
        difficultyText.setText(session.getDifficultyLevel().toString());
        userListCounter = 0;
        displayTile = (Button) findViewById(R.id.displayTile);
        displayTile.setEnabled(false);


        b1 = (Button) findViewById(R.id.tiles_12_btn);
        b2 = (Button) findViewById(R.id.tiles_21_btn);
        b3 = (Button) findViewById(R.id.tiles_23_btn);
        b4 = (Button) findViewById(R.id.tiles_32_btn);

        setDifficultyParameters();
        colorsText.setText(String.valueOf(noOfColorsUsed));
        trialsText = (TextView) findViewById(R.id.trialsText);
        trialsText.setText(String.valueOf(trials));
        totalTime = 0;//initiating both to zero when the application is started
        score = 0;

        colorSequence = new ArrayList<>();
        colorSequence.add(0, getRandomColor(noOfColorsUsed));
        flashListColors();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.rainbow_matrix_menu, menu);
        return true;
    }

    //method to be called on a button click passing the color reference number i.e. 1,2,3,4/1,2,3,4,5/1,2,3,4,5,6
    public void colorClickedCheck(int i) {
        Log.i("USERINFO", "colorClickedCheck: value of userListCounter: " + userListCounter);
        Log.i("USERINFO", "colorClickedCheck: value of i passed by button: " + i);


        if (colorSequence.get(userListCounter) == i) {

            Log.i("INSIDE IF", "Trials value " + trials);
            //consider userListCounter as a global looper for listAdapter
            userListCounter++;
            trialsText.setText(String.valueOf(trials));
        } else {
            Log.i("INSIDE ELSE", "SHOULDNT BE HERE AT ALL : Trials value " + trials);
            //total trials left must be reduced if user does it wrong and patter should repeat itself when he does
            trials--;

            //tv.setText(trial);
            trialsText.setText(String.valueOf(trials));

            if (trials > 0) {
                flashListColors();
            } else {
                trials = 0;
                trialsText = (TextView) findViewById(R.id.trialsText);
                //calculating final score
                finishTime = System.currentTimeMillis();
                timeTaken = finishTime - startTime;
                totalTime += timeTaken;
                Log.i("USERINPUTTIME", "time taken per sequence" + timeTaken);

                Log.i("SCORECHECK", "Score before game over before final calculation : " + score);

                score *= difficultyMultiplier;
                Log.i("SCORECHECK", "Score before game over : " + score);

                //saving user data in the pojo class to save his/her score in database

                //beans class to store data
                RainbowMatrixScores rms = new RainbowMatrixScores();

                //retrieving general user info from session
                HashMap<String, String> userInfo = session.getUserDetails();

                //setting up the beans class
                rms.setUser_nickname(userInfo.get(SessionManager.KEY_NICKNAME));
                rms.setUser_email(SessionManager.KEY_EMAIL);
                rms.setScore(score);
                rms.setDifficultylevel(session.getDifficultyLevel());
                rms.setGame_name(SessionManager.MEMORYMATRIX);

                db.addRainbowMatrixGameScore(rms);


                //============GAMEOVER HERE============================
                //sending score via intent to the gamevoer screen to be displayed
                Intent gameOver = new Intent(this, GameOverActivity.class);
                Log.i("USERINPUT", "final score: " + score);
                gameOver.putExtra("score", score);
                startActivity(gameOver);
                finish();
            }


        }

        if (userListCounter == colorSequence.size()) {

            //adding to score if user is success full in identifying a pattern
            finishTime = System.currentTimeMillis();
            timeTaken += finishTime - startTime;
            totalTime += timeTaken;
            lvlfx = MediaPlayer.create(this, R.raw.level_complete_sound_fx);
            lvlfx.start();
            Log.i("USERINPUTTIME", "time taken per sequence" + timeTaken);
            score += (colorSequence.size() * 100000) / timeTaken;
            Log.i("SCORECHECK", "Score after completion of one round : " + score);
            //adding to the new color to the list of colors in the current sequence list
            colorSequence.add(getRandomColor(noOfColorsUsed));
            flashListColors();


        }
    }

    //this method is called when we want to flash the initial color sequence or sequences later on.
    public void flashListColors() {
        Counter counter = new Counter((colorSequence.size() * colorFlashTime + colorFlashTime), colorFlashTime);
        counter.start();

    }

    //gives random color out of given list of color codes
    public int getRandomColor(int noOfColors) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <= noOfColors; i++) {
            list.add(i);
        }

        Collections.shuffle(list);
        return list.get(0);

    }

    public void setDifficultyParameters() {
        switch (session.getDifficultyLevel()) {
            case SessionManager.EASY:
                noOfColorsUsed = RainbowMatrixGameDifficultyParameters.NOOFCOLORSUSEDEASY;
                colorFlashTime = RainbowMatrixGameDifficultyParameters.COLORFLASHTIMEEASY;
                trials = RainbowMatrixGameDifficultyParameters.TRIALSEASY;
                difficultyMultiplier = RainbowMatrixGameDifficultyParameters.DIFFICULTYMULTIPLIEREASY;
                break;
            case SessionManager.MEDIUM:
                noOfColorsUsed = RainbowMatrixGameDifficultyParameters.NOOFCOLORSUSEDMEDIUM;
                colorFlashTime = RainbowMatrixGameDifficultyParameters.COLORFLASHTIMEMEDIUM;
                trials = RainbowMatrixGameDifficultyParameters.TRIALSMEDIUM;
                difficultyMultiplier = RainbowMatrixGameDifficultyParameters.DIFFICULTYMULTIPLIERMEDIUM;
                break;
            case SessionManager.HARD:
                noOfColorsUsed = RainbowMatrixGameDifficultyParameters.NOOFCOLORSUSEDHARD;
                colorFlashTime = RainbowMatrixGameDifficultyParameters.COLORFLASHTIMEHARD;
                trials = RainbowMatrixGameDifficultyParameters.TRIALSHARD;
                difficultyMultiplier = RainbowMatrixGameDifficultyParameters.DIFFICULTYMULTIPLIERHARD;
                break;
            case SessionManager.EXTREME:
                noOfColorsUsed = RainbowMatrixGameDifficultyParameters.NOOFCOLORSUSEDEXTREME;
                colorFlashTime = RainbowMatrixGameDifficultyParameters.COLORFLASHTIMEEXTREME;
                trials = RainbowMatrixGameDifficultyParameters.TRIALSEXTREME;
                difficultyMultiplier = RainbowMatrixGameDifficultyParameters.DIFFICULTYMULTIPLIEREXTREME;
                break;
            case SessionManager.IMBALANCED:
                noOfColorsUsed = RainbowMatrixGameDifficultyParameters.NOOFCOLORSUSEDIMBALANCED;
                colorFlashTime = RainbowMatrixGameDifficultyParameters.COLORFLASHTIMEIMBALANCED;
                trials = RainbowMatrixGameDifficultyParameters.TRIALSIMBALANCED;
                difficultyMultiplier = RainbowMatrixGameDifficultyParameters.DIFFICULTYMULTIPLIERIMBALANCED;
                break;
            default:
                //this should never run
                noOfColorsUsed = RainbowMatrixGameDifficultyParameters.NOOFCOLORSUSEDMEDIUM;
                colorFlashTime = RainbowMatrixGameDifficultyParameters.COLORFLASHTIMEMEDIUM;
                trials = RainbowMatrixGameDifficultyParameters.TRIALSMEDIUM;
                difficultyMultiplier = RainbowMatrixGameDifficultyParameters.DIFFICULTYMULTIPLIERMEDIUM;
                break;
        }
    }

    public void tileRedOnClick(View view) {
        /*

        flash as long as user taps


         */
        colorClickedCheck(1);
    }

    public void tileGreenOnClick(View view) {
        /*

        flash as long as user taps


         */
        colorClickedCheck(2);
    }

    public void tileBlueOnClick(View view) {
        /*

        flash as long as user taps


         */
        colorClickedCheck(3);
    }

    public void tileYellowOnClick(View view) {
        /*

        flash as long as user taps


         */
        colorClickedCheck(4);
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
        Intent intent = new Intent(this, Rainbowmatrix_instruction_Activity.class);
        startActivity(intent);
    }

    //this counter class is used to flash the color sequence on our main tile
    class Counter extends CountDownTimer {

        //this variable is created to loop through list colorSequence
        public int k;


        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */

        public Counter(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            k = 0;
            b1.setEnabled(false);
            b2.setEnabled(false);
            b3.setEnabled(false);
            b4.setEnabled(false);

            Log.i("USERINPUT", "int k value:  " + k);
            /*


                    Implement text view here


             */
        }

        @Override
        public void onTick(long millisUntilFinished) {
            Log.i("USERINPUT", "PER display k value BEFORE:  " + k);
            switch (colorSequence.get(k)) {
                //1 stands for color red
                case 1:

                    displayTile.setBackgroundResource(R.color.rainbow_red);
                    k++;
                    break;
                //2 stands for green
                case 2:

                    displayTile.setBackgroundResource(R.color.rainbow_green);
                    k++;
                    break;
                //3 stands for blue
                case 3:
                    displayTile.setBackgroundResource(R.color.rainbow_blue);
                    k++;
                    break;

                //4 stands for yellow
                case 4:

                    Log.i("USERINPUT", "inside yellow color  " + k);
                    displayTile.setBackgroundResource(R.color.rainbow_yellow);
                    k++;
                    break;

            }
            Log.i("USERINPUT", "per display k value AFTER:  " + k);

        }

        @Override
        public void onFinish() {

            displayTile.setBackgroundResource(R.color.rainbow_default);

            //this counter is used to check the current number where user is selecting color
            userListCounter = 0;

            //note the current time of the system and set the startTime used later to add up in time left after removing this from finish time
            startTime = System.currentTimeMillis();
            b1.setEnabled(true);
            b2.setEnabled(true);
            b3.setEnabled(true);
            b4.setEnabled(true);

        }
    }
}
