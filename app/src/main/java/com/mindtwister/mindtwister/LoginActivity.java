package com.mindtwister.mindtwister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

            Toast.makeText(getApplicationContext(), "Pease enter all fields", Toast.LENGTH_LONG).show();


        } else if (!isValidname(username)) {

            etUsername.setError("Please enter only letters ");
        } else if (!isValidPassword(password)) {
            etPassword.setError("Password must be greater than 6");
        } else {

            //retrieve userinfo object from database
            RegisterClass rc = db.selectuser(username, password);
            if (rc == null) {

                //invalid user toast
                Toast.makeText(getApplicationContext(), "invalid user", Toast.LENGTH_LONG).show();
            } else {

                String name = rc.getUser_nickname();
                String pass = rc.getUser_password();
                if ((name == null) || (pass == null)) {
                } else {
                    if (rc.getUser_email() != null)
                        session.createLoginSession(rc.getUser_name(), rc.getUser_nickname(), rc.getUser_email());
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
            }
        }
    }

    private boolean isValidname(String name) {
        String namepattern = "[A-Za-z]+";
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
