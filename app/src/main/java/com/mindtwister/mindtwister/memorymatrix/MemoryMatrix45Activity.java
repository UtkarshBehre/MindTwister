package com.mindtwister.mindtwister.memorymatrix;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mindtwister.mindtwister.GameOverActivity;
import com.mindtwister.mindtwister.R;
import com.mindtwister.mindtwister.managers.DBHandler;
import com.mindtwister.mindtwister.managers.MemoryMatrixScores;
import com.mindtwister.mindtwister.managers.SessionManager;
import com.mindtwister.mindtwister.memorymatrix.utility.MemoryMatrixGameDifficultyParameters;
import com.mindtwister.mindtwister.memorymatrix.utility.UtilityMethodsForMemoryMatrix;

import java.util.ArrayList;
import java.util.HashMap;

public class MemoryMatrix45Activity extends AppCompatActivity {
    private final String USERTIME = "User Input Time";
    private final int TOTALTILES = 20;
    private final int TILESTOFLASH = 6;
    private final int CURRENTLEVEL = 4;
    public TextView difficultyText;
    public TextView trialsText;
    public TextView matrixText;
    public HashMap<Integer, Boolean> gridSet;
    public HashMap<Integer, Boolean> checkerGridSet;
    public ArrayList<Button> buttonsList;
    SessionManager session;
    DBHandler db;
    private int TOTALTRIALS;
    private int difficultyMultiplier;
    private long TIMETOFLASH;
    private long startTime;
    private long finishTime;
    private int trialsLeft;
    private int score;
    private String TAG = "VARIABLES CHECK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_matrix_45);
        difficultyText = (TextView) findViewById(R.id.difficultyText);
        trialsText = (TextView) findViewById(R.id.trialsText);
        matrixText = (TextView) findViewById(R.id.matrixText);
        matrixText.setText("4x5");
        /**************************************************************************************************/
        session = new SessionManager(this);
        db = new DBHandler(this);

        trialsLeft = getIntent().getIntExtra("currentTrialsLeft", 0);
        score = getIntent().getIntExtra("score", 0);


        setDifficultyLevelTIMETOFLASH();
        trialsText.setText(String.valueOf(trialsLeft));

        Log.i(TAG, "trialsLeft: " + trialsLeft);
        //making object of our utility class to use its methods
        UtilityMethodsForMemoryMatrix utilityMethodsForMemoryMatrix = new UtilityMethodsForMemoryMatrix();

        //using RandomTilesSelector method in utility to get a random gridSet
        gridSet = utilityMethodsForMemoryMatrix.RandomTilesSelector(TOTALTILES, TILESTOFLASH);

        //instantiate checkerGridSet to use it later for compare with the previously generated grid set
        checkerGridSet = utilityMethodsForMemoryMatrix.getGridSet(TOTALTILES);
        /**************************************************************************************************/

