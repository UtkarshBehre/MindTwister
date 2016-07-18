package com.mindtwister.mindtwister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

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

        //retrieve userinfo object from database
        RegisterClass rc = db.selectuser(username, password);
        if (rc != null) {
            session.createLoginSession(rc.getUser_name(), rc.getUser_nickname(), rc.getUser_email());
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        finish();


    }



}
