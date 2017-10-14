package com.dms.beinone.application.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.dms.beinone.application.R;

public class SurveyListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_list);

        TextView titleTV = (TextView) findViewById(R.id.tv_toolbar_title);
        titleTV.setText("설문조사");
    }
}
