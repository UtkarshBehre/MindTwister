package com.mindtwister.mindtwister.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mindtwister.mindtwister.MainActivity;
import com.mindtwister.mindtwister.R;
import com.mindtwister.mindtwister.managers.DBHandler;
import com.mindtwister.mindtwister.managers.UserInfo;
import com.mindtwister.mindtwister.managers.SessionManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    DBHandler db;
    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DBHandler(this);
        MainActivity.stopMusic(this);
        session = new SessionManager(this);
    }

    public void register(View v) {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
        finish();
    }

    public void login(View v) throws NullPointerException {

        //get user name input
        EditText etUsername = (EditText) findViewById(R.id.etUsername);
        String username = etUsername.getText().toString();

        //get password input
        EditText etPassword = (EditText) findViewById(R.id.etPassword);
        String password = etPassword.getText().toString();

        if (username.length() == 0 || password.length() == 0) {

            Toast.makeText(getApplicationContext(), "Please enter all fields!", Toast.LENGTH_LONG).show();


        } else if (!isValidNickname(username)) {

            etUsername.setError("no special characters are allowed!");
        } else if (!isValidPassword(password)) {
            etPassword.setError("Password must be greater than 6!");
        } else {

            //retrieve UserInfo object from database
            UserInfo rc = db.selectUser(username, password);
            if (rc == null) {

                //invalid user toast
                Toast.makeText(getApplicationContext(), "invalid user!", Toast.LENGTH_LONG).show();
            } else {

                String name = rc.getUser_nickname();
                String pass = rc.getUser_password();
                if ((name != null) || (pass != null)) {
                    if (rc.getUser_email() != null)
                        session.createLoginSession(rc.getUser_name(), rc.getUser_nickname(), rc.getUser_email(), rc.getUser_age());
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }
    }

    private boolean isValidNickname(String name) {
        String namepattern = "[A-Za-z0-9_]+";
        Pattern pattern = Pattern.compile(namepattern);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    private boolean isValidPassword(String pass) {
        if (pass.length() > 6)
            return true;
        return false;
    }
}
