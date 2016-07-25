package com.mindtwister.mindtwister.generallayouts;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.mindtwister.mindtwister.R;

/**
 * Created by kanchan on 7/18/2016.
 */
public class FeedbackActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
    }

    public void feedbackSubmitOnClick(View view) {
        TextView feedbackText = (TextView) findViewById(R.id.feedbackText);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Mind Twister Game feedback");
        intent.putExtra(Intent.EXTRA_TEXT, feedbackText.getText().toString());
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}

