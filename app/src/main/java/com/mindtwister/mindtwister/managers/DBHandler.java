package com.mindtwister.mindtwister.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jyothi on 7/18/2016.
 */
public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "MindTwisters";
    // Contacts table name
    private static final String TABLE_USERINFO = "UserInfo";

    private static final String TABLE_MEMORYMATRIXSCORES = "MemoryMatrixScores";

    //    private static final String TABLE_RAINBOWMATRIXSCORES = "RainbowMatrixScores";
//
//    private static final String TABLE_SUDOKUSCORES = "SudokuScores";
    // Shops Table Columns names
    private static final String KEY_NAME = "user_name";
    private static final String KEY_NICKNAME = "user_nickname";
    private static final String KEY_PASSWORD = "user_password";
    private static final String KEY_EMAIL = "user_email";
    private static final String KEY_AGE = "user_age";
    private static final String TAG = "MyActivity";
    private static final String KEY_GAMENAME = "game_name";
    private static final String KEY_DIFFICULTY_LEVEL = "difficulty_level";
    private static final String KEY_SCORE = "user_score";
    private static final String FINAL_SCORES = "FinalScores";
    private static final String KEY_FINALSCORE = "user_finalscore";
    public DBHandler(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table " + TABLE_USERINFO +
                        "(" + KEY_NAME + " text," + KEY_NICKNAME + " text unique," + KEY_PASSWORD + " text," + KEY_EMAIL + " text primary key," + KEY_AGE + " integer)"
        );
        //   db.execSQL(CREATE_CONTACTS_TABLE);

        //   }
        db.execSQL(
                "create table " + TABLE_MEMORYMATRIXSCORES +
                        "(" + KEY_NICKNAME + " text, " + KEY_GAMENAME + " text, " + KEY_DIFFICULTY_LEVEL + " text," + KEY_SCORE + " integer, " + KEY_EMAIL + " text, FOREIGN KEY(" + KEY_EMAIL + ") REFERENCES " + TABLE_USERINFO + "(" + KEY_EMAIL + "))"
        );

//        db.execSQL(
//                "create table " + TABLE_RAINBOWMATRIXSCORES +
//                        "(" + KEY_NICKNAME + " text, " + KEY_GAMENAME + " text, " + KEY_DIFFICULTY_LEVEL + " text," + KEY_SCORE + " integer, " + KEY_EMAIL + " text)"
//        );
//
//        db.execSQL(
//                "create table " + TABLE_SUDOKUSCORES +
//                        "(" + KEY_NICKNAME + " text, " + KEY_GAMENAME + " text, " + KEY_DIFFICULTY_LEVEL + " text," + KEY_SCORE + " integer, " + KEY_EMAIL + " text)"
//        );
        db.execSQL("create table " + FINAL_SCORES + "(" + KEY_FINALSCORE + " integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Creating tables again on patch or game update via playstore
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERINFO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMORYMATRIXSCORES);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RAINBOWMATRIXSCORES);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUDOKUSCORES);
        db.execSQL("DROP TABLE IF EXISTS " + FINAL_SCORES);
        onCreate(db);
    }

    public String addUserInfo(UserInfo rc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, rc.getUser_name()); // Register Name
        values.put(KEY_NICKNAME, rc.getUser_nickname());
        values.put(KEY_PASSWORD, rc.getUser_password());
        values.put(KEY_EMAIL, rc.getUser_email());
        values.put(KEY_AGE, rc.getUser_age());

        // Inserting Row
        long returnedValue = 0;
        String status = null;
        try {
            //check for unique nickname
            String selectQuery = "SELECT * FROM " + TABLE_USERINFO + " where " + KEY_NICKNAME + "='" + rc.getUser_nickname() + "'";
            Log.i(TAG, "addRegisterClass: " + selectQuery);
            SQLiteDatabase db1 = this.getReadableDatabase();
            Cursor cursor = db1.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {

                status = "notunique";
                cursor.close();
                return status;

            } else {
                returnedValue = db.insertOrThrow(TABLE_USERINFO, null, values);//returns row id if inserted ....
            }
            cursor.close();
        } catch (SQLException e) {
            Log.e(DATABASE_NAME, e.toString());
        }
        //check for unique email
        status = String.valueOf(returnedValue);
        Log.i(DATABASE_NAME, status);
        //status is 0 then it is not inserted,else status displays the row id...
        return status;

    }

    public UserInfo selectUser(String username, String password) {
        String selectQuery = "SELECT * FROM " + TABLE_USERINFO + " where " + KEY_NICKNAME + "='" + username + "' and " + KEY_PASSWORD + "='" + password + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
////// looping through all rows and adding to list
        UserInfo rc = new UserInfo();
        if (cursor.moveToFirst()) {
            rc.setUser_name(cursor.getString(0));
            rc.setUser_nickname(cursor.getString(1));
            rc.setUser_password(cursor.getString(2));
            rc.setUser_email(cursor.getString(3));
            rc.setUser_age(cursor.getInt(4));
            cursor.close();
            return rc;
        } else
            cursor.close();
            return null;
    }

    //add user score in the table
    public void addMemoryMatrixGameScore(MemoryMatrixScores mms) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NICKNAME, mms.getUser_nickname());
        values.put(KEY_GAMENAME, mms.getGame_name());
        values.put(KEY_DIFFICULTY_LEVEL, mms.getDifficulty_level());
        values.put(KEY_SCORE, mms.getScore());
        values.put(KEY_EMAIL, mms.getUser_email());

        long checker = db.insertOrThrow(TABLE_MEMORYMATRIXSCORES, null, values);
        if (checker >= 0) {
            Log.i(TAG, "addMemoryMatrixGameScore: SUCCESS" + checker);
        } else {
            Log.i(TAG, "addMemoryMatrixGameScore: FAILURE" + checker);
        }
    }

