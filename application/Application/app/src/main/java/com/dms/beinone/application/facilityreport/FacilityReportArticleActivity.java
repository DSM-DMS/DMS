package com.dms.beinone.application.facilityreport;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.dms.beinone.application.R;

/**
 * Created by BeINone on 2017-01-23.
 */

public class FacilityReportArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facilityreport_article);

        // display back button on action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FacilityReport facilityReport =
                getIntent().getParcelableExtra(getString(R.string.EXTRA_FACILITYREPORT));
        bind(facilityReport);
    }

    /**
     * sets text of article
     * @param facilityReport FacilityReport object that contains information of article
     */
    private void bind(FacilityReport facilityReport) {
        TextView titleTV = (TextView) findViewById(R.id.tv_facilityreport_article_title);
        TextView writerTV = (TextView) findViewById(R.id.tv_facilityreport_article_writer);
        TextView dateTV = (TextView) findViewById(R.id.tv_facilityreport_article_writedate);
        TextView contentTV = (TextView) findViewById(R.id.tv_facilityreport_article_content);

        titleTV.setText(facilityReport.getTitle());
        writerTV.setText(facilityReport.getWriter());
        dateTV.setText(facilityReport.getWriteDate());
        contentTV.setText(facilityReport.getContent());
    }

}
