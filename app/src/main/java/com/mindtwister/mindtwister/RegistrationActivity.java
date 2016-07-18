package com.mindtwister.mindtwister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;


public class RegistrationActivity extends AppCompatActivity {
    DBHandler db;
    SessionManager session;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        db = new DBHandler(this);
        session = new SessionManager(this);
    }


    public void insert(View v) throws NullPointerException {
        // try {
        EditText editText1 = (EditText) findViewById(R.id.etName);
        String name = editText1.getText().toString();

        EditText editText2 = (EditText) findViewById(R.id.etUsername);
        String username = editText2.getText().toString();

        EditText editText3 = (EditText) findViewById(R.id.etPassword);
        String password = editText3.getText().toString();

        EditText editText4 = (EditText) findViewById(R.id.etEmail);
        String email = editText4.getText().toString();

        EditText editText5 = (EditText) findViewById(R.id.etAge);
        String age = editText5.getText().toString();
        int ages = Integer.parseInt(age);

        RegisterClass rc = new RegisterClass();
        rc.setUser_name(name);
        rc.setUser_nickname(username);
        rc.setUser_password(password);
        rc.setUser_email(email);
        rc.setUser_age(ages);


        db.addRegisterClass(rc);
        session.createLoginSession(rc.getUser_name(), rc.getUser_nickname(), rc.getUser_email());
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();



    }


}