        //Filling buttonsList with the buttons ids
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
//flashing the tiles for time depending on difficulty
        flashTheTiles(gridSet, buttonsList, TIMETOFLASH);
    }

    //this method is used to flash the tiles and is called in onCreate
    private void flashTheTiles(HashMap<Integer, Boolean> gridSet, ArrayList<Button> buttonsList, long tilesFlashingTime) {
        Counter counter = new Counter(tilesFlashingTime, 1000, gridSet, buttonsList);
        counter.start();
    }

    //call this method on a button click to check if tile is correct or not
    public void checkTileCorrect(int TileNumberClicked, Button tile) {

        Log.i("Current button " + (TileNumberClicked), String.valueOf(gridSet.get(TileNumberClicked)));

        //if tile selected is correct
        if (gridSet.get(TileNumberClicked)) {
            //CHANGE correct color tile click background here ========================
            //CHANGE COLOR OF TILE WHEN CLICKED
            tile.setBackgroundResource(R.color.appAccent400);
            checkerGridSet.put(TileNumberClicked, true);

            //this method compares the current state of tiles with the initial flashed state
            checkAllTilesCorrectOrNot();
        }
        //if tile selected is wrong
        else {
            tile.setBackgroundResource(R.color.wrong_tile_selection_color);
            checkerGridSet.put(TileNumberClicked, true);
            /*
            to do
            IMPLEMENT POPUP WINDOW TO TELL USER WRONG TILE SELECTED
             */

            //reducing trials left by 1
            trialsLeft--;
            session.setTrialsLeft(trialsLeft);
            if (trialsLeft == 0) {

                score *= difficultyMultiplier;
                score = score * trialsLeft / TOTALTRIALS;

                //saving user data in the pojo class to save his/her score in database
                //beans class to store data
                MemoryMatrixScores mms = new MemoryMatrixScores();

                //retrieving general user info from session
                HashMap<String, String> userInfo = session.getUserDetails();

                //setting up the beans class
                mms.setUser_nickname(userInfo.get(SessionManager.KEY_NICKNAME));
                mms.setUser_email(SessionManager.KEY_EMAIL);
                mms.setScore(session.getScore());
                mms.setDifficulty_level(session.getDifficultyLevel());
                mms.setGame_name(SessionManager.MEMORYMATRIX);

                db.addMemoryMatrixGameScore(mms);

                //============GAMEOVER HERE============================
                //sending score via intent to the gamevoer screen to be displayed
                Intent gameOver = new Intent(this, GameOverActivity.class);
                Log.i("USERINPUT", "final score: " + score);
                gameOver.putExtra("score", score);
                startActivity(gameOver);
                finish();


            } else {
            /*
                                CHANGEME
                !!!!!!!!!!!!!!!!INTENT changes for every new activity!!!!!!!!!!!!!!
             */
                //sending user to previous level since this is first activity so we re-instantiate itself
                Intent previousLevel = new Intent(this, MemoryMatrix44Activity.class);
                previousLevel.putExtra("currentTrialsLeft", trialsLeft);
                previousLevel.putExtra("score", score);
                startActivity(previousLevel);
                finish();
            }

        }
    }

    public void checkAllTilesCorrectOrNot() {
        //Log.i("GRIDSET VALUES : ", "onCreate:" + "1:  " + gridSet.get(0) + "\t2: " + gridSet.get(1) + "\t3: " + gridSet.get(2) + "\t4: " + gridSet.get(3) + "\t5: " + gridSet.get(4) + "\t6: " + gridSet.get(5) + "\t7: " + gridSet.get(6) + "\t8: " + gridSet.get(7) + "\t9: " + gridSet.get(8));

        //Log.i("CheckerGRIDSET: ", "onCreate:" + "1:  " + checkerGridSet.get(0) + "\t2: " + checkerGridSet.get(1) + "\t3: " + checkerGridSet.get(2) + "\t4: " + checkerGridSet.get(3) + "\t5: " + checkerGridSet.get(4) + "\t6: " + checkerGridSet.get(5) + "\t7: " + checkerGridSet.get(6) + "\t8: " + checkerGridSet.get(7) + "\t9: " + checkerGridSet.get(8));

        if (compareNewAndOldGridStates()) {
            finishTime = System.currentTimeMillis();
            Log.i(USERTIME, "Finish time: " + finishTime);
            long timeTaken = finishTime - startTime;
            Log.i(USERTIME, "Total time taken: " + timeTaken);

            //adding up user's score
            score += (10000000 * CURRENTLEVEL) / timeTaken;
            Log.i(USERTIME, "Score at 3x3: " + score);

            /*
                              CHANGEME
                !!!!!!!!!!!!!!!!Intent changes for every new activity!!!!!!!!!!!!!!
             */
            //sending user to next level
            Intent nextLevel = new Intent(this, MemoryMatrix55Activity.class);
            nextLevel.putExtra("score", score);
            nextLevel.putExtra("currentTrialsLeft", trialsLeft);
            startActivity(nextLevel);
            finish();
        }
    }

    //call this function to set time flash acc. to difficulty
    private void setDifficultyLevelTIMETOFLASH() {
        switch (session.getDifficultyLevel()) {
            case SessionManager.EASY: {
                difficultyText.setText(MemoryMatrixGameDifficultyParameters.EASYGAME);
                TOTALTRIALS = MemoryMatrixGameDifficultyParameters.TOTALTRIALSEASY;
                TIMETOFLASH = MemoryMatrixGameDifficultyParameters.TIMETOFLASHEASY;
                difficultyMultiplier = MemoryMatrixGameDifficultyParameters.DIFFICULTYMULTIPLIEREASY;
                break;
            }
            case SessionManager.MEDIUM: {
                difficultyText.setText(MemoryMatrixGameDifficultyParameters.MEDIUMGAME);
                TOTALTRIALS = MemoryMatrixGameDifficultyParameters.TOTALTRIALSMEDIUM;
                TIMETOFLASH = MemoryMatrixGameDifficultyParameters.TIMETOFLASHMEDIUM;
                difficultyMultiplier = MemoryMatrixGameDifficultyParameters.DIFFICULTYMULTIPLIERMEDIUM;
                break;
            }
            case SessionManager.HARD:
                difficultyText.setText(MemoryMatrixGameDifficultyParameters.HARDGAME);
                TOTALTRIALS = MemoryMatrixGameDifficultyParameters.TOTALTRIALSHARD;
                TIMETOFLASH = MemoryMatrixGameDifficultyParameters.TIMETOFLASHHARD;
                difficultyMultiplier = MemoryMatrixGameDifficultyParameters.DIFFICULTYMULTIPLIERHARD;
                break;
            default:
                TOTALTRIALS = MemoryMatrixGameDifficultyParameters.TOTALTRIALSMEDIUM;
                TIMETOFLASH = MemoryMatrixGameDifficultyParameters.TIMETOFLASHMEDIUM;
                difficultyMultiplier = MemoryMatrixGameDifficultyParameters.DIFFICULTYMULTIPLIERMEDIUM;
        }

    }

    public boolean compareNewAndOldGridStates() {
        boolean status = true;
        for (int i = 0; i <= gridSet.size(); i++) {
            if (gridSet.get(i) != checkerGridSet.get(i)) {
                status = false;
            }
        }

        return status;
    }

    public void b1OnClick(View view) {
        checkTileCorrect(0, buttonsList.get(0));
    }

    public void b2OnClick(View view) {
        checkTileCorrect(1, buttonsList.get(1));
    }

    public void b3OnClick(View view) {
        checkTileCorrect(2, buttonsList.get(2));
    }

    public void b4OnClick(View view) {
        checkTileCorrect(3, buttonsList.get(3));
    }

    public void b5OnClick(View view) {
        checkTileCorrect(4, buttonsList.get(4));
    }

    public void b6OnClick(View view) {
        checkTileCorrect(5, buttonsList.get(5));
    }

    public void b7OnClick(View view) {
        checkTileCorrect(6, buttonsList.get(6));
    }

    public void b8OnClick(View view) {
        checkTileCorrect(7, buttonsList.get(7));
    }

    public void b9OnClick(View view) {
        checkTileCorrect(8, buttonsList.get(8));
    }

    public void b10OnClick(View view) {
        checkTileCorrect(9, buttonsList.get(9));
    }

    public void b11OnClick(View view) {
        checkTileCorrect(10, buttonsList.get(10));
    }

    public void b12OnClick(View view) {
        checkTileCorrect(11, buttonsList.get(11));
    }

    public void b13OnClick(View view) {
        checkTileCorrect(12, buttonsList.get(12));
    }

    public void b14OnClick(View view) {
        checkTileCorrect(13, buttonsList.get(13));
    }

    public void b15OnClick(View view) {
        checkTileCorrect(14, buttonsList.get(14));
    }

    public void b16OnClick(View view) {
        checkTileCorrect(15, buttonsList.get(15));
    }

    public void b17OnClick(View view) {
        checkTileCorrect(16, buttonsList.get(16));
    }

    public void b18OnClick(View view) {
        checkTileCorrect(17, buttonsList.get(17));
    }

    public void b19OnClick(View view) {
        checkTileCorrect(18, buttonsList.get(18));
    }

    public void b20OnClick(View view) {
        checkTileCorrect(19, buttonsList.get(19));
    }

    //CounterDownTimer class to flash tiles
    public class Counter extends CountDownTimer {
        HashMap<Integer, Boolean> gridSet;
        ArrayList<Button> buttonsList;
        TextView helperText;

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
            helperText = (TextView) findViewById(R.id.helperText);
            helperText.setText("Remember the tiles positions with the color green!");
            //this loop loops for all the buttons in the grid
            /*

                USE A TEXT VIEW to say
                Please remember the pattern and repeat and it goes away


             */

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


            helperText.setText("Now please repeat the pattern you just saw!");
            /*

                use the same text view to say Now repeat the pattern you just saw


             */

            for (int i = 0; i < buttonsList.size(); i++) {
                Button tile = buttonsList.get(i);
                tile.setEnabled(true);

                if (gridSet.get(i)) {
                    //=====================SETTING COLOR OF TILE TO FLASH HERE====================
                    tile.setBackgroundResource(R.color.memory_matrixs_tile_default_color);
                }
            }
            startTime = System.currentTimeMillis();
            Log.i(USERTIME, "Start time: " + startTime);
        }

    }

}
