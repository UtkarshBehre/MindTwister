package com.mindtwister.mindtwister;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashMap;

/**
 * Created by Utkarsh on 18-07-2016.
 */
public class SessionManager {

    // User name (make variable public to access from outside)
    public static final String KEY_NAME = "name";
    //User nickname
    public static final String KEY_NICKNAME = "nickname";
    // Email address (make variable public to access from outside)
    public static final String KEY_EMAIL = "email";
    // Shared preference file name
    private static final String PREF_NAME = "AndroidHivePref";
    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";
    // Music on or off setting
    private static final String KEY_MUSIC = "music";
    // Sound fx on or off settings
    private static final String KEY_SOUNDFX = "soundFx";

    //shared preferences
    SharedPreferences pref;
    //editor for shared preferences
    Editor editor;
    //oontext
    Context _context;
    int PRIVATE_MODE = 0;

    // Constructor
    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     */
    public void createLoginSession(String name, String nickname, String email) {
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putString(KEY_NAME, name);

        //Storing nickname in pref
        editor.putString(KEY_NICKNAME, nickname);

        // Storing email in pref
        editor.putString(KEY_EMAIL, email);

        // setting music on
        editor.putBoolean(KEY_MUSIC, true);

        // setting sound fx on
        editor.putBoolean(KEY_SOUNDFX, true);

        // commit changes
        editor.commit();
    }

    //method to set sound fx
    public void setSoundStatus(boolean b) {
        editor.putBoolean(KEY_SOUNDFX, b);
        editor.commit();
    }

    public boolean getMusicStatus() {
        return pref.getBoolean(KEY_MUSIC, true);
    }

    //method to set music status
    public void setMusicStatus(boolean b) {
        editor.putBoolean(KEY_MUSIC, b);
        editor.commit();
    }

    public boolean getSoundFxStatus() {
        return pref.getBoolean(KEY_SOUNDFX, true);
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     */
    public void checkLogin(MainActivity mainActivity) {
        // Check login status
        if (!this.isLoggedIn()) {
            mainActivity.finish();
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }

    }

    /**
     * Get stored session data
     */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));

        // user name
        user.put(KEY_NICKNAME, pref.getString(KEY_NICKNAME, null));

        // user email id
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        // return user
        return user;
    }

    /**
     * Clear session details
     */
    public void logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }

    // Get Login State
    public boolean isLoggedIn() {
        if (pref != null)
        return pref.getBoolean(IS_LOGIN, false);
        else
            return false;
    }
}

