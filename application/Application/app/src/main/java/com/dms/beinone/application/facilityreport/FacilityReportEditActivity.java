package com.dms.beinone.application.facilityreport;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.dms.beinone.application.R;

/**
 * Created by BeINone on 2017-03-06.
 */

public class FacilityReportEditActivity extends AppCompatActivity {

    private FacilityReportWriteFragment mFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facilityreport_edit);

        // display back button on action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mFragment = new FacilityReportWriteFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_facilityreport_edit_container, mFragment)
                .commit();

        SharedPreferences prefs =
                getSharedPreferences(getString(R.string.PREFS_ACCOUNT), MODE_PRIVATE);
        final String writer = prefs.getString(getString(R.string.PREFS_ACCOUNT_ID), "");

        Button submitBtn = (Button) findViewById(R.id.btn_facilityreport_edit_submit);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = mFragment.getTitle();
                String room = mFragment.getRoom();
                String content = mFragment.getContent();

//                new EditFacilityReportTask()
//                        .execute(new FacilityReport(title, content, Integer.valueOf(room), writer));
            }
        });
    }

//    private class EditFacilityReportTask extends AsyncTask<FacilityReport, Void, Integer> {
//
//        @Override
//        protected Integer doInBackground(FacilityReport... params) {
//
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Integer integer) {
//            super.onPostExecute(integer);
//        }
//
//        private int editFacilityReport(FacilityReport facilityReport) {
//            facilityReport
//        }
//    }

}