//    public void addRainbowMatrixGameScore(RainbowMatrixScores rms) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(KEY_NICKNAME, rms.getUser_nickname());
//        values.put(KEY_GAMENAME, rms.getGame_name());
//        values.put(KEY_DIFFICULTY_LEVEL, rms.getDifficultylevel());
//        values.put(KEY_SCORE, rms.getScore());
//        values.put(KEY_EMAIL, rms.getUser_email());
//
//        long checker = db.insertOrThrow(TABLE_RAINBOWMATRIXSCORES, null, values);
//        if (checker >= 0) {
//            Log.i(TAG, "addRainbowMatrixGameScore: SUCCESS" + checker);
//        } else {
//            Log.i(TAG, "addRainbowMatrixGameScore: FAILURE" + checker);
//        }
//    }
//
//
//    public void addSudokuGameScore(SudokuScores sudoku) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(KEY_NICKNAME, sudoku.getUser_nickname());
//        values.put(KEY_GAMENAME, sudoku.getGame_name());
//        values.put(KEY_DIFFICULTY_LEVEL, sudoku.getDifficulty_level());
//        values.put(KEY_SCORE, sudoku.getScore());
//        values.put(KEY_EMAIL, sudoku.getUser_email());
//
//        long checker = db.insertOrThrow(TABLE_SUDOKUSCORES, null, values);
//        if (checker >= 0) {
//            Log.i(TAG, "addSudokuGameScore: SUCCESS" + checker);
//        } else {
//            Log.i(TAG, "addSudokuGameScore: FAILURE" + checker);
//        }
//    }

    public List<listClass> viewMemoryMatrixScore(int game) {
        //String[] args={id};
        ArrayList<listClass> result = new ArrayList<listClass>();
        SQLiteDatabase db = this.getWritableDatabase();

        String selectQuery;
        switch (game) {
            case 1:
                selectQuery = "SELECT * FROM " + TABLE_MEMORYMATRIXSCORES + " ORDER BY " + KEY_SCORE + " DESC";
                break;
//            case 2:
//                selectQuery = "SELECT * FROM " + TABLE_RAINBOWMATRIXSCORES + " ORDER BY " + KEY_SCORE + " DESC";
//                break;
//            case 3:
//                selectQuery = "SELECT * FROM " + TABLE_SUDOKUSCORES + " ORDER BY " + KEY_SCORE + " DESC";
//                break;
            default:
                selectQuery = "SELECT * FROM " + TABLE_MEMORYMATRIXSCORES + " ORDER BY " + KEY_SCORE + " DESC";
                break;
        }
        Cursor cursor = db.rawQuery(selectQuery, null);
        // selectQuery, null
        try {
            if (cursor.moveToFirst()) {
                do {
                    listClass list = new listClass();
                    list.setNickname(cursor.getString(0));
                    list.setGamename(cursor.getString(1));
                    list.setLevel(cursor.getString(2));
                    list.setScore(Integer.parseInt(cursor.getString(3)));

                    // Adding category to list
                    result.add(list);
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }
        db.close();
        return result;
    }
}
