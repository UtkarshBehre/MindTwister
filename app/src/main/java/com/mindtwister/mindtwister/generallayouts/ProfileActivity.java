package com.mindtwister.mindtwister.generallayouts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.mindtwister.mindtwister.R;
import com.mindtwister.mindtwister.managers.SessionManager;

import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity {
    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        session = new SessionManager(this);
        HashMap<String, String> userInfo = session.getUserDetails();
        TextView name = (TextView) findViewById(R.id.nameText);
        TextView nickname = (TextView) findViewById(R.id.usernameText);
        TextView email = (TextView) findViewById(R.id.emailText);
        TextView age = (TextView) findViewById(R.id.ageText);
        name.setText(userInfo.get(SessionManager.KEY_NAME));
        nickname.setText(userInfo.get(SessionManager.KEY_NICKNAME));
        email.setText(userInfo.get(SessionManager.KEY_EMAIL));
        age.setText(userInfo.get(SessionManager.KEY_AGE));
    }
}
