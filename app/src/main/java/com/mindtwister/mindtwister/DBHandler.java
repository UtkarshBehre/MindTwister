package com.mindtwister.mindtwister;

/**
 * Created by jyothi on 7/16/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

import java.util.*;

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

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table UserInfo " +
                        "(user_id integer primary key autoincrement, user_name text,user_nickname text,user_password text,user_email text,user_age integer)"
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

    public boolean addRegisterClass(RegisterClass rc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user_name", rc.getUser_name()); // Register Name
        values.put("user_nickname", rc.getUser_nickname());
        values.put("user_password", rc.getUser_password());
        values.put("user_email", rc.getUser_email());
        values.put("user_age", rc.getUser_age());
// Inserting Row

        db.insert("UserInfo", null, values);
        return true;
            // db.close(); // Closing database connection
//        } else {
//            return false;
//        }
    }

    public RegisterClass selectuser(String username, String password) {
        String selectQuery = "SELECT * FROM UserInfo where user_nickname='" + username + "' and user_password='" + password + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
//// looping through all rows and adding to list
        RegisterClass rc = new RegisterClass();
        if (cursor.moveToNext()) {
            rc.setUser_name(cursor.getString(1));
            rc.setUser_nickname(cursor.getString(2));
            rc.setUser_password(cursor.getString(3));
            rc.setUser_email(cursor.getString(4));
            rc.setUser_age(cursor.getInt(5));
        }
        return rc;
    }
//
//
//// Adding contact to list
//                RegisterClassList.add(RegisterClass);
//            } while (cursor.moveToNext());
//        }
//// return contact list
//        return RegisterClassList;
//    }


//    public List<RegisterClass> getAllRegisterClasss() {
//        List<RegisterClass> RegisterClassList = new ArrayList<RegisterClass>();
//// Select All Query
//        String selectQuery = "SELECT * FROM " + TABLE_REGISTER;
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//// looping through all rows and adding to list
//        if (cursor.moveToFirst()) {
//            do {
//                RegisterClass RegisterClass = new RegisterClass();
//                //RegisterClass.(Integer.parseInt(cursor.getString(0)));
//                RegisterClass.setUser_name(cursor.getString(2));
//                RegisterClass.setUser_nickname(cursor.getString(3));
//                RegisterClass.setUser_password(cursor.getString(4));
//                RegisterClass.setUser_email(cursor.getString(5));
//                RegisterClass.setUser_age(cursor.getInt(6));
//
//
//// Adding contact to list
//                RegisterClassList.add(RegisterClass);
//            } while (cursor.moveToNext());
//        }
//// return contact list
//        return RegisterClassList;
//    }
}
