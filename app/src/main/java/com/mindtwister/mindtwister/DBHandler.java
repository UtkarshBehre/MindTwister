package com.mindtwister.mindtwister;

/**
 * Created by jyothi on 7/16/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "MindTwister";
    // Contacts table name
    private static final String TABLE_REGISTER = "UserInfo";
    // Shops Table Columns names
    private static final String KEY_ID = "user_id";
    private static final String KEY_NAME = "user_name";
    private static final String KEY_USERNAME = "user_nickname";
    private static final String KEY_PASSWORD = "user_password";
    private static final String KEY_EMAIL = "user_email";
    private static final String KEY_AGE = "user_age";
    private static final String TAG = "MyActivity";

    public DBHandler(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Creating UserInfo table in the database
        db.execSQL(
                "create table UserInfo " +
                        "(user_name text,user_nickname text,user_password text,user_email text primary key,user_age integer)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REGISTER);
// Creating tables again
        onCreate(db);
    }

    public boolean addRegisterClass(RegisterClass rc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user_name", rc.getUser_name()); // Register Name
        values.put("user_nickname", rc.getUser_nickname());
        values.put("user_password", rc.getUser_password());
        values.put("user_email", rc.getUser_email());
        values.put("user_age", rc.getUser_age());

        long returnedValue = 0;
        try {
            returnedValue = db.insertOrThrow("userinfo", null, values);

        } catch (SQLException e) {
            Log.e(DATABASE_NAME, e.toString());
        }
        String v = String.valueOf(returnedValue);
        Log.i(DATABASE_NAME, v);
        if (returnedValue > 0)
            return true;
        else
            return false;
    }

    public RegisterClass selectuser(String username, String password) {

        //get logged in user data
        String selectQuery = "SELECT * FROM UserInfo where user_nickname='" + username + "' and user_password='" + password + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // retrieving all column details to our pojo class
        RegisterClass rc = new RegisterClass();
        if (cursor.moveToNext()) {
            rc.setUser_name(cursor.getString(0));
            rc.setUser_nickname(cursor.getString(1));
            rc.setUser_password(cursor.getString(2));
            rc.setUser_email(cursor.getString(3));
            rc.setUser_age(cursor.getInt(4));
            cursor.close();
            return rc;
        } else
            return null;
    }
}
