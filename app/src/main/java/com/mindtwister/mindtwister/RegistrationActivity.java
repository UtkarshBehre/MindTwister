package com.mindtwister.mindtwister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegistrationActivity extends AppCompatActivity {
    DBHandler db;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        db = new DBHandler(this);
        session = new SessionManager(this);
    }


    public void insert(View v) throws NullPointerException {

        //retrieve fields from text fields on registration form
        EditText editText1 = (EditText) findViewById(R.id.etName);
        String name = editText1.getText().toString();

        EditText editText2 = (EditText) findViewById(R.id.etNickname);
        String nickname = editText2.getText().toString();

        EditText editText3 = (EditText) findViewById(R.id.etPassword);
        String password = editText3.getText().toString();

        EditText editText4 = (EditText) findViewById(R.id.etEmail);
        String email = editText4.getText().toString();

        //getting age from form and handling empty string bug
        EditText editText5 = (EditText) findViewById(R.id.etAge);
        int age;
        if (editText5.getText().toString().length() == 0) {
            age = 0;
        } else {
            age = Integer.parseInt(editText5.getText().toString());
        }

        if (name.length() == 0 || nickname.length() == 0 || password.length() == 0 || email.length() == 0 || age == 0) {

            Toast.makeText(getApplicationContext(), "Please enter all fields!", Toast.LENGTH_LONG).show();

        } else if (!isValidName(name)) {

            editText1.setError("Please enter only letters!");
        } else if (!isValidNickname(nickname)) {

            editText2.setError("no special characters are allowed!");
        } else if (!isValidPassword(password)) {

            editText3.setError("Password must be greater than 6!");
        } else if (!isValidEmail(email)) {

            editText4.setError("enter in format(abc@gmail.com)!");
        } else if (!isValidAge(age)) {
            if (age > 110)
                editText5.setError("Age must be below 110");
            else
                editText5.setError("Children below 5 years are not allowed to play");
        } else {

            RegisterClass rc = new RegisterClass();
            rc.setUser_name(name);
            rc.setUser_nickname(nickname);
            rc.setUser_password(password);
            rc.setUser_email(email);
            rc.setUser_age(age);

            if (db.addRegisterClass(rc)) {
                Toast.makeText(getApplicationContext(), "user already exists", Toast.LENGTH_LONG).show();
            } else {
                session.createLoginSession(rc.getUser_name(), rc.getUser_nickname(), rc.getUser_email());
                //REDIRECTING TO OTHER ACTIVITY HERE=============================
                Intent i = new Intent(this, MainActivity.class);

                // Closing all the Activities
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                // Add new Flag to start new Activity
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                this.startActivity(i);

                finish();
            }
        }
    }

    //validating methods start here =======================

    //validate email
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    //validate name
    private boolean isValidName(String name) {
        String namepattern = "[A-Za-z]+";
        Pattern pattern = Pattern.compile(namepattern);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    private boolean isValidNickname(String name) {
        String namepattern = "[A-Za-z0-9]+";
        Pattern pattern = Pattern.compile(namepattern);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    // validate password
    private boolean isValidPassword(String pass) {
        if (pass.length() > 6)
            return true;
        return false;
    }

    private boolean isValidAge(int age) {
        if ((age >= 5) && (age <= 110))
            return true;
        else
            return false;
    }

}
