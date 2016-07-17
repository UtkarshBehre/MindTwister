package com.mindtwister.mindtwister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    DBHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DBHandler(this);
    }

    public void register(View v) {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
        finish();
    }

    public void login(View v) throws NullPointerException {
        EditText editText2 = (EditText) findViewById(R.id.etUsername);
        String username = editText2.getText().toString();

        EditText editText3 = (EditText) findViewById(R.id.etPassword);
        String password = editText3.getText().toString();

        //  RegisterClass rc = new RegisterClass();

        //   rc.setUser_nickname(username);
        //  rc.setUser_password(password);
        RegisterClass rc = db.selectuser(username, password);
        //  Toast.makeText(getApplicationContext(),rc.getUser_nickname(), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MainActivity.class);
        // intent.putExtra("username",rc.getUser_nickname());
        startActivity(intent);
        finish();


    }



}
