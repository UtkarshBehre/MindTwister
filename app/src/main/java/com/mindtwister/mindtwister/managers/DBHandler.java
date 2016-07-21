package com.mindtwister.mindtwister.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mindtwister.mindtwister.loginregister.RegisterClass;

/**
 * Created by jyothi on 7/18/2016.
 */
public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "MindTwisters";
    // Contacts table name
    private static final String TABLE_REGISTER = "userinfo";
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
        db.execSQL(
                "create table userinfo " +
                        "(user_name text,user_nickname text unique,user_password text,user_email text primary key,user_age integer)"
        );
        //   db.execSQL(CREATE_CONTACTS_TABLE);

        //   }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REGISTER);
// Creating tables again
        onCreate(db);
    }

    public String addRegisterClass(RegisterClass rc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user_name", rc.getUser_name()); // Register Name
        values.put("user_nickname", rc.getUser_nickname());
        values.put("user_password", rc.getUser_password());
        values.put("user_email", rc.getUser_email());
        values.put("user_age", rc.getUser_age());
// Inserting Row
        long returnedValue = 0;
        String status = null;
        try {
            //check for unique nickname
            String selectQuery = "SELECT * FROM userinfo where user_nickname='" + rc.getUser_nickname() + "'";
            SQLiteDatabase db1 = this.getReadableDatabase();
            Cursor cursor = db1.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {

                status = "notunique";
                return status;

            } else {
                returnedValue = db.insertOrThrow("userinfo", null, values);//returns row id if inserted ....
            }
        } catch (SQLException e) {
            Log.e(DATABASE_NAME, e.toString());
        }
        //check for unique email
        status = String.valueOf(returnedValue);
        Log.i(DATABASE_NAME, status);
        //status is 0 then it is not inserted,else status displays the row id...

        return status;

    }

    public RegisterClass selectuser(String username, String password) {
        String selectQuery = "SELECT * FROM userinfo where user_nickname='" + username + "' and user_password='" + password + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
////// looping through all rows and adding to list
        RegisterClass rc = new RegisterClass();
        if (cursor.moveToFirst()) {
            rc.setUser_name(cursor.getString(0));
            rc.setUser_nickname(cursor.getString(1));
            rc.setUser_password(cursor.getString(2));
            rc.setUser_email(cursor.getString(3));
            rc.setUser_age(cursor.getInt(4));
            return rc;
        } else
            return null;
    }

}
